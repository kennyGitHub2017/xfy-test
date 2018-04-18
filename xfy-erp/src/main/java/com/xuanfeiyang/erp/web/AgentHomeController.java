package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.AgentConfigureDao;
import com.xuanfeiyang.erp.dao.AgentRebateDao;
import com.xuanfeiyang.erp.domain.AgentConfigure;
import com.xuanfeiyang.erp.domain.AgentRebate;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.AgentRebateParam;
import com.xuanfeiyang.erp.param.OrderParam;
import com.xuanfeiyang.erp.param.SellerDepositParams;
import com.xuanfeiyang.erp.service.OrderService;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.service.SellerDepositService;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.service.WithdrawApplyService;
import com.xuanfeiyang.erp.web.util.SessionUser;

@Controller
@SessionAttributes(App.SESSION_USER_KEY)
public class AgentHomeController extends BaseController{
	
	@Resource
	private SellerService sellerService;
	
	@Resource
	private PlatformAccountService platformAccountService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private AgentRebateDao agentRebateDao; 
	
	@Resource
	private AgentConfigureDao agentConfigureDao;
	
	@Resource
	private WithdrawApplyService withdrawApplyService;
	
	@Resource
	private SellerDepositService sellerDepositService;
	
	@Resource 
	private UserService userService;
	
	private static Date startDate;
	
	private static Date endDate = new Date();
	
	
	private static DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
	
	static {
		startDate = DateUtils.addMonths(endDate, -1);
	}
	
	private Logger logger = LoggerFactory.getLogger(AgentHomeController.class);
	
