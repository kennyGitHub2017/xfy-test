package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.GoodsTransferOrderDao;
import com.xuanfeiyang.erp.domain.GoodsTransferOrder;
import com.xuanfeiyang.erp.param.GoodsTransferOrderParam;
import com.xuanfeiyang.erp.service.GoodsInventoryService;
import com.xuanfeiyang.erp.service.GoodsTransferOrderService;
@Service("goodsTransferOrderService")
public class GoodsTransferOrderServiceImpl implements GoodsTransferOrderService{
	@Resource
	private GoodsTransferOrderDao goodsTransferOrderDao;
	
	@Resource
	private GoodsInventoryService goodsInventoryService;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Long add(GoodsTransferOrder goodsAllocateOrder) {
		// 更新库存信息
		this.goodsInventoryService.increase(goodsAllocateOrder.getGoodsSku(), goodsAllocateOrder.getToStoreId(), goodsAllocateOrder.getToShoreShelf(), goodsAllocateOrder.getGoodsCount());
		this.goodsInventoryService.decrease(goodsAllocateOrder.getGoodsSku(), goodsAllocateOrder.getFromStoreId(), goodsAllocateOrder.getFromStoreShelf(),goodsAllocateOrder.getGoodsCount());
		return this.goodsTransferOrderDao.insert(goodsAllocateOrder);
	}

	@Override
	public GoodsTransferOrder get(Long id) {
		return goodsTransferOrderDao.get(id);
	}

	@Override
	public List<GoodsTransferOrder> find(GoodsTransferOrderParam parameter) {
		return goodsTransferOrderDao.find(parameter);
	}

	@Override
	public Page<GoodsTransferOrder> findPage(PageRequest requestPage,
			GoodsTransferOrderParam param) {
		return goodsTransferOrderDao.findPage(requestPage, param);
	}
}
