package com.xuanfeiyang.erp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.SellerGoodsDao;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.SellerGoods;
import com.xuanfeiyang.erp.param.GoodsParam;
import com.xuanfeiyang.erp.service.SellerGoodsService;

@Service
public class SellerGoodsServiceImpl implements SellerGoodsService {
	
	@Resource
	private SellerGoodsDao sellerGoodsDao;
	
	@Resource
	private GoodsDao goodsDao;

	@Override
	public Page<Goods> findSellerGoods(PageRequest pageRequest,
			GoodsParam params, Integer sellerId) {
		
		params.setSellerId(sellerId);
		return this.goodsDao.findPage(pageRequest, params);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(Integer sellerId, List<Integer> goodsIds) {
		if (sellerId == null || goodsIds == null || goodsIds.size() == 0) {
			return;
		}
		
		for (Integer goodsId : goodsIds) {
			SellerGoods sg = new SellerGoods();
			sg.setSellerId(sellerId);
			sg.setGoodsId(goodsId);
			sg.setCreatedTime(new Date());
			
			try {
				this.sellerGoodsDao.insert(sg);
			} catch (DuplicateKeyException d) {
				// 若有重复，不处理
				continue;
			}
		}
	}

	@Override
	public void delete(Integer sellerId, List<Integer> goodsIds) {
		if (sellerId == null || goodsIds == null || goodsIds.size() == 0) {
			return;
		}
		
		for (Integer goodsId : goodsIds) {
			this.sellerGoodsDao.deleteBySellerIdAndGoodsId(sellerId, goodsId);
		}
	}

}
