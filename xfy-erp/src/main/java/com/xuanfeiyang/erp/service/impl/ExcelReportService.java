package com.xuanfeiyang.erp.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.buzheng.excel.Column;
import org.buzheng.excel.ExcelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.StatDao;

/**
 * 生成报表excel相关业务逻辑
 * 
 * @author Adam
 *
 */
@Service
public class ExcelReportService {

	private static Logger logger = LoggerFactory
			.getLogger(ExcelReportService.class);

	@Resource
	private StatDao statDao;

	private static String filenamePattern = "%s/report/%s/%s.xlsx";

	/**
	 * 生成所有sku的信息报表
	 */
	public void allSkusInfo() {
		try {
			List<Map<String, Object>> allSkuInfo = this.statDao.allSkuInfo();

			List<Column> cs = new ArrayList<>();
			cs.add(new Column("添加时间", "CREATED_TIME", "yyyy-mm-dd hh:mm:ss"));
			cs.add(new Column("开发时间", "DEVELOP_TIME", "yyyy-mm-dd"));
			cs.add(new Column("SKU", "GOODS_SKU"));
			cs.add(new Column("SKU", "OLD_SKU"));
			cs.add(new Column("名称", "NAME"));
			cs.add(new Column("颜色", "COLOR"));
			cs.add(new Column("尺码", "GOODS_SIZE"));
			cs.add(new Column("规格", "RULES"));
			cs.add(new Column("型号", "MODEL"));
			cs.add(new Column("成本", "COST", "0.00"));
			cs.add(new Column("最新采购价", "GOODS_COST", "0.00"));
			cs.add(new Column("支付方式", "PAY_METHOD"));
			cs.add(new Column("供应商", "COMPANY_NAME"));
			cs.add(new Column("大类", "BASE_CATEGORY_NAME"));
			cs.add(new Column("中类", "MID_CATEGORY_NAME"));
			cs.add(new Column("小类", "CATEGORY_NAME"));
			cs.add(new Column("重量", "WEIGHT", "0.000"));
			cs.add(new Column("货架", "STORE_SHELF_CODE"));
			cs.add(new Column("采购员", "BUY_USER_NAME"));

			ExcelBuilder<Map<String, Object>> eb = new ExcelBuilder<>();
			eb.setData(allSkuInfo);
			eb.setColumns(cs);

			String type = "sku-info";
			String day = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			String filePath = this.buildFilePath(type, day);

			eb.toFile(filePath);
		} catch (IOException e) {
			logger.error("生成sku信息表出错", e);
		}
	}

	private String buildFilePath(String type, String filename) {
		String filePath = String.format(filenamePattern,
				App.getConfig("upload.base.dir"), type, filename);

		File file = new File(filePath).getParentFile();
		if (!file.exists()) {
			file.mkdirs();
		}

		return filePath;
	}

	/**
	 * 已发货商品报表
	 * 
	 * @param day
	 *            查询日期，格式 yyyy-MM-dd
	 */
	public void dayShippedGoods(String day) {

		try {
			// 日期为空取昨天
			Date dayDate = day == null ? this.yestoday() : this.parseDate(day);

			List<Map<String, Object>> data = this.statDao.shippedGoods(dayDate,
					dayDate);

			List<Column> cs = new ArrayList<>();
			cs.add(new Column("订单日期", "CREATED_TIME", "yyyy-mm-dd hh:mm:ss"));
			cs.add(new Column("订单号", "ID"));
			cs.add(new Column("SKU", "SKU"));
			cs.add(new Column("品名", "NAME"));
			cs.add(new Column("货架号", "CODE"));
			cs.add(new Column("数量", "PACKAGE_AMOUNT"));
			cs.add(new Column("大类", "BASE_CATEGORY_NAME"));
			cs.add(new Column("中类", "MID_CATEGORY_NAME"));
			cs.add(new Column("小类", "CATEGORY_NAME"));
			cs.add(new Column("平台账号", "ACCOUNT_NAME"));
			cs.add(new Column("发货渠道", "SHIPPING_NAME"));
			cs.add(new Column("发货日期", "SCANNED_TIME", "yyyy-mm-dd hh:mm:ss"));
			cs.add(new Column("发到国", "SHIPPING_COUNTRY"));

			ExcelBuilder<Map<String, Object>> eb = new ExcelBuilder<>();
			eb.setData(data);
			eb.setColumns(cs);

			String type = "shipped-goods";
			String reportday = DateFormatUtils.format(dayDate, "yyyy-MM-dd");
			String filePath = this.buildFilePath(type, reportday);
			eb.toFile(filePath);
		} catch (IOException e) {
			logger.error("生成已发货商品报表出错", e);
		}
	}

