package com.xuanfeiyang.erp.web.interceptor;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.web.util.SessionUser;

public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String contextPath = request.getContextPath();
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(App.SESSION_USER_KEY);
		
		// 判断session有效
		if (sessionUser != null) {
			// 对于未审核的卖家，只能访问首页和上传认证资料页
			String uri = request.getRequestURI();
			String index = contextPath + "/";
			if (sessionUser.getSellerId() > App.DEFAULT_SELLER_ID && sessionUser.getSellerStatus() != 2
					&& (! (uri.equals(index) || uri.equals(contextPath) || 
							uri.equals(contextPath + "/my/audit-info") ||
							uri.equals(contextPath + "/my/password")||
							uri.equals(contextPath + "/process2-page")||
							uri.equals(contextPath + "/process1-page")))) {
						
				response.sendRedirect(index);
				return false;
			}
			
			return true;
		}
		
		String xRequestedWith = request.getHeader("X-Requested-With");

		if (xRequestedWith != null && ! xRequestedWith.isEmpty()) {
			// ajax 请求, 添加特殊头标识，以便前端处理
			String referer = request.getHeader("Referer");
			response.setHeader("Session-Timeout-Location", this.getLoginUrl(contextPath, referer));
			response.setHeader("Context-Type", "text/plain;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print("{\"message\":\"请重新登陆\", \"data\":[]}");
			out.close();
			
		} else {
			// 正常请求
			String url = null;
			if ("GET".equals(request.getMethod().toUpperCase())) {
				url = request.getRequestURL().toString();
				String queryString = request.getQueryString();
				if (queryString != null) {
					url += ("?" + queryString);
				}
			} else {
				url = request.getHeader("Referer");
			}
			response.sendRedirect(this.getLoginUrl(contextPath, url));
		}
		return false;
	}

	private String getLoginUrl(String contextPath, String url) {
		if (url != null) {
			try {
				url = URLEncoder.encode(url, "utf-8");
			} catch (UnsupportedEncodingException e) {
				
			}
			
			return contextPath + "/login?url=" + url;
		}
		
		return contextPath + "/login";
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
