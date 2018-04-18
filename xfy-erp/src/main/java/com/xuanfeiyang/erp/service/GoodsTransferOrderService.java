package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsTransferOrder;
import com.xuanfeiyang.erp.param.GoodsTransferOrderParam;

public interface GoodsTransferOrderService {
	
	public Long add(GoodsTransferOrder goodsAllocateOrder);

	public GoodsTransferOrder get(Long id);

	public List<GoodsTransferOrder> find(GoodsTransferOrderParam parameter);
	
	public Page<GoodsTransferOrder> findPage(PageRequest requestPage,GoodsTransferOrderParam param);
}