	/**
	 * 日采购入库清单
	 * 
	 * @param day
	 */
	public void dayPurchaseInStoreListing(String day) {
		try {
			// 日期为空取昨天
			Date dayDate = day == null ? this.yestoday() : this.parseDate(day);
			day = DateFormatUtils.format(dayDate, "yyyy-MM-dd");
			List<Map<String, Object>> data = this.statDao.inStoreListing(
					dayDate, dayDate, "po");

			List<Column> cs = new ArrayList<>();
			cs.add(new Column("采购单号", "PO_ORDER_NO"));
			cs.add(new Column("供应商", "SUPPLIER_NAME"));
			cs.add(new Column("产品大类", "BASE_CATEGORY_NAME"));
			cs.add(new Column("SKU", "GOODS_SKU"));
			cs.add(new Column("商品名称", "GOODS_NAME"));
			cs.add(new Column("数量", "GOODS_COST", "0.00"));
			cs.add(new Column("单价", "IO_COUNT"));
			cs.add(new Column("总金额", "IO_AMOUNT", "0.00"));
			cs.add(new Column("入库时间", "IO_TIME", "yyyy-mm-dd hh:mm:ss"));
			cs.add(new Column("采购时间", "PO_TIME", "yyyy-mm-dd hh:mm:ss"));
			cs.add(new Column("备注", "IO_NOTE"));

			ExcelBuilder<Map<String, Object>> eb = new ExcelBuilder<>();
			eb.setData(data);
			eb.setColumns(cs);
			eb.setCaption(String.format("采购入库清单(%s)", day));

			String type = "in-store-listing";
			String filename = day + "-purchase";
			String filePath = this.buildFilePath(type, filename);

			eb.toFile(filePath);
		} catch (IOException e) {
			logger.error("生成采购入库清单出错", e);
		}
	}

	/**
	 * 日手工入库清单
	 * 
	 * @param day
	 */
	public void dayManualInStoreListing(String day) {
		try {
			// 日期为空取昨天
			Date dayDate = day == null ? this.yestoday() : this.parseDate(day);
			day = DateFormatUtils.format(dayDate, "yyyy-MM-dd");
			List<Map<String, Object>> data = this.statDao.inStoreListing(
					dayDate, dayDate, "npo");

			List<Column> cs = new ArrayList<>();
			cs.add(new Column("产品大类", "BASE_CATEGORY_NAME"));
			cs.add(new Column("SKU", "GOODS_SKU"));
			cs.add(new Column("商品名称", "GOODS_NAME"));
			cs.add(new Column("数量", "GOODS_COST", "0.00"));
			cs.add(new Column("单价", "IO_COUNT"));
			cs.add(new Column("总金额", "IO_AMOUNT", "0.00"));
			cs.add(new Column("入库时间", "IO_TIME", "yyyy-mm-dd hh:mm:ss"));
			cs.add(new Column("入库原因", "IO_REASON"));
			cs.add(new Column("备注", "IO_NOTE"));

			ExcelBuilder<Map<String, Object>> eb = new ExcelBuilder<>();
			eb.setData(data);
			eb.setColumns(cs);
			eb.setCaption(String.format("手工入库清单(%s)", day));

			String type = "in-store-listing";
			String filename = day + "-manual";
			String filePath = this.buildFilePath(type, filename);

			eb.toFile(filePath);
		} catch (IOException e) {
			logger.error("生成手工入库清单出错", e);
		}
	}

	private Date parseDate(String day) {
		try {
			return DateUtils.parseDate(day, "yyyy-MM-dd");
		} catch (Exception e) {
			throw new IllegalArgumentException("日期不正确：" + day, e);
		}
	}

	private Date yestoday() {
		Date dayDate = DateUtils.addDays(new Date(), -1);
		dayDate = DateUtils.setHours(dayDate, 0);
		dayDate = DateUtils.setMinutes(dayDate, 0);
		dayDate = DateUtils.setSeconds(dayDate, 0);
		dayDate = DateUtils.setMilliseconds(dayDate, 0);

		return dayDate;
	}
}
