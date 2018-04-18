package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.buzheng.excel.Column;
import org.buzheng.excel.FieldFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.xuanfeiyang.annotations.Token;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.AgentConfigureDao;
import com.xuanfeiyang.erp.dao.AgentRebateDao;
import com.xuanfeiyang.erp.domain.AgentConfigure;
import com.xuanfeiyang.erp.domain.WithdrawApply;
import com.xuanfeiyang.erp.param.AgentRebateParam;
import com.xuanfeiyang.erp.param.WithdrawApplyParam;
import com.xuanfeiyang.erp.service.BankCardService;
import com.xuanfeiyang.erp.service.WithdrawApplyService;
import com.xuanfeiyang.erp.util.ExportUtil;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/withdraw")
@SessionAttributes(App.SESSION_USER_KEY)
public class AgentWithdrawalController extends  BaseController{
	private static final Logger logger = LoggerFactory.getLogger(AgentWithdrawalController.class);
	@Resource
	private WithdrawApplyService withdrawApplyService;
	@Resource
	private BankCardService  bankCardService;
	@Resource
	private AgentRebateDao agentRebateDao; 
	@Resource
	private AgentConfigureDao agentConfigureDao;
	
	private static Date startDate;
	
	private static Date endDate = new Date();
	
	private static DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
	
	static {
		startDate = DateUtils.addMonths(endDate, -1);
	}
	
	@RequestMapping("my-apply")
	@Token(save=true)
	public String applyRecord(Model model,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		AgentConfigure deposit = agentConfigureDao.getDeposit(curUser.getUserId());
		AgentRebateParam param = new AgentRebateParam();
		param.setUserId(curUser.getUserId().toString());
		param.setCreateTimeBegin(dft.format(startDate));
		param.setCreateTimeEnd(dft.format(endDate));
		param.setType("0");
		BigDecimal inCome = agentRebateDao.rebateTotal(param);
		BigDecimal applyAmount = withdrawApplyService.applyWithdrawAmount(curUser.getUserId());
		model.addAttribute("agentInfo", deposit);
		model.addAttribute("inCome", (inCome==null?0:inCome));
		model.addAttribute("applyAmount", (applyAmount==null?0:applyAmount));
		return "agent/withdraw-apply-list";
	}
	
	@RequestMapping("apply")
	@Token(remove=true)
	public String apply(WithdrawApply record,RedirectAttributes attr){
		try{
			withdrawApplyService.insert(record);
		}catch(Exception e){
			attr.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/withdraw/my-apply?status=0";
	}
	
	/**
	 * 
	 * @param status  0:未审核提现  1:已审核提现  2:已处理提现 
	 * @return
	 */
	@RequestMapping({"/","list"})
	public String list(@RequestParam(value="status")Integer status,Model model){
		model.addAttribute("cards", bankCardService.findAll());
		return "system/withdraw-apply-list";
	}
	
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<WithdrawApply> orderPageJson(@RequestBody DataTableRequest<WithdrawApplyParam> dtr){
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<WithdrawApply> page = this.withdrawApplyService.findPage(pageRequest,dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	@RequestMapping("audit")
	public String audit(@RequestParam("id")Integer id,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		withdrawApplyService.audit(id,curUser.getUserId());
		logger.info("用户%s成功审核提现申请%d",curUser.getUsername(),id);
		return "redirect:/withdraw/list?status=0";
	}
	
	@RequestMapping("reverseAudit")
	public String reverseAudit(@RequestParam("id")Integer id,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		withdrawApplyService.reverseAudit(id,curUser.getUserId());
		logger.info("用户%s成功反审核提现申请%d",curUser.getUsername(),id);
		return "redirect:/withdraw/list?status=1";
	}

	@RequestMapping("payForApply")
	public String payForApply(@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,Integer id,String bankName,String cardNo,RedirectAttributes attr){
		try{
			WithdrawApply apply = withdrawApplyService.get(id);
			apply.setPayedUser(curUser.getUserId());
			apply.setPayedTime(new Date());
			apply.setPayedBank(bankName);
			apply.setPayedCardno(cardNo);
			apply.setStatus((short)2);
			withdrawApplyService.payForApply(apply);
		}catch(RuntimeException e){
			attr.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/withdraw/list?status=1";
	}
	

	/**
	 * 导出缺货订单
	 * @return
	 */
	@RequestMapping("export")
	public void export(HttpServletResponse response,WithdrawApplyParam param,Integer []ids,Integer status,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser) throws Exception{
		Integer recordCount =  withdrawApplyService.recordCount(param, ids);
		if(recordCount > App.getConfigInt("export.excel.maxnumber")){
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("error");
			response.getWriter().close();
			return;
		}
		List<WithdrawApply> rows= withdrawApplyService.find(param, ids);
		status = (param.getStatus()!=null)?param.getStatus():status;
		String filename = status==0?"未审核.xlsx":status==1?"已审核.xlsx":"已付款.xlsx";
		List<Column> cs = getColumns(status,curUser.getUserId());
		ExportUtil.export(filename, response, rows,cs);
	}
	
	private List<Column> getColumns(final Integer status,final Integer userId){
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("代理商","applyUserName"));
		cs.add(new Column("保证金额度（￥）","bond"));
		cs.add(new Column("账号余额（￥）","deposit"));
		cs.add(new Column("可提取金额","deposit",
				new FieldFormatter<BigDecimal,WithdrawApply>(){
					public BigDecimal format(BigDecimal deposit, WithdrawApply rowValue){
						BigDecimal	aviableWithdraw = deposit.subtract(rowValue.getBond()).doubleValue()<100?BigDecimal.valueOf(0):deposit.subtract(rowValue.getBond());
						BigDecimal applyAmount = withdrawApplyService.applyWithdrawAmount(userId);
						if (applyAmount!=null){
							aviableWithdraw =  aviableWithdraw.subtract(applyAmount);
						}
						return aviableWithdraw;
					 }
			}
		));
		cs.add(new Column("申请提取金额","amount"));
		cs.add(new Column("收款人","payeeName"));
		cs.add(new Column("开户银行","payeeBank"));
		cs.add(new Column("卡号","payeeCardno"));
		cs.add(new Column("备注","note"));
		cs.add(new Column("申请时间","createdTime","yyyy-MM-dd"));
		if (status==1){
			cs.add(new Column("审核时间","auditTime","yyyy-MM-dd"));
			cs.add(new Column("审核人","auditUserName"));
		}else if (status==2){
			cs.add(new Column("付款时间","payedTime","yyyy-MM-dd"));
			cs.add(new Column("付款银行","payedBank"));
			cs.add(new Column("付款卡号","payedCardno"));
		}
		return cs;
	}
}
