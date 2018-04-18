package com.xuanfeiyang.erp.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserLogin;
import com.xuanfeiyang.erp.service.AuthCodeService;
import com.xuanfeiyang.erp.service.RegisterService;
import com.xuanfeiyang.erp.service.SmsSendService;
import com.xuanfeiyang.erp.service.UserService;

/**
 * 注册相关操作
 * @author Adam
 *
 */
@Controller
public class RegisterController {
	
	private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Resource
	private RegisterService registerService;
	
	@Resource
	private AuthCodeService authCodeService;
	
	@Resource
	private SmsSendService smsSendService;
	
	@Resource
	private UserService userService;

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerPage(Model model) {
		model.addAttribute("user", new UserInfo());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(
			RedirectAttributes redirect, Model model, UserInfo userInfo,
			@RequestParam("password") String password,
			@RequestParam("authCode") String authCode,
			@RequestParam("infoAcquisitionChannel") String infoAcquisitionChannel) {
		
		if (! this.authCodeService.check(userInfo.getMobile(), authCode)) {
			model.addAttribute("user", userInfo);
			model.addAttribute("message", "短信验证码输入不正确");
			return "register";
		}
		
		try {
			userInfo.setInfoAcquisitionChannel(infoAcquisitionChannel);
			this.registerService.registerSeller(userInfo, password);
		} catch (Exception e) {
			logger.error("注册用户异常", e);
			model.addAttribute("user", userInfo);
			model.addAttribute("message", "system.error");
			return "register";
		}
		
		// 将验证码失效
		this.authCodeService.invalideCode(userInfo.getMobile());

		redirect.addFlashAttribute("message", "注册成功");
		return "redirect:/login";
	}
	
	/**
	 * 获取验证码
	 * @return
	 */
	@RequestMapping("get-auth-code.json")
	@ResponseBody
	public boolean getAuthCode(@RequestParam("mobile") String mobile) {

		if (mobile == null) {
			return false;
		}
		
		String code = this.authCodeService.createCode(mobile);
		String content = String.format(App.getConfig("sms.content.auth.code"), code);
		return this.smsSendService.send(mobile, content);
	}
	
	/**
	 * 检查验证码
	 * @return
	 */
	@RequestMapping("check-auth-code.json")
	@ResponseBody
	public boolean checkAuthCode(@RequestParam("mobile") String mobile,
			@RequestParam("authCode") String authCode) {
		return this.authCodeService.check(mobile, authCode);
	}
	
	/***
	 * 注册弹绑定账号页面
	 * @return
	 */
	@RequestMapping("process1-page")
	public String registerProcess1(){
		return "system/register-process1";
	}
	
	/***
	 * 注册弹绑定账号页面
	 * @return
	 */
	@RequestMapping("process2-page")
	public String registerProcess2(){
		return "system/register-process2";
	}
	
	@RequestMapping(value="/register-agent", method=RequestMethod.GET)
	public String registerAgentPage(Model model) {
		model.addAttribute("user", new UserInfo());
		return "register-agent";
	}
	
	@RequestMapping(value="/register-agent", method=RequestMethod.POST)
	public String registerAgent(
			RedirectAttributes redirect, Model model, UserInfo userInfo,
			@RequestParam("password") String password,
			@RequestParam("authCode") String authCode) {
		
		if (! this.authCodeService.check(userInfo.getMobile(), authCode)) {
			model.addAttribute("user", userInfo);
			model.addAttribute("message", "短信验证码输入不正确");
			return "register-agent";
		}
		
		try {
			this.registerService.registerAgent(userInfo, password);
		} catch (Exception e) {
			logger.error("注册用户异常", e);
			model.addAttribute("user", userInfo);
			model.addAttribute("message", "system.error");
			return "register-agent";
		}
		
		// 将验证码失效
		this.authCodeService.invalideCode(userInfo.getMobile());

		redirect.addFlashAttribute("message", "注册成功");
		return "redirect:/login";
	}
	
	@RequestMapping(value="/upgrade-agent", method=RequestMethod.POST)
	public String registerUpgrade(
			RedirectAttributes redirect, Model model,
			@RequestParam("u") String username,
			@RequestParam("p") String password,
			@RequestParam("c") String authCode) {
		
		UserLogin userLogin = this.userService.loadUserLoginByUsernameAndPassword(username, password);
		if (userLogin == null) {
			redirect.addFlashAttribute("upgradeMessage", "用户名或密码错误");
			return "redirect:/register-agent";
		}
		
//		if (! this.authCodeService.check(username, authCode)) {
//			redirect.addFlashAttribute("upgradeMessage", "短信验证码输入不正确");
//			return "redirect:/register-agent";
//		}
		
		try {
			this.registerService.upgradeAgent(userLogin);
		} catch (IllegalArgumentException e) {
			redirect.addFlashAttribute("upgradeMessage", e.getMessage());
			return "redirect:/register-agent";
		}
		
		// 将验证码失效
		this.authCodeService.invalideCode(username);

		redirect.addFlashAttribute("message", "提交代理商申请成功");
		return "redirect:/login";
	}
	
}
