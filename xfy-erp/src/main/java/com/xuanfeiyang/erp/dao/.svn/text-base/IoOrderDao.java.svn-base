package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.IoOrder;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.param.IoOrderParam;
public interface IoOrderDao {
	
	public IoOrder load(Integer id);
	
	/**
	 * 查询
	 * @param pageRequest
	 * @param ioOrder
	 * @param type
	 * @return
	 */
	public Page<IoOrder> findIoOrder(PageRequest pageRequest,@Param("param")IoOrderParam param);
	
	/**
	 * 采购入库单页面汇总数据
	 * @param param
	 * @return
	 */
	public  PurchaseOrderStatistic  purchaseOrderStatistic(@Param("param")IoOrderParam param);
	
	/**
	 * 添加
	 * @param ioOrder
	 * @return
	 */
	public int add(IoOrder ioOrder);
	
	/**
	 * 根据单号查询
	 * @param orderNo
	 * @return
	 */
	public IoOrder loadByOrderNo(String  orderNo);
	
	/**
	 * 根据采购单id查询
	 * @param purchaseOrderId
	 * @return
	 */
	public List<IoOrder> getOrderByPurchaseOrderNo(String purchaseOrderId);
	
	/**
	 * 跟句I的更新
	 * @param id
	 * @return
	 */
	public int updateById(@Param("id")Integer id,@Param("param")IoOrder ioOrder);

	public void update(IoOrder order);

	public void delete(Integer id);

}