	@RequestMapping("/agent-index")
	public String agentHome(Model model,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser){
		
		OrderParam orderParam1 = new OrderParam();
		OrderParam orderParam2 = new OrderParam();
		OrderParam orderParam3 = new OrderParam();
		OrderParam orderParam4 = new OrderParam();
		OrderParam orderParam5 = new OrderParam();
		OrderParam orderParam6 = new OrderParam();
		
		Integer notAudit = 0;     //未审核订单数
		Integer suspend = 0;     //暂停订单数
		Integer pending = 0;     //待发货订单数
		Integer shipped = 0;     //已发货订单数
		Integer cancel = 0;      //取消订单数
		Integer total = 0;       //总订单数
		int accountNumber = 0;     //绑定账号数量
		BigDecimal totalAmount = new BigDecimal(0);    //流水总金额
		List<Integer> list = new ArrayList<Integer>();
		Integer userId = sessionUser.getUserId();
		List<Seller> sellers = this.sellerService.findByAgent(userId);
		
		UserInfo userInfo = this.userService.loadUserInfo(userId);
		model.addAttribute("userInfo", userInfo);
		
		if(sellers != null) {
			for (Seller seller : sellers) {
				Integer sellerId = seller.getId();
				List<PlatformAccount> platformAccountList = this.platformAccountService.loadBySellerId(sellerId);
				if(platformAccountList != null) {
					accountNumber = platformAccountList.size();
					for (PlatformAccount platformAccount : platformAccountList) {
						Integer id = platformAccount.getId();
						list.add(id);
					}
					logger.info("AgentHomeController:list{}",list);
					if(list != null && list.size() > 0) {
						orderParam1.setAccountIdsList(list);
						orderParam1.setOrderStatus(1);
						Integer notAudit1 = this.orderService.orderNumbers(orderParam1);
						if(notAudit1 != null && notAudit1 > 0){
							notAudit = notAudit + notAudit1;
							logger.info("AgentHomeController:notAudit:{}",notAudit);
						}
						
						orderParam2.setAccountIdsList(list);
						orderParam2.setSuspend(1);
						Integer suspend1 = this.orderService.orderNumbers(orderParam2);
						if(suspend1 != null && suspend1 > 0){
							suspend = suspend + suspend1;
							logger.info("AgentHomeController:suspend:{}",suspend);
						}
						
						orderParam3.setAccountIdsList(list);
						orderParam3.setOrderStatus(5);
						Integer pending1 = this.orderService.orderNumbers(orderParam3);
						if(pending1 != null && pending1 > 0){
							pending = pending + pending1;
							logger.info("AgentHomeController:pending:{}",pending);
						}
						
						orderParam4.setAccountIdsList(list);
						orderParam4.setOrderStatus(7);
						Integer shipped1 = this.orderService.orderNumbers(orderParam4);
						if(shipped1 != null && shipped1 > 0){
							shipped = shipped + shipped1;
							logger.info("AgentHomeController:shipped:{}",shipped);
						}
						
						orderParam5.setAccountIdsList(list);
						orderParam5.setOrderStatus(6);
						Integer cancel1 = this.orderService.orderNumbers(orderParam5);
						if(cancel1 != null && cancel1 > 0){
							cancel = cancel + cancel1;
							logger.info("AgentHomeController:cancel:{}",cancel);
						}
						
						orderParam6.setAccountIdsList(list);
						Integer total1 = this.orderService.orderNumbers(orderParam6);
						if(total1 != null && total1 > 0){
							total = total + total1;
							logger.info("AgentHomeController:total:{}",total);
						}
						
					}
					
				}
				SellerDepositParams params = new SellerDepositParams();
				params.setSellerId(sellerId);
				params.setType(1);
				List<SellerDepositLog> depositLogList = this.sellerDepositService.findDepositLog(params);
				for (SellerDepositLog sellerDepositLog : depositLogList) {
					BigDecimal amount = sellerDepositLog.getAmount();
					if(amount != null) {
						totalAmount = totalAmount.add(amount);
					}
				}
				logger.info("AgentHomeController:totalAmount:{}",totalAmount);
				
				
			}
		}
		model.addAttribute("sellers", sellers);
		model.addAttribute("notAudit", notAudit);
		model.addAttribute("suspend", suspend);
		model.addAttribute("pending", pending);
		model.addAttribute("shipped", shipped);
		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("accountNumber",accountNumber);
		if(total-cancel >= 0) {
			model.addAttribute("total", total-cancel);   //订单总量
		} else {
			model.addAttribute("total", 0);
		}
		
		
		BigDecimal totalInCome = new BigDecimal(0);
		AgentConfigure agentInfo = agentConfigureDao.getDeposit(userId);
		List<AgentRebate> agentRebateList = this.agentRebateDao.findByAgentId(userId);
		if(agentRebateList != null) {
			AgentRebateParam param1 = new AgentRebateParam();
			for (AgentRebate agentRebate : agentRebateList) {
				Date createdDate = agentRebate.getCreatedTime();
				param1.setCreateTimeBegin(dft.format(createdDate));
				param1.setCreateTimeEnd(dft.format(endDate));
				param1.setUserId(userId.toString());
				param1.setType("0");
				BigDecimal inCome1 = agentRebateDao.rebateTotal(param1);
				if(inCome1 != null){
					totalInCome = totalInCome.add(inCome1);
					logger.info("AgentHomeController:totalInCome1:{}",totalInCome);
				}
			}
		}
		
		AgentRebateParam param = new AgentRebateParam();
		param.setUserId(userId.toString());
		param.setCreateTimeEnd(dft.format(endDate));
		param.setCreateTimeBegin(dft.format(startDate));
		param.setType("0");
		BigDecimal inCome = agentRebateDao.rebateTotal(param);
		BigDecimal applyAmount = withdrawApplyService.applyWithdrawAmount(userId);
		model.addAttribute("agentInfo", agentInfo);
		model.addAttribute("inCome", (inCome==null?0:inCome));       //近一个月收入
		logger.info("AgentHomeController:inCome:{}",inCome);
		model.addAttribute("applyAmount", (applyAmount==null?0:applyAmount));   
		model.addAttribute("totalInCome", (totalInCome==null?0:totalInCome));    //累积总额
		logger.info("AgentHomeController:totalInCome2:{}",totalInCome);
		
		List<Seller> sellerList = this.sellerService.findByAgent(userId);
		if(sellerList != null) {
			Integer sellerNumber = sellerList.size();      //卖家数量
			if(sellerNumber > 0) {
				model.addAttribute("sellerNumber", sellerNumber);
			} else {
				model.addAttribute("sellerNumber", 0);
			}
		}
		
		List<AgentRebate> logList = this.agentRebateDao.findAgentFundLog(userId, 5);
		if(logList != null && logList.size() > 0) {
			model.addAttribute("logList", logList);     //资金消息日志
		} else {
			model.addAttribute("logList", null);
		}
		
		return "agent-index";
	}
}
