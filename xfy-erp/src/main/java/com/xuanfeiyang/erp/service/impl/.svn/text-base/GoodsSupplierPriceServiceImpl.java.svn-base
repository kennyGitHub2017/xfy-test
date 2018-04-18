package com.xuanfeiyang.erp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.GoodsSupplierPriceDao;
import com.xuanfeiyang.erp.domain.GoodsSupplierPrice;
import com.xuanfeiyang.erp.service.GoodsSupplierPriceService;

@Service
public class GoodsSupplierPriceServiceImpl implements GoodsSupplierPriceService {

	@Resource
	private GoodsSupplierPriceDao goodsSupplierPriceDao;
	
	@Override
	public Page<GoodsSupplierPrice> findPage(PageRequest pageRequest, 
			GoodsSupplierPrice goodsSupplierPrice) {
		return this.goodsSupplierPriceDao.findPage(pageRequest, goodsSupplierPrice);
	}

	@Override
	public void save(GoodsSupplierPrice gsp) {
		if (gsp == null) {
			return;
		}
		
		// TODO 是否需要验证 SKU的有效性
		
		Date now = new Date();
		gsp.setCreatedTime(now);
		gsp.setLastUpdatedTime(now);
		this.goodsSupplierPriceDao.insert(gsp);
	}

	@Override
	public void update(GoodsSupplierPrice gsp) {
		if (gsp == null || gsp.getId() == null) {
			return;
		}
		
		// TODO 是否需要验证 SKU的有效性

		gsp.setLastUpdatedTime(new Date());
		this.goodsSupplierPriceDao.update(gsp);
	}

	@Override
	public void delete(Integer id) {
		if (id != null) {
			this.goodsSupplierPriceDao.delete(id);
		}
	}

	@Override
	public GoodsSupplierPrice load(Integer id) {
		return id == null ? null : this.goodsSupplierPriceDao.load(id);
	}

	@Override
	public GoodsSupplierPrice findBySupplySku(Integer supplyId, String sku) {
		List<GoodsSupplierPrice> data = this.goodsSupplierPriceDao.findBySupplySku(supplyId, sku);
		if (null!=data && data.size()>0){
			return data.get(0);
		}
		return null;
	}

	@Override
	public GoodsSupplierPrice findByPriorityRuleSku(String sku,
			Integer priorityRule) {
		return this.goodsSupplierPriceDao.findByPriorityRuleSku(sku, priorityRule);
	}

	@Override
	public void batchAddSupplierPrice() {
		this.goodsSupplierPriceDao.batchAddSupplierPrice();
	}
	
	
}
