package com.xuanfeiyang.erp.web.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;

/**
 * Session 缓存，根据用户ID 存放session信息，防止用户多次重复登陆。
 * 
 * @author Adam
 *
 */
public class SessionCache {
	
	private SessionCache() {}
	
	private static ConcurrentMap<Integer, HttpSession> sessions = new ConcurrentHashMap<>();
	
	/**
	 * 添加一个用户对应的 Session 信息。如果已经存在，销毁掉，删除掉，再添加。
	 * 
	 * @param userId
	 * @param session
	 */
	public static void add(Integer userId, HttpSession session) {
		if (sessions.containsKey(userId)) {
			remove(userId);
		}
		
		sessions.put(userId, session);
	}
	
	/**
	 * 删除一个用户的 session
	 * 
	 * @param userId
	 */
	public static void remove(Integer userId) {
		HttpSession session = sessions.remove(userId);
		if (session != null) {
			session.invalidate();
		}
	}
	
}
