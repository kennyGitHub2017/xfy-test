package com.xuanfeiyang.erp.web;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.SysModule;
import com.xuanfeiyang.erp.domain.SysPage;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserLogin;
import com.xuanfeiyang.erp.domain.UserLoginLog;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.SysModuleService;
import com.xuanfeiyang.erp.service.UserLoginLogService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.SessionUserPowersCache;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private SellerService sellerService;
	
	@Resource
	private SysModuleService sysModuleService;
	
	@Resource
	private UserLoginLogService userLoginLogService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value="url", required=false) String url) {
		
		try {
			UserLogin userLogin = this.userService.loadUserLoginByUsernameAndPassword(username, password);
			if (userLogin == null) {
				model.addAttribute("message", "user.login.incorrect");
				return "login";
			}
			
			Integer userId = userLogin.getUserId();
			
			UserInfo userInfo = this.userService.loadUserInfo(userId);
			checkArgument(userInfo.getLocked() == 0, "用户已被锁定");
			
			int sellerId = userInfo.getSellerId() == null ? 0 : userInfo.getSellerId();
			
			// 1. 设置 session 数据
			SessionUser sessionUser = new SessionUser();
			sessionUser._userId(userId);
			sessionUser._name(userInfo.getName());
			sessionUser._username(username.trim());
			sessionUser._sellerId(sellerId);
			sessionUser._admin(userInfo.getIsAdmin() != null && userInfo.getIsAdmin() == 1);
			sessionUser._type(userInfo.getType());

			// 如果是 其他卖家，则查询卖家状态， App.DEFAULT_SELLER_ID 为本公司ID，特殊卖家
			if (sellerId > App.DEFAULT_SELLER_ID) {
				Seller seller = this.sellerService.load(userInfo.getSellerId());
				checkArgument(seller.getLocked() == 0, "用户已被锁定");
				sessionUser._sellerStatus(seller.getStatus());
			}
			
			HttpSession session = request.getSession();
			session.setAttribute(App.SESSION_USER_KEY, sessionUser);
			
			//SessionCache.add(userId, session); // session缓存控制单用户session唯一
			
			// 2. 缓存权限信息
			this.setUserPowerAndMenu(userId, sessionUser.isAdmin());
			
			// 3. 设置 Cookie 信息
			Cookie cookie = new Cookie("userId", userId.toString());
			this.setCookie(cookie);
			response.addCookie(cookie);

			cookie = new Cookie("sellerId", String.valueOf(userInfo.getSellerId()));
			this.setCookie(cookie);
			response.addCookie(cookie);
			
			cookie = new Cookie("username", userLogin.getUsername());
			this.setCookie(cookie);
			response.addCookie(cookie);
			
			cookie = new Cookie("loginTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
			this.setCookie(cookie);
			response.addCookie(cookie);
			
			cookie = new Cookie("token", session.getId());
			this.setCookie(cookie);
			response.addCookie(cookie);
			
			// 记录卖家用户登录日志
			if (sellerId > App.DEFAULT_SELLER_ID) {
				try {
					UserLoginLog ull = new UserLoginLog();
					ull.setUserId(userId);
					ull.setSellerId(sellerId);
					ull.setUsername(username);
					ull.setLoginTime(new Date());
					ull.setLoginIp(WebHelper.getIpAddr(request));
				this.userLoginLogService.save(ull);
				} catch (Exception e) {
					logger.error("记录登录日志出错", e);
				}
			}
			
//			if (url != null && !url.isEmpty()) {
//				try {
//					url = URLDecoder.decode(url, "utf-8");
//				} catch (UnsupportedEncodingException e) {
//				}
//				return "redirect:" + url;
//			}
			Integer type = userInfo.getType();
			if(type == 1 || type == 2){
				return "redirect:/";
			} else {
				return "redirect:/agent-index";
			}
		} catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			return "login";
		} catch (Exception e) {
			logger.error("登陆出错", e);
			model.addAttribute("message", "system.error");
			return "login";
		}
	}
	
	private void setCookie(Cookie cookie) {
		String cookieDomain = App.getConfig("cookie.domain");
		int maxAge = 7 * 24 * 60 * 60; // 7天有效期
		cookie.setMaxAge(maxAge);
		cookie.setDomain(cookieDomain);
		cookie.setPath("/");
	}
	
	/**
	 * 用户权限和菜单
	 * @param userId
	 * @return
	 */
	private void setUserPowerAndMenu(Integer userId, boolean isAdmin) {
		// 按类型分类的权限代码
		Map<String, Set<String>> userPowers = this.userService.findUserPowers(userId);
		Preconditions.checkArgument(userPowers != null && 
				CollectionUtils.isNotEmpty(userPowers.get("module")), 
				"用户没有任何权限");
		logger.info("user powers: {}", userPowers);
		
		// 树形菜单结构
		List<SysModule> userModules = new ArrayList<>(); 
		// 权限代码和菜单放入缓存
		SessionUserPowersCache.set(userId, userPowers, userModules);
		
		List<SysModule> allModules = this.sysModuleService.findAll();
		
		// 管理员则看全部菜单
		if (isAdmin) {
			userModules.addAll(allModules);
		} else {
			for (SysModule sysModule : allModules) {
				if (userPowers.get("module") != null && userPowers.get("module").contains(sysModule.getCode())) {
					SysModule tmpModule = new SysModule();
					userModules.add(tmpModule);
					tmpModule.setCode(sysModule.getCode());
					tmpModule.setName(sysModule.getName());
					tmpModule.setUrl(sysModule.getUrl());
					
					List<SysPage> pages = new ArrayList<>();
					tmpModule.setPages(pages);
					
					for (SysPage sysPage : sysModule.getPages()) {
						if (userPowers.get("page").contains(sysPage.getCode())) {
							SysPage tmpPage = new SysPage();
							pages.add(tmpPage);
							tmpPage.setCode(sysPage.getCode());
							tmpPage.setName(sysPage.getName());
							tmpPage.setUrl(sysPage.getUrl());
							tmpPage.setSysCode(sysPage.getSysCode());
						}
					}
				}
			}
		}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		SessionUser sessionUser = (SessionUser) session.getAttribute(App.SESSION_USER_KEY);
		if (sessionUser != null) {
			// 清除权限缓存
			// SessionUserPowersCache.remove(sessionUser.getUserId());
			// session 失效
			session.invalidate();
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping("/check-username-password.json")
	@ResponseBody
	public boolean checkUsernamePassword(
			@RequestParam("u") String username,
			@RequestParam("p") String password) {
		
		UserLogin userLogin = this.userService.loadUserLoginByUsernameAndPassword(username, password);
		return userLogin != null;
	}
}
