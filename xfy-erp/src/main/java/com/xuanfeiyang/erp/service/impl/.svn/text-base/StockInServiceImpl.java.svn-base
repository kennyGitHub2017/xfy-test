package com.xuanfeiyang.erp.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.IoOrderItemDao;
import com.xuanfeiyang.erp.dao.PurchaseOrderLogDao;
import com.xuanfeiyang.erp.domain.IoOrder;
import com.xuanfeiyang.erp.domain.IoOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrder;
import com.xuanfeiyang.erp.domain.PurchaseOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderLog;
import com.xuanfeiyang.erp.service.GoodsInventoryService;
import com.xuanfeiyang.erp.service.IoOrderService;
import com.xuanfeiyang.erp.service.PurchaseOrderService;
import com.xuanfeiyang.erp.service.StockInService;
import com.xuanfeiyang.erp.service.TableKeyService;
@Service("stockInService")
public class StockInServiceImpl implements StockInService{
	@Resource
	private IoOrderService ioOrderService;
	@Resource
	private IoOrderItemDao ioOrderItemDao; 
	@Resource
	private PurchaseOrderService purchaseOrderService;
	@Resource
	private GoodsInventoryService goodsInventoryService;
	@Resource
	private PurchaseOrderLogDao purchaseOrderLogDao;
	@Resource
	private TableKeyService tableKeyService; 
	
	private DateFormat dft = new SimpleDateFormat("yyyyMMdd");
	
	//常量 不合格仓库ID
	private final Integer UNQUALIFIED_STORE = App.getConfigInt("unqualified.store.id");
	
	public void setIoOrderService(IoOrderService ioOrderService) {
		this.ioOrderService = ioOrderService;
	}

	public void setPurchaseOrderService(PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}

	public void setGoodsInventoryService(GoodsInventoryService goodsInventoryService) {
		this.goodsInventoryService = goodsInventoryService;
	}

	@Override
	@Transactional
	public void addStock(IoOrder order,
			List<IoOrderItem> orderItems,String operator) {
		boolean exceptionStockIn = false;   //是否异常入库
		Map<String, IoOrderItem> tempMap = new HashMap<String,IoOrderItem>();
		List<IoOrderItem> newItems = new ArrayList<IoOrderItem>();
		for(IoOrderItem item:orderItems){
			newItems.add(item);
			//合格数量及不合格数量均为0,代表未接收 所以不处理
			if (0==item.getQualifiedCount() && item.getUnqualifiedCount()==0){
				continue;
			}
			tempMap.put(String.valueOf(item.getItemId()), item);
			/*
			 * 增加合格/不合格库存
			if (item.getQualifiedCount()>0){
				goodsInventoryService.increase(item.getGoodsSku(), item.getStoreId(), item.getStoreShelfId(), item.getQualifiedCount());
			}
			if (item.getUnqualifiedCount()>0){
				goodsInventoryService.increase(item.getGoodsSku(), UNQUALIFIED_STORE, item.getUnqualifiedShelfId(), item.getQualifiedCount());
			}
			*/
		}
		order.setType(0);
		order.setAuditStatus(1);
		order.setIoDate(new Date());
		String dateStr = dft.format(Calendar.getInstance().getTime());
		String fmt =String.format("LS-%s-",dateStr)+"%d";
		order.setSerialNumber(tableKeyService.nextSerialNumber("i_orders",fmt));    //设置入库单流水号
		order.setAuditTime(new Date());
		order.setTypeDetail(56);			//设置出入库小类型：采购入库
		order.setItems(newItems);
		ioOrderService.save(order);
	//	ioOrderService.addOrder(order, orderItems);
		PurchaseOrder purchaseOrder = purchaseOrderService.findByOrderNo(order.getBuyOrderNo());
		//回写采购单子表  合格/不合格/已入库数量
		List<PurchaseOrderItem> items = purchaseOrderService.findItem(purchaseOrder.getId());
		List<PurchaseOrderItem> updateItem = new ArrayList<PurchaseOrderItem>();
		for(PurchaseOrderItem item:items){
			IoOrderItem  ioitem = tempMap.get(String.valueOf(item.getId()));
			if (ioitem==null){
				continue;
			}
			item.setQualifiedCount(item.getQualifiedCount()+ioitem.getQualifiedCount());
			item.setUnQualifiedCount(item.getUnQualifiedCount()+ioitem.getUnqualifiedCount());
			item.setReceivedCount(item.getReceivedCount()+ioitem.getReceivedCount());
			updateItem.add(item);
		}
		purchaseOrderService.editItem(updateItem);
		//回写采购单的入库单号
		purchaseOrder.setIoOrderNo(order.getOrderNo());
		
		for(PurchaseOrderItem item:items){
			//sku异常 入库 (即采购数量!=合格数量)
			exceptionStockIn = exceptionStockIn || !item.getGoodsCount().equals(item.getQualifiedCount());
		}
		
		//记录采购单生成日志
		int oldStatus = purchaseOrder.getStatus();
		String oldStatusName = "";
		switch(oldStatus){
			case -1:oldStatusName="异常入库";break;
			case 1: oldStatusName="待审核";break;
			case 2: oldStatusName="审核未接收";break;
			case 5: oldStatusName="审核已接收";break;
		}
		PurchaseOrderLog log = new PurchaseOrderLog();
		log.setOrderSn(purchaseOrder.getOrderNo());
		log.setCreatedTime(new Date());
		log.setOperUser(operator);
		log.setOldStatus((short)oldStatus);
		if (exceptionStockIn){
			log.setNewStatus((short)-1);
			log.setContent("采购单变更状态:" +oldStatusName +"到导常入库");
			purchaseOrder.setStatus((short)-1);
		}else{														//所有子项都已完成入库,则更新对应采购单状态 为[3:正常关闭]
			log.setNewStatus((short)3);
			log.setContent("采购单变更状态:" +oldStatusName +"到正常关闭");
			purchaseOrder.setStatus((short)3);
		}
		purchaseOrderLogDao.insert(log);
		//end
		purchaseOrderService.edit(purchaseOrder, null);
		
	}

