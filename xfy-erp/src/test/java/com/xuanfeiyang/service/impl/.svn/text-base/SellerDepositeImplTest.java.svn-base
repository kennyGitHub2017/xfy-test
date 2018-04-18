package com.xuanfeiyang.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanfeiyang.erp.service.DepositBalanceShortageException;
import com.xuanfeiyang.erp.service.SellerDepositService;
import com.xuanfeiyang.test.BaseTestCase;

public class SellerDepositeImplTest extends BaseTestCase {
	
	private static Logger logger = LoggerFactory.getLogger(SellerDepositeImplTest.class);
	
	@Resource private SellerDepositService sellerDepositService;
	
	@Test
	public void testDecreaseDeposit() {
		Integer sellerId = 63;
		BigDecimal amount = new BigDecimal("102.07");
		String note = "订单费用，订单: 121312313, 成本: 100.04, 处理费: 2.00, 运费: 0.03";
		
		try {
			this.sellerDepositService.decreaseDeposit(sellerId, amount, note);
		} catch (DepositBalanceShortageException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
