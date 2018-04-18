package com.xuanfeiyang.erp.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryLockDao;
import com.xuanfeiyang.erp.dao.GoodsPackingMaterialDao;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.dao.OrderItemDao;
import com.xuanfeiyang.erp.dao.OrderLogDao;
import com.xuanfeiyang.erp.dao.OrderPackageDao;
import com.xuanfeiyang.erp.dao.OrderPackageItemDao;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsInventoryLock;
import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderLog;
import com.xuanfeiyang.erp.domain.OrderPackage;
import com.xuanfeiyang.erp.domain.OrderPackageItem;
import com.xuanfeiyang.erp.enums.OrderStatusEnum;
import com.xuanfeiyang.erp.service.GeneratePackageService;

@Service
public class GeneratePackageServiceImpl implements GeneratePackageService{
	private static Logger logger = LoggerFactory.getLogger(GeneratePackageServiceImpl.class);
	
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderItemDao orderItemDao;
	@Resource
	private GoodsInventoryDao goodsInventoryDao;
	@Resource
	private GoodsInventoryLockDao goodsInventoryLockDao;
	@Resource
	private OrderPackageDao orderPackageDao;
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private OrderPackageItemDao orderPackageItemDao;
	@Resource
	private GoodsPackingMaterialDao packingMaterialDao;
	@Resource
	private OrderLogDao orderLogDao;
	
	

