package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.PaypalAccount;

public interface PaypalAccountService {
	
	
	public List<PaypalAccount> find();
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean delete(Long id);
	
	/**
	 * 添加paypal账号
	 * @param paypalAccounts
	 * @return
	 */
	public boolean add(PaypalAccount paypalAccount);
	
	/***
	 * 修改paypal账号信息
	 * @param paypalAccounts
	 * @return
	 */
	public boolean update(PaypalAccount paypalAccount);
	
	/**
	 * 
	 * 根据id查询paypal账号
	 * @param id
	 * @return
	 */
	public PaypalAccount findById(Long id);
	

}
