package com.xuanfeiyang.erp.web.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.web.util.SessionCache;
import com.xuanfeiyang.erp.web.util.SessionUser;

@WebListener
public class SessionListener implements HttpSessionListener {
	
	private static Logger logger = LoggerFactory.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		if (session == null) {
			return;
		}
		
		SessionUser user = (SessionUser) session.getAttribute(App.SESSION_USER_KEY);
		if (user == null) {
			return;
		}

		Integer userId = user.getUserId();
		
		logger.debug("清除用户 session : {}", userId);
		SessionCache.remove(userId);
	}

}