	/**
	 * 生成包裹ID 格式 yyyyMMdd00001
	 * 计算包裹id
	 * 规则生成包裹ID
	 * @return- KENNY
	 * OK
	 */
	public String getPackageId() {

		String packageId = "";
		DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String dateStr = fmt.format(new Date());
		Integer dateInt = Integer.parseInt(dateStr);

		OrderPackage op = orderPackageDao.findMaxById();
		if (op == null) {
			packageId = dateStr + "00001";

		} else {

			String id = op.getId();
			String idSub = id.substring(0, 8);
			Integer idInt = Integer.parseInt(idSub);
			if (dateInt > idInt) {

				packageId = dateStr + "00001";

			} else if (dateInt.equals(idInt)) {

				String before = id.substring(0, 8);
				String numStr = id.substring(8, id.length());// 00101

				Integer numInt = Integer.parseInt(numStr); // 101
				Integer addnumInt = numInt + 1;

				Integer numLenght = addnumInt.toString().length();

				Integer lenght = 5 - numLenght;

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < lenght; i++) {
					sb.append("0");
				}

				String mid = sb.toString();
				packageId = before + mid + addnumInt;
			}
		}
		return packageId;
	}
	
	
	/**
	 * 根据sku获得包装规格id
	 * @param sku
	 * @return
	 */
	private GoodsPackingMaterial getPackageMaterial(String sku){
		Goods gs = goodsDao.findBySku(sku);
		if(gs == null){
			return null;
		}else{
			Integer id = gs.getPackingMaterialId();
			return id == null ? null : packingMaterialDao.getById(id);
		}

	}

	
	/**
	 * 得到最大的包装规格
	 * @param array
	 * @return
	 */
	private String  setSpecifications(List<String> array){
		if (array==null || array.size()==0){
			return "";
		}
		Collections.sort(array);
		String maxValue = array.get(array.size()-1);
		return maxValue;
	}

	
	/**
	 * 订单重量
	 * @param items
	 * @return
	 */
	public BigDecimal getOrderWeight(List<OrderItem> items) {
		BigDecimal totalWeight = new BigDecimal(0);
		for (int i = 0; i < items.size(); i++) {
			String sku = items.get(i).getSku();
			if(sku == null){
				logger.info("计算订单重量----sku有空");
				break;
			}
			Goods goods = this.goodsDao.findBySku(sku);
			BigDecimal weight = goods.getWeight();
			if(weight == null){
				continue;
			}
			
			Integer count = items.get(i).getItemQuantity();
			BigDecimal countAmount = weight.multiply(new BigDecimal(count));
			
			totalWeight = totalWeight.add(countAmount);
		}
		return totalWeight;
	}
	
	/**
	 * 包裹重量
	 * @param items
	 * @return
	 */
	public BigDecimal getPackageWeight(List<OrderPackageItem> items) {
		
		BigDecimal totalWeight = new BigDecimal(0);
		for (int i = 0; i < items.size(); i++) {
			String sku = items.get(i).getSku();
			if(sku == null){
				logger.info("计算包裹重量----sku有空");
				break;
			}
			Goods goods = this.goodsDao.findBySku(sku);
			BigDecimal weight = goods.getWeight();
			if(weight == null){
				continue;
			}
			Integer count = items.get(i).getPackageAmount();
			BigDecimal countAmount = weight.multiply(new BigDecimal(count));
			
			totalWeight = totalWeight.add(countAmount);
		}
		return totalWeight;
	}

	/**
	 * 生成包裹，只针对一个订单对象
	 * 
	 * @param order
	 */
	@Transactional(rollbackFor=Exception.class)
	public void generateOneOrder(Order order){
		boolean flag = true;
		List<OrderItem> items = this.orderItemDao.queryByOrder(order.getId());
		Map<String,OrderItem> skuItemsMap = mergeOrderItemsAmount(items);
		//for (int j = 0; j < items.size(); j++) {
		for (OrderItem thisItem:skuItemsMap.values()) {
			//OrderItem thisItem  = items.get(j);
			
			//SKU实际的库存数量
			Integer realCount =  this.goodsInventoryDao.getCountBySku(thisItem.getSku());
			if(realCount == null){
				realCount = 0;
			}
			
			//SKU的锁定数量
			Integer skuLockedCount = this.goodsInventoryLockDao.getLockCountBySku(thisItem.getSku());
			if(skuLockedCount == null){
				skuLockedCount = 0;
			}
			
			
			Integer buyCount = thisItem.getItemQuantity(); //购买数量
			Integer cancelCount = thisItem.getCancelAmount();//取消数量
			if(cancelCount == null){
				cancelCount = 0;
			}
			Integer realBuyCount = buyCount - cancelCount;//真实购买的数量-***
			Integer lockedCount = 0;//已经锁定的数量-***
			
			GoodsInventoryLock lockInfo = this.goodsInventoryLockDao.findByOrderIdAndSku(order.getId(), thisItem.getSku());
			if(lockInfo == null){
				lockedCount = 0;
			}else{
				lockedCount = lockInfo.getLockCount();//已经锁定数量
			}
			
			Integer needLock = realBuyCount - lockedCount; //还需要锁定的数量
			Integer count = realCount - skuLockedCount;//库存数量
			
			if(needLock > count){
				logger.info("【自动生成包裹3】- {}, {} 库存不足. 购买数量 {}, 取消数量 {}, 订单已锁定数量 {}, 所有锁定数量 {}, 实际库存 {}", 
						order.getId(), thisItem.getSku(), buyCount, cancelCount, 
						lockedCount, skuLockedCount, realCount);
				flag = false;
				break;
			}
			
		}
		
		if (flag) {
			Order tempO = new Order();
			tempO.setId(order.getId());
			tempO.setVersion(order.getVersion());
			tempO.setOrderStatus(OrderStatusEnum.ALL_PACKAGE.code().shortValue());
			//this.orderDao.updateStateById(5, order.getId());//修改订单状态
			int result = orderDao.update(tempO);
			logger.debug("generateOneOrder update order");
			if (result>0){
				String pId = this.addPackage(order.getId());//添加包裹头
				this.new_addPackageItem(items, order.getId(), pId);//包裹明细&&锁定库存
				this.addOrderLog(order.getId());//添加日志
				logger.info("【自动生成包裹3】- {} 生成包裹成功", order.getId());
			}
		}
	}
	
	/* 返回合并相同sku订单行的buy数量
	 * 防止多行相同sku订单行时,出现锁定库存不足的sku的bug
	*/
	private Map<String,OrderItem> mergeOrderItemsAmount(List<OrderItem> items){
		Map<String,OrderItem> skuItemsMap  = new HashMap<String,OrderItem>();
		for(OrderItem item :items){
			if (!skuItemsMap.containsKey(item.getSku())){
					try {
						skuItemsMap.put(item.getSku(), (OrderItem)item.clone());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
			}else{
				OrderItem temp = skuItemsMap.get(item.getSku());
				temp.setItemQuantity(temp.getItemQuantity()+item.getItemQuantity());
				skuItemsMap.put(item.getSku(), temp);
			}
		}
		return skuItemsMap;
	}
	
	public void addPackageItem(List<OrderItem> items,Integer orderId,String pId){
		List<String> array = new ArrayList<String>();
		
		//this.goodsInventoryLockDao.deleteBySaleOrderId(orderId);//清除 此 订单 锁定的 sku
		
		for (int i = 0; i < items.size(); i++) {
			OrderItem thisItem = items.get(i);
			
			/*更新库存*/
			GoodsInventoryLock gl = this.goodsInventoryLockDao.findByOrderIdAndSku(orderId, thisItem.getSku());
			if(gl == null){
				GoodsInventoryLock record = new GoodsInventoryLock();
				record.setGoodsSku(thisItem.getSku());
				record.setLockCount(thisItem.getItemQuantity());
				record.setSaleOrderId(orderId);
				record.setLastUpdatedTime(new Date());
				this.goodsInventoryLockDao.insert(record);
			}else{
				GoodsInventoryLock record = new GoodsInventoryLock();
				record.setGoodsSku(thisItem.getSku());
				record.setSaleOrderId(orderId);
				record.setLockCount(thisItem.getItemQuantity()+gl.getLockCount());
				record.setLastUpdatedTime(new Date());
				goodsInventoryLockDao.updateByOrderIdAndSku(record);
			}
			
			/*生成包裹明细*/
			OrderPackageItem pItem = new OrderPackageItem();
			pItem.setPackageId(pId);
			pItem.setOrderId(orderId);
			pItem.setSku(thisItem.getSku());
			pItem.setOrderAmount(thisItem.getItemQuantity());
			pItem.setPackageAmount(thisItem.getItemQuantity());
			pItem.setShipmentAmount(0);
			pItem.setCreatedTime(new Date());
			pItem.setPrice(thisItem.getItemPrice());
			pItem.setTotalPrice(thisItem.getItemPrice().multiply(BigDecimal.valueOf(thisItem.getItemQuantity())));
			GoodsPackingMaterial gpm = this.getPackageMaterial(thisItem.getSku());
			
			pItem.setSpecifications(gpm == null ? null : gpm.getRules());
			this.orderPackageItemDao.insert(pItem);
			
			if (pItem.getSpecifications() != null) {
				array.add(pItem.getSpecifications());
			}
			
			/*回写订单明细信息 */
			OrderItem orderItem = new OrderItem();
			orderItem.setLockAmount(thisItem.getItemQuantity());
			orderItem.setPackageAmount(thisItem.getItemQuantity());
			orderItem.setSku(thisItem.getSku());
			orderItem.setOrderId(orderId);
			orderItemDao.updateBySkuAndOrderId(orderItem);
		}
		
		/*回写包裹头的信息*/
		OrderPackage orderPackage = new OrderPackage();
		orderPackage.setId(pId);
		if(items.size()>1){
			orderPackage.setIsMix(1);//是混合
		}else{
			orderPackage.setIsMix(0);//非混合
		}
		orderPackage.setSpecifications(this.setSpecifications(array));
		orderPackageDao.update(orderPackage);
	}
	
	/***
	 * 
	 * 添加包裹头
	 * @param orderId
	 * @return
	 */
	public String addPackage(Integer orderId){
		
		OrderPackage op = new OrderPackage();
		Order orderInfo = this.orderDao.load(orderId);
		List<OrderItem> orderItem =  orderInfo.getItems();
		
		op.setId(this.getPackageId());//包裹ID
		op.setOrderId(orderId);//订单ID
		op.setPrintedFlag((short) 0);// 否打印
		
		if(orderInfo.getIsSend() == null){
			op.setIsSend((short) 0);
		}else{
			op.setIsSend(orderInfo.getIsSend());
		}
		
		if(orderInfo.getPackageWeight() == null){
			op.setPackageWeight(this.getOrderWeight(orderItem));
		}else{
			op.setPackageWeight(orderInfo.getPackageWeight());
		}
		
		if (orderInfo.getTrackNumber() == null) {
			if (orderInfo.getShippingName() != null) {
				op.setStatus(2);
			} else {
				op.setStatus(1);
			}
		} else {
			op.setStatus(3);
		}
		
		op.setCreatedTime(new Date());
		op.setTrackNumber(orderInfo.getTrackNumber());
		op.setTrackNumberRef(orderInfo.getTrackNumberRef());
		
		op.setIsMix(null);//是否混合---暂时为空
		op.setSpecifications(null);//包装规格--暂时为空
		op.setShippingName(orderInfo.getShippingName());
		
		if (orderInfo.getShippingFee() == null) {
			op.setShippingFee(new BigDecimal(0));// 包裹运费？
		} else {
			
			op.setShippingFee(orderInfo.getShippingFee());// 包裹运费
		}
		
		this.orderPackageDao.insert(op);
		return op.getId();
	}


	@Override
	@Transactional(rollbackFor=Exception.class)
	public void generateOneOrder(Integer orderId){
		Order order = this.orderDao.load(orderId);
		
		if (order == null) {
			logger.info("【生成包裹】 - %s, 订单不存在", orderId);
			return;
		}
		
		this.generateOneOrder(order);
	}
	
	public void addOrderLog(Integer orderId){
		OrderLog ol = new OrderLog();
		ol.setLog("订单修改之前[3],自动改变为"+OrderStatusEnum.ALL_PACKAGE.desc());
		ol.setOperUserId("自动");
		ol.setOperTime(new Date());
		ol.setOrderId(orderId);
		this.orderLogDao.insert(ol);
	}
	
	
	
	public void new_addPackageItem(List<OrderItem> items,Integer orderId,String pId){
		List<String> array = new ArrayList<String>();
		for (int i = 0; i < items.size(); i++) {
			OrderItem thisItem = items.get(i);
			
			/*更新库存*/
			GoodsInventoryLock gl = this.goodsInventoryLockDao.findByOrderIdAndSku(orderId, thisItem.getSku());
			if(gl == null){
				GoodsInventoryLock record = new GoodsInventoryLock();
				record.setGoodsSku(thisItem.getSku());
				record.setLockCount(thisItem.getItemQuantity());
				record.setSaleOrderId(orderId);
				record.setLastUpdatedTime(new Date());
				this.goodsInventoryLockDao.insert(record);
			}else{
				GoodsInventoryLock record = new GoodsInventoryLock();
				record.setGoodsSku(thisItem.getSku());
				record.setSaleOrderId(orderId);
				record.setLockCount(thisItem.getItemQuantity());
//				record.setLockCount(thisItem.getItemQuantity()+gl.getLockCount());   //edit by bernard @10-09
				record.setLastUpdatedTime(new Date());
				goodsInventoryLockDao.updateByOrderIdAndSku(record);
			}
			
			/*生成包裹明细*/
			OrderPackageItem pItem = new OrderPackageItem();
			OrderPackageItem resItemInfo = this.orderPackageItemDao.getPackageItemByParam(pId, orderId, thisItem.getSku());
			if(resItemInfo == null){
				
				pItem.setPackageId(pId);
				pItem.setOrderId(orderId);
				pItem.setSku(thisItem.getSku());
				pItem.setOrderAmount(thisItem.getItemQuantity());
				pItem.setPackageAmount(thisItem.getItemQuantity());
				pItem.setShipmentAmount(0);
				pItem.setCreatedTime(new Date());
				pItem.setPrice(thisItem.getItemPrice());
				pItem.setTotalPrice(thisItem.getItemPrice().multiply(BigDecimal.valueOf(thisItem.getItemQuantity())));
				GoodsPackingMaterial gpm = this.getPackageMaterial(thisItem.getSku());
				pItem.setSpecifications(gpm == null ? null : gpm.getRules());
				this.orderPackageItemDao.insert(pItem);
			}else{
				
				pItem.setId(resItemInfo.getId());
				Integer count1 = resItemInfo.getPackageAmount();//包裹数量
				Integer count2 = count1+thisItem.getItemQuantity();
				pItem.setOrderAmount(count2);
				pItem.setPackageAmount(count2);
				pItem.setTotalPrice(thisItem.getItemPrice().multiply(BigDecimal.valueOf(count2)));
				pItem.setSpecifications(resItemInfo.getSpecifications());
				this.orderPackageItemDao.update(pItem);
			}
			
			if (pItem.getSpecifications() != null) {
				array.add(pItem.getSpecifications());
			}
			
			/*回写订单明细信息 */
			OrderItem orderItem = new OrderItem();
			orderItem.setLockAmount(thisItem.getItemQuantity());
			orderItem.setPackageAmount(thisItem.getItemQuantity());
			orderItem.setSku(thisItem.getSku());
			orderItem.setOrderId(orderId);
			orderItem.setId(thisItem.getId());
			this.orderItemDao.update(orderItem);
			//orderItemDao.updateBySkuAndOrderId(orderItem);
		}
		
		/*回写包裹头的信息*/
		OrderPackage orderPackage = new OrderPackage();
		orderPackage.setId(pId);
		if(items.size()>1){
			orderPackage.setIsMix(1);//是混合
		}else{
			orderPackage.setIsMix(0);//非混合
		}
		orderPackage.setSpecifications(this.setSpecifications(array));
		orderPackageDao.update(orderPackage);
	}
	
	
}
