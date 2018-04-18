package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.param.GoodsParam;

public interface SellerGoodsService {
	
	/**
	 * 分页查询卖家商品
	 * @param pageRequest
	 * @param params 
	 * @return
	 */
	public Page<Goods> findSellerGoods(PageRequest pageRequest, GoodsParam params, Integer sellerId);
	
	/**
	 * 卖家保存收藏的商品
	 * @param sellerId
	 * @param goodsIds
	 */
	public void save(Integer sellerId, List<Integer> goodsIds);
	
	/**
	 * 卖家删除收藏的某个商品
	 * @param sellerId
	 * @param goodsIds
	 */
	public void delete(Integer sellerId, List<Integer> goodsIds);
	
}
