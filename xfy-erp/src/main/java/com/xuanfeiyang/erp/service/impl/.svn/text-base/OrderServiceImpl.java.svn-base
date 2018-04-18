package com.xuanfeiyang.erp.service.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryLockDao;
import com.xuanfeiyang.erp.dao.GoodsPackingMaterialDao;
import com.xuanfeiyang.erp.dao.IoOrderItemDao;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.dao.OrderItemDao;
import com.xuanfeiyang.erp.dao.OrderLogDao;
import com.xuanfeiyang.erp.dao.OrderPackageDao;
import com.xuanfeiyang.erp.dao.OrderPackageItemDao;
import com.xuanfeiyang.erp.dao.OrderShippingFeeDao;
import com.xuanfeiyang.erp.dao.SellerDepositDao;
import com.xuanfeiyang.erp.dao.SellerFeeWithHoldDao;
import com.xuanfeiyang.erp.dao.ShippingDao;
import com.xuanfeiyang.erp.dao.ShippingFee2Dao;
import com.xuanfeiyang.erp.dao.ShippingFeeDao;
import com.xuanfeiyang.erp.dao.TrackNumberDao;
import com.xuanfeiyang.erp.domain.ExportOrder;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsInventoryLock;
import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderLog;
import com.xuanfeiyang.erp.domain.OrderPackage;
import com.xuanfeiyang.erp.domain.OrderPackageItem;
import com.xuanfeiyang.erp.domain.OrderRefund;
import com.xuanfeiyang.erp.domain.OrderShippingFee;
import com.xuanfeiyang.erp.domain.OrderStatistic;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SellerFeeWithHold;
import com.xuanfeiyang.erp.domain.Shipping;
import com.xuanfeiyang.erp.domain.ShippingFee;
import com.xuanfeiyang.erp.domain.ShippingFee2;
import com.xuanfeiyang.erp.domain.StockOutSku;
import com.xuanfeiyang.erp.domain.TrackNumber;
import com.xuanfeiyang.erp.enums.OrderPackageStatus;
import com.xuanfeiyang.erp.enums.OrderStatusEnum;
import com.xuanfeiyang.erp.param.OrderParam;
import com.xuanfeiyang.erp.param.SellerParams;
import com.xuanfeiyang.erp.service.CurrencyRatesService;
import com.xuanfeiyang.erp.service.FileImportException;
import com.xuanfeiyang.erp.service.GeneratePackageService;
import com.xuanfeiyang.erp.service.OrderItemService;
import com.xuanfeiyang.erp.service.OrderService;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.service.PurchaseOrderService;
import com.xuanfeiyang.erp.service.TrackerApplyResult;
import com.xuanfeiyang.erp.util.ExcelParser;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	private static Logger logger = LoggerFactory
			.getLogger(OrderServiceImpl.class);
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderItemDao orderItemDao;
	@Resource
	private OrderLogDao orderLogDao;
	@Resource
	private GoodsInventoryLockDao goodsInventoryLockDao;
	@Resource
	private GoodsInventoryDao goodsInventoryDao;
	@Resource
	private PurchaseOrderService purchaseOrderService;
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private OrderPackageDao orderPackageDao;
	@Resource
	private OrderPackageItemDao orderPackageItemDao;
	@Resource
	private ShippingDao shippingDao;
	@Resource
	private ShippingFeeDao shippingFeeDao;
	@Resource
	private OrderShippingFeeDao orderShippingFeeDao;
	@Resource
	private CurrencyRatesService currencyRatesService;
	@Resource
	private TrackNumberDao trackNumberDao;
	@Resource
	private OrderItemService orderItemService;
	@Resource
	private GoodsPackingMaterialDao packingMaterialDao;
	@Resource
	private IoOrderItemDao ioOrderItemDao; 
	@Resource
	private PlatformAccountService platformAccountService;
	@Resource
	private SellerDepositDao sellerDepositDao;
	@Resource	
	private SellerFeeWithHoldDao sellerFeeWithHoldDao;
	@Resource
	private OrderSyncService orderSyncService;
	@Resource
	private GeneratePackageService generatePackageService;
	@Resource
	private ShippingFee2Dao shippingFee2Dao;
	
	private static AtomicBoolean isRunning = new AtomicBoolean(false);

	public void setOrderPackageDao(OrderPackageDao orderPackageDao) {
		this.orderPackageDao = orderPackageDao;
	}

	public void setOrderPackageItemDao(OrderPackageItemDao orderPackageItemDao) {
		this.orderPackageItemDao = orderPackageItemDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setGoodsInventoryDao(GoodsInventoryDao goodsInventoryDao) {
		this.goodsInventoryDao = goodsInventoryDao;
	}

	public void setGoodsInventoryLockDao(
			GoodsInventoryLockDao goodsInventoryLockDao) {
		this.goodsInventoryLockDao = goodsInventoryLockDao;
	}

	public void setOrderLogDao(OrderLogDao orderLogDao) {
		this.orderLogDao = orderLogDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}
	

	@Override
	public void updateStateById(Integer status, Integer id) {
		this.orderDao.updateStateById(status, id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		if (null == id) {
			return;
		}
		orderItemDao.deleteByOrder(id);
		orderDao.delete(id);
	}

	@Override
	@Transactional
	public int insert(Order record) {
		orderDao.insert(record);
		Integer orderId = record.getId();
		if (record.getItems() != null && record.getItems().size() > 0) {
			for (OrderItem item : record.getItems()) {
				item.setOrderId(orderId);
				orderItemDao.insert(item);
			}
		}
		if (StringUtils.isEmpty(record.getBuyinfo().getOrderSn())){
			record.getBuyinfo().setOrderSn(record.getOrderSn());
		}
		if (StringUtils.isEmpty(record.getBuyinfo().getOrderPlatform())){
			record.getBuyinfo().setOrderPlatform(record.getOrderPlatform());
		}
		orderDao.addOrderBuyerInfo(record.getBuyinfo());
		return orderId;
	}

	@Override
	public Order load(Integer id) {
		return orderDao.load(id);
	}

	@Override
	@Transactional
	public Integer copy(Integer originalId, String operUser) {
		Order o = orderDao.load(originalId);
		
		List<OrderItem> items = o.getItems();
		checkArgument(items != null && items.size() > 0, "该订单缺少订单行");
		
		OrderBuyerInfo buyerInfo = o.getBuyinfo();
		checkArgument(buyerInfo != null, "该订单缺少买家地址信息");
		
		// 复制订单信息
		Order newOrder = this.copyOrderInfo(o);
		// 复制订单行信息
		this.copyOrderItems(newOrder, items);
		// 复制卖家信息
		this.copyOrderBuyerInfo(newOrder, buyerInfo);
		
		// 复制订单的日志
		OrderLog log = new OrderLog();
		log.setOrderId(newOrder.getId());
		log.setLog("复制的的订单,来源: " + originalId);
		log.setOperUserId(operUser);
		log.setOperTime(new Date());
		
		this.orderLogDao.insert(log);
		
		return newOrder.getId();
	}

	private void copyOrderBuyerInfo(Order newOrder, OrderBuyerInfo buyerInfo) {
		OrderBuyerInfo tmp = new OrderBuyerInfo();
		tmp.setOrderPlatform(buyerInfo.getOrderPlatform());
		tmp.setOrderSn(newOrder.getOrderSn());
		tmp.setBuyerUserId(buyerInfo.getBuyerUserId());
		tmp.setBuyerEmail(buyerInfo.getBuyerEmail());
		tmp.setShippingName(buyerInfo.getShippingName());
		tmp.setShippingStreet1(buyerInfo.getShippingStreet1());
		tmp.setShippingStreet2(buyerInfo.getShippingStreet2());
		tmp.setShippingCity(buyerInfo.getShippingCity());
		tmp.setShippingState(buyerInfo.getShippingState());
		tmp.setShippingCountry(buyerInfo.getShippingCountry());
		tmp.setShippingCountryName(buyerInfo.getShippingCountryName());
		tmp.setShippingPostcode(buyerInfo.getShippingPostcode());
		tmp.setShippingPhone(buyerInfo.getShippingPhone());
		tmp.setShippingMobile(buyerInfo.getShippingMobile());
		
		this.orderDao.addOrderBuyerInfo(tmp);
	}

	private void copyOrderItems(Order newOrder, List<OrderItem> items) {

		Date now = new Date();
		for (OrderItem item : items) {
			OrderItem tmp = new OrderItem();
			tmp.setOrderId(newOrder.getId());
			tmp.setOrderSn(newOrder.getOrderSn());
			tmp.setItemId(item.getItemId());
			tmp.setItemTitle(item.getItemTitle());
			tmp.setItemPic(item.getItemPic());
			tmp.setItemUrl(item.getItemUrl());
			tmp.setSku(item.getSku());
			tmp.setItemSku(item.getItemSku());
			tmp.setItemPrice(item.getItemPrice());
			tmp.setItemQuantity(item.getItemQuantity());
			tmp.setCreatedTime(now);
			tmp.setOldSku(item.getOldSku());
		//	tmp.setEbayFee(item.getEbayFee());						//平台费用
			tmp.setOrderLimitId(item.getOrderLimitId());			//OrderLimitId
			tmp.setPurchasePrice(item.getPurchasePrice());			//卖家sku成本
		//	tmp.setPlatShipfee(item.getPlatShipfee());             //平台运费
			tmp.setPurchasePrice(item.getPurchasePrice());
			this.orderItemDao.insert(tmp);
		}
		
	}

	private Order copyOrderInfo(Order o) {
		Order tmp = new Order();
		tmp.setOrderPlatform(o.getOrderPlatform());
		tmp.setOrderSn("copy_" + o.getOrderSn() + "_" + DateFormatUtils.format(new Date(), "MMddHHmmssS"));
		tmp.setPayStatus(o.getPayStatus());
		tmp.setOrderSaleTime(o.getOrderSaleTime());
		tmp.setOrderPaidTime(o.getOrderPaidTime());
		tmp.setOrderType(o.getOrderType());
		tmp.setOrderStatus(OrderStatusEnum.NOAUDIT.code().shortValue()); // 未审核
		tmp.setCurrency(o.getCurrency());
		tmp.setAmount(o.getAmount());
		tmp.setIsSend((short) 0);
		tmp.setPrintedFlag((short) 0);
		tmp.setAccountId(o.getAccountId());
		tmp.setSite(o.getSite());
		tmp.setSuspend(0);
		tmp.setCombine((short) 0);
		tmp.setReissuedFlag((short)1);			//设置为补发订单
		tmp.setExchangeRate(o.getExchangeRate());	//订单币种兑换率(RMB)
		tmp.setMixedFlag(o.getMixedFlag());     //是否混合订单
		tmp.setPlatShippinMethod(o.getPlatShippinMethod());  //平台发货方式
		tmp.setPlatShippingFee(o.getPlatShippingFee());      //平台运费
		tmp.setBuyerNote(o.getBuyerNote());                  //买家留言
	//	tmp.setPaypalFee(o.getPaypalFee());                  //paypal费用
		tmp.setPackingMaterialFee(o.getPackingMaterialFee());
		Date now = new Date();
		tmp.setCreatedTime(now);
		tmp.setLastUpdatedTime(now);
		
		this.orderDao.insert(tmp);
		
		return tmp;
	}

	@Override
	@Transactional
	public void update(Order record) {
		Integer orderId = record.getId();
		if (null == orderId) {
			return;
		}
		orderItemDao.deleteByOrder(orderId);
		if (record.getItems() != null && record.getItems().size() > 0) {
			for (OrderItem item : record.getItems()) {
				item.setOrderId(orderId);
				orderItemDao.insert(item);
			}
		}
		// 防止更新出错. Added by Adam.
		if (record.getBuyinfo() != null) {
			orderDao.updateOrderBuyerInfo(record.getBuyinfo());
		}

		orderDao.update(record);
	}

	@Override
	public void traceNumberImport(List<Order> orders) {
		if (null == orders || orders.size() < 1) {
			return;
		}
		orderDao.traceNumberImport(orders);
	}

	@Override
	public void batchEditOrder(String[] columnAry, String[] columnValueAry,
			Integer[] id) {
		if (columnAry == null || columnValueAry == null
				|| columnAry.length == 0 || columnValueAry.length == 0
				|| columnAry.length != columnValueAry.length) {
			return;
		}
		Map<String, String> cvMap = new HashMap<String, String>();
		for (int i = 0; i < columnAry.length; i++) {
			cvMap.put(columnAry[i], columnValueAry[i]);
		}
		orderDao.batchUpdateOrder(cvMap, id);
	}

	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public List<Order> find(OrderParam param) {
		return orderDao.find(param);
	}

	@Override
	public List<Order> findMergeOrder(Integer userId) {
		return orderDao.findmergeOrder(userId);
	}

	@Override
	public Page<Order> findPage(PageRequest pageRequest, OrderParam param) {
		if ("c.sku".equals(param.getSearchColumn()) && StringUtils.isNotBlank(param.getSearchValue().trim())){
				param.setSkuList(Arrays.asList(param.getSearchValue().split(",")));
		}
		return orderDao.findPage(pageRequest, param);
	}
	

	@Override
	public Page<Order> findEbabyTwoSendPage(PageRequest pageRequest,
			OrderParam param) {
		return orderDao.findEbabyTwoSendPage(pageRequest, param);
	}

	@Override
	public void addOrderBuyerInfo(OrderBuyerInfo buyinfo) {
		orderDao.addOrderBuyerInfo(buyinfo);
	}

	@Override
	public void updateOrderBuyerInfo(OrderBuyerInfo buyinfo) {
		orderDao.updateOrderBuyerInfo(buyinfo);
	}

	@Override
	public OrderBuyerInfo getBuyInfoByOrderId(Integer orderId) {
		return orderDao.getBuyInfoByOrderId(orderId);
	}
	
	

	@Override
	@Transactional
	public void lockOrderInventory(Integer [] ids){
		for(Integer id:ids){
			boolean allSkuLock = true; // 所有行锁定
			Order order = orderDao.load(id);
			Map<String,OrderItem> skuItemsMap = mergeOrderItemsAmount(order.getItems());
			Integer skuAmountLockBefore = null;
			//锁sku库存并发控制
			synchronized (this) {
				for (OrderItem item : skuItemsMap.values()) {
					// sku总库存数量
					Integer skuCount = goodsInventoryDao.getCountBySku(item.getSku());
					Integer skuLocked = goodsInventoryLockDao.getLockCountBySku(item.getSku()); // sku已锁定数
					skuCount = (skuCount == null ? 0 : skuCount) - (skuLocked == null ? 0 : skuLocked); // 实际库存数-sku已锁定数
					skuCount = skuCount<0?0:skuCount;
					skuAmountLockBefore = skuCount;
					allSkuLock = allSkuLock &&  (item.getItemQuantity() <= skuCount);
				}
			
				if (allSkuLock) {
					for (OrderItem item : order.getItems()){
						GoodsInventoryLock skulock = goodsInventoryLockDao.findByOrderIdAndSku(order.getId(), item.getSku());
							//新增锁定库存或更新库存
							if (skulock==null || skulock.getId()==null){
								skulock = new GoodsInventoryLock();
								skulock.setGoodsSku(item.getSku());
								skulock.setLastUpdatedTime(new Date());
								skulock.setLockCount(item.getItemQuantity());
								skulock.setSaleOrderId(item.getOrderId());
								goodsInventoryLockDao.insert(skulock);
							}else{
								GoodsInventoryLock temp = new GoodsInventoryLock();
								temp.setId(skulock.getId());
								temp.setLockCount(skulock.getLockCount()+item.getItemQuantity());
								temp.setLastUpdatedTime(new Date());
								goodsInventoryLockDao.update(temp);
							}
							// 回写订单行的锁定数量
							item.setLockAmount(item.getItemQuantity());
							item.setAmountBeforelock(skuAmountLockBefore);
							orderItemDao.update(item);
					}
					orderDao.updateStateById(OrderStatusEnum.LOCKED.code(), id); // 订单状态:
				}
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
	

	@Override
	@Transactional(rollbackFor= RuntimeException.class)
	public void audit(Integer id, String auditUserId) throws RuntimeException {
		
		Order order = orderDao.load(id);
		if (null==order){
			throw new RuntimeException("该订单不存在");
		}
		//不处理暂停订单
		if (order.getSuspend()==1) {
					throw new RuntimeException("该订单已暂停");
		}
		// 只处理未审核订单
		if (order.getOrderStatus() != 1 || order.getItems() == null
				|| order.getItems().size() == 0) {
			throw new RuntimeException("该订单已审核或订单无sku行");
		}
		if (StringUtils.isEmpty(order.getBuyinfo().getShippingPhone()) && order.getAmount().doubleValue()>=200){
			throw new RuntimeException("该订单无电话信息，请确认后审核。");
		}
		Boolean skuValid = true; // sku均可以匹配
		for (OrderItem item : order.getItems()) {
			if (StringUtils.isEmpty(item.getSku())) {
				skuValid = false;
				break;
			}
			Goods goods = goodsDao.findBySku(item.getSku());
			if (null == goods) {
				skuValid = false;
				break;
			}
		}
		// 订单有sku无法匹配,订单不予处理
		if (!skuValid) {
			throw new RuntimeException("该订单有sku行无法匹配");
		}
		// 订单无买家国家信息
		if (order.getBuyinfo()==null || StringUtils.isEmpty(order.getBuyinfo().getShippingCountryName())
				&& StringUtils.isEmpty(order.getBuyinfo().getShippingCountry())) {
			throw new RuntimeException("该订单无买家国家信息");
		}
		Map<String,OrderItem> skuItemsMap = mergeOrderItemsAmount(order.getItems());
		boolean allSkuLock = true; // 所有行锁定
		for (OrderItem item : skuItemsMap.values()) {
			//设置item的成本
			BigDecimal newCost = ioOrderItemDao.getLatestGoodsCostBySku(item.getSku());
			Goods goods  = goodsDao.findBySku(item.getSku());
			BigDecimal price = (newCost==null)?goods.getCost():newCost;
			price = price.multiply(BigDecimal.valueOf(1.1));
			price = price.setScale(1,BigDecimal.ROUND_HALF_UP);
			OrderItem temp = new OrderItem();
			temp.setId(item.getId());
			temp.setPurchasePrice(price);
			temp.setItemWeight(goods.getWeight());
			temp.setPackingMaterialFee(ioOrderItemDao.getLatestGoodsPackingMaterialFeeBySku(item.getSku()));		//设置sku包装费
			orderItemDao.update(temp);
			
			// sku总库存数量
			synchronized (this) {
				Integer skuCount = goodsInventoryDao.getCountBySku(item.getSku());
				Integer skuLocked = goodsInventoryLockDao.getLockCountBySku(item.getSku()); // sku已锁定数
				skuCount = (skuCount == null ? 0 : skuCount) - (skuLocked == null ? 0 : skuLocked); // 实际库存数-sku已锁定数
				skuCount = skuCount<0?0:skuCount;
				allSkuLock = allSkuLock &&  (item.getItemQuantity() <= skuCount);
			}
		}
		// 设置订单成本=Sum(每个订单行的成本),每个订单行成本=sku购买数量*sku成本
		orderDao.calculateOrderCost(id);
		//设置订单重量
		this.orderWeight(id);
		// 设置订单包装费
		orderDao.setOrderPackingMaterialFee(id);
		if (!withholdSellerFee(id)){
					throw new RuntimeException("您的余额不足以支付现有订单费用，为了不影响您订单的发出，请您尽快充值！");
		}
		StringBuffer logtxt = new StringBuffer("订单修改之前状态[未审核]");
		if (allSkuLock){
			lockOrderInventory(new Integer[]{id});			//锁定库存
			logtxt.append(",修改之后的状态[" + OrderStatusEnum.LOCKED.desc() + "]");
		} else {
			orderDao.updateStateById(OrderStatusEnum.AUDITED.code(), id); // 订单状态:待锁定
			logtxt.append(",修改之后的状态[" + OrderStatusEnum.AUDITED.desc() + "]");
		}
		OrderLog log = new OrderLog();
		log.setOrderId(id);
		log.setOperUserId(auditUserId);
		log.setLog(logtxt.toString());
		log.setOperTime(new Date());
		orderLogDao.insert(log);
	}
	
	/**
	 * 预扣卖家费用
	 * @return
	 */
	private boolean withholdSellerFee(Integer orderId){
		Order order = orderDao.load(orderId);
		/*
		BigDecimal  orderCost = order.getCost();
		BigDecimal packageFee = order.getPackingMaterialFee() != null ? order.getPackingMaterialFee() : new BigDecimal(0);
		BigDecimal goodsCostSum = orderCost.add(packageFee).setScale(2, RoundingMode.CEILING);
		goodsCostSum = goodsCostSum.setScale(2, RoundingMode.CEILING);
		BigDecimal handleFee = new BigDecimal(2).setScale(2, RoundingMode.CEILING);; 
		BigDecimal shippingFee = (order.getPackageShipfee() != null ? order.getPackageShipfee() : 
			(order.getShippingFee() != null ? order.getShippingFee() : new BigDecimal(0))).setScale(2, RoundingMode.CEILING);

		BigDecimal sellerFee = goodsCostSum.add(handleFee).add(shippingFee).setScale(2, RoundingMode.CEILING);
		PlatformAccount account = this.platformAccountService.loadById(order.getAccountId());
		// 默认卖家不处理费用
		if (account.getSellerId() == App.DEFAULT_SELLER_ID) {
					return true;
		}
		//卖家预扣费用 = 订单成本*1.8
		sellerFee = sellerFee.multiply(BigDecimal.valueOf(1.8)).setScale(2, RoundingMode.CEILING);
		*/
		PlatformAccount account = this.platformAccountService.loadById(order.getAccountId());
		// 默认卖家不处理费用
		if (account.getSellerId() == App.DEFAULT_SELLER_ID) {
					return true;
		}
		BigDecimal  sellerFee = order.getCost();				//订单成本
		sellerFee = sellerFee.add(BigDecimal.valueOf(2));		//订单处理费2元
		if (order.getCalcWeight()!=null){
			BigDecimal weigthShippFee =  order.getCalcWeight().multiply(BigDecimal.valueOf(134)).setScale(2, RoundingMode.CEILING);  //产品总重量*运费均价（134/公斤）。
			weigthShippFee  = weigthShippFee.multiply(BigDecimal.valueOf(1.2)).setScale(2, RoundingMode.CEILING);			// 产品总重量*运费均价（134/公斤）*1.2
			sellerFee = sellerFee.add(weigthShippFee);			//=订单成本+产品总重量*运费均价（134/公斤）*1.2+处理费2元
		}
		SellerDeposit sd = this.sellerDepositDao.load(account.getSellerId());
		//当订单审核后重新修改sku或sku数量,订单还原至未审核 清空订单之前锁定的预扣数据
		sellerFeeWithHoldDao.deletedByOrderId(orderId);
		BigDecimal withHoldFeeTotal = sellerFeeWithHoldDao.getWitholdFeeTotalBySeller(account.getSellerId());			//审核订单已预扣费用合计
		withHoldFeeTotal = withHoldFeeTotal==null?BigDecimal.valueOf(0):withHoldFeeTotal;
		//卖家预扣费用不足
		if (null==sd || null==sd.getDeposit() || sd.getDeposit().compareTo(sellerFee.add(withHoldFeeTotal))<0){
			return false;
		}else{
			SellerFeeWithHold sfd = new SellerFeeWithHold();
			sfd.setOrderId(orderId);
			sfd.setSellerId(account.getSellerId());
			sfd.setSellerFee(sd.getDeposit());
			sfd.setWithholdFee(sellerFee);
			sfd.setCreatedTime(new Date());
			sellerFeeWithHoldDao.insert(sfd);
			return true;
		}
	}


	@Override
	@Transactional
	public String merge(List<Order> orderList,String curUser) throws Exception {
		List<Order> mergenewList = new ArrayList<Order>();
		Map<Order, StringBuffer> msgMap = new HashMap<>();
		Map<String, List<Order>> mergedOrdersMap = new HashMap<>();
		try {
			for (int i = 0; i < orderList.size() - 1; i++) {
				Order o1 = orderList.get(i);
				// 如果订单已做合并标识则不处理
				if (o1.isMerge()) {
					continue;
				}
				for (int j = i + 1; j < orderList.size(); j++) {
					Order o2 = orderList.get(j);
					// 如果订单已做合并标识则不处理
					if (o2.isMerge()) {
						continue;
					}
					// 订单合并条件
					if (o1.getBuyinfo().getBuyerUserId()
							.equals(o2.getBuyinfo().getBuyerUserId())
							&& o1.getAccountId().equals(o2.getAccountId())
							&& StringUtils.isNotBlank(o1.getBuyinfo()
									.getShippingStreet1())
							&& StringUtils.isNotBlank(o2.getBuyinfo()
									.getShippingStreet1())
							&& o1.getBuyinfo()
									.getShippingStreet1().replaceAll(" +","")
									.equals(o2.getBuyinfo()
											.getShippingStreet1().replaceAll(" +",""))
							&& StringUtils.isNotBlank(o1.getBuyinfo()
									.getShippingCity())
							&& StringUtils.isNotBlank(o2.getBuyinfo()
									.getShippingCity())
							&& o1.getBuyinfo().getShippingCity()
									.equals(o2.getBuyinfo().getShippingCity())
							&& StringUtils.isNotBlank(o1.getBuyinfo()
									.getShippingState())
							&& StringUtils.isNotBlank(o2.getBuyinfo()
									.getShippingState())
							&& o1.getBuyinfo().getShippingState()
									.equals(o2.getBuyinfo().getShippingState())) {
						String buyeruserid = o1.getBuyinfo().getBuyerUserId();
						Order temporder1 = new Order(o1.getId(),
								o1.getOrderSn(), o1.getAmount(), o1.getCost());
						temporder1.setItems(o1.getItems());
						Order temporder2 = new Order(o2.getId(),
								o2.getOrderSn(), o2.getAmount(), o2.getCost());
						temporder2.setItems(o2.getItems());
						if (!mergedOrdersMap.containsKey(buyeruserid)) {
							List<Order> tempList = new ArrayList<>();
							tempList.add(o1);
							tempList.add(temporder1);
							tempList.add(temporder2);
							o1.setMerge(true); // 设置订单已合并,避免被重复合并
							o2.setMerge(true);
							mergedOrdersMap.put(buyeruserid, tempList);
						} else {
							o2.setMerge(true);
							mergedOrdersMap.get(buyeruserid).add(temporder2);
						}
					}
				}
			}

			// 增加新增合并后的订单到集合中
			for (Map.Entry<String, List<Order>> entry : mergedOrdersMap
					.entrySet()) {
				Order neworder = (Order) entry.getValue().get(0).clone();
				neworder.setItems(new ArrayList<OrderItem>());
				StringBuffer combineorderIds = new StringBuffer();
				StringBuffer combineorderSns = new StringBuffer();
				Float totalMount = 0f, totalCost = 0f,paypalCost=0f;
				List<Order> list = entry.getValue();
				StringBuffer tempStr = new StringBuffer();
				tempStr.append("订单[");
				BigDecimal exChangeRate = null;
				for (int i = 1; i < list.size(); i++) {
					Order order = list.get(i);
					neworder.getItems().addAll(order.getItems());
					// 更新已合并订单的合并标识字段
					order.setCombine((short) 1);
					orderDao.update(order);
					totalMount += order.getAmount() == null ? 0 : order
							.getAmount().floatValue();
					totalCost += order.getCost() == null ? 0 : order.getCost()
							.floatValue();
					paypalCost += order.getPaypalFee() == null ? 0 : order.getPaypalFee()
							.floatValue();
					combineorderIds.append(order.getId()).append(",");
					combineorderSns.append(order.getOrderSn()).append(",");
					tempStr.append(order.getId() + ",");
					exChangeRate = order.getExchangeRate()!=null?order.getExchangeRate():null;
				}
				tempStr = tempStr.deleteCharAt(tempStr.length() - 1);
				tempStr = tempStr.append("]");
				neworder.setAmount(BigDecimal.valueOf(totalMount));
				neworder.setCost(BigDecimal.valueOf(totalCost));
				neworder.setPaypalFee(BigDecimal.valueOf(paypalCost));
				if (exChangeRate!=null){
					neworder.setExchangeRate(exChangeRate);
				}
				neworder.setCombineOrders(combineorderIds.toString());
				neworder.setOrderSn(combineorderSns.substring(0,
						combineorderSns.length() - 1));
				mergenewList.add(neworder);
				msgMap.put(neworder, tempStr);
			}

			// 新插合并的订单头及订单行
			for (Order order : mergenewList) {
				order.setId(null);
				// 去掉最后的,
				order.getBuyinfo().setOrderSn(order.getOrderSn());
				// 保存合并后的记录 begin
				orderDao.insert(order);
				Integer orderId = order.getId();
				order.setItems(order.getItems());
				if (order.getItems() != null && order.getItems().size() > 0) {
					for (OrderItem item : order.getItems()) {
						item.setOrderId(orderId);
						orderItemDao.insert(item);
					}
				}
				orderDao.addOrderBuyerInfo(order.getBuyinfo());
				orderDao.mixedOrder(orderId);				//更新是否混合订单标志
				// end
				msgMap.get(order).append("合并后的新订单id:" + orderId);
			}
		} catch (Exception e) {
			throw e;
		}
		StringBuffer sbf = new StringBuffer();
		for (Entry<Order, StringBuffer> entry : msgMap.entrySet()) {
			sbf.append(entry.getValue() + "<br>");
			//保存订单合并日志
			OrderLog log = new OrderLog();
			log.setOrderId(entry.getKey().getId());
			log.setLog(entry.getValue().toString());
			log.setOperTime(new Date());
			log.setOperUserId(curUser);
			orderLogDao.insert(log);
		}
		return sbf.toString();
	}
	
	
	

	@Override
	public String split(List<Order> orderList, String curUser) throws Exception {
		StringBuffer sbf = new StringBuffer("订单折分新订单:[");
		for(Order order:orderList){
			List<OrderItem> firstItem = order.getItems().subList(0, 1);
			List<OrderItem> reMainItem = order.getItems().subList(1, order.getItems().size());
			BigDecimal avgpaypal = null;
			if (order.getPaypalFee()!=null){
				BigDecimal ordersCount = BigDecimal.valueOf(firstItem.size()+reMainItem.size());
				avgpaypal = order.getPaypalFee().divide(ordersCount,BigDecimal.ROUND_CEILING);
				avgpaypal = avgpaypal.setScale(4,BigDecimal.ROUND_HALF_UP);
			}
			for(int i=0;i<reMainItem.size();i++){
				Order newOrder = (Order)order.clone();
				newOrder.setOrderSn("split_"+ order.getOrderSn()+"_"+ DateFormatUtils.format(new Date(), "MMddHHmmssS"));
				newOrder.getBuyinfo().setOrderSn(newOrder.getOrderSn());
				newOrder.setItems(reMainItem.subList(i, i+1));
				newOrder.setPaypalFee(avgpaypal);
				Integer id = this.insert(newOrder);
				sbf.append(id+",");
				//记录日志
				OrderLog log = new OrderLog();
				log.setOrderId(id);
				log.setLog("订单来源:" + order.getId()+"订单拆分");
				log.setOperUserId(curUser);
				log.setOperTime(new Date());
				orderLogDao.insert(log);
			}
			sbf.replace(sbf.length()-1, sbf.length()-1, "]");
			order.setPaypalFee(avgpaypal);
			order.setItems(firstItem);
			this.update(order);
		}
		return sbf.toString();
	}

	/**
	 * 合并多个相同sku
	 * 
	 * @param items

	private List<OrderItem> dealMergeOrderItem(List<OrderItem> items) {
		Map<String, OrderItem> skuItemMap = new HashMap<>();
		List<OrderItem> itemList = new ArrayList<>();
		for (OrderItem item : items) {
			String itemSku = item.getItemSku();
			if (!skuItemMap.containsKey(itemSku)) {
				skuItemMap.put(itemSku, item);
			} else {
				Integer itemQuantity = Integer.sum(skuItemMap.get(itemSku)
						.getItemQuantity(), item.getItemQuantity());
				skuItemMap.get(itemSku).setItemQuantity(itemQuantity);
			}
		}
		for (OrderItem item : skuItemMap.values()) {
			itemList.add(item);
		}
		return itemList;
	}
	*/

	@Override
	public void cancelOrder(Integer orderId,String curUser) throws Exception {
		//释放订单锁定的库存
		goodsInventoryLockDao.deleteBySaleOrderId(orderId);
		
		//设置订单包裹已删除
		List<OrderPackage> packs = orderPackageDao.findByOrderId(orderId);
		for(OrderPackage pack:packs){
			pack.setStatus(OrderPackageStatus.DELETED.code());
			orderPackageDao.update(pack);
			List<OrderPackageItem> packageItems = orderPackageItemDao.findByPackage(pack.getId()); // 取包裹下的包裹行
			for(OrderPackageItem packitem:packageItems){
				packitem.setPackageAmount(0);
				orderPackageItemDao.update(packitem);
			}
			OrderLog log = new OrderLog();
			log.setLog("订单包裹[" +pack.getId()+"]已取消");
			log.setOperTime(new Date());
			log.setOperUserId(curUser);
			log.setOrderId(pack.getOrderId());
			orderLogDao.insert(log);
		}
		
		//设置订单为取消状态并记录日志
		Order order = new Order();
		order.setId(orderId);
		order.setOrderStatus(OrderStatusEnum.CANCEL.code().shortValue());
		order.setSuspend(0);
		orderDao.update(order);
		order = orderDao.load(orderId);
		for(OrderItem item:order.getItems()){
			OrderItem temp = new OrderItem();
			temp.setId(item.getId());
			temp.setCancelAmount(item.getItemQuantity());
			temp.setPackageAmount(0);
			temp.setLockAmount(0);
			orderItemDao.update(temp);
		}
		
		OrderLog log = new OrderLog();
		log.setLog("订单已取消");
		log.setOperTime(new Date());
		log.setOrderId(order.getId());
		log.setOperUserId(curUser);
		orderLogDao.insert(log);
	}
	
	@Override
	@Transactional
	public void cancelOrderItem(Integer itemId, 
			Integer cancellingAmount,String curUser) throws Exception {
		
		checkArgument(itemId != null, "缺少参数：itemId");
		checkArgument(cancellingAmount != null && cancellingAmount > 0, "取消数量需大于0");
		
		OrderItem orderItem = this.orderItemDao.load(itemId);
		checkArgument(orderItem != null, "找不到对应的订单行");
		
		Integer orderId = orderItem.getOrderId();
		Order order = orderDao.load(orderId); 
		checkArgument(order.getOrderStatus().intValue()!=OrderStatusEnum.CANCEL.code(), "该订单已取消");
		// 订单行已出货数量
		Integer shipmentAmount = (orderItem.getShipmentAmount() == null ? 0
				: orderItem.getShipmentAmount());
		// 已取消数量
		Integer canceledAmount = (orderItem.getCancelAmount() == null  ? 0 : orderItem.getCancelAmount());
		
		Integer maxCancelAmount = orderItem.getItemQuantity() - shipmentAmount - canceledAmount;
		checkArgument(cancellingAmount <= maxCancelAmount, "取消数量已超出可取消的最大值：%s", maxCancelAmount);
		
		String sku = orderItem.getSku();
		// 如果不是正常SKU
		if (sku == null || !sku.matches("^[0-9]{10}$")) {
			OrderItem tmp = new OrderItem();
			tmp.setId(orderItem.getId());
			tmp.setCancelAmount(canceledAmount + cancellingAmount);
			this.orderItemDao.update(tmp);
		} else {
			Integer unpackageAmount = orderItem.getItemQuantity()
					- shipmentAmount-canceledAmount
					- (orderItem.getPackageAmount() == null ? 0 : orderItem
							.getPackageAmount()); // sku未包裹数量
			GoodsInventoryLock lock = goodsInventoryLockDao.findByOrderIdAndSku(
					orderId, sku); // 订单行锁定库存
			// sku未包裹数量大于取消数量,不涉及到包裹
			if (unpackageAmount >= cancellingAmount) {
				orderItem.setCancelAmount((orderItem.getCancelAmount() == null ? 0
						: orderItem.getCancelAmount()) + cancellingAmount); // 设置订单行取消数量
				orderItemDao.update(orderItem);
				//删除对应的锁定库存
				if (lock!=null){
					goodsInventoryLockDao.deleteById(lock.getId());
				}
			} else { // sku取消涉及到包裹
				List<OrderPackage> packages = orderPackageDao
						.findByOrderId(orderId); // 订单包裹
				for (OrderPackage pack : packages) {
					if (pack.getStatus() == 6 || pack.getStatus() == 7) { // 已交接/已删除包裹的sku不可取消
						continue;
					}
					// 如果取消数量都已经从包裹中减除
					if (cancellingAmount == 0) {
						return;
					}
					List<OrderPackageItem> packageItems = orderPackageItemDao
							.findByPackage(pack.getId()); // 取包裹下的包裹行
					boolean packvalid = false; // 包裹是否有效
					for (OrderPackageItem packitem : packageItems) {
						// 从包裹中移除取消数量操作
						if (StringUtils.equals(sku, packitem.getSku())) {
							// 需要从包裹中移除的数量
							int removeFrompackage = cancellingAmount - unpackageAmount;
							// 重新设置包裹数量
							packitem.setPackageAmount(packitem.getPackageAmount()
									- removeFrompackage);
							orderPackageItemDao.update(packitem);
							if (lock!=null){
								lock.setLockCount(lock.getLockCount()- removeFrompackage);
								if (lock.getLockCount()>0){
									goodsInventoryLockDao.update(lock);
								}else{
									goodsInventoryLockDao.deleteById(lock.getId());
								}
							}
							orderItem
									.setCancelAmount((orderItem.getCancelAmount() == null ? 0
											: orderItem.getCancelAmount())
											+ cancellingAmount); // 累加订单行取消数量
							orderItem.setPackageAmount(orderItem.getPackageAmount()
									- removeFrompackage); // 累加订单行包裹数量
							orderItemDao.update(orderItem);
							cancellingAmount = cancellingAmount - removeFrompackage;
						}
						// 只有包裹行中sku包裹数量大于0,则该包裹有效
						if (packitem.getPackageAmount() > 0) {
							packvalid = packvalid || true;
						}
					}
					// 如果包裹中所有sku的包裹都已取消,则包裹设置删除标记
					if (!packvalid) {
						orderPackageDao.updateState(7, pack.getId());
						OrderLog log = new OrderLog();
						log.setLog("订单包裹[" +pack.getId()+"]已取消");
						log.setOperTime(new Date());
						log.setOperUserId(curUser);
						log.setOrderId(pack.getOrderId());
						orderLogDao.insert(log);
					}
				}
			}
		}

		order = this.orderDao.load(orderId);
		
		// 如果订单所有sku均已取消则修改订单的状态为无效订单
		boolean allCanceled = true;
		for (OrderItem item : order.getItems()) {
			if (!item.getItemQuantity().equals(item.getCancelAmount())) {
				allCanceled = false;
				break;
			}
		}
		
		if (allCanceled) {
			Order o = new Order();
			o.setId(order.getId());
			o.setSuspend(0);
			orderDao.update(o);
			orderDao.cancelOrder(
					DateUtils.formatDate(new Date(), "yyyy-MM-dd"),
					order.getId()); // 设置订单取消及取消时间
			
			OrderLog log = new OrderLog();
			log.setLog("订单已取消");
			log.setOperTime(new Date());
			log.setOrderId(order.getId());
			log.setOperUserId(curUser);
			orderLogDao.insert(log);
			sellerFeeWithHoldDao.deletedByOrderId(o.getId());
		}
	}

	@Override
	@Transactional
	public void stopOrder(Integer orderId, String curUser,String msg) {
		Order o = this.orderDao.load(orderId);
		if (o.getOrderStatus().intValue()==OrderStatusEnum.AUDITED.code() || o.getOrderStatus().intValue()==OrderStatusEnum.LOCKED.code() ||
		    o.getOrderStatus().intValue()==OrderStatusEnum.WAITSEND.code() || o.getOrderStatus().intValue()==OrderStatusEnum.ALL_PACKAGE.code() ||
		    o.getOrderStatus().intValue()==OrderStatusEnum.SHIPPED.code()
			) {
			Order torder = new Order();
			torder.setId(o.getId());
			torder.setStopFlag(1);
			this.orderDao.update(torder);
			this.goodsInventoryLockDao.deleteBySaleOrderId(torder.getId());
			
			OrderLog log = new OrderLog();
			log.setOperUserId(curUser);
			log.setOperTime(new Date());
			log.setLog(String.format("订单%d停止交易%s", torder.getId(),msg));
			log.setOrderId(torder.getId());
			this.orderLogDao.insert(log);
		}
	}
	
	/**
	 * 退回已发货订单的扣减的费用
	 * 
	 */
	/*
	private void reclaimOrderCost(Integer orderId,Integer sellerId){
		
	}
	*/
	
	/**
	 * 回收停止交易的订单返点
	 */
	/*
	private void recoverOrderRebate(Integer orderId,Integer userId){
		
	}
	*/
	

	/**
	 * 计算订单重量 单个产品 乘 购买数量
	 * 
	 * @param items
	 * @return
	 */
	public BigDecimal getOrderWeight(List<OrderItem> items) {

		BigDecimal totalWeight = new BigDecimal(0);
		for (int i = 0; i < items.size(); i++) {
			String sku = items.get(i).getSku();
			if (sku == null) {
				logger.info("计算订单重量----sku有空");
				break;
			}
			Goods goods = this.goodsDao.findBySku(sku);
			if (goods == null) {
				logger.info("计算订单重量----sku不存在资料表中");
				break;
			}
			BigDecimal weight = goods.getWeight();
			if (weight == null) {
				continue;
			}

			Integer count = items.get(i).getItemQuantity();
			BigDecimal countAmount = weight.multiply(new BigDecimal(count));

			totalWeight = totalWeight.add(countAmount);
		}
		return totalWeight;
	}

	@Override
	public List<OrderShippingFee> getShipFee(Integer orderId) {
		return orderShippingFeeDao.getByOrderId(orderId);
	}

	@Override
	public boolean setShipping(Integer id) {

		OrderShippingFee param = orderShippingFeeDao.getById(id);// 根据Id查询order_shipping_fee信息
		logger.info("====================" + id + "--" + param.getOrderId()
				+ "--" + param.getShippingFee());
		this.orderDao.setShipping(param);
		return true;

	}

	@Override
	public List<ExportOrder> exportInvoice(OrderParam param) {
		return orderDao.exportInvoice(param);
	}

	@Override
	public List<ExportOrder> expirtOrder(OrderParam param, List<Integer> ids) {
		if (ids != null) {
			return orderDao.findOrderInfoById(ids);
		} else {
			return orderDao.findOrderInfoByParam(param);
		}

	}

	@Override
	public List<ExportOrder> exportSale(OrderParam param,List<Integer> ids) {
		if(ids != null){
			return orderDao.findSaleById(ids);
		}else{
			return orderDao.findSaleByParam(param);
		}
	}

	@Override
	public List<StockOutSku> exportStockOut() {
		List<StockOutSku> dataList =  orderDao.exportStockOut();
		//key:缺货Sku   Value:单个sku缺货数量统计
		Map<String,Integer> stockOutCountMap = new HashMap<>();
		for(StockOutSku item:dataList){
//					item.setStockOut(item.getItemQuantity());
//					Integer availeCount = this.goodsInventoryDao.getCountExcludeLockBySku(item.getSku());
//					if (availeCount!=null && availeCount!=0 && availeCount<=item.getItemQuantity()){
//						item.setStockOut(item.getItemQuantity()-availeCount);			//设置缺货数量=购买数量-sku可用库存
//					}
					//统计每个sku缺货数量
					if (!stockOutCountMap.containsKey(item.getSku())){
						stockOutCountMap.put(item.getSku(),item.getStockOut());
					}else{
						stockOutCountMap.put(item.getSku(),stockOutCountMap.get(item.getSku())+item.getStockOut());
					}
		}
		for(StockOutSku row:dataList){
			row.setStockOutCount(stockOutCountMap.get(row.getSku()));
		}
		return dataList;
	}

	@Override
	public void batchSetShip(Integer id) {
		orderDao.batchSetShip(id);
	}

	@Override
	public List<Order> findOrder(OrderParam param) {
		return orderDao.findOrder(param);
	}

	@Override
	public Integer batchInsertOrders(){
		//任务正在执行
		if (isRunning.get()){
			logger.error("订单同步任务正在进行中。。。");
			return -1;
		}
		isRunning.compareAndSet(false, true);
		List<Integer> unsyncIds = null;
		Integer syncCount = 0;
		int deal = 30; // 每次同眇订单数
		try{
			// 获得所有未同步且已付款订单的Id
			unsyncIds = orderDao.getUnSyncOrderId();
			int circlecount = unsyncIds.size() % deal == 0 ? unsyncIds.size()
					/ deal : unsyncIds.size() / deal + 1;
			for (int i = 0; i < circlecount; i++) {
				int from = i * deal;
				int to = (i + 1) * deal;
				to = to < unsyncIds.size() ? to : unsyncIds.size();
				List<Integer> dealIdList = unsyncIds.subList(from, to);
				try {
					orderSyncService.syncOrder(dealIdList);
					syncCount += dealIdList.size();
				} catch (Exception e) {
					logger.error("从刊登平台同步新订单异常:" + e.getMessage());
				}
			}
			logger.info("从刊登平台成功同步新订单:" + unsyncIds + System.lineSeparator()
					+ "本次同步订单数:" + unsyncIds.size());
			if (unsyncIds.size() > 0) {
				// 设置订单与订单行关系
				orderDao.updateOrderItemRelation();
				// 更新是否混合订单标志
				orderDao.mixedOrder(null);
			}
			
		}finally{
			unsyncIds = null;
			isRunning.compareAndSet(true, false);
		}
		return syncCount;
	}

	// @Override
	// public void itemSku2SystemSku() throws Exception {
	//
	// List<OrderItem> itemList = orderItemDao.queryNoSku();
	// if (itemList==null ||itemList.size()<1){
	// return;
	// }
	// Pattern pattern1 = Pattern.compile("^.*?([A-Z]{3}\\d{2}).*?$");
	// Pattern pattern2 =
	// Pattern.compile("^.*?([A-Z]{2}\\d{2}|[A-Z]{1}\\d{3}).*?$");
	// Pattern pattern3 = Pattern.compile("^([A-Z]{3}).*?$");
	// Pattern pattern4 = Pattern.compile("^.*?([A-Z]{3})$");
	// for(OrderItem item:itemList){
	// if (StringUtils.isBlank(item.getItemSku())){
	// continue;
	// }
	// Matcher mtacher1 = pattern1.matcher(item.getItemSku());
	// Matcher mtacher2 = pattern2.matcher(item.getItemSku());
	// Matcher mtacher3 = pattern3.matcher(item.getItemSku());
	// Matcher mtacher4 = pattern4.matcher(item.getItemSku());
	// String sysSku = null;
	// if(sysSku==null && mtacher1.find()){
	// sysSku = mtacher1.group(1);
	// }
	// else if(sysSku==null && mtacher2.find()){
	// sysSku = mtacher2.group(1);
	// }
	// else if(sysSku==null && mtacher3.find()){
	// sysSku = mtacher3.group(1);
	// }
	// else if(sysSku==null && mtacher4.find()){
	// sysSku = mtacher4.group(1);
	// }
	// if (sysSku!=null){
	// logger.info("更新oldSku:" + sysSku);
	// orderItemDao.updateSkuByoldSku(item.getId(),sysSku);
	// }
	// }
	// itemList = null;
	// }

	@Override
	public void calculateOrderProfit() {
		List<Integer> idList = findAfterGepackOrderId();
		for (Integer oid : idList) {
			configOrderOtherInfo(oid); // 填充订单运费
			calculateOrderProfit(oid); // 计算订单利润
		}
	}

	private void calculateOrderProfit(Integer orderId) {
		BigDecimal profit = new BigDecimal(0);
		Order order = orderDao.load(orderId);
		String orderCurrency = order.getCurrency(); // 订单支付币种
		if (order.getExchangeRate()==null || StringUtils.isEmpty(orderCurrency) || order.getAmount() == null || order.getProfit()!=null) {
			return;
		}
		BigDecimal cost = order.getCost() == null ? BigDecimal.valueOf(0)
				: order.getCost();
		BigDecimal strikeCost = order.getStrikeCost() == null ? BigDecimal
				.valueOf(0) : order.getStrikeCost().multiply(order.getExchangeRate()); // 订单成交费
		BigDecimal shippingFee = order.getShippingFee() == null ? BigDecimal
				.valueOf(0) : order.getShippingFee(); // 订单总运费
		BigDecimal refundFee = order.getRefundFee() == null ? BigDecimal
				.valueOf(0) : order.getRefundFee().multiply(order.getExchangeRate()); // 订单退款
		// 订单利润=订单金额 -订单成本-订单成交费 -订单总运费 -订单退款
		profit = order.getAmount().multiply(order.getExchangeRate())
				.subtract(cost)
				.subtract(strikeCost).subtract(shippingFee).subtract(refundFee);
		orderDao.calculateOrderProfit(orderId, profit);
	}

	/***
	 * 
	 * [锁定库存]
	 * 
	 * 将订单状态[2待锁定] 变成 [3已经锁定]
	 * 
	 * 处理订单的状态为:[2待锁定]
	 * 
	 * 检查sku(产品) 是否已经存在 库存
	 * 
	 * 如果存在库存,就锁定库存,订单的状态 改为 --已锁定
	 * 
	 */
	
	public void inventoryLock() {
		List<Order> orders = orderDao.getByState(2); // order 订单查询

		
		for (int i = 0; i < orders.size(); i++) {
	
			Order thisOrder = orders.get(i);
			
			if(thisOrder.getSuspend() == 1){
				logger.info("[库存锁定2] - {} 订单已经暂停,不处理", thisOrder.getId());
				//订单已经暂停 不处理
				continue;
			}

			List<OrderItem> items = orderItemDao
					.queryByOrder(thisOrder.getId());

			for (int j = 0; j < items.size(); j++) {

				OrderItem thisItem = items.get(j);
				
				Integer resCount = goodsInventoryDao.getCountBySku(thisItem.getSku());// 实际库存SUM
						
				if (resCount == null) {
					logger.info("[库存锁定2] - {}, {} 不存在库存信息", thisItem.getOrderId(), thisItem.getSku());
					resCount = 0;
				}

				Integer lockCount = goodsInventoryLockDao
						.getLockCountBySku(thisItem.getSku());// 锁定库存SUM

				if (lockCount == null) {

					lockCount = 0;
				}

				Integer count = resCount - lockCount; // 库存 减 锁定[可用数量]

				if (count > 0) {// 存在库存

					Integer buySku = thisItem.getItemQuantity();// 此SKU购买数量
					GoodsInventoryLock resultInfo = this.goodsInventoryLockDao.findByOrderIdAndSku(thisOrder.getId(), thisItem.getSku());
					if (buySku > count) { // 购买数量大于库存---只锁定库存数量--修改订单状态3
						
						if(resultInfo == null){
							// 新增的锁定库存
							GoodsInventoryLock record = new GoodsInventoryLock();
							record.setGoodsSku(thisItem.getSku());
							record.setLockCount(count);
							record.setLastUpdatedTime(new Date());
							record.setSaleOrderId(thisOrder.getId());
							this.goodsInventoryLockDao.insert(record);
							
						}else{
							
							GoodsInventoryLock record1 = new GoodsInventoryLock();
							record1.setLastUpdatedTime(new Date());
							record1.setId(resultInfo.getId());
							Integer totalLockCount = resultInfo.getLockCount() + count;
							record1.setLockCount(totalLockCount);
							this.goodsInventoryLockDao.update(record1);
						}
						
							// 回写订单状态
							orderDao.updateStateById(3, thisOrder.getId());
							// 回写订单明细
							OrderItem orderItem = new OrderItem();
							orderItem.setLockAmount(count);
							orderItem.setId(thisItem.getId());
							orderItemDao.update(orderItem);
							this.addOrderLog(thisOrder.getId());

					} else {// 购买数量 小于或等于 库存 --- 锁定购买数量 ---修改订单状态
						
						if(resultInfo == null){
							// 新增的锁定库存
							GoodsInventoryLock record = new GoodsInventoryLock();
							record.setGoodsSku(thisItem.getSku());
							record.setLockCount(buySku);
							record.setLastUpdatedTime(new Date());
							record.setSaleOrderId(thisOrder.getId());
							this.goodsInventoryLockDao.insert(record);
							
						}else{
							
							GoodsInventoryLock record1 = new GoodsInventoryLock();
							record1.setLastUpdatedTime(new Date());
							record1.setId(resultInfo.getId());
							Integer totalLockCount = resultInfo.getLockCount() + buySku;
							record1.setLockCount(totalLockCount);
							this.goodsInventoryLockDao.update(record1);
						}
						
							// 回写订单状态
							orderDao.updateStateById(3, thisOrder.getId());
							// 回写订单明细
							OrderItem orderItem = new OrderItem();
							orderItem.setLockAmount(buySku);
							orderItem.setId(thisItem.getId());
							orderItemDao.update(orderItem);
							this.addOrderLog(thisOrder.getId());
					}

				} else {
					logger.info("[库存锁定2] - {}, {} 库存不足, {}个库存中已锁定 {}", 
							thisItem.getOrderId(), thisItem.getSku(),
							resCount, lockCount);
				}

			}
		}
	}

	/**
	 * 
	 * 匹配物流方式
	 * 
	 * 根据 账号ID 订单金额 订单的重量 国家 订单SKU 匹配物流渠道
	 * 
	 * 根据 订单国家 计算出运费
	 * 
	 * kenny
	 *
	 *
	 * 
	 */
	public void matchingShipping() {
		List<Order> orders = orderDao.findNoShip(); // 查询满足条件的status=1 且没有匹配过的

		if (orders.size() == 0) {
			logger.info("匹配物流方式---没有满足的订单");
		} else {

			for (int n = 0; n < orders.size(); n++) {
				Order order = orderDao.load(orders.get(n).getId());
				List<OrderItem> items = order.getItems();

				if (items.size() == 0) {
					logger.info(orders.get(n).getId() + "订单 没有明细");
					continue;
				}

				List<String> skus = new ArrayList<String>();

				for (int i = 0; i < items.size(); i++) {
					String sku = items.get(i).getSku();
					if (sku == null) {
						logger.info("匹配物流方式---订单sku存在空值");
						break;
					}
					skus.add(sku);
				}

				if (skus.size() == 0) {
					logger.info("匹配物流方式---订单sku是空值");
					continue;
				}
				Shipping shipping = new Shipping();
				shipping.setAccount(ObjectUtils.toString(order.getAccountId()));// 账号ID
				if (order.getAmount() == null) {
					shipping.setAmountOrder(new BigDecimal(0));// 订单金额
				} else {
					shipping.setAmountOrder(order.getAmount());// 订单金额
				}

				BigDecimal weightOrder = this.getOrderWeight(items);
				shipping.setWeightOrder(weightOrder);// 订单的重量KG
				String countryOrder = null;
				if(order.getBuyinfo() == null){
					logger.info("匹配物流方式-----没有购买信息");
					continue;
				}
				
				if(order.getBuyinfo().getShippingCountryName() == null){
					countryOrder = order.getBuyinfo().getShippingCountry();
				}else{
					countryOrder = order.getBuyinfo().getShippingCountryName();
					
				}
				
						
				if (countryOrder == null) {
					logger.info("匹配物流方式---订单国家为空");
					continue;
				}
				shipping.setCountry(countryOrder);// 国家
				shipping.setSkus(skus);// 订单SKU
				List<Shipping> result = shippingDao.findByParam(shipping, skus);// 获取物流方式
				if (result.size() == 0) {
					logger.info("订单匹配物流方式--没有获得匹配的物流方式");
				} else {

					for (int j = 0; j < result.size(); j++) {
						Shipping thisShip = result.get(j);
						ShippingFee shippingFee = this.shippingFeeDao
								.getByCountry(countryOrder, thisShip.getId());// 获取运费计算数据

						BigDecimal total = new BigDecimal(0);
						if (shippingFee == null) {
							total = new BigDecimal(0);
							logger.info("订单匹配物流----没有对应的运费");

						} else {

							boolean bl = this.checkEub(thisShip.getId());
							if (bl) {// 不是EUB发货方式

								if (shippingFee.getType() == 1) {
									// （邮费*订单实际重量+处理费+增项费用－减项费用）*折扣
									//NEW （邮费*订单实际重量*折扣)+处理费 +增项费用－减项费用 
									BigDecimal postFee = shippingFee.getPostFee();
									BigDecimal addFee = shippingFee.getAddFee();
									BigDecimal subtFee = shippingFee.getSubtractFee();
									BigDecimal serviceFee = shippingFee.getServiceFee();
									BigDecimal discRate = shippingFee.getDiscountRate();
									
									BigDecimal wAmount = postFee.multiply(weightOrder);
									BigDecimal sf1 =  wAmount.multiply(discRate);
									BigDecimal total1 = sf1.add(serviceFee).add(addFee).subtract(subtFee);
									total = total1.setScale(2, BigDecimal.ROUND_HALF_UP);
											

								} else if (shippingFee.getType() == 2) {

									BigDecimal first = shippingFee.getFirstWeight();
											
									BigDecimal firstAmount = shippingFee.getFirstWeightAmount();
									BigDecimal add = shippingFee.getAddWeight();
									BigDecimal addAmount = shippingFee.getAddWeightAmount();
									BigDecimal registeredFee = shippingFee.getRegisteredFee();
									BigDecimal serviceFee = shippingFee.getServiceFee();
									BigDecimal disc = shippingFee.getDiscount();
									BigDecimal discRate = shippingFee.getDiscountRate();
											
									// （包裹重量－首重）/续重
									BigDecimal weightToG = weightOrder.multiply(new BigDecimal(1000));// 千克 转换为克
									
									if (weightToG.compareTo(first) != 1) {
										BigDecimal sum1 = firstAmount.add(registeredFee).add(serviceFee).subtract(disc);
										BigDecimal count1 = sum1.multiply(discRate);
										total = count1.setScale(2,BigDecimal.ROUND_HALF_UP);
										logger.info("发货方式:"+ thisShip.getShippingName()+ "运费:" + total + "订单ID:"+ order.getId());
									} else {
										BigDecimal subtract = weightToG.subtract(first);
										BigDecimal divideSet = subtract.divide(add, 0);
										BigDecimal addWeight = divideSet.setScale(0,BigDecimal.ROUND_UP);
														
										// 续重部分金额＝续重部分*续重金额
										BigDecimal addAmount1 = addWeight.multiply(addAmount);
										// （首重金额+续重部分金额+挂号费+处理费－折扣）*折扣率
										BigDecimal sum = firstAmount.add(addAmount1).add(registeredFee).add(serviceFee);
										BigDecimal count = sum.subtract(disc);
										total = count.multiply(discRate);
									}

								}

							} else {// 是EUB发货发货方式

								// （邮费*订单实际重量+处理费+增项费用－减项费用）*折扣
								BigDecimal postFee = shippingFee.getPostFee();
								BigDecimal addFee = shippingFee.getAddFee();
								BigDecimal subtFee = shippingFee.getSubtractFee();
								BigDecimal serviceFee = shippingFee.getServiceFee();
								BigDecimal discRate = shippingFee.getDiscountRate();
								BigDecimal eubMinWeight = new BigDecimal(0.06);// 最小重量是60g

								if (weightOrder.compareTo(eubMinWeight) != 1) {// 小于60G// 或者// 等于// 60G	
									
									BigDecimal eubMinWeight1 = eubMinWeight.multiply(postFee);
									BigDecimal sf1 =  eubMinWeight1.multiply(discRate);
									BigDecimal total1 = sf1.add(serviceFee).add(addFee).subtract(subtFee);
									total = total1.setScale(2,BigDecimal.ROUND_HALF_UP);
									
								} else {
									BigDecimal wAmount = postFee.multiply(weightOrder);
									BigDecimal sf1 =  wAmount.multiply(discRate);
									BigDecimal total1 = sf1.add(serviceFee).add(addFee).subtract(subtFee);
									total = total1.setScale(2,BigDecimal.ROUND_HALF_UP);
								}
							}

							logger.info("发货方式:" + thisShip.getShippingName()+ "运费:" + total + "订单ID:" + order.getId());
							OrderShippingFee param = new OrderShippingFee();
							param.setOrderId(order.getId());
							param.setShippingFee(total);
							param.setShippingName(thisShip.getShippingName());
							orderShippingFeeDao.insert(param);// 添加运费

						}
					}
				}
			}
		}

	}

	/***
	 * EUB运输方式判断
	 * 是 EUB 返回 false
	 * @param shipId 运输方式ID
	 * @return
	 */
	public boolean checkEub(Integer shipId) {
		Shipping shipping = shippingDao.getById(shipId);
		String name = shipping.getCarrierSn();
		if (("EUB").equals(name) || ("eub").equals(name)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Integer> findAfterGepackOrderId() {
		return orderDao.findAfterGepackOrderId();
	}

	@Override
	public void configOrderOtherInfo(Integer orderId) {
		orderDao.configOrderOtherInfo(orderId);
	}

	@Override
	public void orderSuspendAndEnable(List<Integer> orderId,
			Integer suspendEnbale, String operUser) {
		for(Integer oid:orderId){
			orderDao.orderSuspendAndEnable(oid, suspendEnbale);
			//保存订单操作记录
			OrderLog log = new OrderLog();
			log.setOperUserId(operUser);
			log.setLog("订单"+(suspendEnbale==0?"启用":"暂停"));
			log.setOperTime(new Date());
			log.setOrderId(oid);
			orderLogDao.insert(log);
		}
	}

	@Transactional
	public void reStoreOrder(Order order) {
		if (null == order) {
			return;
		}
		Integer orderId = order.getId();
		orderDao.reStoreOrder(orderId);
		orderItemDao.restoreItem(orderId);
		orderPackageDao.deleteByOrderId(orderId);
		orderPackageItemDao.deleteByOrderId(orderId);
		orderShippingFeeDao.deleteByOrderId(orderId);
	}

	@Override
	public void importTrackFile(MultipartFile file, TrackNumber tNumber,
			Integer type) throws FileImportException {

		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取上传文件出错", e);
		}

		if (is == null) {
			return;
		}

		String filename = file.getOriginalFilename();
		String fileExtension = FilenameUtils.getExtension(filename);

		List<String[]> data = ExcelParser.parseToString(is, fileExtension);
		if (data == null) {
			return;
		}

		for (int i = 1; i < data.size(); i++) {
			String[] rowData = data.get(i);

			rowData[0] = StringUtils.trimToNull(rowData[0]);
			if (rowData[0] == null) {
				continue;
			}

			this.insertTrackNumber(i, rowData, type);
		}

	}

	private void insertTrackNumber(int lineNo, String[] rowData, Integer type) {
		TrackNumber tn = new TrackNumber();
		tn.setType(type);
		tn.setTrackName(rowData[0]);
		tn.setTrackNo(rowData[1]);
		trackNumberDao.insert(tn);
	}

	/***
	 * 
	 * 获得订单ids的长度 获得顺邮宝的跟踪号 删除跟踪号码
	 * 
	 */
	public List<TrackerApplyResult> sybTrackerNumber(List<Integer> orderIds,
			Integer type) {

		List<TrackerApplyResult> results = new ArrayList<TrackerApplyResult>();

		for (int i = 0; i < orderIds.size(); i++) {

			Order od = orderDao.getById(orderIds.get(i));

			TrackerApplyResult result = new TrackerApplyResult();

			if (od.getTrackNumber() != null) {
				result.setOrderId(orderIds.get(i));
				result.setSuccess(false);
				result.setMessage("跟踪号已经存在！");
				results.add(result);// 返回数据
				break;
			}

			TrackNumber tn = trackNumberDao.getByType(type);
			if (tn == null) {
				result.setOrderId(orderIds.get(i));
				result.setSuccess(false);
				result.setMessage("跟踪号已经使用完");
				results.add(result);// 返回数据
				break;
			} else {
				
				Order record = new Order();
				record.setId(orderIds.get(i));
				record.setTrackNumber(tn.getTrackNo());
				orderDao.update(record); // 填写订单
				trackNumberDao.delete(type, tn.getTrackNo());// 删除跟踪号
				this.orderPackageDao.updateTrackNumberAndShippingNameByOrderId(orderIds.get(i));//同步到包裹上
				
				result.setOrderId(orderIds.get(i));
				result.setSuccess(true);
				result.setTrackNumber(tn.getTrackNo());
				results.add(result);// 返回数据

			}
		}

		return results;
	}
	
	
	
	
	/****
	 * 
	 * 计算运费
	 * @param orderId
	 * @param shipName
	 * 方法用在 设置运输方式时使用
	 *TODO
	 * 
	 */
	public BigDecimal countShiFee(Order order,String shipName){
		BigDecimal total = new BigDecimal(0);
		String country1 = order.getBuyinfo().getShippingCountryName();
		String country2 = order.getBuyinfo().getShippingCountry();
		
		Shipping shipping = this.shippingDao.getByShipName(shipName);

		ShippingFee shipFee = null;
		
		if(shipping.getShippFeeType() != null && shipping.getShippFeeType() == 1){//区间计算
			
			//计算运费2 DHL 计算运费
			String thisCountry  = country1;
			if(country1 == null){
				thisCountry = country2;
			}
			BigDecimal weightOrder = this.getOrderWeight(order.getItems());//订单的重量KG
			ShippingFee2 feeInfo = shippingFee2Dao.getFeeByWeight(shipping.getId(), weightOrder, thisCountry);
			if(feeInfo != null){
				total = feeInfo.getShippFee();
			}
			
		}else{//非区间计算
			
			if(country1 != null){
				shipFee = this.shippingFeeDao.getByCountry(country1, shipping.getId());
			}else{
				shipFee = this.shippingFeeDao.getByCountry(country2, shipping.getId());
			}
			
			
			if(shipFee == null){
				total = new BigDecimal(0);//没有查到相应的运费计算规则
				
			}else{
				
				boolean bl = this.checkEub(shipping.getId());
				BigDecimal weightOrder = this.getOrderWeight(order.getItems());//订单的重量KG
				if(bl){//不是EUB
					
					if(shipFee.getType() == 1){ //按照重量计算
						//NEW （邮费*订单实际重量*折扣)+处理费 +增项费用－减项费用 
						BigDecimal firstAmountSf = new BigDecimal(0.05);//最低重量SF
						BigDecimal postFee = shipFee.getPostFee();
						BigDecimal addFee = shipFee.getAddFee();
						BigDecimal subtFee = shipFee.getSubtractFee();
						BigDecimal serviceFee = shipFee.getServiceFee();
						BigDecimal discRate = shipFee.getDiscountRate();
						
						boolean blSf = this.checkSf(shipping.getCarrierSn());//检查是否是SF//
						boolean blIntL = this.checkIntL(shipping.getShippingName());//检查是否是国际小包/平邮
						
						if(blSf || blIntL){
							if(weightOrder.compareTo(firstAmountSf) != 1){
								weightOrder = firstAmountSf;
							}
						}
						BigDecimal wAmount = postFee.multiply(weightOrder);
						BigDecimal sf1 =  wAmount.multiply(discRate);
						BigDecimal total1 = sf1.add(serviceFee).add(addFee).subtract(subtFee);
						total = total1.setScale(2, BigDecimal.ROUND_HALF_UP);
					}else{//首重续重计算
						
						BigDecimal first = shipFee.getFirstWeight();
						BigDecimal firstAmount = shipFee.getFirstWeightAmount();
						BigDecimal add = shipFee.getAddWeight();
						BigDecimal addAmount = shipFee.getAddWeightAmount();
						BigDecimal registeredFee = shipFee.getRegisteredFee();
						BigDecimal serviceFee = shipFee.getServiceFee();
						BigDecimal disc = shipFee.getDiscount();
						BigDecimal discRate = shipFee.getDiscountRate();
						
						//（包裹重量－首重）/续重
						BigDecimal weightToG = weightOrder.multiply(new BigDecimal(1000));//千克 转换为克
						if(weightToG.compareTo(first) != 1){
							//total = firstAmount;(首重金额)*折扣率+处理费
							BigDecimal sum1 = firstAmount.add(registeredFee).subtract(disc);
							BigDecimal count1 = sum1.multiply(discRate).add(serviceFee);
							total = count1.setScale(2,BigDecimal.ROUND_HALF_UP);
						}else{
							
						BigDecimal subtract =  weightToG.subtract(first);
						BigDecimal divideSet = subtract.divide(add,0);
						BigDecimal addWeight= divideSet.setScale(0, BigDecimal.ROUND_UP);
						//续重部分金额＝续重部分*续重金额
						BigDecimal addAmount1 = addWeight.multiply(addAmount);
						//（首重金额+续重部分金额+挂号费－折扣）*折扣率+处理费
						BigDecimal sum = firstAmount.add(addAmount1).add(registeredFee);
						BigDecimal count = sum.subtract(disc);
						total = count.multiply(discRate).add(serviceFee).setScale(2,BigDecimal.ROUND_HALF_UP);
						}
					}
					
					
				}else{//是EUB
					//NEW(邮费*订单实际重量*折扣)+处理费 +增项费用－减项费用 
					BigDecimal postFee = shipFee.getPostFee();
					BigDecimal addFee = shipFee.getAddFee();
					BigDecimal subtFee = shipFee.getSubtractFee();
					BigDecimal serviceFee = shipFee.getServiceFee();
					BigDecimal discRate = shipFee.getDiscountRate();
					BigDecimal eubMinWeight = new BigDecimal(0.07);// 最小重量是70g
					
					if (weightOrder.compareTo(eubMinWeight) != 1) {// 小于70G// 或者// 等于// 70G	
						
						BigDecimal eubMinWeight1 = eubMinWeight.multiply(postFee);
						BigDecimal sf1 =  eubMinWeight1.multiply(discRate);
						BigDecimal total1 = sf1.add(serviceFee).add(addFee).subtract(subtFee);
						total = total1.setScale(2,BigDecimal.ROUND_HALF_UP);
								
					} else {

						BigDecimal wAmount = postFee.multiply(weightOrder);
						BigDecimal sf1 =  wAmount.multiply(discRate);
						BigDecimal total1 = sf1.add(serviceFee).add(addFee).subtract(subtFee);
						total = total1.setScale(2,BigDecimal.ROUND_HALF_UP);
					}
				}
				
			}
		}

		return total;
	}
	
	

	@Override
	public BigDecimal countByTrackNumber(String trackNumber) {
		return this.orderDao.countByTrackNumber(trackNumber);
	}

	@Override
	public void orderWeight(Integer orderId) {
		if (null==orderId){
			return;
		}
		Order order = orderDao.load(orderId);
		order.setCalcWeight(BigDecimal.valueOf(0));
		for(OrderItem item:order.getItems()){
				if (item.getItemWeight()==null){
					continue;
				}
				BigDecimal skuWeight = BigDecimal.valueOf(item.getItemQuantity()).multiply(item.getItemWeight());
				order.setCalcWeight(order.getCalcWeight().add(skuWeight));
		}
		
		BigDecimal pw = this.getPackageWeight(order);//获得最重的包装袋
		
		Order temporder = new Order();
		temporder.setId(order.getId());
		temporder.setCalcWeight(order.getCalcWeight().add(pw));
		orderDao.update(temporder);
	}
	
	/***
	 * 
	 * 私有方法，添加日志
	 * @param orderId
	 */
	private void addOrderLog(Integer orderId){
		OrderLog ol = new OrderLog();
		ol.setLog("订单修改之前[待锁定],自动改变为"+OrderStatusEnum.LOCKED.desc());
		ol.setOperUserId("自动");
		ol.setOperTime(new Date());
		ol.setOrderId(orderId);
		this.orderLogDao.insert(ol);
	}

	@Override
	public Integer[] getOrderIdAfterSortByQuantity(List<Integer> ids) {
		return orderDao.getOrderIdAfterSortByQuantity(ids);
	}
	
	/***
	 * 
	 * [自动锁定库存]
	 * 
	 * 将订单状态[2待锁定] 变成 [3已经锁定]
	 * 
	 * 处理订单的状态为:[2待锁定 且 没有暂停]
	 * 
	 * 订单的sku 数量库存全部满足时间才锁定
	 * 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void inventoryLockNew(){
		List<Order> orders = orderDao.findInventoryLockOrder();// 查询订单
		//List<Order> orders = orderDao.getByState(2); // order 订单查询
		if (orders.size() > 0) {
			
			for (int i = 0; i < orders.size(); i++) {
				Order thisOrder = orders.get(i);
				List<OrderItem> items = orderItemDao.queryByOrder(thisOrder.getId());
				Map<String,OrderItem> skuItemsMap = mergeOrderItemsAmount(items);			//added by bernard @10-08
			//	if(items.size() > 0){
				if(skuItemsMap.size() > 0){					//add by bernard @10-08
					boolean flag = true;
					Integer buySku = 0;
			//		for (int j = 0; j < items.size(); j++) {
					for (OrderItem thisItem:skuItemsMap.values()) {  //add by bernard @10-08
						//OrderItem thisItem = items.get(j);
						Integer resCount = goodsInventoryDao.getCountBySku(thisItem.getSku());// 实际库存SUM
						if (resCount == null || resCount == 0) {
							logger.info("[库存锁定2] - {}, {} 不存在库存信息",thisItem.getOrderId(), thisItem.getSku());
							flag = false;
							break;
						}
						
						Integer lockCount = goodsInventoryLockDao.getLockCountBySku(thisItem.getSku());// 锁定库存SUM
						if (lockCount == null) {
							lockCount = 0;
						}
						
						Integer count = resCount - lockCount; // 库存 减 锁定=[可用数量]
						if(count > 0){
							
								Integer buySku1 = thisItem.getItemQuantity();// 此SKU购买数量
								Integer buySku2 = thisItem.getCancelAmount();// 取消数量
								if (buySku2 == null) {
									buySku2 = 0;
								}
								buySku = buySku1 - buySku2;// 真实购买数量
								if(buySku > count){
									flag = false;
									logger.info("[库存锁定2]--" + thisItem.getSku()+ "购买数量大于库存可用数量");
									break;
								}
							
						}else{
							flag = false;
							logger.info("[库存锁定2]--" + thisItem.getSku()+ "库存不足");
							break;
						}
					}
					
					if(flag){
						this.updateOrderInfo(items, thisOrder.getId(), buySku); //锁定库存
					}
					
				}else{
					logger.info("[库存锁定2]--" + thisOrder.getId() + "无订单行");
					break;
				}
				
			}
			
		}else{
			logger.info("[库存锁定2]--没有满足条件的订单");
		}
		
	}
	
	
	/***
	 * 锁定库存2,修改订单/库存信息
	 * 
	 * @param items 订单行集合
	 * @param orderId 订单ID
	 * @param buySku 真实购买数量
	 * 
	 */
	private void updateOrderInfo(List<OrderItem> items, Integer orderId,Integer buySku){
		for (int i = 0; i < items.size(); i++) {
			OrderItem thisItem = items.get(i);
			// 锁定库存逻辑 【正常的逻辑下-只有新增】
			GoodsInventoryLock resultInfo = this.goodsInventoryLockDao.findByOrderIdAndSku(orderId, thisItem.getSku());
			if (resultInfo == null) {
				// 新增的锁定库存
				GoodsInventoryLock record = new GoodsInventoryLock();
				record.setGoodsSku(thisItem.getSku());
				record.setLockCount(buySku);
				record.setLastUpdatedTime(new Date());
				record.setSaleOrderId(orderId);
				this.goodsInventoryLockDao.insert(record);
				
			} else {
				// 修改的锁定库存
				GoodsInventoryLock record1 = new GoodsInventoryLock();
				record1.setLastUpdatedTime(new Date());
				record1.setId(resultInfo.getId());
				Integer totalLockCount = resultInfo.getLockCount() + buySku;
				record1.setLockCount(totalLockCount);
				this.goodsInventoryLockDao.update(record1);
			}
			
			// 回写订单行信息
			OrderItem orderItem = new OrderItem();
			orderItem.setLockAmount(buySku);
			orderItem.setId(thisItem.getId());
			orderItemDao.update(orderItem);
		}
		// 回写订单头状态
		orderDao.updateStateById(3,orderId);
		this.addOrderLog(orderId);
	}
	
	/***
	 * 导入退回订单
	 */
	@Transactional(rollbackFor=Exception.class)
	public List<Map<String, String>> importOrderReturn(MultipartFile file,Integer returnType, String userName)throws FileImportException{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取上传文件出错", e);
			throw fileImportException(0,0,"获取上传文件出错");
		}
		
		if (is == null) {
			throw fileImportException(0,0,"获取上传文件出错");
		}

		String filename = file.getOriginalFilename();
		String fileExtension = FilenameUtils.getExtension(filename);
		List<String[]> data = ExcelParser.parseToString(is, fileExtension);
		if (data == null) {
			throw fileImportException(0,0,"获取上传文件出错");
		}
		for (int i = 2; i < data.size(); i++) {
			String[] rowData = data.get(i);
			rowData[0] = StringUtils.trimToNull(rowData[0]);
			if (rowData[0] == null) {
				continue;
			}
			map = this.oneOrderReturnNew(rowData[0],rowData[1], returnType,i,userName);
			String resMap = map.get("succ");
			if("success" != resMap){
				list.add(map);
			}
			
		}
		
		return list;
	}
	
	private FileImportException fileImportException(int lineNo, int columnNo, String message) throws FileImportException {
		return new FileImportException(lineNo+1, columnNo+1, message);
	}
	
	public Map<String, String> oneOrderReturnNew(String trackNumber, String returnNote, Integer returnType,int lineNo,String userName)throws FileImportException{
		Map<String, String> map = new HashMap<String, String>();
		OrderParam op = new OrderParam();
		op.setTraceNumber(trackNumber);
		Order thisOrder = this.orderDao.loadByParam(op);

		if (thisOrder == null) {
			map.put(trackNumber, "找不到");
		} else {
			Short orderStatus = thisOrder.getOrderStatus();
			if (orderStatus != 7) {
				map.put(trackNumber, "状态不对");
			} else {
				Order order = new Order();
				order.setId(thisOrder.getId());
				order.setOrderStatus((short) 8);
				/* order.setReturnType(returnType); */
				order.setLastUpdatedTime(new Date());
				order.setReturnTime(new Date());
				order.setReturnNote(returnNote);
				this.orderDao.update(order);
				map.put("succ", "success");
				OrderLog log = new OrderLog();
				log.setOperTime(new Date());
				log.setOperUserId(userName);
				log.setOrderId(thisOrder.getId());
				log.setLog("订单变成 退货订单");
				this.orderLogDao.insert(log);
			}
		}
		return map;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void oneOrderReturn(String trackNumber, String returnNote, Integer returnType,int lineNo,String userName)throws FileImportException{
		OrderParam op = new OrderParam();
		op.setTraceNumber(trackNumber);
		Order thisOrder = this.orderDao.loadByParam(op);

			if (thisOrder == null) {
				logger.info(trackNumber + "订单中找不到对应的跟踪号");
				throw fileImportException(lineNo, 0, "订单列表找不到对应的跟踪号" + trackNumber);
			} else {
	
				Short orderStatus = thisOrder.getOrderStatus();
				if (orderStatus != 7) {
	
					throw fileImportException(lineNo, 0, "跟踪号对应的订单状态不对" + trackNumber);
	
				} else {
					Order order = new Order();
					order.setId(thisOrder.getId());
					order.setOrderStatus((short) 8);
					/*order.setReturnType(returnType);*/
					order.setLastUpdatedTime(new Date());
					order.setReturnTime(new Date());
					order.setReturnNote(returnNote);
					this.orderDao.update(order);
					
					OrderLog log = new OrderLog();
					log.setOperTime(new Date());
					log.setOperUserId(userName);
					log.setOrderId(thisOrder.getId());
					log.setLog("订单变成 退货订单");
					this.orderLogDao.insert(log);
				}
			}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map<String, String>> sellerAdOrder() {
		List<SellerParams> sellerOrder = this.orderDao.getSellerOrder();
		List list = new ArrayList();
		Map map = new HashMap();
		
		for (int i = 0; i < sellerOrder.size(); i++) {
			SellerParams thisSeller = sellerOrder.get(i);
			list.add(map.put(thisSeller.getSellerId(),thisSeller.getContacts()+"-"+thisSeller.getOrderCount()));
		}
		
		return list;
	}

	@Override
	public OrderStatistic orderStatistic(OrderParam param) {
		if ("c.sku".equals(param.getSearchColumn()) && StringUtils.isNotBlank(param.getSearchValue().trim())){
			param.setSkuList(Arrays.asList(param.getSearchValue().split(",")));
		}
		return orderDao.orderStatistic(param);
	}
	/***
	 * 检查是否是顺丰
	 * @param carrierSn 物流公司代号
	 * @return
	 */
	private boolean checkSf(String carrierSn){
		if("SF".equals(carrierSn)){
			return true;
		}else{
			return false;
		}
	}
	/***
	 * 检查是否是国际小包
	 * @param carrierSn
	 * @return
	 */
	private boolean checkIntL(String shippingName){
		if("国际小包平邮".equals(shippingName) || "国际小包挂号".equals(shippingName)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Integer expirtOrderCount(OrderParam param, List<Integer> ids) {
		return this.orderDao.findOrderInfoCountByParam(param, ids);
	}

	@Override
	public List<ExportOrder> findOrderFee(OrderParam param) {
		return this.orderDao.findOrderFee(param);
	}

	@Override
	public Integer findOrderFeeCount(OrderParam param) {
		return this.orderDao.findOrderFeeCount(param);
	}
	
	//订单包装袋的重量
	private BigDecimal getPackageWeight(Order order){
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		
		for(OrderItem item:order.getItems()){
			Goods gs = goodsDao.findBySku(item.getSku());
			if (gs.getPackingMaterialId()==null){
				continue;
			}
			GoodsPackingMaterial gpm = packingMaterialDao.getById(gs.getPackingMaterialId());
			list.add(gpm.getWeight());
		}
		if (list.size()>0){
			Collections.sort(list);
			return list.get(list.size()-1);
		}
		return BigDecimal.valueOf(0);
	}
	
	
	@Override
	public List<Integer> uploadOrderFile(MultipartFile file)
			throws FileImportException {
		List<Integer> list = new ArrayList<Integer>();

		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取上传文件出错", e);
		}

		if (is == null) {
			throw fileImportException(0, 0, "获取上传文件出错");
		}

		String filename = file.getOriginalFilename();
		String fileExtension = FilenameUtils.getExtension(filename);

		List<String[]> data = ExcelParser.parseToString(is, fileExtension);
		if (data == null) {
			throw fileImportException(0, 0, "获取上传文件出错");
		}

		for (int i = 0; i < data.size(); i++) {
			String[] rowData = data.get(i);
			rowData[0] = StringUtils.trimToNull(rowData[0]);
			if (rowData[0] == null) {
				continue;
			}

			list.add(Integer.parseInt(rowData[0]));
		}
		return list;
	}

	@Override
	public void applyRefund(OrderRefund refund,Order order) {
			///-----------------------------设置OrderRefund属性值------------------------------------------
			if (order.getAmount().doubleValue()==refund.getTotal().doubleValue()){		//根据金额设置部分退款或全额退款
				refund.setRefundtype("1");
			}else{
				refund.setRefundtype("0");
			}
			refund.setTransactionid(order.getPaypaltransid());
			refund.setCreatetime(new Date());
			refund.setPlatid(order.getOrderPlatform());
			refund.setRefundfrom((short)1);			//0.case退款;1.系统添加退款
			refund.setRefundstatus((short)0);		//退款状态(0.新建待审核;1.已审核待退款;2.审核不通过;3.已退款)
			refund.setShippingdate(order.getShippedTime());
			refund.setPaiddate(order.getOrderPaidTime());
			refund.setSrn(order.getSrn());
			refund.setVoidflag((short)0);
			refund.setOrderid(order.getOrderSn());
			refund.setAccountid(order.getAccountId().toString());
			refund.setAcctid(order.getAccountName());
			refund.setCurrency(order.getCurrency());
			refund.setOrderAmount(refund.getTotal().compareTo(order.getAmount())>0?order.getAmount():refund.getTotal());
			if (null!=order.getBuyinfo()){
				refund.setCountry(order.getBuyinfo().getShippingCountryName());
				refund.setBuyerid(order.getBuyinfo().getBuyerUserId());
			}
			this.orderDao.applyRefund(refund);
	}

	@Override
	public List<String> readSmtOrderMessage(String orderSn) {
		return this.orderDao.readSmtOrderMessage(orderSn);
	}
	
	/**
	 * 自动生成包裹：适用于已锁定的订单
	 * 状态是3
	 */
	public void generatePackage3() {
		List<Order> orders = orderDao.getByStateAdTkNo();
		logger.info("【自动生成包裹3】 - 待处理的订单数：{}", orders.size());
		
		if(orders.size() > 0){
			for (int i = 0; i < orders.size(); i++) {
				Order thisOrder = orders.get(i);
				try {
					generatePackageService.generateOneOrder(thisOrder);
				} catch (Exception e) {
					logger.info("【自动生成包裹3】 出错", e);
				}
			}
		}
	}

	@Override
	public Integer orderNumbers(OrderParam param) {
		return this.orderDao.orderNumbers(param);
	}
}
