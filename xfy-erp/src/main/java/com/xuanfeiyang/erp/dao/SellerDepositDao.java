package com.xuanfeiyang.erp.dao;

import com.xuanfeiyang.erp.domain.SellerDeposit;

public interface SellerDepositDao {
	public void insert(SellerDeposit sd);
	
	public void update(SellerDeposit sd);
	
	public void delete(Integer sellerId);

	public SellerDeposit load(Integer sellerId);
}
