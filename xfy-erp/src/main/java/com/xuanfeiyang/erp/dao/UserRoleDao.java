package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.Role;
import com.xuanfeiyang.erp.domain.UserRole;

public interface UserRoleDao {
	int delete(Integer id);

	int insert(UserRole userRole);

	UserRole load(Integer id);

	int update(UserRole userRole);
	
	List<Role> findRolesByUserId(Integer userId);
	
	void deleteByUserId(Integer userId);

	void deleteByRoleId(Integer roleId);
	
	void deleteByUserIdAndRoleId(@Param("userId") Integer userId, 
			@Param("roleId") Integer roleId);

}