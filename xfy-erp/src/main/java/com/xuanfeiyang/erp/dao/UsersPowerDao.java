package com.xuanfeiyang.erp.dao;

import java.util.List;

import com.xuanfeiyang.erp.domain.UsersPower;

public interface UsersPowerDao {
	int delete(Integer id);

	int insert(UsersPower record);

	UsersPower load(Integer id);
	
	void deleteByUserId(Integer userId);

	List<UsersPower> findByUserId(Integer userId);
}