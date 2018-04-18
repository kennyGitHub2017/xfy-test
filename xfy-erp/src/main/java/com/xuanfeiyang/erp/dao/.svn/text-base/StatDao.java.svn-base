package com.xuanfeiyang.erp.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsInventoryStat;
import com.xuanfeiyang.erp.param.GoodsInventoryParam;

/**
 * 统计相关查询Dao
 * 
 * @author Adam
 *
 */
public interface StatDao {
	
	/**
	 * 库存统计
	 * 
	 * @param pageRequest
	 * @param goodsInventoryParam
	 * @return
	 */
	Page<GoodsInventoryStat> findGoodsInventoryStatPage(
			PageRequest pageRequest, 
			@Param("param") GoodsInventoryParam goodsInventoryParam);
	
	List<GoodsInventoryStat> find(@Param("param") GoodsInventoryParam goodsInventoryParam);
	
	/**
	 * 查询所有SKU信息
	 * @return
	 */
	List<Map<String,Object>> allSkuInfo();
	
	/**
	 * 查询已发货商品信息
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Map<String,Object>> shippedGoods(
			@Param("startDate") Date startDate, 
			@Param("endDate") Date endDate);
	
	/**
	 * 入库清单
	 * @param startDate
	 * @param endDate
	 * @param queryType 查询类型  po - 采购入库, npo - 手工入库
	 * @return
	 */
	List<Map<String,Object>> inStoreListing(
			@Param("startDate") Date startDate, 
			@Param("endDate") Date endDate,
			@Param("queryType") String queryType);
}
