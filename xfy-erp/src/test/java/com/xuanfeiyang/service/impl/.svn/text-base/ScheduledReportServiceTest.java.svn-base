package com.xuanfeiyang.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.xuanfeiyang.erp.service.impl.ExcelReportService;
import com.xuanfeiyang.test.BaseTestCase;

public class ScheduledReportServiceTest extends BaseTestCase {
	
	@Resource
	private ExcelReportService reportService;
	
	@Test
	public void testAllSkuInfo() {
		this.reportService.allSkusInfo();
	}
	
	@Test
	public void testDayShippedGoods() {
		String day = "2015-06-10";
		this.reportService.dayShippedGoods(day);
	}
	
	@Test
	public void testDayPurchaseInStoreListing() {
		this.reportService.dayPurchaseInStoreListing(null);
	}
	

	@Test
	public void testDayManualInStoreListing() {
		this.reportService.dayManualInStoreListing(null);
	}
}
