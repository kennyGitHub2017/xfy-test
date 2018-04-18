package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.AgentConfigure;
import com.xuanfeiyang.erp.domain.AgentConfigureLog;

public interface AgentConfigureDao {
	
	AgentConfigure getDeposit(Integer agentUserId);

	void updateDeposit(@Param("param")AgentConfigure agentDeposit);

	Page<AgentConfigure> findPage(PageRequest pageRequest,@Param("param")AgentConfigure param);
	
	public AgentConfigure getByUserId(Integer userId);
	
	public void addConfigureLog(@Param("param")AgentConfigureLog param);
	
	public List<AgentConfigureLog> getLogByUserId(Integer userId);
	
	public int insert(@Param("param")AgentConfigure agentConfigure);
}
