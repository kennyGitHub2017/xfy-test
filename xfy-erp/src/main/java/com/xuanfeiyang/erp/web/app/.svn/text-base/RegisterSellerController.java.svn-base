package com.xuanfeiyang.erp.web.app;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserLogin;
import com.xuanfeiyang.erp.service.AuthCodeService;
import com.xuanfeiyang.erp.service.DuplicateDataExcepption;
import com.xuanfeiyang.erp.service.RegisterService;
import com.xuanfeiyang.erp.service.SmsSendService;
import com.xuanfeiyang.erp.service.UserService;

@Controller
@RequestMapping("/app")
public class RegisterSellerController {
	private static Logger logger = LoggerFactory.getLogger(RegisterSellerController.class);
	
	@Resource
	private RegisterService registerService;
	@Resource
	private AuthCodeService authCodeService;
	@Resource
	private SmsSendService smsSendService;
	@Resource
	private UserService userService;
	
	
	
	/***
	 * 
	 * 手机注册
	 * 
	 * @param name 姓名
	 * @param idCardNo 身份证号码
	 * @param address 地址
	 * @param email 邮箱
	 * @param password 密码
	 * @param passwordConfirmed 确认密码
	 * @param mobile 手机号
	 * @param authCode 验证码
	 * @param referrerMobile 推荐手机号
	 * @return
	 * 
	 */
	@RequestMapping("register")
	@ResponseBody
	public Map<String,Object> register(
			@RequestParam(value="name",required=true)String name,
			@RequestParam(value="idCardNo",required=false)String idCardNo,
			@RequestParam(value="address",required=false)String address,
			@RequestParam(value="email",required=true)String email,
			@RequestParam(value="password",required=true)String password,
			@RequestParam(value="mobile",required=true)String mobile,
			@RequestParam(value="authCode",required=true)String authCode,
			@RequestParam(value="referrerMobile",required=true)String referrerMobile){
		
			String code = this.authCodeService.getCode(mobile);
			Map<String,Object> map = new HashMap<String,Object>();
			
			if(authCode.equals(code)){
				
				UserInfo userInfo = new UserInfo();
				userInfo.setName(name);
				userInfo.setIdCardNo(idCardNo);
				userInfo.setAddress(address);
				userInfo.setEmail(email);
				userInfo.setMobile(mobile);
				userInfo.setReferrerMobile(referrerMobile);
	
				try {
					this.registerService.registerSeller(userInfo, password);
					map.put("result", true);
				} catch (DuplicateDataExcepption e) {
					logger.error("APP注册异常",e);
					map.put("result", false);
					map.put("error", "注册失败");
				}
				
				this.authCodeService.invalideCode(userInfo.getMobile());// 将验证码失效
				
			}else{
				
				map.put("error", "验证码不正确");
			}
			return map;
	}

	/***
	 * 
	 * APP获取验证码 发送到手机
	 * 
	 * @param mobile 手机号
	 * @return
	 */
	@RequestMapping("getAuthCode")
	@ResponseBody
	public boolean getAuthCode(@RequestParam("mobile")String mobile) {

		if (mobile == null) {
			return false;
		}
		
		String code = this.authCodeService.createCode(mobile);
		String content = String.format(App.getConfig("sms.content.auth.code"), code);
		return this.smsSendService.send(mobile, content);
	}
	
	
	/***
	 * APP登陆
	 * 
	 * @param userName 登陆名
	 * @param password 登陆密码
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public Map<String,Object> login(@RequestParam(value="userName", required=true)String userName,
			@RequestParam(value="password", required=true)String password){
		Map<String, Object> map = new HashMap<String, Object>();
		
		UserLogin userInfo = userService.loadUserLoginByUsernameAndPassword(userName, password);
		if(userInfo != null){
			map.put("result", true);
			map.put("result", userInfo.getUserId());
		}else{
			
			map.put("result", false);
			map.put("error", "登陆失败");
		}
		return map;
	}
	
	
	/***
	 * 
	 * 检查手机号
	 * 
	 * @param mobile 手机号
	 * @return
	 */
	@RequestMapping("checkMobile")
	@ResponseBody
	public Map<String, Object> checkMobile(@RequestParam(value="mobile", required=true)String mobile){
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = this.userService.loadByMobile(mobile);
		
		if(userInfo != null){
			map.put("result", true);
		}else{
			map.put("result", false);
		}
		return map;
	}
	
	
	/***
	 * 
	 *检查邮箱
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("checkEmail")
	@ResponseBody
	public Map<String, Object> checkEmail(@RequestParam(value="email", required=true)String email){
		Map<String, Object> map = new HashMap<String,Object>();
		UserInfo userInfo = this.userService.loadByEmail(email);
		
		if(userInfo != null){
			map.put("result", true);
		}else{
			map.put("result", false);
		}
		return map;
	}
	
	
	/***
	 * APP找回密码 第一步
	 * 
	 * 验证手机用户
	 * 
	 * @param mobile 手机号
	 * @param authCode 验证码
	 * @return
	 */
	@RequestMapping("checkUser")
	@ResponseBody
	public Map<String, Object> forgetPwd(
			@RequestParam("mobile") String mobile,
			@RequestParam("authCode") String authCode){
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo user = this.userService.loadByMobile(mobile);
		if(user != null){
			
			if (authCode.equals(this.authCodeService.getCode(mobile))) { //验证码通过，发送动态密码
				
				//删除验证码
				this.authCodeService.invalideCode(mobile);
				
				// 动态密码
				String randomPassword = this.authCodeService.createCode(mobile);
			
				// 发送动态密码
				String content = String.format(App.getConfig("sms.content.reset.password.app"), randomPassword);
				this.smsSendService.send(mobile, content);
				
				map.put("result", true);
				
			}else{
				
				map.put("result", false);
				map.put("error", "验证码不正确");
			}
			
		}else{
			map.put("result", false);
			map.put("error", "手机号不存在");
		}
		return map;
	}
	
	/***
	 * APP找回密码 第二 步
	 * 
	 * 修改密码
	 * 
	 * @param mobile
	 * @param password 动态码
	 * @param passwordNew 新密码
	 * @return
	 */
	@RequestMapping("resetPwd")
	@ResponseBody
	public Map<String, Object> resetPwd(
			@RequestParam("mobile") String mobile,
			@RequestParam(value="password",required=true)String password,
			@RequestParam(value="passwordNew",required=true)String passwordNew){
		
			Map<String, Object> map = new HashMap<String, Object>();
			if(password.equals(this.authCodeService.getCode(mobile))){
				
				this.authCodeService.invalideCode(mobile);//删除动态密码
				UserInfo user = this.userService.loadByMobile(mobile);
				
				if(user != null){
					this.userService.updateUserPassword(user.getUserId(), passwordNew);
					map.put("result", true);	
				}else{
					map.put("result", false);
					map.put("error", "手机号不存在");
				}
			
				
			}else{
				map.put("result", false);
				map.put("error", "动态密码不正确");
			}
		return map;
	}


}
