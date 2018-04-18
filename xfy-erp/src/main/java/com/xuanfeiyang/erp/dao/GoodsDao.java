package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.param.GoodsParam;

public interface GoodsDao {
	
	public int insert(Goods goods);
	
	public Page<Goods> findPage(PageRequest pageRequest,@Param("params") GoodsParam goodsParam);
	
	public Goods findById(Integer id);
	
	public Goods findBySku(@Param("sku")String sku);
	
	public int update(Goods goods);
	
	public int delete(Integer id);
	
	public Goods findGoods(@Param("goods")Goods goods);
	
	public Integer countGoodsByCategory(Integer categoryId);

	public Goods findByIdWithSupplierName(Integer id);
	
	public void updateOpenFlag(@Param("id") Integer id, @Param("openFlag") String openFlag);
	
	List<Goods> findByParam(@Param("params") GoodsParam goodsParam);

	public List<String> findAllOldSkus();

	public Goods findByOldSkuOrGoodsSku(String sku);

	public String findGoodsSkuByOldSku(String oldSku);
	
	public Integer getCountByParam(@Param("params") GoodsParam goodsParam);
	
	public Integer updateBySku(Goods goods);
	
	/**
	 * 选择不为空的字段更新
	 * 
	 * @param goods
	 * @return
	 */
	public int updateSelective(Goods goods);

	public List<Goods> findListById(@Param("ids")List<Integer> ids);
	
	public Page<Goods> getListingInfo(PageRequest pageRequest, @Param("goodsSku")String goodsSku);
	
	public List<Goods> getListingCount(@Param("sku")List<String> sku);
	
	
}
