package com.xuanfeiyang.erp.service.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryCostDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryLockDao;
import com.xuanfeiyang.erp.dao.GoodsPackingMaterialDao;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.dao.OrderItemDao;
import com.xuanfeiyang.erp.dao.OrderLogDao;
import com.xuanfeiyang.erp.dao.OrderPackageDao;
import com.xuanfeiyang.erp.dao.OrderPackageItemDao;
import com.xuanfeiyang.erp.dao.OrderShippingFeeDao;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.dao.SellerFeeWithHoldDao;
import com.xuanfeiyang.erp.dao.ShippingDao;
import com.xuanfeiyang.erp.dao.ShippingFee2Dao;
import com.xuanfeiyang.erp.dao.ShippingFeeDao;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsInventoryCost;
import com.xuanfeiyang.erp.domain.GoodsInventoryLock;
import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;
import com.xuanfeiyang.erp.domain.IoOrder;
import com.xuanfeiyang.erp.domain.IoOrderItem;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderLog;
import com.xuanfeiyang.erp.domain.OrderPackage;
import com.xuanfeiyang.erp.domain.OrderPackageItem;
import com.xuanfeiyang.erp.domain.OrderShippingFee;
import com.xuanfeiyang.erp.domain.PlatformAccount;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.Shipping;
import com.xuanfeiyang.erp.domain.ShippingFee;
import com.xuanfeiyang.erp.domain.ShippingFee2;
import com.xuanfeiyang.erp.enums.OrderPackageStatus;
import com.xuanfeiyang.erp.enums.OrderStatusEnum;
import com.xuanfeiyang.erp.param.OrderPackageParam;
import com.xuanfeiyang.erp.param.OrderParam;
import com.xuanfeiyang.erp.service.DepositBalanceShortageException;
import com.xuanfeiyang.erp.service.IoOrderService;
import com.xuanfeiyang.erp.service.OrderPackageService;
import com.xuanfeiyang.erp.service.PlatformAccountService;
import com.xuanfeiyang.erp.service.SellerDepositService;
import com.xuanfeiyang.erp.service.TableKeyService;

@Service
public class OrderPackageServiceImpl implements OrderPackageService {
	private static Logger logger = LoggerFactory.getLogger(OrderPackageService.class);
	
	@Resource
	private OrderPackageDao orderPackageDao;
	@Resource
	private OrderPackageItemDao orderPackageItemDao;
	@Resource
	private GoodsInventoryDao goodsInventoryDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderItemDao orderItemDao;
	@Resource
	private GoodsInventoryLockDao goodsInventoryLockDao;
	
	@Resource
	private GoodsInventoryCostDao goodsInventoryCostDao;
	
	@Resource
	private IoOrderService ioOrderService;
	@Resource
	private OrderShippingFeeDao orderShippingFeeDao;
	
	@Resource
	private GoodsDao goodsDao;
	
	@Resource
	private GoodsPackingMaterialDao packingMaterialDao;
	@Resource
	private ShippingDao shippingDao;
	
	@Resource
	private ShippingFeeDao shippingFeeDao;
	
	@Resource 
	private SellerDepositService sellerDepositService;
	
	@Resource
	private PlatformAccountService platformAccountService;
	
	@Resource
	private OrderLogDao orderLogDao;
	
	@Resource
	private SellerDao sellerDao;
	
	@Resource
	private SellerFeeWithHoldDao sellerFeeWithHoldDao;
	
	@Resource
	private TableKeyService tableKeyService;
	
	@Resource
	private ShippingFee2Dao shippingFee2Dao;

	private DateFormat dft = new SimpleDateFormat("yyyyMMdd");
	
	@Override
	public Page<OrderPackage> findPage(PageRequest pageRequest,
			OrderPackageParam orderPackage) {
		return orderPackageDao.findPage(pageRequest, orderPackage);
	}

	@Override
	public List<OrderPackageItem> findByPackage(String id) {
		return orderPackageItemDao.findByPackage(id);
	}
	
	@Override
	public List<OrderPackageItem> findByOrder(Integer orderId) {
		return orderPackageItemDao.findByOrderId(orderId);
	}

	@Override
	public int countByOrderId(Integer orderId) {
		return orderPackageDao.countOrderId(orderId);
		
	}

	@Override
	public int updateState(Integer state, String id) {
		return orderPackageDao.updateState(state, id);
	}

	@Override
	public OrderPackage getById(String id) {
		return orderPackageDao.getById(id);
	}
	
