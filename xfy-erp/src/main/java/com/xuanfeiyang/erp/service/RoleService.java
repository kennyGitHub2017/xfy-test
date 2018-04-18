package com.xuanfeiyang.erp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xuanfeiyang.erp.domain.Role;

/**
 * 角色相关
 * 
 * @author Adam
 *
 */
public interface RoleService {
	
	/**
	 * 新增加角色
	 * @param role
	 */
	public void save(Role role);
	
	/**
	 * 根据ID修改角色
	 * @param role
	 */
	public void update(Role role);
	
	/**
	 * 根据ID删除角色
	 * @param id 删除记录的ID
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 * @return 角色列表
	 */
	public List<Role> find();
	
	/**
	 * 根据ID查询单个对象
	 * @param id 
	 * @return
	 */
	public Role load(Integer id);
	
	/**
	 * <p>查询指定角色的权限信息
	 * <p>返回map结构结构为 {powerType => [powerCode1, powerCode2]} 如下：<br>
	 * {<br>
	 *   "module" => ["system", "order"],<br>
	 *   "page" => ["user", "role", ...],<br>
	 *   "function" => ["user_delete", "user_add", "role_delte", ...]<br>
	 * }
	 * 
	 * @param roleId
	 * @return 指定角色的所有权限信息
	 */
	public Map<String, Set<String>> findRolePowers(Integer roleId);
	
	/**
	 * 保存角色权限
	 * 
	 * @param roleId 角色ID
	 * @param modulePowers 赋予的模块权限 
	 * @param pagePowers 赋予的页面权限
	 * @param functionPowers 赋予的功能权限
	 */
	public void saveRolePowers(Integer roleId, 
			List<String> modulePowers, List<String> pagePowers, 
			List<String> functionPowers);

}
