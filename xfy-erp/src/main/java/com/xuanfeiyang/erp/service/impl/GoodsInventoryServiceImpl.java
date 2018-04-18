package com.xuanfeiyang.erp.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.GoodsInventoryCostDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryDao;
import com.xuanfeiyang.erp.dao.GoodsInventoryLockDao;
import com.xuanfeiyang.erp.dao.StatDao;
import com.xuanfeiyang.erp.domain.GoodsInventory;
import com.xuanfeiyang.erp.domain.GoodsInventoryCost;
import com.xuanfeiyang.erp.domain.GoodsInventoryStat;
import com.xuanfeiyang.erp.param.GoodsInventoryParam;
import com.xuanfeiyang.erp.service.GoodsInventoryService;
import com.xuanfeiyang.erp.service.InventoryShortageException;

@Service
public class GoodsInventoryServiceImpl implements GoodsInventoryService {

	@Resource
	private GoodsInventoryDao goodsInventoryDao;
	
	@Resource
	private StatDao statDao;
	
	@Resource
	private GoodsInventoryCostDao goodsInventoryCostDao;
	
	@Resource
	private GoodsInventoryLockDao goodsInventoryLockDao;
	
	@Override
	public int getInventory(String sku) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getInventory(String sku, Integer storeId, Integer storeShelfId) {
		return this.goodsInventoryDao.getCount(sku, storeId, storeShelfId);
	}

	@Override
	public synchronized void increase(String sku, int storeId, int storeShelfId, int increaseCount) {
		if (sku == null || increaseCount == 0) {
			return;
		}
		
		GoodsInventory gi = this.goodsInventoryDao.load(sku, storeId, storeShelfId);
		// 增加库存时，如果有则在以前库存的基础累加，如果没有，则将库存信息保存
		if (gi != null) {
			gi.setCount(gi.getCount() + increaseCount);
			gi.setLastUpdatedTime(new Date());
			this.goodsInventoryDao.updateById(gi);
			
		} else {
			gi = new GoodsInventory();
			gi.setGoodsSku(sku);
			gi.setStoreId(storeId);
			gi.setStoreShelfId(storeShelfId);
			gi.setCount(increaseCount);
			gi.setLastUpdatedTime(new Date());
			this.goodsInventoryDao.insert(gi);
		}
	}

	@Override
	public synchronized void decrease(String sku, 
			int storeId, int storeShelfId, int decreaseCount)
			throws InventoryShortageException {
		
		if (sku == null || decreaseCount == 0) {
			return;
		}
		
		GoodsInventory gi = this.goodsInventoryDao.load(sku, storeId, storeShelfId);
		
		// 扣减库存时，前提是存在库存信息，并且库存数足够 ，才能正确扣减；否则抛出异常
		if (gi != null && gi.getCount() >= decreaseCount) {
			gi.setCount(gi.getCount() - decreaseCount);
			gi.setLastUpdatedTime(new Date());
			this.goodsInventoryDao.updateById(gi);
		} else {
			gi = new GoodsInventory();
			gi.setGoodsSku(sku);
			gi.setStoreId(storeId);
			gi.setStoreShelfId(storeShelfId);
			gi.setCount(0);
			throw new InventoryShortageException(gi, decreaseCount);
		}
	}

	@Override
	public Page<GoodsInventory> findPage(PageRequest pageRequest, 
			GoodsInventoryParam goodsInventoryParam) {
		return this.goodsInventoryDao.findPage(pageRequest, goodsInventoryParam);
	}

	@Override
	public Page<GoodsInventoryStat> findStatPage(PageRequest pageRequest,
			GoodsInventoryParam goodsInventoryParam) {
		return this.statDao.findGoodsInventoryStatPage(
				pageRequest, goodsInventoryParam);
	}
	
	
	@Override
	public List<GoodsInventory> find(GoodsInventoryParam goodsInventoryParam) {
		return goodsInventoryDao.find(goodsInventoryParam);
	}

	@Override
	public List<GoodsInventoryStat> findStat(
			GoodsInventoryParam goodsInventoryParam) {
		return statDao.find(goodsInventoryParam);
	}

	private GoodsInventoryCost loadCost(String goodsSku) {
		GoodsInventoryCost cost = this.goodsInventoryCostDao.load(goodsSku);
		
		// 没有记录时初始化一条记录
		if (cost == null) {
			cost = new GoodsInventoryCost();
			cost.setGoodsSku(goodsSku);
			cost.setAmount(new BigDecimal(0));
			cost.setPrice(new BigDecimal(0));
			cost.setCount(0);
			cost.setLastUpdatedTime(new Date());
			
			this.goodsInventoryCostDao.insert(cost);
		}
		
		return cost;
	}

	
	public GoodsInventoryCost updateCostWhenDecrease(String goodsSku, int count) throws InventoryShortageException {
		
		GoodsInventoryCost nowCost = this.loadCost(goodsSku);
		
		GoodsInventoryCost newCost = new GoodsInventoryCost(); 
		newCost.setGoodsSku(goodsSku);
		
		// 平均价保持不变
		newCost.setPrice(nowCost.getPrice());
		// 库存数减少
		newCost.setCount(nowCost.getCount() - count);
		if (newCost.getCount() < 0) {
			throw new InventoryShortageException(goodsSku, newCost.getCount(), count);
		}
		// 重新计算总成本：平均价格 * 最新总库存
		newCost.setAmount(newCost.getPrice().multiply(new BigDecimal(newCost.getCount())).setScale(2, RoundingMode.CEILING));
		
		newCost.setLastUpdatedTime(new Date());
		this.goodsInventoryCostDao.update(newCost);
		
		return newCost;
	}
	
	
	public GoodsInventoryCost updateCostWhenIncrease(String goodsSku, int count, BigDecimal price) {
		
		GoodsInventoryCost nowCost = this.loadCost(goodsSku);
		
		GoodsInventoryCost newCost = new GoodsInventoryCost();
		newCost.setGoodsSku(goodsSku);
		
		// 库存数增加 ，最新总库存
		newCost.setCount(nowCost.getCount() + count);
		
		// 本次增加的成本
		BigDecimal thisAmount = price.multiply(new BigDecimal(count)).setScale(2, RoundingMode.CEILING);
		// 最新的总成本：以前的成本+本次的成本
		newCost.setAmount(nowCost.getAmount().add(thisAmount));
		
		// 计算新的平均价：最新总成本/最新总库存
		BigDecimal newPrice = newCost.getAmount().divide(new BigDecimal(newCost.getCount()), 2, RoundingMode.CEILING);
		newCost.setPrice(newPrice);
		
		newCost.setLastUpdatedTime(new Date());
		
		this.goodsInventoryCostDao.update(newCost);
		
		return newCost;
	}

	@Override
	public BigDecimal getLatestGoodsCostBySku(String goodsSku) {
		GoodsInventoryCost cost = this.goodsInventoryCostDao.load(goodsSku);
		return cost == null ? null : cost.getPrice();
	}

	@Override
	public Integer getLockCountBySku(String sku) {
		return this.goodsInventoryLockDao.getLockCountBySku(sku);
	
	}

	@Override
	public Integer getCountBySku(String sku) {
		return this.goodsInventoryDao.getCountBySku(sku);
		
	}
	
	/**
	 * 将不同货位的库存合并到基础资料的货位上去
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void combineStoreShelf() {
		this.goodsInventoryDao.updateInventoryWitchInFewShelf();
		this.goodsInventoryDao.deleteCombined();
	}
}