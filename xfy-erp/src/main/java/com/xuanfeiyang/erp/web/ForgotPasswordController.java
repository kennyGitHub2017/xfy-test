package com.xuanfeiyang.erp.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.service.AuthCodeService;
import com.xuanfeiyang.erp.service.SmsSendService;
import com.xuanfeiyang.erp.service.UserService;

@Controller
public class ForgotPasswordController {
	
	private static Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);

	@Resource
	private UserService userService;
	
	@Resource
	private AuthCodeService authCodeService;

	@Resource
	private SmsSendService smsSendService;

	/**
	 * 加载忘记密码页面
	 * @return
	 */
	@RequestMapping(value="forget-password", method=RequestMethod.GET)
	public String forgetPwdPage() {
		return "/forget-password";
	}
	
	/**
	 * 查询用户注册手机号，将密码重置为123456
	 */
	@RequestMapping(value="forget-password",method=RequestMethod.POST)
	public String selectMobile( Model model, 
			@RequestParam("mobile") String mobile,
			@RequestParam("authCode") String authCode) {
		
		// 手机号不能为空
		if(mobile != null && mobile.length() > 0){
			model.addAttribute("mobile", mobile);
			UserInfo user = this.userService.loadByMobile(mobile);
			if (user != null) {
				logger.debug("查询到的手机号：{}", user.getMobile());
				
				//验证码不能为空
				if(authCode != null && authCode.length() > 0){
					
					// 1，验证验证码是否有效
					if (authCode.equals(this.authCodeService.getCode(mobile))) {
			
						// 2，如果有效，重置密码
						String resetPassword = "123456";
						this.userService.updateUserPassword(user.getUserId(), resetPassword);
			
						// 3, 重置成功，发送短信
						String content = String.format(App.getConfig("sms.content.reset.password"), resetPassword);
						this.smsSendService.send(mobile, content);
						
						// 将验证码失效
						this.authCodeService.invalideCode(mobile);
						model.addAttribute("message", "user.send.reset.password");
					} else {
						model.addAttribute("message", "user.auth.code.incorrect");
					}
				} else {
					model.addAttribute("message", "验证码不能为空！");
				}
			
			} else {
				model.addAttribute("message", "该手机号未注册！");
			}
		} else {
			model.addAttribute("message", "手机号不能为空！");
		}
		return "/forget-password";
	}
}
