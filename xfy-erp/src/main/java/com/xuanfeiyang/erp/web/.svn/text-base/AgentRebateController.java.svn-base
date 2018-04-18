package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.AgentRebateDao;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.dao.UserInfoDao;
import com.xuanfeiyang.erp.domain.AgentRebate;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.AgentRebateParam;
import com.xuanfeiyang.erp.param.PlatformAccountParams;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;
/**
 * 代理商资金流水
 */
@Controller
@RequestMapping("/agent-rebate")
@SessionAttributes(App.SESSION_USER_KEY)
public class AgentRebateController extends BaseController{
	@Resource
	private AgentRebateDao agentRebateDao;
	@Resource
	private SellerDao sellerDao;
	@Resource
	private PlatformAccountService platformAccountService;
	@Resource
	private UserInfoDao userInfoDao;
	
	private AgentRebateParam param = new AgentRebateParam();
	@RequestMapping({"","/"})
	public String list(Model model,@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		PlatformAccountParams params2 = new PlatformAccountParams();
		params2.setAgentUserId(curUser.getUserId());
		model.addAttribute("accountIdList", this.platformAccountService.findNameIdPairs(params2));
		model.addAttribute("sellerList",this.sellerDao.findByAgent(curUser.getUserId()));
		model.addAttribute("search",param);
		return "/agent/agent-rebate";
	}
	
	/***
	 * 代理商自己流水列表
	 * @param dtr
	 * @return
	 */
	@RequestMapping("pageJson")
	@ResponseBody
	public DataTableResponse<AgentRebate> orderPageJson(@RequestBody DataTableRequest<AgentRebateParam> dtr){
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<AgentRebate> page = this.agentRebateDao.findPage(pageRequest,dtr.getParams());
		return WebHelper.assembleDataTableResponse(dtr, page);
	}

	/**
	 * 代理商资金流水页面
	 * system
	 * @return
	 */
	@RequestMapping("rebate-all")
	public String rebateAllPage(Model model){
		List<UserInfo> agentList = this.userInfoDao.findAgentList();//代理商
		model.addAttribute("agentList", agentList);
		List<Seller> sellers = sellerDao.findAll(2);//卖家
		model.addAttribute("sellers", sellers);
		return "agent/agent-rebate-all";
	}
	
	/**
	 * 统计代理商收益订单
	 * @param userId
	 * @return
	 */
	@RequestMapping("countOrder")
	@ResponseBody
	public Integer countByUserId(Integer userId){
		return this.agentRebateDao.countByUserId(userId);
	}
	
	/**
	 * 最近一个月的收益
	 * @param userId
	 * @return
	 */
	@RequestMapping("sumAmount")
	@ResponseBody
	public Integer sumAmountByUser(Integer userId){
		return this.agentRebateDao.sumAmountByUserId(userId);
	}
	
	@RequestMapping("getSellers")
	@ResponseBody
	public Integer getSellers(Integer userId){
		return this.sellerDao.getCountByUserId(userId);
	}
	
	/**
	 * 代理商订单统计
	 * @return
	 */
	@RequestMapping("countSellerOrder")
	public String getSellerOrdersByUserId(Model model,Integer userId){
		List<AgentRebate> list = this.agentRebateDao.countOrderByAgentId(userId);
		model.addAttribute("countOrder", list);
		return "agent/count-orders";
	}
	
	
}
