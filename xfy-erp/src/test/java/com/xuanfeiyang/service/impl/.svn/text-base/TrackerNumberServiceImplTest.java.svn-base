package com.xuanfeiyang.service.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.xuanfeiyang.erp.service.TrackerApplyResult;
import com.xuanfeiyang.erp.service.TrackerNumberService;
import com.xuanfeiyang.erp.service.impl.TrackerApplierHolder;
import com.xuanfeiyang.test.BaseTestCase;

public class TrackerNumberServiceImplTest extends BaseTestCase {
	
	@Resource
	private TrackerNumberService trackerNumberService;
	
	@Test
	public void testApplyOneOrder() {
		
		Integer orderId = 10055149;
		
		TrackerApplyResult result = this.trackerNumberService.applyOneOrder(TrackerApplierHolder.SfRussia.name(), orderId);
		this.log(result);
		Assert.assertTrue(result.isSuccess());
	}
	
	@Test
	public void testApplyOneOrderJunHui() {
		
		Integer orderId = 10489342;
		
		TrackerApplyResult result = this.trackerNumberService.applyOneOrder(TrackerApplierHolder.JunHuiDEPY.name(), orderId);
		this.log(result);
		
//		result = this.trackerNumberService.applyOneOrder(TrackerApplierHolder.JunHuiDERG.name(), orderId);
//		this.log(result);
//		
//		result = this.trackerNumberService.applyOneOrder(TrackerApplierHolder.JunHuiNLPY.name(), orderId);
//		this.log(result);
//		
//		result = this.trackerNumberService.applyOneOrder(TrackerApplierHolder.JunHuiNLRG.name(), orderId);
//		this.log(result);
		
		
		//Assert.assertTrue(result.isSuccess());
	}
	
	private void log(Object text) {
		System.out.println("==========> " + text);
	}
}
