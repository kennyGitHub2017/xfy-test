package com.xuanfeiyang.erp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.IoOrder;
import com.xuanfeiyang.erp.domain.IoOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.param.IoOrderParam;
import com.xuanfeiyang.erp.param.SupplierScoreParam;

public interface IoOrderService {
	
	/**
	 * 供应商评分
	 * @param supplierId
	 * @return
	 */
	public List<IoOrderItem> supplierScore(SupplierScoreParam param);
	
	/**
	 * 入库单详情
	 * @param orderNo
	 * @return
	 */
	public List<IoOrderItem> detail(String orderNo);
	
	/**
	 * 分页查找找
	 * @param pageRequest
	 * @param param
	 * @return
	 */
	public Page<IoOrder> findPage(PageRequest pageRequest, IoOrderParam param);
	
	/**
	 * 采购入库单页面汇总数据
	 * @param param
	 * @return
	 */
	public PurchaseOrderStatistic purchaseOrderStatistic(IoOrderParam param);
	
	/**
	 * 保存一个出入库单，包括订单行
	 * 
	 * @param ioOrder
	 */
	public void save(IoOrder ioOrder) throws InventoryShortageException;

	/**
	 * 更新出入库单
	 * 
	 * @param order
	 */
	public void update(IoOrder order);
	
	/**
	 * 加载一个出入库单，包括订单行
	 * 
	 * @param id
	 * @return
	 */
	public IoOrder load(Integer id);
	
	
	/**
	 * 添加出入库表 单头,单体数据
	 * @param ioOrder
	 * @param items
	 * @return
	 */
	public Integer addOrder(IoOrder ioOrder,List<IoOrderItem> items);
	
	
	/**
	 * 添加出入库单Items 信息
	 * @param ioOrderItem
	 * @return
	 */
	public boolean addOrderItem(IoOrderItem ioOrderItem);
	
	/**
	 * 根据采购单号获取入库单信息
	 * @param purchaseOrderId
	 * @return
	 */
	public List<IoOrder> getOrderByPurchaseOrderNo(String purchaseOrderId);
	
	/**
	 * 收发明细
	 * @param pageRequest
	 * @param ioOrderItem
	 * @return
	 */
	public Page<IoOrderItem> goodsIoItem(PageRequest pageRequest,IoOrderItem ioOrderItem);
	
	/**
	 * 收发明细汇总
	 * @param param
	 * @return
	 */
	public PurchaseOrderStatistic statisticIoItem(IoOrderItem param);
	
	/**
	 * 审核通过
	 * @param id
	 */
	public void approve(Integer id, Integer userId);
	
	/**
	 * 删除一个出入库单
	 * 
	 * @param id
	 */
	public void delete(Integer id);
	
	/***
	 * 出入库单导入
	 * 	此功能为 临时 功能 ,页面隐藏此功能按钮
	 * @param file
	 * @param ioOrder
	 * @throws FileImportException
	 */
	public void importIoOrder(MultipartFile file, IoOrder ioOrder)
			throws FileImportException;
	
	
	/**
	 * 根据订单编号查询
	 * @param orderNo
	 * @return
	 */
	public IoOrder loadByOrderNo(String  orderNo);
	
	/***
	 * 出入库明细
	 * @param pageRequest
	 * @param ioOrderItem
	 * @return PAGE
	 */
	public Page<IoOrderItem> findIoOrderItem(PageRequest pageRequest,IoOrderItem ioOrderItem);
	
	/**
	 * 出入库明细
	 * @param ioOrderItem
	 * @return List
	 */
	public List<IoOrderItem> ioOrderItemList(IoOrderItem ioOrderItem);
	
	public Integer findIoOrderItemCount(IoOrderItem ioOrderItem);
}
