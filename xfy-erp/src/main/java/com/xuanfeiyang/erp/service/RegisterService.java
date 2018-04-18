package com.xuanfeiyang.erp.service;

import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.domain.UserLogin;

/**
 * 注册业务逻辑类
 * 
 * @author Adam
 *
 */
public interface RegisterService {
	
	/**
	 * 卖家注册业务逻辑
	 */
	public void registerSeller(UserInfo userInfo, String password) throws DuplicateDataExcepption;
	
	/**
	 * 注册代理商
	 * @param userInfo
	 * @param password
	 * @throws DuplicateDataExcepption
	 */
	public void registerAgent(UserInfo userInfo, String password) throws DuplicateDataExcepption;

	public void upgradeAgent(UserLogin userLogin);
	
}
