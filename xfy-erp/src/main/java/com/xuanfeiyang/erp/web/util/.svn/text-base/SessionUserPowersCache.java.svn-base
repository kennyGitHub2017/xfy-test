package com.xuanfeiyang.erp.web.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.xuanfeiyang.erp.domain.SysModule;

/**
 * Session级缓存，缓存用户权限
 * 
 * @author Adam
 *
 */
public class SessionUserPowersCache {
	
	private SessionUserPowersCache() {}
	
	/**
	 * 按类型存放权限
	 * <p>map结构为 {powerType => [powerCode1, powerCode2]} 如下：<br>
	 * {<br>
	 *   "module" => ["system", "order"],<br>
	 *   "page" => ["user", "role", ...],<br>
	 *   "function" => ["user_delete", "user_add", "role_delte", ...]<br>
	 * }
	 */
	private final static ConcurrentMap<Integer, Map<String, Set<String>>> powers = new ConcurrentHashMap<>(); 
	
	/**
	 * 用户的可用菜单缓存
	 */
	private final static ConcurrentMap<Integer, List<SysModule>> menus = new ConcurrentHashMap<>();
	
	/**
	 * 添加指定用户的权限到缓存
	 * 
	 * @param userId
	 * @param userPowers Map<String, Set<String>>	按类型存放的用户权限代码
	 * @param userMenus List<SysModule> 树形结构的用户菜单权限
	 */
	public static void set(Integer userId, Map<String, Set<String>> userPowers, List<SysModule> userMenus) {
		powers.put(userId, userPowers);
		menus.put(userId, userMenus);
	}
	
	/**
	 * 删除指定用户的权限缓存
	 * @param userId
	 */
	public static void remove(Integer userId) {
		powers.remove(userId);
		menus.remove(userId);
	}
	
	/**
	 * 获取指定用户的权限
	 * 
	 * @param userId
	 * @return
	 */
	public static Map<String, Set<String>> getPowerMap(Integer userId) {
		return powers.get(userId);
	}
	
	public static List<SysModule> getMenus(Integer userId) {
		return menus.get(userId);
	}
}
