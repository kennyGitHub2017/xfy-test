package com.xuanfeiyang.erp.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.ExportOrder;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderMarkShipping;
import com.xuanfeiyang.erp.domain.OrderRefund;
import com.xuanfeiyang.erp.domain.OrderShippingFee;
import com.xuanfeiyang.erp.domain.OrderStatistic;
import com.xuanfeiyang.erp.domain.SimpleOrderWithAccount;
import com.xuanfeiyang.erp.domain.StockOutSku;
import com.xuanfeiyang.erp.param.OrderParam;
import com.xuanfeiyang.erp.param.SellerParams;

public interface OrderDao {
	
	/**
	 * @param ids  订单审核:订单id列表
	 * @return
	 */
	Integer[]  getOrderIdAfterSortByQuantity(@Param("ids")List<Integer> ids);
	
	Integer orderSuspendAndEnable(@Param("orderId") Integer orderId,
			@Param("suspendEnbale") Integer suspendEnbale);

	/**
	 * 还原订单至未审核前的数据
	 */
	void reStoreOrder(@Param("orderId") Integer orderId);

	/**
	 * 根据sku行更新订单是否混合包裹
	 */
	void mixedOrder(@Param("orderId") Integer orderId);

	/**
	 * 计算订单成本
	 */
	void calculateOrderCost(@Param("orderId") Integer orderId);

	/**
	 * 计算订单利润
	 */
	void calculateOrderProfit(@Param("orderId") Integer orderId,
			@Param("profit") BigDecimal profit);

	/**
	 * 设置订单重量，运费,发送时间,扫描时间
	 */
	void configOrderOtherInfo(@Param("orderId") Integer orderId);

	void delete(Integer id);

	int insert(Order record);

	void batchinsert(@Param("id") List<Integer> id);

	Order load(Integer id);

	Order findByOrderSn(@Param("orderSn") String orderSn);

	int update(Order record);

	void traceNumberImport(@Param("orders") List<Order> orders);

	/**
	 * 
	 * @param columnValue
	 *            key:指定修改的列名, value:指定修改的列值
	 * @param id
	 */
	void batchUpdateOrder(
			@Param("columnValue") Map<String, String> columnValue,
			@Param("id") Integer id[]);

	List<Order> findAll();

	List<Order> find(@Param("param") OrderParam param);

	Page<Order> findPage(PageRequest pageRequest,@Param("param") OrderParam param);
	
	Page<Order> findEbabyTwoSendPage(PageRequest pageRequest,@Param("param") OrderParam param);

	int addOrderBuyerInfo(OrderBuyerInfo buyinfo);

	void batchAddOrderBuyerInfo(@Param("id") List<Integer> id);

	void batchOrderItem(@Param("id") List<Integer> id);

	void updateOrderItemRelation();

	void updateOrderBuyerInfo(OrderBuyerInfo buyinfo);
	
	void updateflagOrderBuyinfo(@Param("id")Integer id,@Param("updateFlag")Integer updateFlag);

	OrderBuyerInfo getBuyInfoByOrderId(@Param("orderId") Integer orderId);

	List<Order> getByState(@Param("status") Integer status);

	void cancelOrder(@Param("cancelDate") String cancelDate,
			@Param("id") Integer id);

	void updateStateById(@Param("status") Integer status,
			@Param("id") Integer id);

	Order getById(Integer orderId);

	/**
	 * 获得所有未同步订单的Id
	 * 
	 * @return
	 */
	List<Integer> getUnSyncOrderId();

	/**
	 * 更新刊登系统订单的同步状态字段
	 */
	void updateOrderSyncflag(@Param("id") List<Integer> orderIds);

	/**
	 * 根据订单id 查询标发需要的信息
	 * 
	 * @param id
	 * @return
	 */
	List<OrderMarkShipping> findOrderMarkShipping(List<Integer> ids);

	/**
	 * 根据订单id 查询标发需要的信息
	 * 
	 * @param id
	 * @return
	 */
	List<OrderMarkShipping> findOrderMarkShippingByOrderSn(List<String> orderSn);

	/**
	 * 查询订单信息，带有一些特定的发货信息：申报价值、申报名称等
	 * 
	 * @param id
	 * @return
	 */
	Order getWithShippingInfo(Integer id);

	/**
	 * 查询少量订单信息和账号信息：ID，跟踪号，帐号名，授权信息
	 * 
	 * @param id
	 * @return
	 */
	SimpleOrderWithAccount getSimpleOrderAccountById(Integer id);

