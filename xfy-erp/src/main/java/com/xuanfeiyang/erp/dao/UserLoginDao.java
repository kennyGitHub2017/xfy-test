package com.xuanfeiyang.erp.dao;

import java.util.List;

import com.xuanfeiyang.erp.domain.UserLogin;

public interface UserLoginDao {
	int delete(Integer userId);

	int save(UserLogin userLogin);

	UserLogin load(Integer userId);

	int update(UserLogin userLogin);

	UserLogin loadByUsername(String username);
	
	List<UserLogin> findByUsernameOrMobileOrEmail(String value);
	
	int deleteBySellerId(Integer sellerId);
}