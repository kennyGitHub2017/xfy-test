package com.xuanfeiyang.erp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.UserInfoDao;
import com.xuanfeiyang.erp.domain.AgentConfigure;
import com.xuanfeiyang.erp.domain.AgentConfigureLog;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.service.AgentConfigureService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

/***
 * 代理商保证金相关设置
 * @author kenny
 *
 */
@RequestMapping("/agent-Configure")
@Controller
@SessionAttributes(App.SESSION_USER_KEY)
public class AgentConfigureController {
	
	@Resource
	private AgentConfigureService agentConfigureService;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	/**
	 * 代理商保证金修改页面
	 * @return
	 */
	@RequestMapping("/deposit")
	public String depositPage(Model model){
		List<UserInfo> agentList = this.userInfoDao.findAgentList();//代理商
		model.addAttribute("agentList", agentList);
		
		return "agent/agent-deposit-all";
	}
	
	
	/**
	 * 代理商保证金管理
	 * @param 
	 * @return
	 */
	@RequestMapping("agent-configure.json")
	@ResponseBody
	public DataTableResponse<AgentConfigure> agentConfigure(
			@RequestBody DataTableRequest<AgentConfigure> dtr) {
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		
		Page<AgentConfigure> page = this.agentConfigureService.findPage(pageRequest, dtr.getParams());
		
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/***
	 * 代理商返点修改面板
	 * @return
	 */
	@RequestMapping("rebateEdit-page")
	public String rebateEdit(@RequestParam("userId")Integer userId,Model model){
		model.addAttribute("userId", userId);
		return "agent/rebate-edit";
	}
	
	
	@RequestMapping("update-rebate")
	public String rebateRate(RedirectAttributes redirectAttr,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser,
			@RequestParam("serviceRate") String serviceRate,
			@RequestParam("costRate") String costRate,
			@RequestParam("userId") Integer userId,
			@RequestParam( value="bond", required=false) String bond) {
			
		try {
				this.agentConfigureService.updateRebateRate(costRate, serviceRate, bond, userId,curUser.getUserId());
				redirectAttr.addFlashAttribute("successMessage", "修改成功");
			} catch (Exception e) {
				
				redirectAttr.addFlashAttribute("errorMessage", "修改失败");
			}
		return "redirect:/agent-Configure/deposit";
	}
	
	/***
	 * 代理商返点修改面板
	 * @return
	 */
	@RequestMapping("bondEdit-page")
	public String bondEdit(@RequestParam("userId")Integer userId,Model model){
		model.addAttribute("userId",userId);
		return "agent/agent-bond-edit";
	}
	
	/***
	 * 修改保证金额度
	 * @param bond
	 * @return
	 */
	@RequestMapping("bond-edit")
	public String bondEdit(@RequestParam("bond")Integer bond,@RequestParam("userId")Integer userId,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		this.agentConfigureService.updateAgentBond(bond, userId,curUser.getUserId());
		return "redirect:/agent-Configure/deposit";
	}
	
	/***
	 * 保证金修改日志列表
	 * @param model
	 * @return
	 */
	@RequestMapping("configureLog")
	public String agentConfigureLog(Model model,Integer userId){
		
		List<AgentConfigureLog> list = this.agentConfigureService.getLogByUserId(userId);
		model.addAttribute("logInfo", list);
		return "agent/agent-configure-log";
	}
	

	

}
