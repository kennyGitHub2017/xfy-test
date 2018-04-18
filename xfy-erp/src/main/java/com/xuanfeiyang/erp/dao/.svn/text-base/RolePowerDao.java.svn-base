package com.xuanfeiyang.erp.dao;

import java.util.List;

import com.xuanfeiyang.erp.domain.RolePower;

public interface RolePowerDao {
	
	int insert(RolePower rolePower);

	/**
	 * 查询指定角色的所有权限
	 * @param roleId
	 * @return
	 */
	List<RolePower> findByRoleId(Integer roleId);

	/**
	 * 删除指定角色的所有权限
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Integer roleId);
	
	/**
	 * 查询执行用户的所有角色权限
	 * @param userId
	 * @return
	 */
	List<RolePower> findByUserId(Integer userId);
}