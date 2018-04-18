package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PayOrder;
import com.xuanfeiyang.erp.param.PayOrderParams;

public interface PayOrderDao {
	/**
	 * 新增
	 * @param param
	 */
	public void add(@Param("param")PayOrder param);
	
	
	public void updateByOutTradeNo(@Param("status")Integer status, 
			@Param("payTimeEnd")String payTimeEnd, @Param("outTradeNo")String outTradeNo);
	
	public PayOrder getByTradeNo(@Param("outTradeNo")String outTradeNo);
	
	/**
	 * 账户充值查询分页数据
	 */
	public Page<PayOrder> findPage(PageRequest pageRequest,
			@Param("params") PayOrderParams params);
	/**
	 * 导出账户充值查询的数据
	 * @param params
	 * @return
	 */
	public List<PayOrder> export(@Param("params") PayOrderParams params);
}
