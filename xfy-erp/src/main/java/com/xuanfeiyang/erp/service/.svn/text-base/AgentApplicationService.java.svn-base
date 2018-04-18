package com.xuanfeiyang.erp.service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.UserParams;


public interface AgentApplicationService {
	
	
	public UserInfo load(Integer userId);
	
	public boolean delete(Integer userId);   //逻辑删除
	
	public boolean approve(Integer userId);    //审核
	
	public boolean unapprove(Integer userId,String note);   //反审核
	
	/**
	 * 锁定代理商
	 */
	public void lock(Integer userId);
	
	/**
	 * 解锁代理商
	 */
	public void unlock(Integer userId);
	
	/**
	 * 代理商申请审核分页查询
	 * @param pageRequest
	 * @param params
	 * @return
	 */
	public Page<UserInfo> getPage(PageRequest pageRequest, UserParams params);
	
}