	/**
	 * 根据订单ID设置发货方式
	 * 
	 * @param shipName
	 * @param shipFee
	 */
	void setShipping(@Param("param") OrderShippingFee param);

	/**
	 * 查询满足匹配物流方式的订单
	 * 
	 * @return
	 */
	List<Order> findNoShip();

	/**
	 * 查询自动合并操作中所有可合并的订单
	 * 
	 * @return
	 */
	List<Order> findmergeOrder(@Param("userId")Integer userId);

	/**
	 * 导出发货清单
	 * 
	 * @param ids
	 * @return
	 */
	List<ExportOrder> exportInvoice(@Param("param") OrderParam param);

	/**
	 * 导出订单
	 * (OrderParam param,String ids,Integer exportFlag){
	 * @param ids
	 * @return
	 */
	List<ExportOrder> expirtOrder(@Param("param") OrderParam param, @Param("ids") List<Integer> ids);
	Integer findOrderInfoCountByParam(@Param("param") OrderParam param, @Param("ids") List<Integer> ids);

	/**
	 * 销售报表
	 * 
	 * @param param
	 * @return
	 */
	List<ExportOrder> exportSale(@Param("param") OrderParam param);

	/**
	 * 批量设置运输方式
	 * 
	 * @param id
	 */
	void batchSetShip(Integer id);

	/**
	 * 条件查询订单头
	 * 
	 * @param param
	 * @return
	 */
	List<Order> findOrder(@Param("param") OrderParam param);

	/**
	 * 按状态统计订单数量
	 * 
	 * @param sellerId
	 *            卖家ID, 可为空
	 * @return
	 */
	OrderStatistic statOrderStatusCount(@Param("sellerId") Integer sellerId);
	
	/**
	 * 按异常状态统计各订单数量
	 * @param type 异常单类型
	 * @param sellerId
	 * @return
	 */
	Integer statExceptionOrderCount(@Param("type")String type,@Param("sellerId") Integer sellerId, @Param("userId")Integer userId,@Param("isAgent")Boolean isAgent);

	/**
	 * 查询订单状态 : 已生成包裹或之后的订单
	 * 
	 * @return
	 */
	List<Integer> findAfterGepackOrderId();
	
	
	List<ExportOrder> findOrderInfoByParam(@Param("param") OrderParam param);
	
	List<ExportOrder> findOrderInfoById(@Param("ids")List<Integer> ids);
	/**
	 * 临时处理生成包裹条件
	 * @return
	 */
	List<Order> getByStateAdTkNo();
	
	List<ExportOrder> findSaleByParam(@Param("param")OrderParam param);
	
	List<ExportOrder> findSaleById(@Param("ids")List<Integer> ids);
	
	List<StockOutSku>  exportStockOut();
	
	/***
	 * 
	 * 自动锁定库存订单
	 * KENNY
	 * 状态为2
	 * @return
	 */
	List<Order> findInventoryLockOrder();
	
	/***
	 * 根据条件查询
	 * @param param
	 * @return
	 */
	Order loadByParam(@Param("param")OrderParam param);
	
	/**
	 * 设置订单的包装材料费
	 * @return
	 */
	void setOrderPackingMaterialFee(@Param("orderId")Integer orderId);
	/***
	 * 此方法暂时不用
	 * 查询买家及订单
	 * @return
	 */
	List<SellerParams> getSellerOrder();
	
	OrderStatistic orderStatistic(@Param("param")OrderParam param);
	
	List<ExportOrder> findOrderFee(@Param("param")OrderParam param);
	
	Integer findOrderFeeCount(@Param("param")OrderParam param);

	/**
	 * 根据订单ID查询所有的订单买家的邮件
	 * 
	 * @param orderIds
	 * @return
	 */
	List<String> findBuyerEmailsByOrderIds(List<Integer> orderIds);
	
	void applyRefund(OrderRefund refund);
	
	List<String> readSmtOrderMessage(@Param("orderSn")String orderSn);
	
	public BigDecimal countByTrackNumber(@Param("trackNumber")String trackNumber);
	
	public Order getByTrackNo(@Param("trackNumber")String trackNumber);
	
	/**
	 * 代理商账号下的卖家的订单数量
	 */
	public Integer orderNumbers(@Param("param")OrderParam param);   //订单数量
	
	
	
}
