package com.xuanfeiyang.erp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.param.SellerParams;

public interface SellerDao {
	
	Page<Seller> findPage(PageRequest pageRequest, 
			@Param("params") SellerParams params);
	
	/**
	 * 查询 带保证金字段
	 * @param pageRequest
	 * @param params
	 * @return
	 */
	Page<Seller> findPageWithDeposit(PageRequest pageRequest, 
			@Param("params") SellerParams params);
	
	void delete(Integer id);

	void save(Seller seller);

	Seller load(Integer id);

	void update(Seller seller);
	
	/**
	 * 更新自营卖家标识
	 * @param id 卖家ID 
	 * @param selfFlag 自营标识
	 */
	void updateSelfFlag(@Param("id") Integer id, @Param("selfFlag") String selfFlag);
	
	/**
	 * 更新自营卖家标识
	 * @param id 卖家ID 
	 * @param vipFlag vip标识
	 */
	void updateVipFlag(@Param("id") Integer id, @Param("vipFlag") String vipFlag);

	/**
	 * 按状态计数
	 * @param i
	 */
	int countByStatus(int i);

	/**
	 * 查询可以发信息提醒的卖家
	 * 
	 * @param maxSmsSendTimes
	 * @return
	 */
	List<Seller> findForSendSms(int maxSmsSendTimes);
	
	/**
	 * 短信发送次数 + 1
	 * 
	 * @param sellerId
	 */
	void increaseSmsSendTimes(Integer sellerId);
	
	/**
	 * 根据平台的账号ID 查询对应的买家信息
	 * @param platformAccountId
	 */
	Seller getByPlatformAccountId(Integer platformAccountId);
	
	/***
	 * 
	 * 查询所有
	 * status 状态
	 * @return
	 */
	List<Seller> findAll(Integer status);
	
	/**
	 * 查所指定状态卖家的指定列数据
	 * @param status
	 * @param columns must not null and not empty
	 * @return
	 */
	List<Seller> findAssginColumn(@Param("status")Integer status,@Param("columns")List<String> columns);
	
	/**
	 * 根据代理商查询卖家
	 * @param agentUserId
	 * @return
	 */
	List<Seller> findByAgent(Integer agentUserId);
	
	/**
	 * 根据代理商查询卖家总数
	 * @param agentUserId
	 * @return
	 */
	Integer getCountByUserId(Integer agentUserId);
	
	
}