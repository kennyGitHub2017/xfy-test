package com.xuanfeiyang.erp.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.AgentConfigure;
import com.xuanfeiyang.erp.domain.RolePower;
import com.xuanfeiyang.erp.domain.SysFunction;
import com.xuanfeiyang.erp.domain.SysModule;
import com.xuanfeiyang.erp.domain.SysPage;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserLogin;
import com.xuanfeiyang.erp.param.UserParams;
import com.xuanfeiyang.erp.service.AgentApplicationService;
import com.xuanfeiyang.erp.service.AgentConfigureService;
import com.xuanfeiyang.erp.service.DepartmentService;
import com.xuanfeiyang.erp.service.DuplicateDataExcepption;
import com.xuanfeiyang.erp.service.RoleService;
import com.xuanfeiyang.erp.service.SysModuleService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/user")
@SessionAttributes(App.SESSION_USER_KEY)
public class UserController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class); 
	
	@Resource
	private UserService userService;

	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private RoleService roleService;

	@Resource
	private SysModuleService sysModuleService;
	
	@Resource
	private AgentApplicationService agentService;
	
	@Resource
	private AgentConfigureService agentConfigureService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(Model model) {
		return "system/user";
	}
	
	/**
	 * 分页查询 json
	 * 
	 * @param dtr
	 * @return
	 */
	@RequestMapping(value="page.json")
	@ResponseBody
	public DataTableResponse<UserInfo> pageJson(@RequestBody DataTableRequest<UserParams> dtr) {

		String keywords = dtr.getSearch() == null ? null : dtr.getSearch().getValue();
		
		UserParams params = dtr.getParams();
		params.setKeywords(keywords);
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<UserInfo> page = this.userService.findPage(pageRequest, params);

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	@RequestMapping(value = "/login-info", method = RequestMethod.POST)
	public String saveLoginInfo(RedirectAttributes redirectAttr, 
			@RequestParam(value="flag", required=false) String flag,
			UserLogin loginInfo) throws Exception {
		
		try {
			this.userService.saveOrUpdateLoginInfo(loginInfo);
			redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (DuplicateDataExcepption e) {
			redirectAttr.addFlashAttribute("errorMessage", "user.username.exists");
		}
		
		if ("seller".equals(flag)) {
			return "redirect:/seller";
		}
		
		return "redirect:/user";
	}
	
	// 设置/修改登陆信息页面
	@RequestMapping("/login-form")
	public String loginForm(Model model,
			@RequestParam(value="id", required=false) Integer userId) {
		
		UserLogin user = null;
		if (userId != null) {
			user = this.userService.loadUserLogin(userId);
			model.addAttribute("updateFlag", "1");
			
		} 
		
		model.addAttribute("user", user);
		
		return "system/login-form";
	}
	
	/**
	 * 加载单个数据的form页面
	 */
	@RequestMapping("/form")
	public String form(Model model, 
			@RequestParam(value="id", required=false) Integer userId) {
		UserInfo user = null;
		if (userId != null) {
			user = this.userService.loadUserInfo(userId);
			// 修改标识
			model.addAttribute("updateFlag", "1");
		} else {
			user = new UserInfo();
		}
		
		model.addAttribute("user", user);
		model.addAttribute("departments", this.departmentService.find());
		model.addAttribute("roles", this.roleService.find());
		
		return "system/user-form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttr,
			UserInfo user, 
			@RequestParam(value="roleId", required=false) List<Integer> roleIds) {
		
		try {
			user.setType(1);
			user.setStatus(1);
			this.userService.saveUserInfo(user, null);
			this.userService.saveUserRoles(user.getUserId(), roleIds);
			redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (DuplicateDataExcepption e) {
			redirectAttr.addFlashAttribute("errorMessage", e.getMessage() == null ? "g.tips.error" : e.getMessage());
		}
		
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttr, UserInfo user, 
			@RequestParam(value="roleId", required=false) List<Integer> roleIds) {
		
		try {
			this.userService.updateUserInfo(user);
			this.userService.saveUserRoles(user.getUserId(), roleIds);
			redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (DuplicateDataExcepption e) {
			redirectAttr.addFlashAttribute("errorMessage", e.getMessage() == null ? "g.tips.error" : e.getMessage());
		}
		
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/power", method = RequestMethod.GET)
	public String powerPage(Model model,
			@RequestParam(value="id") Integer userId) {
		
		List<SysModule> modules = this.sysModuleService.findAll();
		model.addAttribute("modules", modules);
		
		model.addAttribute("powersMap", this.userService.findUserPowers(userId));
		
		return "system/user-power";
	}
	
	@RequestMapping(value = "/power-setting", method = RequestMethod.GET)
	public String powerSettingPage(Model model,
			@RequestParam(value="id") Integer userId) {
		
		List<SysModule> modules = this.sysModuleService.findAll();
		List<RolePower> allRolePowers = this.userService.findAllRolesPowers(userId);
		List<SysModule> allRolesModules = this.getAllRoleModules(modules, allRolePowers);
		model.addAttribute("modules", allRolesModules);
		
		Map<String, Set<String>> userPowers = this.userService.findUserPowers(userId);
		model.addAttribute("powersMap", userPowers);
		
		return "system/user-power-setting";
	}
	
	@RequestMapping(value = "/power-setting", method = RequestMethod.POST)
	public String powerSetting(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="id") Integer userId,
			@RequestParam(value="modulePower", required=false) List<String> modulePowers,
			@RequestParam(value="pagePower", required=false) List<String> pagePowers,
			@RequestParam(value="functionPower", required=false) List<String> functionPowers) {
		
		this.userService.savePowers(userId, modulePowers, pagePowers, functionPowers);
		
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		redirectAttr.addAttribute("id", userId);
		return "redirect:/user/power-setting";
	}
	
	private List<SysModule> getAllRoleModules(List<SysModule> allModules,
			List<RolePower> allRolesPowers) {
		
		List<SysModule> allRolesModules = new ArrayList<>();
		
		for (SysModule module : allModules) {
			if (! checkPower("module", module.getCode(), allRolesPowers)) {
				continue;
			}
			
			List<SysPage> modulePages = new ArrayList<>();
			for (SysPage page : module.getPages()) {
				if (! checkPower("page", page.getCode(), allRolesPowers)) {
					continue;
				}
				
				List<SysFunction> pageFunctions = new ArrayList<>();
				for (SysFunction function : page.getFunctions()) {
					if (! checkPower("function", function.getCode(), allRolesPowers)) {
						continue;
					}
					
					SysFunction pageFunction = new SysFunction();
					pageFunction.setCode(function.getCode());
					pageFunction.setName(function.getName());
					pageFunction.setPageCode(function.getPageCode());
					
					pageFunctions.add(pageFunction);
				}
				
				SysPage modulePage = new SysPage();
				modulePage.setCode(page.getCode());
				modulePage.setName(page.getName());
				modulePage.setModuleCode(page.getModuleCode());
				modulePage.setSort(page.getSort());
				modulePage.setUrl(page.getUrl());
				modulePage.setSysCode(page.getSysCode());
				modulePage.setFunctions(pageFunctions);
				modulePages.add(modulePage);
			}
			
			if (modulePages.isEmpty()) {
				continue;
			}
			
			SysModule roleModule = new SysModule();
			roleModule.setCode(module.getCode());
			roleModule.setName(module.getName());
			roleModule.setUrl(module.getUrl());
			roleModule.setPages(modulePages);
			
			allRolesModules.add(roleModule);
		}
		
		return allRolesModules;
	}

	private boolean checkPower(String powerType, String powerCode, List<RolePower> allRolePowers) {
		for (RolePower rolePower : allRolePowers) {
			if (powerType.equals(rolePower.getPowerType()) 
					&& powerCode.equals(rolePower.getPowerCode())) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 检查 工号、手机、身份证号、email 是否存在，每次请求只能检查一个
	 * @param code
	 * @param mobile
	 * @param idCardNo
	 * @param id
	 * @return true 可用，false 不可用
	 */
	@RequestMapping(value="check.json")
	@ResponseBody
	public boolean check(
			@RequestParam(value="code", required=false) String code,
			@RequestParam(value="mobile", required=false) String mobile,
			@RequestParam(value="idCardNo", required=false) String idCardNo,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="id",required=false) Integer id){
		
		if (code == null && mobile == null && idCardNo == null && 
				email == null ) {
			return true;
		}
		
		UserInfo user = null;
		if (code != null) {
			user = this.userService.loadByCode(code);
		} else if (mobile != null) {
			user = this.userService.loadByMobile(mobile);
		} else if (idCardNo != null) {
			user = this.userService.loadByIdCardNo(idCardNo);
		} else if (email != null) {
			user = this.userService.loadByEmail(email);
		} 
		
		if (user == null) {
			return true;
		}
		
		if (user.getUserId().equals(id)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 检查用户名可用
	 * @param username
	 * @return true 可用，false 不可用
	 */
	@RequestMapping(value="check-username.json")
	@ResponseBody
	public boolean checkUsername(
			@RequestParam(value="username", required=false) String username) {
		
		Integer userId = this.userService.getUserIdByUsername(username);
		return userId == null;
	}
	
	
	/**
	 * 检查手机号是否注册
	 * @param mobile
	 * @return true:手机号已注册   false:手机号未注册
	 */
	@RequestMapping(value="check-mobile.json")
	@ResponseBody
	public boolean checkMobile(
			@RequestParam(value="mobile",required=false) String mobile){
		if(mobile == null){
			return false;
		}
		UserInfo user = null;
		if(mobile != null){
			user = this.userService.loadByMobile(mobile);
		}
		if(user == null){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 检查手机号是否代理商账号
	 * @param mobile
	 * @return true:手机号是代理商账号   false:手机号不是代理商账号
	 */
	@RequestMapping(value="check-agent-mobile.json")
	@ResponseBody
	public boolean checkAgentMobile(
			@RequestParam(value="agentMobile",required=false) String mobile){
		if(mobile == null){
			return false;
		}
		UserInfo user = this.userService.loadByMobile(mobile);
		if(user != null && user.getType() == 3){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 当前卖家下所有登录用户界面
	 * 
	 * @param model
	 * @param sessionUser
	 * @return
	 */
	@RequestMapping(value="login-users", method=RequestMethod.GET)
	public String loginUsers(Model model, 
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		UserInfo userInfo = this.userService.loadUserInfo(sessionUser.getUserId());
		model.addAttribute("masterFlag", userInfo.getIsMaster());
		return "system/login-users";
	}
	
	// 简单新增编辑用户信息页面
	@RequestMapping(value="/login-user-form", method=RequestMethod.GET)
	public String loginUserForm(Model model,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		model.addAttribute("sellerId", sessionUser.getSellerId());

		return "system/login-user-form";
	}
	
	// 保存 简单新增编辑用户信息
	@RequestMapping(value="/login-user-form", method=RequestMethod.POST)
	public String saveLoginUser(RedirectAttributes redirectAttr,
			@RequestParam(value = "sellerId", required = true) Integer sellerId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		try {
			this.userService.saveUserInfoForSeller(sellerId, name, username, password);
			redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (Exception e) {
			redirectAttr.addFlashAttribute("errorMessage", e.getMessage() == null ? "g.tips.error" : e.getMessage());
		}
		
		return "redirect:/user/login-users";
	}
	
	@RequestMapping(value="/delete")
	public String delete(RedirectAttributes redirect,
			@RequestHeader("Referer") String referer,
			@RequestParam("id") Integer userId) {
		
		this.userService.delete(userId);
		redirect.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:" + referer;
	}
	
	@RequestMapping("lock")
	public String lock(RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/user") String referer,
			@RequestParam("id") Integer id) {
		this.userService.lock(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		return "redirect:" + referer;
	}

	@RequestMapping("unlock")
	public String unlock(RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/user") String referer,
			@RequestParam("id") Integer id) {
		this.userService.unlock(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		return "redirect:" + referer;
	}
	
	@ResponseBody
	@RequestMapping("getUserByDeparment.json")
	public List<String> getUserByDeparment(@RequestParam(value="depId",required=true) Integer depId,@RequestParam(value="prefixName",required=true) String prefixName){
		List<String> ret = new ArrayList<>();
		List<UserInfo> dataList = this.userService.findByDepartment(depId);
		if (StringUtils.isEmpty(prefixName.trim())){
			for(UserInfo data:dataList){
				ret.add(data.getName());
			}
		}else{
			//只返回匹配包含prefixName的
			for(UserInfo data:dataList){
				if (data.getName().contains(prefixName.trim())){
					ret.add(data.getName());
				}
			}
		}
		return ret;
	}
	
	/**
	 * 系统管理下代理商申请审核
	 */
	@RequestMapping("/agent-application")
	public String agentApp(Model model, 
			@RequestParam(value="status", defaultValue="0") Integer status) {
		model.addAttribute("status", status);
		return "system/agent-application";
	}
	
	/**
	 * 代理商申请审核分页查询 json
	 * 
	 * @param dtr
	 * @return
	 */
	@RequestMapping(value="agent-page-json")
	@ResponseBody
	public DataTableResponse<UserInfo> agentPageJson(@RequestBody DataTableRequest<UserParams> dtr) {

		UserParams params = dtr.getParams();
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<UserInfo> page = this.agentService.getPage(pageRequest, params);
		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/**
	 * 代理商申请审核查看按钮
	 * @param model
	 * @param redirectAttr
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/agent-view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam(value="userId", required=true) Integer userId) {
		UserInfo userInfo = this.agentService.load(userId);
		model.addAttribute("userInfo", userInfo);
		return "system/agent-view-form";
	}
	
	/**
	 * 代理商申请审核加载审核页面
	 * @param model
	 * @param redirectAttr
	 * @param sellerId
	 * @return
	 */
	@RequestMapping(value = "/agent-approve-page", method = RequestMethod.GET)
	public String approvePage(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="userId", required=true) Integer userId) {
		UserInfo userInfo = this.agentService.load(userId);
		AgentConfigure agentConfigure = this.agentConfigureService.getByUserId(userId);
		if(agentConfigure != null) {
			model.addAttribute("agentConfigure", agentConfigure);
		}
		model.addAttribute("userInfo", userInfo);
		return "system/agent-approve-form";
	} 
	
	/**
	 * 未审核到审核通过
	 * @param redirectAttr
	 * @param userId
	 * @param bond
	 * @param serviceRebate
	 * @param costRebate
	 * @return 
	 */
	@RequestMapping(value="/agent-bond", method=RequestMethod.POST)
	public String saveAgentBond(RedirectAttributes redirectAttr,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "bond", required = true) Integer bond,
			@RequestParam(value = "serviceRebate", required = true) BigDecimal serviceRebate,
			@RequestParam(value = "costRebate", required = true) BigDecimal costRebate) {

		try {
			AgentConfigure agent = new AgentConfigure();
			agent.setUserId(userId);
			agent.setBond(bond);
			agent.setCostRebateRate(costRebate);
			agent.setServiceRebateRate(serviceRebate);
			logger.info("UserController:agent:{}",agent);
			this.agentConfigureService.add(agent);    //设置保证金，服务费返点和产品成本返点
			boolean result = this.agentService.approve(userId);
			if(result){
				redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
			}
		} catch (Exception e) {
			redirectAttr.addFlashAttribute("errorMessage", e.getMessage() == null ? "g.tips.error" : e.getMessage());
		}
		
		return "redirect:/user/agent-application";
	}
	
	/**
	 * 审核未通过重新审核
	 * @param redirectAttr
	 * @param userId
	 * @return 
	 */
	@RequestMapping(value="/reExamine", method=RequestMethod.POST)
	public String reExamine(RedirectAttributes redirectAttr,
			@RequestParam(value = "userId", required = true) Integer userId) {

		try {
			boolean result = this.agentService.approve(userId);
			if(result){
				redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
			}
		} catch (Exception e) {
			redirectAttr.addFlashAttribute("errorMessage", e.getMessage() == null ? "g.tips.error" : e.getMessage());
		}
		
		return "redirect:/user/agent-application";
	}
	
	/**
	 * 反审核
	 * @param redirectAttr
	 * @param userId
	 * @return  
	 */
	@RequestMapping(value="/unapprove", method=RequestMethod.POST)
	public String unapprove(RedirectAttributes redirectAttr,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value="note", required=false) String note) {
		try {
			boolean result = this.agentService.unapprove(userId,note);
			if(result){
				redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
			}
		} catch (Exception e) {
			redirectAttr.addFlashAttribute("errorMessage", e.getMessage() == null ? "g.tips.error" : e.getMessage());
		}
		
		return "redirect:/user/agent-application";
	}
	
	/**
	 * 逻辑删除
	 * @param model
	 * @param redirectAttr
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/agent-delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestParam(value="userId", required=true) Integer userId) {
		
		this.agentService.delete(userId);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:/user/agent-application";
	}
	
	/**
	 * 代理商锁定
	 * @param redirectAttr
	 * @param userId
	 * @return
	 */
	@RequestMapping("/agent-lock")
	public String agentLock(RedirectAttributes redirectAttr,
			@RequestParam("userId") Integer userId) {
		this.agentService.lock(userId);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		return "redirect:/user/agent-application";
	}
	
	/**
	 * 代理商解锁
	 * @param redirectAttr
	 * @param userId
	 * @return  
	 */
	@RequestMapping("/agent-unlock")
	public String agentUnlock(RedirectAttributes redirectAttr,
			@RequestParam("userId") Integer userId) {
		this.agentService.unlock(userId);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		return "redirect:/user/agent-application";
	}
	
	/**
	 * 检查手机号是否注册
	 * @param userId
	 * @return true:保证金已经设置   false:保证金未设置
	 */
	@RequestMapping(value="check-bond.json")
	@ResponseBody
	public boolean checkBond(
			@RequestParam(value="userId",required=false) Integer userId){
		if(userId == null){
			return false;
		}
		AgentConfigure agent = null;
		
		if(userId != null){
			agent = this.agentConfigureService.getByUserId(userId);
		}
		if(agent == null){
			return false;
		}
		
		return true;
	}
}
