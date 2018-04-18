package com.xuanfeiyang.erp.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.service.AgentConfigureService;
import com.xuanfeiyang.erp.service.CurrencyRatesService;
import com.xuanfeiyang.erp.service.GeneratePackageService;
import com.xuanfeiyang.erp.service.GoodsInventoryService;
import com.xuanfeiyang.erp.service.GoodsSupplierPriceService;
import com.xuanfeiyang.erp.service.OrderPackageService;
import com.xuanfeiyang.erp.service.OrderService;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.StatisticService;
import com.xuanfeiyang.erp.service.TableKeyService;

/**
 * 定时任务、计划任务业务类<br/>
 * 所有定时、计划相关操作 尽量都集中在此类
 * 
 * @author Adam
 *
 */
@Service
public class ScheduledTaskService {
	private Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);
	
	@Resource
	private OrderPackageService orderPackageService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private StatisticService statisticService;
	
	@Resource
	private CurrencyRatesService currencyRatesService;
	
	@Resource
	private SellerService sellerService;
	
	@Resource
	private GeneratePackageService generatePackageService;
	
	@Resource
	private TableKeyService tableKeyService; 
	
	@Resource
	private GoodsSupplierPriceService goodsSupplierPriceService;
	
	@Resource
	private ExcelReportService excelReportService;
	
	@Resource
	private GoodsInventoryService goodsInventoryService;
	
	@Resource
	private AgentConfigureService agentConfigureService;
	
	/**
	 * 统计 SKU 销售量
	 */
	@Scheduled(cron="0 0 1 * * ?") // 1点触发
	public void statSkuSales() {
		logger.info("任务开始：sku销量统计...");
		
		try {
			this.statisticService.statSkuSales();
		} catch (Exception e) {
			logger.error("sku销量统计 出错", e);
		}
		
		logger.info("任务结束：sku销量统计.");
	}
	
	/**
	 * 计算订单的利润
	 */
	@Scheduled(cron="0 0 0/6 * * ?") // 每6个小时触发
	public void orderProfit() {
		logger.info("任务开始：订单利润计算...");
		try {
			orderService.calculateOrderProfit();
		} catch (Exception e) {
			logger.error("订单利润计算出错", e);
		}
		logger.info("任务结束：订单利润计算.");
	}
	
	/**
	 * 从刊登平台同步订单到系统
	 */
	@Scheduled(cron="0 5/30 * * * ?")
	public void batchInsertOrders() {
		logger.info("系统开始同步订单.....");
		try{
			int syncOrderCount = orderService.batchInsertOrders();
			if (-1==syncOrderCount){
				throw new Exception("订单正在同步中...30分钟后将再次同步");
			}
		}catch(Exception e){
			logger.error("从刊登平台同步新订单发生异常", e);
		}
	}
	
	/***
	 * 自动生成包裹
	 * 只处理订单sku全部满足的订单
	 */
	@Scheduled(cron = "0 18/20 * * * ?")
	public void generatePackage() {
		try {
			this.orderService.generatePackage3();
		} catch (Exception e) {
			logger.error("状态为 3 [即:已锁定]生成包裹产生异常  3", e);
		}
	}
	
	/**
	 * 匹配物流方式
	 */
	//@Scheduled(cron = "0 20/30 * * * ?")
	@Scheduled(cron = "0 50 * * * ?")
	public void matchingShipping() {
		try {
			orderService.matchingShipping();
		} catch (Exception e) {
			logger.error("订单匹配物流方式", e);
		}
	}

	
	/**
	 * 订单锁定库存
	 * 将库存状态有2 变为 3
	 */
	@Scheduled(cron = "0 25/30 * * * ?")
	public void inventoryLock() {
		try {
			orderService.inventoryLockNew();
		} catch (Exception e) {
			logger.error("自动锁定库存出错", e);
		}
	}
	
	/**
	 * 自动给卖家发送信息，提醒提交审核资料
	 */
	@Scheduled(cron="0 0 9 * * ?")
	public void autoSendSmsToSellerforApprove() {

		logger.info("任务开始：自动给卖家发送信息...");
		
		try {
			this.sellerService.autoSendSmsToSellerforApprove();
		} catch (Exception e) {
			logger.error("sku销量统计 出错", e);
		}
		
		logger.info("任务结束：自动给卖家发送信息.");
	}
	
	/**
	 * 每天重置table-key表流水号数据
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void reSettingSerialNumber(){
		tableKeyService.dailyDataInit();
	}
	
	/**
	 * 自动生成报价，每30分钟执行一次
	 */
	@Scheduled(fixedRate = 30 * 60 * 1000, initialDelay = 5 * 60 * 1000)
	public void batchAddSupplierPrice() {
		logger.info("[计划任务][生成采购报价单] 开始 ...");
		try {
			this.goodsSupplierPriceService.batchAddSupplierPrice();
		} catch (Exception e) {
			logger.error("[计划任务][生成采购报价单] 出错", e);
		}
		logger.info("[计划任务][生成采购报价单] 结束 .");
	}
	
	/**
	 * 每天生成excel报表：凌晨3点
	 */
	@Scheduled(cron="0 0 3 * * ?")
	public void excelReportDaily() {
		logger.info("[计划任务][生成报表] 开始 ...");
		try {
			this.excelReportService.allSkusInfo();
			this.excelReportService.dayShippedGoods(null);
			this.excelReportService.dayPurchaseInStoreListing(null);
			this.excelReportService.dayManualInStoreListing(null);
		} catch (Exception e) {
			logger.error("[计划任务][生成报表] 出错 .", e);
		}
		logger.info("[计划任务][生成报表] 结束 .");
	}
	
	@Scheduled(fixedRate = 30 * 60 * 1000, initialDelay = 5 * 60 * 1000)
	public void combineInventory() {
		this.goodsInventoryService.combineStoreShelf();
	}
	
	@Scheduled(fixedRate = 10 * 60 * 1000, initialDelay = 1 * 60 * 1000)
	public void statSkuCostAndSalesAccupy() {
		logger.info("[计划任务][SKU最新采购成本] 开始 ...");
		try {
			this.statisticService.statSkuLatestCost();
		} catch (Exception e) {
			logger.error("[计划任务][SKU最新采购成本] 出错 .", e);
		}
		logger.info("[计划任务][SKU最新采购成本]  结束 .");
		
		logger.info("[计划任务][SKU销占数] 开始 ...");
		try {
			this.statisticService.statSkuSalesAccupy();
		} catch (Exception e) {
			logger.error("[计划任务][SKU销占数] 出错 .", e);
		}
		logger.info("[计划任务][SKU销占数]  结束 .");
	}
	
	/**
	 * 代理商返点
	 */
	//@Scheduled(cron="0 0 23 * * ?")
	public void shippedOrderRebate(){
		this.agentConfigureService.shippedOrderRebate();
	}
}
