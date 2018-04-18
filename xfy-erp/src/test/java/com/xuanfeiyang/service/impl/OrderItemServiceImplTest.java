
package com.xuanfeiyang.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;

import com.xuanfeiyang.erp.dao.SellerDepositLogDao;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.param.SellerDepositParams;
import com.xuanfeiyang.erp.service.OrderItemService;
import com.xuanfeiyang.erp.service.OrderService;
import com.xuanfeiyang.test.BaseTestCase;

public class OrderItemServiceImplTest extends BaseTestCase {
	
	@Resource
	private SellerDepositLogDao sellerDepositLogDao;
	
	@Resource
	private OrderItemService orderItemService;
	
	@Resource
	private OrderService orderService;
	
	@Test
	public void testShippFee(){
		Order oo = this.orderService.load(10491983);
		BigDecimal k = this.orderService.countShiFee(oo, "AM（EUB）");
		System.out.println(k);
	}
	
	
	@Test
	public void testUpdateAllSku() {
		this.orderItemService.updateAllSku();
	}
	
	@Test
	public void testSplitCombineItem() {
		int id = 265741;
		this.orderItemService.splitCombineItem(id);
	}
	
	@Test
	public void testSplitCombineItems() {
		this.orderItemService.splitCombineItems();
	}
	
	@Test
	public void findDepositLog(){
		SellerDepositParams s = new SellerDepositParams();
		this.sellerDepositLogDao.findDepositLog(s);
	}
}	
