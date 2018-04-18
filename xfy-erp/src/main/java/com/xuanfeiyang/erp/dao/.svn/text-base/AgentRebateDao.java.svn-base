package com.xuanfeiyang.erp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.AgentRebate;
import com.xuanfeiyang.erp.param.AgentRebateParam;

/**
 * 代理商返点明细
 * @author Administrator
 *
 */
public interface AgentRebateDao {
	
    int insert(AgentRebate record);

    AgentRebate get(Integer id);
    
    BigDecimal rebateTotal(@Param("param") AgentRebateParam param);
    
    Page<AgentRebate> findPage(PageRequest pageRequest,@Param("param") AgentRebateParam param);
    
    public Integer countByUserId(Integer userId);
    
    public Integer sumAmountByUserId(Integer userId);
    
    public List<AgentRebate> countOrderByAgentId(Integer userId);
    
    public List<AgentRebate> findByAgentId(Integer userId);
    
    public List<AgentRebate> findAgentFundLog(Integer userId,Integer number);   //代理商首页的资金日志显示
}