package com.xuanfeiyang.erp.dao;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.UserParams;
/**
 * 代理商申请审核
 * @author Administrator
 *
 */
public interface AgentApplicationDao {
	
	public UserInfo load(Integer userId);
	
	public int update(@Param("param")UserInfo userInfo);
	
	//逻辑删除
	public int delete(Integer userId);
	
	//审核通过
	public int approve(Integer userId);
	
	//反审核
	public int unapprove(@Param("param")UserInfo userInfo);
	
	public Page<UserInfo> findPage(PageRequest pageRequest,
			@Param("params") UserParams params);
	
}
