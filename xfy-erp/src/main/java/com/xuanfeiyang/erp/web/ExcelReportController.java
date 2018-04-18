package com.xuanfeiyang.erp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuanfeiyang.erp.service.impl.ExcelReportService;

@Controller
@RequestMapping("excel-report")
public class ExcelReportController extends BaseController {
	
	@Resource
	private ExcelReportService excelReportService;
	
	@RequestMapping("all")
	@ResponseBody
	public String all(@RequestParam(value="day", required=false) String day) {
		this.excelReportService.allSkusInfo();
		this.excelReportService.dayShippedGoods(day);
		this.excelReportService.dayManualInStoreListing(day);
		this.excelReportService.dayPurchaseInStoreListing(day);
		
		return "done";
	}
	
	@RequestMapping("all-sku-info")
	@ResponseBody
	public String allSkuInfo() {
		this.excelReportService.allSkusInfo();
		return "done";
	}
	
	@RequestMapping("day-shipped-goods")
	@ResponseBody
	public String dayShippedGoods(
			@RequestParam(value="day", required=false) String day) {
		this.excelReportService.dayShippedGoods(day);
		return "done";
	}
	
	@RequestMapping("day-in-store-listing")
	@ResponseBody
	public String dayInStoreListing(
			@RequestParam(value="day", required=false) String day) {
		this.excelReportService.dayManualInStoreListing(day);
		this.excelReportService.dayPurchaseInStoreListing(day);
		
		return "done";
	}
}