	@Override
	public void editStock(IoOrder order,List<IoOrderItem> orderItems,String operator) {
		boolean stockinAll = true;
		Map<String, IoOrderItem> tempMap = new HashMap<String,IoOrderItem>();
		for(IoOrderItem item:orderItems){
			if (!item.getRemainCount().equals(item.getReceivedCount())){
				stockinAll = false;
			}
			//合格数量及不合格数量均为0,代表未接收 所以不处理
			if (0==item.getQualifiedCount() && item.getUnqualifiedCount()==0){
				continue;
			}
			tempMap.put(item.getGoodsSku(), item);
			//增加合格库存
			if (item.getQualifiedCount()>0){
				goodsInventoryService.increase(item.getGoodsSku(), item.getStoreId(), item.getStoreShelfId(), item.getQualifiedCount());
			}
			//增加不合格库存
			if (item.getUnqualifiedCount()>0){
				goodsInventoryService.increase(item.getGoodsSku(), UNQUALIFIED_STORE, item.getUnqualifiedShelfId(), item.getQualifiedCount());
			}
			if (null==item.getOrderNo() || "".equals(item.getOrderNo())){
				item.setOrderNo(order.getOrderNo());
			}
			ioOrderService.addOrderItem(item);
		}
		PurchaseOrder purchaseOrder = purchaseOrderService.findByOrderNo(order.getBuyOrderNo());
		//所有子项都已完成入库,则更新对应采购单状态 为[3:正常关闭]
		if (stockinAll){
			//记录采购单生成日志
			PurchaseOrderLog log = new PurchaseOrderLog();
			log.setOrderSn(purchaseOrder.getOrderNo());
			log.setCreatedTime(new Date());
			log.setOldStatus(purchaseOrder.getStatus());
			log.setNewStatus((short)3);
			log.setContent("采购单变更状态:正常关闭");
			log.setOperUser(operator);
			purchaseOrderLogDao.insert(log);
			purchaseOrder.setStatus((short)3);
		}
		purchaseOrderService.edit(purchaseOrder, null);

		//回写采购单子表  合格/不合格/已入库数量
		List<PurchaseOrderItem> items = purchaseOrderService.findItem(purchaseOrder.getId());
		List<PurchaseOrderItem> updateItem = new ArrayList<PurchaseOrderItem>();
		for(PurchaseOrderItem item:items){
			IoOrderItem  ioitem = tempMap.get(item.getGoodsSku());
			if (ioitem==null){
				continue;
			}
			item.setQualifiedCount(item.getQualifiedCount()+ioitem.getQualifiedCount());
			item.setUnQualifiedCount(item.getUnQualifiedCount()+ioitem.getUnqualifiedCount());
			item.setReceivedCount(item.getReceivedCount()+ioitem.getReceivedCount());
			updateItem.add(item);
		}
		purchaseOrderService.editItem(updateItem);
	}

	@Override
	public BigDecimal getLatestGoodsWeightBySku(String sku) {
		return ioOrderItemDao.getLatestGoodsWeightBySku(sku);
	}
}
