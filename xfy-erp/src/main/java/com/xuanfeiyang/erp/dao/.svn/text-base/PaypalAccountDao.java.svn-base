package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.PaypalAccount;

/**
 * paypal账号信息
 * @author kenny
 *
 */
public interface PaypalAccountDao {
	
	/**
	 * 
	 * 查询paypalaccount数据
	 * @param pageRequest
	 * @return
	 */
	public List<PaypalAccount> find();
	
	/**
	 * 删除账号
	 * @param id
	 * @return
	 */
	public int delete(@Param("id")Long id);
	/**
	 * 添加paypal账号 
	 * @param paypalAccounts
	 * @return
	 */
	public int save(@Param("param")PaypalAccount paypalAccount);
	
	/**
	 * 修改paypal信息
	 * @param paypalAccounts
	 * @return
	 */
	public int update(@Param("param")PaypalAccount paypalAccount);
	
	/**
	 * 根据id查询paypal账号
	 * @param id
	 * @return
	 */
	public PaypalAccount findById(Long id);


}
