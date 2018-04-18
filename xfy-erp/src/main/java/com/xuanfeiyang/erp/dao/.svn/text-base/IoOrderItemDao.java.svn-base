package com.xuanfeiyang.erp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.IoOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.param.SupplierScoreParam;

public interface IoOrderItemDao {
	/**
	 * 供应商评分
	 * @param param
	 * @return
	 */
	public List<IoOrderItem> supplierScore(@Param("param") SupplierScoreParam param);
	
	/**
	 * 采购入库单详情
	 * @param orderNo
	 * @return
	 */
	public List<IoOrderItem>  detail(@Param("orderNo")String orderNo);
	
	/**
	 * 添加
	 * @param ioOrderItem
	 * @return
	 */
	public int add(IoOrderItem  ioOrderItem);
	
	/**
	 * 根据订单号查询
	 * @param orderNo
	 * @return
	 */
	public List<IoOrderItem> findByOrderNo(String orderNo);
	
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id);
	
	/**
	 * 产品收发明细
	 * @return
	 */
	public Page<IoOrderItem> goodsIoItem(PageRequest pageRequest,@Param("param")IoOrderItem ioOrderItem);
	
	public PurchaseOrderStatistic  statisticIoItem(@Param("param")IoOrderItem param);

	/**
	 * 通过订单号删除所有订单行
	 */
	public void deleteByOrderNo(String orderNo);

	/**
	 * 
	 * @param orderItem
	 */
	public void update(IoOrderItem orderItem);
	
	/**
	 * 根据sku获取成本价
	 * @param goodsSku
	 * @return
	 */
	public BigDecimal getLatestGoodsCostBySku(@Param("goodsSku")String goodsSku);
	
	/**
	 * 根据sku获取最新入库重量
	 * @param goodsSku
	 * @return
	 */
	public BigDecimal getLatestGoodsWeightBySku(@Param("goodsSku")String goodsSku);
	
	
	/**
	 * 根据sku获取包装费
	 * @param goodsSku
	 * @return
	 */
	public BigDecimal getLatestGoodsPackingMaterialFeeBySku(@Param("goodsSku")String goodsSku);
	
	
	/***
	 * 出入库明细
	 * @param pageRequest
	 * @param ioOrderItem
	 * @return PAGE
	 */
	public Page<IoOrderItem> findIoOrderItem(PageRequest pageRequest, @Param("param")IoOrderItem ioOrderItem);
	
	/***
	 * 出入库明细
	 * @param ioOrderItem
	 * @return List
	 */
	public List<IoOrderItem> IoOrderList(@Param("param")IoOrderItem ioOrderItem);
	public Integer findIoOrderItemCount(@Param("param")IoOrderItem ioOrderItem);

}
