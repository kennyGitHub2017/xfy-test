package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.AgentConfigure;
import com.xuanfeiyang.erp.domain.AgentConfigureLog;

public interface AgentConfigureService {
	
	public Page<AgentConfigure> findPage(PageRequest pageRequest, AgentConfigure param);
	
	public void updateRebateRate(String costRate,String serviceRate,String bond,Integer userId,Integer operId);
	
	public void updateAgentBond(Integer bond,Integer userId,Integer operId);
	
	public AgentConfigure getByUserId(Integer userId);
	
	/***
	 * 发货订单返点
	 */
	public void shippedOrderRebate();

	public List<AgentConfigureLog> getLogByUserId(Integer userId);

	public boolean add(AgentConfigure agentConfigure);
}
