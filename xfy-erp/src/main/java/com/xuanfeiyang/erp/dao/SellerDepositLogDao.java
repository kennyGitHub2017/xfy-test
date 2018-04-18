package com.xuanfeiyang.erp.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.param.SellerDepositParams;

public interface SellerDepositLogDao {

	int insert(SellerDepositLog record);

	Page<SellerDepositLog> findPage(PageRequest pageRequest, 
			@Param("params") SellerDepositParams params);
	
	List<SellerDepositLog> findDepositLog(@Param("params") SellerDepositParams params);
	
	public Integer getSumAmountLog(@Param("params")SellerDeposit params);
	
	public Integer findDepositLogCount(@Param("params") SellerDepositParams params);
	
	public Map<String,BigDecimal> getTotalInfo(@Param("params") SellerDepositParams params);
	
	public SellerDepositLog getMaxByOrderId(@Param("orderId")Integer orderId);
	
	public Integer updateLogById(@Param("params") SellerDepositLog params);
	
	/**
	 * 最近几天的平均流水
	 * @param sellerId 卖家ID
	 * @param type 类型
	 * @param days 几天
	 * @return
	 */
	public BigDecimal latestDaysAverageAmount(@Param("sellerId") Integer sellerId, 
			@Param("type") Integer type,
			@Param("days") Integer days);
	
	public List<SellerDepositLog> findShippedOrdersDepositLog(@Param("params") SellerDepositParams params);
	
}