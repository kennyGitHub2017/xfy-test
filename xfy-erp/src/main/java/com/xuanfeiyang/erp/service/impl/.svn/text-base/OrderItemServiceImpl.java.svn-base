package com.xuanfeiyang.erp.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xuanfeiyang.erp.dao.GoodsCombinationDao;
import com.xuanfeiyang.erp.dao.GoodsCombinationItemDao;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.OrderItemDao;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsCombination;
import com.xuanfeiyang.erp.domain.GoodsCombinationItem;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.SkuMapping;
import com.xuanfeiyang.erp.service.OrderItemService;
import com.xuanfeiyang.erp.service.SkuMappingService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	private static Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

	@Resource
	private OrderItemDao orderItemDao;

	@Resource
	private GoodsDao goodsDao;

	@Resource
	private GoodsCombinationDao goodsCombinationDao;
	
	@Resource
	private GoodsCombinationItemDao goodsCombinationItemDao;
	
	@Resource
	private SkuMappingService skuMappingService;

	private LoadingCache<String, List<String>> oldSkuCache = null;
	
	/*
	 * 新SKU缓存: SKU -> 1
	 */
	private LoadingCache<String, String> goodsSkuCache = null;
	
	@PostConstruct
	protected void init() {
		logger.info("====== init cache.");
		if (goodsSkuCache == null) {
			goodsSkuCache = CacheBuilder.newBuilder().maximumSize(7000)
					//.weakKeys()
					//.softValues()
					//.refreshAfterWrite(60, TimeUnit.MINUTES)
					.build(new CacheLoader<String, String>() {
		
						@Override
						public String load(String key) throws Exception {
							Goods g = goodsDao.findBySku(key);
							if (g != null) {
								return "1";
							}
							
							return "0";
						}
		
					});
		}
		
		if (oldSkuCache == null) {
			oldSkuCache = CacheBuilder.newBuilder().maximumSize(2)
					//.weakKeys()
					//.softValues()
					.refreshAfterWrite(60, TimeUnit.MINUTES)
					.build(new CacheLoader<String, List<String>>() {

						@Override
						public List<String> load(String key) throws Exception {
							
							List<String> goodsSkus = goodsDao.findAllOldSkus();
							List<String> combSkus = goodsCombinationDao
									.findAllSkus();

							goodsSkus.addAll(combSkus);

							if (goodsSkus != null) {
								Collections.sort(goodsSkus,
										new Comparator<String>() {

											@Override
											public int compare(String o1, String o2) {
												return o2.length() - o1.length();
											}
										});
							}

							logger.debug("goodssku:{}",goodsSkus);
							return goodsSkus;
						}

					});
		}
	}

	@Override
	public void updateSku(Integer id, String sku) {
		OrderItem tmp = orderItemDao.load(id);
		Preconditions.checkArgument(tmp != null, "订单行不存在");

		// 首先查找普通商品
		Goods goods = goodsDao.findByOldSkuOrGoodsSku(sku);
		
		if (goods != null) {
			OrderItem item = new OrderItem();
			item.setId(tmp.getId());
			item.setOldSku(goods.getOldSku());
			item.setSku(goods.getGoodsSku());
			
			orderItemDao.update(item);
			
			// 成功匹配后将匹配结果保存到 sku_mapping 
			try {
				this.saveSkuMapping(tmp.getItemSku(), goods.getGoodsSku(), goods.getOldSku());
			} catch (Exception e) {
				logger.error("保存 sku mapping 出错", e);
			}
		} else {
			// 再检查是否组合SKU
			GoodsCombination gc = this.goodsCombinationDao.loadBySku(sku);
			Preconditions.checkArgument(gc != null, "系统无法匹配该sku: %s", sku);
			
			OrderItem item = new OrderItem();
			item.setId(tmp.getId());
			item.setOldSku(gc.getCombSku());
			orderItemDao.update(item);
			
			// 如果是组合SKU，则进行拆分
			this.splitCombineItem(id);
		}
	}

	private void saveSkuMapping(String itemSku, String goodsSku, String oldSku) {
		SkuMapping skuMapping = new SkuMapping();
		skuMapping.setPlatformSku(itemSku);
		skuMapping.setNewSku(goodsSku);
		skuMapping.setOldSku(oldSku);
		skuMapping.setCreateDate(new Date());
		
		this.skuMappingService.saveOrUpdate(skuMapping);
	}

	@Override
	public void updateAllSku() {

		List<OrderItem> items = orderItemDao.queryNoSku();
		if (items == null || items.size() < 1) {
			return;
		}

		// 要更新老SKU的 item 项
		List<OrderItem> updatingOldSkuItems = new ArrayList<>(30);
		// 要更新新SKU的item 项
		List<OrderItem> updatingNewSkuItems = new ArrayList<>(30);

		for (OrderItem item : items) {
			
			String platformSku = item.getItemSku();
			if (StringUtils.isBlank(platformSku)) {
				continue;
			}

			OrderItem tmp = new OrderItem();
			tmp.setId(item.getId());
			
			platformSku = platformSku.trim();
			// 如果是当前新SKU格式, 则直接作为系统SKU, 暂时先简单粗暴处理
			if (platformSku.matches("^[0-9]{9,10}$")) {
				boolean result = this.checkSku(platformSku);
				if (result) {
					tmp.setSku(platformSku);
					updatingNewSkuItems.add(tmp);
				}
			} else {
				String oldSku = this.getOldSkuFromPlatformSku(platformSku);
				if (oldSku != null) {
					tmp.setOldSku(oldSku);
					updatingOldSkuItems.add(tmp);
				}
			}
		}

		this.updateOldSku(updatingOldSkuItems);
		this.updateNewSku(updatingNewSkuItems);
	}

	// 验证是否系统SKU
	private boolean checkSku(String platformSku) {
		try {
			return "1".equals(this.goodsSkuCache.get(platformSku));
		} catch (ExecutionException e) {
			logger.error("查找SKU出错: {}", e.getMessage());
			return false;
		}
	}

	private void updateNewSku(List<OrderItem> updatingNewSkuItems) {
		if (updatingNewSkuItems == null || updatingNewSkuItems.isEmpty()) {
			return;
		}

		this.orderItemDao.batchUpdateSku(updatingNewSkuItems);
	}

	private void updateOldSku(List<OrderItem> updatingOldSkuItems) {
		if (updatingOldSkuItems == null || updatingOldSkuItems.isEmpty()) {
			return;
		}

		this.orderItemDao.batchUpdateSkuByoldSku(updatingOldSkuItems);
	}

	public String getOldSkuFromPlatformSku(String platformSku) {
		// 匹配结果
		String oldSku = null;

		// 2. 按SKU的长度从长到短匹配系统SKU
		List<String> goodsSkus = this.cached("goodsSkus");

		for (String sku : goodsSkus) {
			if (platformSku.indexOf(sku) != -1) {
				oldSku = sku;
				break;
			}
			if (platformSku.toUpperCase().indexOf(sku) != -1) {
				oldSku = sku;
				break;
			}
		}
		return oldSku;
	}

	private List<String> cached(String key) {

		try {
			return oldSkuCache.get(key);
		} catch (ExecutionException e) {
			return null;
		}
	}

	@Override
	public void splitCombineItems() {
		
		List<Integer> ids = this.orderItemDao.findCombItemIds();
		logger.info("共找到 {} 个组合产品订单行", (ids == null ? 0 : ids.size()));
		
		if (ids == null || ids.isEmpty()) {
			return;
		}
		
		for (Integer id : ids) {
			this.splitCombineItem(id);
		}
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void splitCombineItem(Integer id) {
		OrderItem item = this.orderItemDao.load(id);
		Preconditions.checkArgument(item != null, "记录ID输入不正确");
		
//		int itemId = item.getId();
//		String oldSku = item.getOldSku();
		BigDecimal itemPrice = item.getItemPrice(); // 组合产品的单价
		int itemQuantity = item.getItemQuantity(); // 组合产品行数量
		BigDecimal ebayFee = item.getEbayFee();    // ebay费用
		String orderLimitId = item.getOrderLimitId();
		
		List<GoodsCombinationItem> combItems = this.goodsCombinationItemDao.findByCombSku(item.getOldSku());
		Preconditions.checkArgument(combItems != null && combItems.size() > 0, (item.getOldSku() + "不是组合产品"));
		
		// 根据组合产品的明细，将订单行拆分为多行
		int loop = 0;
		for (GoodsCombinationItem combItem : combItems) {
			String theItemNewSku = combItem.getNewSku();
			Preconditions.checkArgument(theItemNewSku != null, "组合产品中的SKU %s 没有找到对应的系统SKU");
			
			String theItemOldSku = combItem.getOldSku();
			
			int combQuantity = combItem.getQuantity() != null ? combItem.getQuantity() : 1;
			int theItemQuantity = itemQuantity * combQuantity;
			
			// 将价格累积到第一个item上 
			// 为 组合产品单价 / 组合产品中此SKU的数量
			// ebay费用 和 orderlimitid 只存第一行
			if (loop == 0) {
				BigDecimal theItemPrice = itemPrice.divide(new BigDecimal(combQuantity), 2, RoundingMode.CEILING);
				item.setItemPrice(theItemPrice.setScale(2, RoundingMode.CEILING));
				item.setEbayFee(ebayFee);
				item.setOrderLimitId(orderLimitId);
			} else {
				item.setItemPrice(new BigDecimal(0));
				item.setEbayFee(null);
				item.setOrderLimitId(null);
			}
			
			item.setItemQuantity(theItemQuantity);;
			item.setOldSku(theItemOldSku);
			item.setSku(theItemNewSku);
			
			this.orderItemDao.insert(item);
			
			loop++;
		}
		
		// 干掉最初的组合产品的行
		this.orderItemDao.delete(id);
	}

	@Override
	public List<OrderItem> findDetailByParam(String trackNumber) {
		return this.orderItemDao.findDetailByParam(trackNumber);
		
	}

	
	
}
