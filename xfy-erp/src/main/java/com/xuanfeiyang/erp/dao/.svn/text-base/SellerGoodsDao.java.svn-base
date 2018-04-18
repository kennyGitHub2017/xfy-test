package com.xuanfeiyang.erp.dao;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.SellerGoods;

public interface SellerGoodsDao {
	int delete(Integer id);

	int deleteBySellerId(Integer sellerId);

	int deleteBySellerIdAndGoodsId(@Param("sellerId") Integer sellerId,
			@Param("goodsId") Integer goodsId);

	int insert(SellerGoods record);

	Page<SellerGoods> findPageBySellerId(PageRequest pageRequest,
			@Param("sellerId") Integer sellerId);

	int update(SellerGoods record);

}