	private boolean skuEnough(Order order){
		boolean allSkuLock = true;
		Map<String,OrderItem> skuItemsMap  = new HashMap<String,OrderItem>();
		for(OrderItem item : order.getItems()){
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
		
		for (OrderItem item : skuItemsMap.values()) {
			if (!allSkuLock){
				break;
			}
			//sku已锁定,判断已锁定数量是否等于购买数量
			if (item.getLockAmount()!=null){
				allSkuLock = allSkuLock && (item.getItemQuantity()==item.getLockAmount()); 	
			}else{		//sku未锁定,判断当前可用库存是否大于购买数量
				// sku总库存数量
				Integer skuCount = goodsInventoryDao.getCountBySku(item.getSku());
				Integer skuLocked = goodsInventoryLockDao.getLockCountBySku(item.getSku()); // sku已锁定数
				skuCount = (skuCount == null ? 0 : skuCount) - (skuLocked == null ? 0 : skuLocked); // 实际库存数-sku已锁定数
				skuCount = skuCount<0?0:skuCount;
				allSkuLock = allSkuLock  &&  (item.getItemQuantity() <= skuCount);
			}
		}
		return allSkuLock;
	}

	@Transactional(rollbackFor=Exception.class)
	public void generatePackage(Integer orderId,String curUser){
		Order order = orderDao.load(orderId);
		boolean allSkuLock = true; // 所有行锁定
		boolean hasLocked = order.getOrderStatus().intValue()==3?true:false;		//订单是否已锁定
		List<OrderPackageItem> packItemList = new ArrayList<>();			//sku包裹行
		if (order.getOrderStatus().intValue()==OrderStatusEnum.AUDITED.code()){
			allSkuLock = skuEnough(order);
		}
		//订单所有sku均有库存才生成包裹
		if (allSkuLock){
			//修改订单状态
			Order torder = new Order();
			torder.setId(order.getId());
			torder.setVersion(order.getVersion());
			torder.setOrderStatus(OrderStatusEnum.ALL_PACKAGE.code().shortValue());			//修改订单状态 :5 已生成包裹
			int reslutCount = orderDao.update(torder);
			logger.debug("generatePackage update order");
			if (reslutCount>0){
				
				for (OrderItem item : order.getItems()){
					//待锁定订单,锁库存
					if (!hasLocked){
						
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
					}
					//增加包裹行
					OrderPackageItem opm = generatePackageItem(item,item.getItemQuantity()); 
					packItemList.add(opm);
					//更新订单行锁定数量,包裹数量
					item.setLockAmount(item.getItemQuantity());
					item.setPackageAmount(item.getItemQuantity());
					orderItemDao.update(item);
				}
				
				if (packItemList.size()>0){
					String newpackId = addPackage(orderId,packItemList);
					Map<String,OrderPackageItem> packItemMap = new HashMap<String,OrderPackageItem>();		//合并相同sku
					for(OrderPackageItem opim:packItemList){
						opim.setPackageId(newpackId);
						if (!packItemMap.containsKey(opim.getSku())){
							packItemMap.put(opim.getSku(), opim);
						}else{
							OrderPackageItem opim1 = packItemMap.get(opim.getSku());
							opim1.setPackageAmount(opim1.getPackageAmount()+opim.getPackageAmount());
							opim1.setOrderAmount(opim1.getOrderAmount()+opim.getOrderAmount());
							packItemMap.put(opim.getSku(), opim1);
						}
					}
					for(OrderPackageItem opim:packItemMap.values()){
						orderPackageItemDao.insert(opim);
					}
				}
				
				//保存日志
				OrderLog log = new OrderLog();
				log.setOrderId(orderId);
				log.setOperTime(new Date());
				log.setOperUserId(curUser);
				log.setLog("订单状态:已生成包裹");
				orderLogDao.insert(log);
			}
		}
	}
	
	
	/**
	 * 
	 * @param item 订单行
	 * @param lockCount  sku包裹行本次锁定数量 
	 * @return
	 */
	private OrderPackageItem generatePackageItem(OrderItem item,Integer lockCount){
		OrderPackageItem opItem = new OrderPackageItem();
		opItem.setOrderId(item.getOrderId());
		opItem.setSku(item.getSku());
		opItem.setOrderAmount(item.getItemQuantity());
		opItem.setPackageAmount(lockCount);
		opItem.setShipmentAmount(0);
		opItem.setCreatedTime(new Date());
		opItem.setPrice(item.getItemPrice());
		opItem.setTotalPrice(opItem.getPrice().multiply(BigDecimal.valueOf(opItem.getPackageAmount())));
		GoodsPackingMaterial material = this.getPackageMaterial(item.getSku());
		opItem.setSpecifications(material!=null?material.getRules():"");// 包装规格
		opItem.setSpecificationModel(material!=null?material.getModel():"");
		return opItem; 
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

	/***
	 * 
	 * 添加包裹头
	 * @param orderId 订单ID
	 * kenny
	 * 
	 */
	private String addPackage(Integer orderId, List<OrderPackageItem> items) {
		OrderPackage pg = new OrderPackage();
		Order order = orderDao.load(orderId);
		pg.setId(this.getPackageId());
		pg.setOrderId(orderId);
		if (order.getIsSend() == null) {
			pg.setIsSend((short) 0);// 是否标发
		} else {
			pg.setIsSend(order.getIsSend());// 是否标发
		}
		if (order.getPrintedFlag() == null) {
			pg.setPrintedFlag((short) 0);// 否打印
		} else {
			pg.setPrintedFlag(order.getPrintedFlag());// 是否打印
		}
		BigDecimal pw = this.getPackageWeight(order);//获得最重的包装袋
		if (items != null && items.size() > 0) {
			pg.setPackageWeight(getPackageWeight(items).add(pw)); // 包裹重量
		} else {
			pg.setPackageWeight(this.getOrderWeight(order.getItems()).add(pw));// 包裹重量
		}

		pg.setElectronWeight(null);// 电子称重量

		if (order.getTrackNumber() == null) {
			if (order.getShippingName() != null) {
				pg.setStatus(2);
			} else {
				pg.setStatus(1);
			}
		} else {
			pg.setStatus(3);
		}

		
		pg.setCreatedTime(new Date());
		pg.setTrackNumber(order.getTrackNumber());// 发货跟踪号码
		pg.setTrackNumberRef(order.getTrackNumberRef());
		
		OrderPackageItem temp = null;
		Map<String, String> skuMap = new HashMap<String, String>();
		if (items != null && items.size() > 0) {
			temp = items.get(0);
			for (OrderPackageItem item : items) {
				skuMap.put(item.getSku(), item.getSku());
			}
			pg.setSpecifications(temp!=null?temp.getSpecifications():null);// 包装规格
			pg.setIsMix(skuMap.size() == 1 ? 0 : 1); // 是否混合包裹
		} else {
			pg.setSpecifications(null); // 包装规格 --先为空
			pg.setIsMix(null);// 是否混合包裹--先为空
		}
		pg.setShippingName(order.getShippingName());// 发货方式？
		pg.setScannedTime(null);// 扫描时间
		pg.setHandoverTime(null);// 物流交接时间

		if (order.getShippingFee() == null) {
			pg.setShippingFee(new BigDecimal(0));// 包裹运费？
		} else {
			pg.setShippingFee(order.getShippingFee());// 包裹运费
		}

		pg.setScanFlag(0);// 是否扫描
		orderPackageDao.insert(pg);
		return pg.getId();
	}

	/**
	 * 生成包裹子明细
	 * 1锁定库存
	 * 2回写订单明细
	 * 3回写包裹头
	 * @param items 
	 * @param id 订单ID
	 * KENNY
	 */
	private void addPackageItem(List<OrderItem> items,Integer orderId){
		List<String> array = new ArrayList<String>();
		String packageFlag = "";
		
		for (int n = 0; n < items.size(); n++) {
			GoodsInventoryLock goodsLock = goodsInventoryLockDao.findByOrderIdAndSku(orderId, items.get(n).getSku());
			OrderItem item = items.get(n);
			Integer skuCount = goodsInventoryDao.getCountBySku(item.getSku());		//获得仓库sku数量
			skuCount = skuCount==null?0:skuCount;
			//sku已锁定则更新锁定;未锁定 则新增锁定
			if (goodsLock == null){
				GoodsInventoryLock record = new GoodsInventoryLock();
				record.setGoodsSku(item.getSku());
				record.setLockCount(skuCount<item.getItemQuantity()?skuCount:item.getItemQuantity());
				record.setSaleOrderId(item.getOrderId());
				record.setLastUpdatedTime(new Date());
				this.goodsInventoryLockDao.insert(record);
			}else{
				
				/*//如果sku未全部锁定,则更新锁定信息
					if (goodsLock.getLockCount()!=item.getItemQuantity()){
					Integer needLock = item.getItemQuantity()-goodsLock.getLockCount();
					skuCount = skuCount - goodsLock.getLockCount();		//实际库存-锁定数量
					//仓库sku数量大于0才更新锁定信息
					if (skuCount!=null && skuCount>0){
						needLock = needLock>skuCount?skuCount:needLock;
						goodsLock.setLockCount(goodsLock.getLockCount()+needLock);
						goodsLock.setLastUpdatedTime(new Date());
					}
				}
				*/
				
				
			/* 更新库存 */
			GoodsInventoryLock record = new GoodsInventoryLock();
			record.setGoodsSku(item.getSku());
			record.setSaleOrderId(orderId);
			record.setLockCount(item.getItemQuantity());
			record.setLastUpdatedTime(new Date());
			goodsInventoryLockDao.updateByOrderIdAndSku(record);

			}
			/* 生成包裹子明细 */
			OrderPackageItem pItem = new OrderPackageItem();
			List<OrderPackage> orderPackage = orderPackageDao.findByOrderId(orderId);
			packageFlag = orderPackage.get(0).getId();//处理方式不对
			pItem.setPackageId(packageFlag);
			pItem.setOrderId(item.getOrderId());
			pItem.setSku(item.getSku());
			pItem.setOrderAmount(item.getItemQuantity());// sku购买数量
			Integer buyCount = item.getItemQuantity();
			pItem.setPackageAmount(buyCount);// 本包裹所含sku的数量
			pItem.setShipmentAmount(0);// 出货数量
			pItem.setCreatedTime(new Date());//
			BigDecimal price = item.getItemPrice();
			pItem.setPrice(price);// 单价
			pItem.setTotalPrice(price.multiply(BigDecimal.valueOf(buyCount)));// 单价*包裹数量
			
			GoodsPackingMaterial pm = this.getPackageMaterial(items.get(n).getSku());
			pItem.setSpecifications(pm == null ? null : pm.getRules());// 包装规格
			orderPackageItemDao.insert(pItem);
			
			if (pItem.getSpecifications() != null) {
				array.add(pItem.getSpecifications());
			}
			
			/*回写订单明细*/
			OrderItem orderItem = new OrderItem();
			orderItem.setLockAmount(buyCount);
			orderItem.setPackageAmount(buyCount);
			orderItem.setSku(item.getSku());
			orderItem.setOrderId(orderId);
			orderItemDao.updateBySkuAndOrderId(orderItem);
		}
		
		/*回写包裹头的包装规格*/
		OrderPackage orderPackage = new OrderPackage();
		BigDecimal weight = this.getOrderWeight(items);
		orderPackage.setId(packageFlag);
		orderPackage.setPackageWeight(weight);
		orderPackage.setSpecifications(this.setSpecifications(array));
		if(items.size()>1){
			orderPackage.setIsMix(1);//是混合
		}else{
			orderPackage.setIsMix(0);//非混合
		}
		orderPackageDao.update(orderPackage);
		
	}
	
	/**
	 * 得到最大的包装规格
	 * @param array
	 * @return
	 */
	private String  setSpecifications(List<String> array){
		if (array==null || array.size()==0){
			return "";
		}else{
			
		Collections.sort(array);
		String maxValue = array.get(array.size()-1);
		return maxValue;
		}
	}


	@Override
	public OrderPackage getByTrackNumber(String trackNumber) {
		if (StringUtils.isEmpty(trackNumber)){
			return null;
		}
		OrderPackage pack = this.orderPackageDao.getByTrackNumber(trackNumber);
//		if (pack!=null){
//			Order order = orderDao.load(pack.getOrderId()) ;
//			pack.setBuyerEmail(order==null?"":order.getBuyinfo().getBuyerEmail());
//			pack.setBuyerUserId(order==null?"":order.getBuyinfo().getBuyerUserId());
//		}
		return pack;
	}

	@Override
	public int traceNumberImport(List<OrderPackage> list) {
		return this.orderPackageDao.traceNumberImport(list);
	}
	
	public int rejectPackBytraceNumber(List<String> list){
		return this.orderPackageDao.rejectPackBytraceNumber(list);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public OrderPackage confirmScan(String trackNumber, String userCode) {
		checkArgument(trackNumber != null, "缺少跟踪号");
		
		OrderPackage p = this.orderPackageDao.getByTrackNumber(trackNumber);
		checkArgument(p != null, "找不到跟踪号为 %s 的包裹", trackNumber);
		
		OrderPackageStatus nowStatus = OrderPackageStatus.valueOfCode(p.getStatus());
		checkArgument(OrderPackageStatus.PRINTED.code().equals(p.getStatus()), 
				"包裹 %s：%s", trackNumber, nowStatus.desc());
		
		Order order = orderDao.load(p.getOrderId());
		checkArgument(order != null, "未找到 %s 对应的订单信息，订单ID: %s", trackNumber, p.getOrderId());
		
		// 已取消的单不能扫描
		checkArgument(! (OrderStatusEnum.CANCEL.code().shortValue() == order.getOrderStatus()), 
				"包裹 %s 对应的 订单已取消 不能进行扫描", trackNumber);
		
		// 已暂停的单不能扫描
		checkArgument(! (order.getSuspend() != null && order.getSuspend() == 1), 
				"包裹 %s 对应的 订单已暂停 不能进行扫描", trackNumber);
		
		// 已修改收货地址的订单不能扫描
				checkArgument(! (order.getBuyinfo()!=null && order.getBuyinfo().getUpdateFlag()!=null && order.getBuyinfo().getUpdateFlag()==1), 
						"包裹 %s 对应的 订单已修改收货地址不能进行扫描", trackNumber);
		
		// 非自营卖家的订单, 必须有称重 和 运费信息
		//Seller seller = this.sellerDao.getByPlatformAccountId(order.getAccountId());
		/*if ("0".equals(seller.getSelfFlag())) {*/ //公司包裹增加称重
			checkArgument(order.getPackageWeight() != null
					&& order.getPackageWeight().doubleValue() > 0,
					"包裹 %s 卖家订单必须称重后才能发货。", trackNumber);
			
			checkArgument(order.getPackageShipfee() != null
					&& order.getPackageShipfee().doubleValue() > 0,
					"包裹 %s 卖家订单运费必须根据称重计算，目前运费：%s。", trackNumber,
					order.getPackageShipfee());
		/*}*/
		
		// 更改包裹状态为 已扫描
		OrderPackage op = new OrderPackage();
		op.setId(p.getId());
		op.setStatus(OrderPackageStatus.SCANNED.code());
		op.setScanFlag(1);  		//设置已扫描标记 
		op.setScannedTime(new Date());
		op.setScannedUser(userCode);
		this.orderPackageDao.update(op);
		
		// 1、[包裹上、订单行 ]锁定库存减少，发货数量增加；
		List<OrderPackageItem> items = this.orderPackageItemDao.findByPackage(p.getId());
		checkArgument(items != null && items.size() > 0, "缺少包裹行");
		
		Date now = new Date();
		List<IoOrderItem> iois = new ArrayList<>(items.size()); // 出库单详细信息
		for (OrderPackageItem item : items) {
			GoodsInventoryLock lock = this.goodsInventoryLockDao.findByOrderIdAndSku(p.getOrderId(), item.getSku());
			if (lock != null) {
				// 如果锁定的数量 大于包裹中的数量，说明有多个包裹，此次之扣减本包裹中的数量
				// 否则删除掉此锁定记录
				if (lock.getLockCount() > item.getPackageAmount()) {
					lock.setLockCount(lock.getLockCount() - item.getPackageAmount());
					this.goodsInventoryLockDao.update(lock);
				} else {
					this.goodsInventoryLockDao.deleteById(lock.getId());
				}
			}
			
			// 修改发货数量
			item.setShipmentAmount(item.getShipmentAmount() + item.getPackageAmount());
			this.orderPackageItemDao.update(item);
			
			// 修改订单行锁定数量,发货数量
			{
				// 包裹行中的数量
				int packedQty = item.getPackageAmount() == null ? 0 : item.getPackageAmount();
				
				List<OrderItem> orderItems = orderItemDao.queryByOrder(item.getOrderId());
				for (OrderItem oItem : orderItems) {
					if (! item.getSku().equals(oItem.getSku())) {
						continue;
					}
					
					// 订单行已经锁定的数量
					int lockedQty = oItem.getLockAmount() == null ? 0 : oItem.getLockAmount();
					if (lockedQty == 0) {
						continue;
					}
					
					// 订单行发货数量
					int shippedQty = oItem.getShipmentAmount() == null ? 0 : oItem.getShipmentAmount();
					
					OrderItem tmp = new OrderItem();
					tmp.setId(oItem.getId());
					
					// 包裹数量 > 此订单行锁定数量时,锁定数量清空,发货数量增加, 然后继续处理下一订单行
					if (lockedQty < packedQty) {
						tmp.setLockAmount(0);
						tmp.setShipmentAmount(shippedQty + lockedQty);
						this.orderItemDao.update(tmp);
						packedQty = packedQty - lockedQty;
					} 
					// 包裹数量 <= 此订单行数量,只能处理此订单行
					else {
						tmp.setLockAmount(lockedQty - packedQty);
						tmp.setShipmentAmount(shippedQty + packedQty);
						this.orderItemDao.update(tmp);
						break;
					}
				}
			}
			
			IoOrderItem ioi = new IoOrderItem();
			ioi.setGoodsSku(item.getSku());
			ioi.setQualifiedCount(item.getPackageAmount());
			Goods goods = goodsDao.findBySku(item.getSku());
			ioi.setStoreId(null!=goods?goods.getStoreId():null);
			ioi.setStoreShelfId(null!=goods?goods.getStoreShelfId():null);
			ioi.setLastUpdatedTime(now);
			ioi.setGoodsName(goods.getName());
			// 查询加权成本价
			GoodsInventoryCost gc = this.goodsInventoryCostDao.load(item.getSku());
			ioi.setGoodsCost(gc == null ? (new BigDecimal(0)) : gc.getPrice());
			
			iois.add(ioi);
			
		}
		
		// 2、另生成一张自动审核的出库单
		IoOrder io = new IoOrder();
		io.setType(1);
		io.setTypeDetail(10);
		io.setAuditStatus(1);
		io.setAuditTime(now);
		io.setBuyOrderNo(String.valueOf(p.getOrderId()));
		io.setCreatedTime(now);
		io.setLastUpdatedTime(now);
		io.setAccountName(order.getAccountName());
		io.setSellOrderId(order.getId());
		
		io.setItems(iois);
		String dateStr = dft.format(Calendar.getInstance().getTime());
		String fmt =String.format("LS-%s-",dateStr)+"%d";
		io.setSerialNumber(tableKeyService.nextSerialNumber("o_orders",fmt));			//设置出库流水号
		
		this.ioOrderService.save(io);
		
		// 3、若某订单下的所有包裹都已经扫描，标记订单为 已发货、已扫描，同时扣除订单的费用
		List<OrderPackage> orderPackages = this.orderPackageDao.findByOrderId(p.getOrderId());
		
		boolean allScanned = true;
		if (orderPackages.size() > 0) {
			for (OrderPackage tmp : orderPackages) {
				if (! OrderPackageStatus.SCANNED.code().equals(tmp.getStatus())) {
					allScanned = false;
					break;
				}
			}
		}
		
		if (allScanned) {
			Order tmpOrder = new Order();
			tmpOrder.setId(p.getOrderId());
			tmpOrder.setOrderStatus(OrderStatusEnum.SHIPPED.code().shortValue());
			tmpOrder.setScannedTime(now);
			this.orderDao.update(tmpOrder);
			 
			this.deductSellerFee(p.getOrderId());
		}
		
		//4、释放卖家预扣费用
		sellerFeeWithHoldDao.deletedByOrderId(p.getOrderId());
		
		// 日志
		OrderLog log = new OrderLog();
		log.setOrderId(order.getId());
		log.setOperTime(now);
		log.setOperUserId(userCode);
		log.setLog("订单已扫描出库");
		this.orderLogDao.insert(log);
		
		OrderPackage tmp = this.orderPackageDao.getByTrackNumber(trackNumber);
		return tmp;
	}

	private void deductSellerFee(Integer orderId) {
		Order o = this.orderDao.load(orderId);
		checkArgument(o != null, "找不到 %s 对应的订单", orderId);
		
		int accountId = o.getAccountId();
		PlatformAccount account = this.platformAccountService.loadById(accountId);
		checkArgument(account != null, "找不到 %s 对应的账号信息", accountId);
	
		Integer sellerId = account.getSellerId();
		
		// 默认卖家不处理费用
		if (sellerId == App.DEFAULT_SELLER_ID) {
			return;
		}
		
		
		//卖家成本直接取 订单上的卖家成本 
		BigDecimal goodsCostSum1 = o.getCost();
		checkArgument(goodsCostSum1 != null && goodsCostSum1.doubleValue() > 0, "缺少卖家成本信息，扣卖家费用失败。");
		
		//BigDecimal packageFee = o.getPackingMaterialFee() != null ? o.getPackingMaterialFee() : new BigDecimal(0); // 去掉包装袋费用
		BigDecimal goodsCostSum = goodsCostSum1.setScale(2, RoundingMode.CEILING);
		
		checkArgument(goodsCostSum != null && goodsCostSum.doubleValue() > 0, "缺少卖家成本信息，扣卖家费用失败。");
		
		// 卖家费用：卖家成本 + 订单处理费（一个包裹２元）+ 订单的运费
		goodsCostSum = goodsCostSum.setScale(2, RoundingMode.CEILING);
		BigDecimal handleFee = new BigDecimal(2).setScale(2, RoundingMode.CEILING);
		BigDecimal shippingFee = (o.getPackageShipfee() != null ? o.getPackageShipfee() : 
			(o.getShippingFee() != null ? o.getShippingFee() : new BigDecimal(0))).setScale(2, RoundingMode.CEILING);

		BigDecimal sellerFee = goodsCostSum.add(handleFee).add(shippingFee).setScale(2, RoundingMode.CEILING);
		
		String note = String.format("订单费用，订单: %s, 成本: %s, 处理费: %s, 运费: %s", orderId, goodsCostSum, handleFee, shippingFee);
		
		try {
			this.sellerDepositService.decreaseDeposit(sellerId, sellerFee, note);
		} catch (DepositBalanceShortageException e) {
			Seller seller = this.sellerDao.load(sellerId);
			throw new IllegalArgumentException(String.format("卖家(%s)余额不足", seller.getContacts()));
		}
		
	}

	/**
	 * 修改包裹的发货方式
	 * 
	 * 1修改 包裹表 信息
	 * 2修改  订单表 信息
	 */
	@Override
	@Transactional
	@Deprecated
	public void updateShipping(String packageId, Integer id) {
		OrderShippingFee osf = orderShippingFeeDao.getById(id);
		
		OrderPackage op = new OrderPackage();
		op.setId(packageId);
		op.setShippingName(osf.getShippingName());
		op.setShippingFee(osf.getShippingFee());
		orderPackageDao.update(op);//修改 包裹表 信息
		
		OrderShippingFee param = new OrderShippingFee();
		param.setOrderId(osf.getOrderId());
		param.setShippingFee(osf.getShippingFee());
		param.setShippingName(osf.getShippingName());
		orderDao.setShipping(param);//修改 订单表 的信息
	}
	
	
	@Override
	public void batcheditPackage(List<OrderPackage> list) {
		if (null!=list && list.size()>0){
			for(OrderPackage pack:list){
				orderPackageDao.update(pack);
			}
		}
	}
	
	public void update(OrderPackage orderPackage){
		orderPackageDao.update(orderPackage);
	}

	/**
	 * 计算包裹id
	 * 规则生成包裹ID
	 * @return
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
		 * 根据sku获得包装规格
		 * @param sku
		 * @return
		 */
		private GoodsPackingMaterial getPackageMaterial(String sku){
			Goods gs = goodsDao.findBySku(sku);
			Integer id = gs.getPackingMaterialId();
			return id == null ? null : packingMaterialDao.getById(id);
		}

	/**
	 * 订单列表点击标发，检查包裹 ,更新包裹
	 * 
	 */
		
	@Override
	public void updateShipInfo(List<Integer> orderId){
		for (int n = 0; n < orderId.size(); n++) {
		
			
			Order order = orderDao.load(orderId.get(n));
			
			List<OrderPackage> orderPackage = orderPackageDao.findByOrderId(orderId.get(n));
			if(orderPackage.size() == 0){
				logger.info("订单页面点击标发----此订单没有生成包裹");
			}else{
				
				List<String> packageIdList = new ArrayList<String>();
				for (int i = 0; i < orderPackage.size(); i++) {
					packageIdList.add(orderPackage.get(i).getId());
				}
				
				Collections.sort(packageIdList);
				
				String minPackageId = packageIdList.get(0);
				OrderPackage  param = new OrderPackage();
				param.setTrackNumber(order.getTrackNumber());
				param.setIsSend(order.getIsSend());
				param.setShippingName(order.getShippingName());
				param.setId(minPackageId);
				
				orderPackageDao.update(param);
			}
			
		}
		
	}
	
	
	/***
	 * 
	 * 包裹页面点击标发按钮
	 * @param packageId
	 */
	public void updateShipInfo2(List<String> packageId){
		for (int i = 0; i < packageId.size(); i++) {
			
			OrderPackage orderPackage = orderPackageDao.getById(packageId.get(i));
			
			Integer orderId = orderPackage.getOrderId();
			
			Order order = orderDao.load(orderId);
			
			if(order.getIsSend() != null){
				
				logger.info("包裹页面点击标发-----此订单已经有包裹标发");
				
			}else{
				
				Order param = new Order();
				param.setShippingName(orderPackage.getShippingName());
				param.setIsSend(orderPackage.getIsSend());
				param.setTrackNumber(orderPackage.getTrackNumber());
				param.setId(orderId);
				orderDao.update(param);
				
			}
		}
		
	}
	
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
	
	/***
	 * 
	 * 包裹匹配物流方式
	 */
	// 账号ID  国家  金额  订单的重量  订单SKU
	public void matchingShipping2(){
		List<OrderPackage> list =  orderPackageDao.findNoShip();//检查没有匹配的
		
		for (int i = 0; i < list.size(); i++) {
			
			String id = list.get(i).getId();
	
			List<OrderPackageItem> items =  orderPackageItemDao.findByPackage(id);
			
			List<String> skus = new ArrayList<String>();
			
			for (int j = 0; j < items.size(); j++) {
				skus.add(items.get(j).getSku());
			}
			
			Order order = orderDao.load(list.get(i).getOrderId());
			if(order == null){
				logger.info("包裹匹配物流方式----订单部存在");
				continue;
			}
			String countryOrder = order.getBuyinfo().getShippingCountryName();
			if(countryOrder == null){
				logger.info("包裹匹配物流方式----国家不存在");
				continue;
			}
			Shipping shipping = new Shipping();
			if(order.getAccountId() == null){
				logger.info("包裹匹配物流方式----账号不存在");
				continue;
			}
			shipping.setAccount(ObjectUtils.toString(order.getAccountId(), null));//账号
			shipping.setCountry(countryOrder);//国家
			if(list.get(i).getPackageWeight() == null){
				logger.info("包裹匹配物流方式----重量为空值");
				continue;
			}
			shipping.setWeightOrder(list.get(i).getPackageWeight());//重量
			shipping.setAmountOrder(this.returnAmount(items));//金额

			if(skus.size() == 0){
				logger.info("包裹匹配物流方式----SKU不存在");
				continue;
			}
			shipping.setSkus(skus);//sku
			
			List<Shipping> ship = shippingDao.findByParam(shipping, skus);
			if(ship.size() == 0){
				logger.info("包裹匹配物流方式--没有获得匹配的物流方式");
				continue;
			}
			
			Map<String, BigDecimal> tm = new TreeMap<String, BigDecimal>();
			
			for (int n = 0; n < ship.size(); n++) {
				String shippingName = ship.get(n).getShippingName();
				Integer shipId = ship.get(n).getId();
				BigDecimal shiFee = this.countShiFee(countryOrder,shipId,list.get(i).getPackageWeight());
				tm.put(shiFee+"-"+shippingName,shiFee);//存放到map中
			}
			
			
			Iterator<Entry<String,BigDecimal>> it = tm.entrySet().iterator();
			
			String key = "";
			BigDecimal value = null;
			
			while(it.hasNext()){
				Map.Entry<String,BigDecimal> entry =(Map.Entry<String,BigDecimal>) it.next();
				key =  (String) entry.getKey();
				value=(BigDecimal) entry.getValue();
			}
			
			int index = key.indexOf("-");
			String shipName = key.substring(index+1, key.length());
			/*回写发货方式*/
			OrderPackage orderPackage = new OrderPackage();
			orderPackage.setShippingName(shipName);
			orderPackage.setShippingFee(value);
			orderPackage.setStatus(2);//状态改为2
			orderPackage.setId(id);
			orderPackageDao.update(orderPackage);
			
		}
		
	}
	
	public BigDecimal countShiFee1(String country,Integer shipId,BigDecimal weightOrder){
		BigDecimal total = new BigDecimal(0);
		ShippingFee shippingFee= this.shippingFeeDao.getByCountry(country, shipId);//获取运费计算数据
		if(shippingFee == null){
			total = new BigDecimal(0);
		}else{
			if(shippingFee.getType() == 1){
				
				//（邮费*订单实际重量+处理费+增项费用－减项费用）*折扣
				BigDecimal postFee = shippingFee.getPostFee();
				BigDecimal addFee = shippingFee.getAddFee();
				BigDecimal subtFee = shippingFee.getSubtractFee();
				BigDecimal serviceFee = shippingFee.getServiceFee();
				BigDecimal wAmount = postFee.multiply(weightOrder);
				BigDecimal discRate = shippingFee.getDiscountRate();
				
				BigDecimal sum = wAmount.add(serviceFee).add(addFee).subtract(subtFee);
				BigDecimal total1 = sum.multiply(discRate);
				total = total1.setScale(2, BigDecimal.ROUND_HALF_UP);
				
			}else if(shippingFee.getType() == 2){
				BigDecimal first = shippingFee.getFirstWeight();
				BigDecimal firstAmount = shippingFee.getFirstWeightAmount();
				BigDecimal add = shippingFee.getAddWeight();
				BigDecimal addAmount = shippingFee.getAddWeightAmount();
				BigDecimal registeredFee = shippingFee.getRegisteredFee();
				BigDecimal serviceFee = shippingFee.getServiceFee();
				BigDecimal disc = shippingFee.getDiscount();
				BigDecimal discRate = shippingFee.getDiscountRate();
				
				//（包裹重量－首重）/续重
				BigDecimal weightToG = weightOrder.multiply(new BigDecimal(1000));//千克 转换为克
				if(weightToG.compareTo(first) != 1){
					//total = firstAmount;
					
					BigDecimal sum1 = firstAmount.add(registeredFee).add(serviceFee).subtract(disc);
					BigDecimal count1 = sum1.multiply(discRate);
					total = count1.setScale(2,BigDecimal.ROUND_HALF_UP);
				}else{
					
				BigDecimal subtract =  weightToG.subtract(first);
				BigDecimal divideSet = subtract.divide(add,0);
				BigDecimal addWeight= divideSet.setScale(0, BigDecimal.ROUND_UP);
				//续重部分金额＝续重部分*续重金额
				BigDecimal addAmount1 = addWeight.multiply(addAmount);
				//（首重金额+续重部分金额+挂号费+处理费－折扣）*折扣率
				BigDecimal sum = firstAmount.add(addAmount1).add(registeredFee).add(serviceFee);
				BigDecimal count = sum.subtract(disc);
				total = count.multiply(discRate);
				}
			}
			

		}
		return total;
	}

	
	public BigDecimal addAmount(BigDecimal p1,BigDecimal p2){
		return p1.add(p2);
	}
	
	private BigDecimal returnAmount(List<OrderPackageItem> items){
		BigDecimal price = new BigDecimal(0);
		for (int i = 0; i < items.size(); i++) {
			price = this.addAmount(items.get(i).getTotalPrice(), price);
		}
		return price.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	
	/***
	 * 自动生成包裹
	 * 
	 * 将状态为4 的订单  生成包裹
	 * 
	 * 状态为4表示 {部分生成包裹}
	 * 
	 */
	@Override
	public void generatePackage4(){
		List<Order> orders = orderDao.getByState(4);
		
		for (int i = 0; i < orders.size(); i++) {
			
			Order thisOrder = orders.get(i);
			
			if(thisOrder.getSuspend() == 1){
				//订单已经暂停 不处理
				continue;
			}
			
			List<OrderItem> items = orderItemDao.queryByOrder(thisOrder.getId());
			
			boolean flag = true;
			for (int j = 0; j < items.size(); j++) {
				
				OrderItem thisItem = items.get(j);
				
				Integer resCount = goodsInventoryDao.getCountBySku(thisItem.getSku());// 实际库存SUM
				if(resCount == 1){
					resCount = 0;
				}
				Integer lockCount =  goodsInventoryLockDao.getLockCountBySku(thisItem.getSku());//锁定库存SUM
				if(lockCount == null){
					
					lockCount = 0;
				}
				
				Integer count = resCount - lockCount;//实际库存 减 锁定库存
				
				Integer buyCount = thisItem.getItemQuantity();//购买数量
				
				Integer packCount = thisItem.getPackageAmount();//包裹数量
				if(packCount == null){
					packCount = 0;
				}
				
				Integer canelCount = thisItem.getCancelAmount();//取消数量
				if(canelCount == null){
					canelCount = 0;
				}
				
				Integer needCount = buyCount - packCount - canelCount;
				
				if(needCount > count){
					
					logger.error("订单自动生成包裹4---需要数量大于库存数量");
					flag = false;
					break;
				}
			}
			
			if(flag){
				// 锁库存
				orderDao.updateStateById(5, thisOrder.getId()); //改变状态=5(订单行已经全部生成包裹)
				this.addPackage(thisOrder.getId(), null);// 包裹头
				this.addPackageItem(items, thisOrder.getId());//包裹明细
				
			}
		}
	}

	/***
	 * 检查是否是EUB运输方式
	 * @param shipId
	 * @return
	 */
	public boolean checkEub(Integer shipId){
		Shipping shipping = shippingDao.getById(shipId);
		String name = shipping.getCarrierSn();
		if(("EUB").equals(name) || ("eub").equals(name)){
			return false;
		}else{
			return true;
		}
	}
	
	/***
	 * 包裹页面计算运费函数
	 * @param country
	 * @param shipId
	 * @param weightOrder
	 * @return
	 */
	// 要测试-新方法-旧方法暂时不删除
	public BigDecimal countShiFee(String country,Integer shipId,BigDecimal weightOrder){
		BigDecimal total = new BigDecimal(0);
		ShippingFee shippingFee= this.shippingFeeDao.getByCountry(country, shipId);//获取运费计算数据
		if(shippingFee == null){
			total = new BigDecimal(0);
		}else{

			boolean bl = this.checkEub(shipId);
			
			if(bl){//不是EUB发货
				
				if(shippingFee.getType() == 1){
					
					//（邮费*订单实际重量+处理费+增项费用－减项费用）*折扣
					BigDecimal postFee = shippingFee.getPostFee();
					BigDecimal addFee = shippingFee.getAddFee();
					BigDecimal subtFee = shippingFee.getSubtractFee();
					BigDecimal serviceFee = shippingFee.getServiceFee();
					BigDecimal wAmount = postFee.multiply(weightOrder);
					BigDecimal discRate = shippingFee.getDiscountRate();
					
					BigDecimal sum = wAmount.add(serviceFee).add(addFee).subtract(subtFee);
					BigDecimal total1 = sum.multiply(discRate);
					total = total1.setScale(2, BigDecimal.ROUND_HALF_UP);
					
				}else if(shippingFee.getType() == 2){
					
					BigDecimal first = shippingFee.getFirstWeight();
					BigDecimal firstAmount = shippingFee.getFirstWeightAmount();
					BigDecimal add = shippingFee.getAddWeight();
					BigDecimal addAmount = shippingFee.getAddWeightAmount();
					BigDecimal registeredFee = shippingFee.getRegisteredFee();
					BigDecimal serviceFee = shippingFee.getServiceFee();
					BigDecimal disc = shippingFee.getDiscount();
					BigDecimal discRate = shippingFee.getDiscountRate();
					
					//（包裹重量－首重）/续重
					BigDecimal weightToG = weightOrder.multiply(new BigDecimal(1000));//千克 转换为克
					if(weightToG.compareTo(first) != 1){
						//total = firstAmount;
						
						BigDecimal sum1 = firstAmount.add(registeredFee).add(serviceFee).subtract(disc);
						BigDecimal count1 = sum1.multiply(discRate);
						total = count1.setScale(2,BigDecimal.ROUND_HALF_UP);
					}else{
						
					BigDecimal subtract =  weightToG.subtract(first);
					BigDecimal divideSet = subtract.divide(add,0);
					BigDecimal addWeight= divideSet.setScale(0, BigDecimal.ROUND_UP);
					//续重部分金额＝续重部分*续重金额
					BigDecimal addAmount1 = addWeight.multiply(addAmount);
					//（首重金额+续重部分金额+挂号费+处理费－折扣）*折扣率
					BigDecimal sum = firstAmount.add(addAmount1).add(registeredFee).add(serviceFee);
					BigDecimal count = sum.subtract(disc);
					total = count.multiply(discRate);
					}
				}
				
			}else{//是EUB发货
				
				// （邮费*订单实际重量+处理费+增项费用－减项费用）*折扣
				BigDecimal postFee = shippingFee.getPostFee();
				BigDecimal addFee = shippingFee.getAddFee();
				BigDecimal subtFee = shippingFee.getSubtractFee();
				BigDecimal serviceFee = shippingFee.getServiceFee();
				BigDecimal discRate = shippingFee.getDiscountRate();
				BigDecimal eubMinWeight = new BigDecimal(70);// 最小重量是60g // 修改成70克 by adam @20150930

				if (weightOrder.compareTo(eubMinWeight) != 1) {// 小于60G// 或者// 等于// 60G														
					BigDecimal sum = eubMinWeight.add(serviceFee).add(addFee).subtract(subtFee);
					BigDecimal total1 = sum.multiply(discRate);
					total = total1.setScale(2,BigDecimal.ROUND_HALF_UP);
							
				} else {

					BigDecimal wAmount = postFee.multiply(weightOrder);
					BigDecimal sum = wAmount.add(serviceFee).add(addFee).subtract(subtFee);
					BigDecimal total1 = sum.multiply(discRate);
					total = total1.setScale(2,BigDecimal.ROUND_HALF_UP);
				}
				
			}
			
		}
		
		return total;
	}

	@Override
	public Integer countPackageStatus(Integer status,Integer orderId){
		return orderPackageDao.countPackageStatus(status, orderId);
	}
	
	@Override
	public Integer findCount(OrderPackageParam orderPackage,List<String> ids) {
		return orderPackageDao.findCount(orderPackage,ids);
	}

	@Override
	public List<OrderPackage> find(OrderPackageParam orderPackage,List<String> ids) {
		return orderPackageDao.find(orderPackage,ids);
	}

	@Override
	@Transactional
	public void markPrintFlag(String packageId, String userCode) {
		OrderPackage p = new OrderPackage();
		p.setStatus(4);
		p.setPrintedFlag((short)1);
		p.setPrintedTime(new Date());
		p.setId(packageId);
		p.setPrintedUser(userCode);
		this.orderPackageDao.update(p);
		

		Order record = new Order();
		OrderPackage op = this.getById(packageId);
		record.setId(op.getOrderId());
		record.setPrintedFlag((short)1);//已打印
		this.orderDao.update(record);
		
		OrderLog log = new OrderLog();
		log.setOrderId(op.getOrderId());
		log.setOperTime(new Date());
		log.setOperUserId(userCode);
		log.setLog("订单已被标打印");
		this.orderLogDao.insert(log);
		
		//(已修改收货地址的订单重新打印后,清除收货地址表更新标志)
		record = orderDao.load(op.getOrderId());
		if (record.getBuyinfo().getUpdateFlag()!=null && record.getBuyinfo().getUpdateFlag()==1){
			this.orderDao.updateflagOrderBuyinfo(record.getBuyinfo().getId(), 0);
		}
	}

	@Override
	public Integer updateByTrackNo(OrderPackage orderPackage) {
		
		return this.orderPackageDao.updateByTrackNo(orderPackage);
	}

	@Override
	public Page<OrderPackage> printFindPage(PageRequest pageRequest,
			OrderPackageParam orderPackage) {
		return this.orderPackageDao.printFindPage(pageRequest, orderPackage);
	}
	
	
	
	@Override
	public void readPackageWeight(String trackNumber, String deviceNo) throws WeightCompareException {
		String filePath = App.getConfig("electron.weight.file");
		File file = new File(filePath + deviceNo + ".txt");

		if (file.isFile() && file.exists()) {
			
			String weight = null;
			try {
				weight = FileUtils.readFileToString(file, "utf-8");
			} catch (IOException e) {
				logger.error("读取重量文件出错", e);
			}
			
			if (StringUtils.isNotBlank(weight)) {
				weight = weight.trim();
				if (NumberUtils.toDouble(weight)> 0.002) { //最小产品重量
					
					// 将称重值 和 运费 先写在包裹上面

					OrderPackage op = new OrderPackage();
					op.setElectronWeight(new BigDecimal(weight));
					op.setTrackNumber(trackNumber);
					int updatedCount = this.orderPackageDao.updateByTrackNo(op);// 修改包裹电子称重值
					
					if (updatedCount > 0) {
						// 计算称重运费，并回写订单信息
						this.setPackageFee(trackNumber, new BigDecimal(weight));
						
						boolean result = this.compareWeight(trackNumber, new BigDecimal(weight)); //称重比较
						if (!result) {
							Order order = this.orderDao.getByTrackNo(trackNumber);
							throw new WeightCompareException(trackNumber+"包裹计算/称重的重量差距太大["+ order.getCalcWeight()+":"+weight+"]");
						}
					}
				}
			}
			file.delete();
		} else {
			logger.info("包裹扫描-找不到重量文件");
		}

	}
	
	/****
	 * 
	 * 计算包裹称重运费
	 * 
	 * 称重计算运费 修改订单信息
	 * 称重计算运费 修改包裹信息
	 * 
	 * @return
	 *
	 */
	public void setPackageFee(String trackNo,BigDecimal weightOrder){

		checkArgument(StringUtils.isNotBlank(trackNo), "跟踪号不能为空");
		
		BigDecimal total = new BigDecimal(0);
		
		OrderParam param = new OrderParam();
		param.setTraceNumber(trackNo);
		Order order = this.orderDao.loadByParam(param);
		Shipping ship = this.shippingDao.getByShipName(order.getShippingName());
		
		String country1 = order.getBuyinfo().getShippingCountryName();
		String country2 = order.getBuyinfo().getShippingCountry();
		ShippingFee shipFee = null;
		
		//TODO
		if(ship.getShippFeeType() != null && ship.getShippFeeType() == 1){//重量区间计算
			String thisCountry = country1;
			if(country1 == null){
				thisCountry = country2;
			}
			
			ShippingFee2 feeInfo = this.shippingFee2Dao.getFeeByWeight(ship.getId(), weightOrder, thisCountry);
			if(feeInfo != null){
				total = feeInfo.getShippFee();
			}
			
		}else{//非区间计算
				
			if(country1 != null){
				shipFee = this.shippingFeeDao.getByCountry(country1, ship.getId());
			}else{
				shipFee = this.shippingFeeDao.getByCountry(country2, ship.getId());
			}
		
			if(shipFee == null){
				total = order.getShippingFee() != null ? order.getShippingFee() : new BigDecimal(0);
			}else{
				
				boolean bl = this.checkEub(ship.getId());//检查是否是EUB
				
				if(bl){//不是EUB
					
					
					if(shipFee.getType() == 1){
						
						//NEW （邮费*订单实际重量*折扣)+处理费 +增项费用－减项费用 
						BigDecimal firstAmountSf = new BigDecimal(0.05);//最低重量SF
						BigDecimal postFee = shipFee.getPostFee();
						BigDecimal addFee = shipFee.getAddFee();
						BigDecimal subtFee = shipFee.getSubtractFee();
						BigDecimal serviceFee = shipFee.getServiceFee();
						BigDecimal discRate = shipFee.getDiscountRate();
						boolean blSf = this.checkSf(ship.getCarrierSn());//检查是否是SF
						boolean blIntL = this.checkIntL(ship.getShippingName());//检查是否是国际小包/平邮
						if(blSf || blIntL){
							if(weightOrder.compareTo(firstAmountSf) != 1){
								weightOrder = firstAmountSf;
							}
						}
						
						BigDecimal wAmount = postFee.multiply(weightOrder);
						BigDecimal sf1 =  wAmount.multiply(discRate);
						BigDecimal total1 = sf1.add(serviceFee).add(addFee).subtract(subtFee);
						total = total1.setScale(2, BigDecimal.ROUND_HALF_UP);
					}else{
						
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
				
				}else{
					//NEW(邮费*订单实际重量*折扣)+处理费 +增项费用－减项费用 
					BigDecimal postFee = shipFee.getPostFee();
					BigDecimal addFee = shipFee.getAddFee();
					BigDecimal subtFee = shipFee.getSubtractFee();
					BigDecimal serviceFee = shipFee.getServiceFee();
					BigDecimal discRate = shipFee.getDiscountRate();
					BigDecimal eubMinWeight = new BigDecimal(0.07);// 最小重量是60g // 修改成70克 by adam @20150930
					
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
			}
			
			
			
		}
		
			
			//回写订单称重/运费信息
			Order record = new Order();
			record.setId(order.getId());//订单ID
			record.setPackageWeight(weightOrder);//包裹真实重量
			record.setPackageShipfee(total); //包裹的称重运费
			this.orderDao.update(record);
			
			//回写包裹的的运费
			OrderPackage op = new OrderPackage();
			op.setTrackNumber(trackNo);
			op.setShippingFee(total);
			this.updateByTrackNo(op);
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
	
	/****
	 * 称重重量与计算重量型比较
	 * @param trackNumber
	 * @param electronWeight
	 * @return
	 */
	private boolean compareWeight(String trackNumber,BigDecimal electronWeight){
		checkArgument(StringUtils.isNotBlank(trackNumber), "跟踪号不能为空");
		
		boolean bl = false;
		OrderParam op = new OrderParam();
		op.setTraceNumber(trackNumber);
		Order thisOrder = this.orderDao.loadByParam(op);

		if (thisOrder == null) {
			throw new IllegalArgumentException(trackNumber + "找不到对应的订单");
		} else {
			BigDecimal rand = new BigDecimal(0.3).setScale(2,
					BigDecimal.ROUND_HALF_UP); // 浮动数
			BigDecimal calcWeight = thisOrder.getCalcWeight(); // 计算重量
			if (calcWeight == null) {
				calcWeight = new BigDecimal(0);
			} else {

				BigDecimal subtractWeight = electronWeight.subtract(calcWeight)
						.setScale(2, BigDecimal.ROUND_HALF_UP).abs();// 误差绝对值
				BigDecimal result = subtractWeight.divide(calcWeight, 2)
						.setScale(2, BigDecimal.ROUND_HALF_UP);// 误差占比
				if (result.compareTo(rand) != 1) {// 误差占比 小于/等于 0.3
					bl = true;
				} else {
					bl = false;
				}
			}
		}
		return bl;
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
	public void cancelweighService(String trackNumber) {
		OrderPackage op = this.orderPackageDao.getByTrackNumber(trackNumber);
		//回写订单称重/运费信息
		Order record = new Order();
		record.setId(op.getOrderId());//订单ID
		record.setPackageWeight(new BigDecimal(0));//包裹真实重量
		record.setPackageShipfee(new BigDecimal(0)); //包裹的称重运费
		this.orderDao.update(record);
		
	}
	
}
