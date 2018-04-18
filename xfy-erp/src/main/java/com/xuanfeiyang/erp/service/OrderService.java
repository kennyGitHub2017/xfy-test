package com.xuanfeiyang.erp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.ExportOrder;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderRefund;
import com.xuanfeiyang.erp.domain.OrderShippingFee;
import com.xuanfeiyang.erp.domain.OrderStatistic;
import com.xuanfeiyang.erp.domain.StockOutSku;
import com.xuanfeiyang.erp.domain.TrackNumber;
import com.xuanfeiyang.erp.param.OrderParam;

public interface OrderService {
	
	void updateStateById( Integer status,Integer id);
	
	void orderWeight(Integer orderId);
	
	/**
	 * 订单暂停或重新启用
	 * @param orderId
	 * @param suspendEnbale
	 */
	void orderSuspendAndEnable(List<Integer> orderId,Integer suspendEnbale, String operUser);
	/**
	 * 还原订单至未审核前的数据
	 * @param order
	 */
	void reStoreOrder(Order order);
	
	void configOrderOtherInfo(Integer orderId);
	/**
	 * 计算订单利润
	 * @param id
	 */
	void calculateOrderProfit();
	
	void delete(Integer id);

    int insert(Order record);

    Order load(Integer id);
    /**
     * 复制订单
     * 
     * @param originalId 原订单ID
     * @param operUser 操作用户
     * 
     * @return 复制新订单的id
     */
    Integer copy(Integer originalId, String operUser);

    void update(Order record);
    
    void traceNumberImport(List<Order> orders);
    /**
     * 
     * @param id
     * @param auditUserId
     */
    void  audit(Integer id,String auditUserId) throws RuntimeException;
    
    /**
     * 
     * 根据订单sku购买数量
     * 从小到大锁定订单库存
     */
    void lockOrderInventory(Integer [] ids);
    
    /**
     * 根据订单sum(sku购买数量)，订单支付时间排序
     * @param ids
     * @return  排序后的订单id列表
     */
    Integer[] getOrderIdAfterSortByQuantity(List<Integer> ids);
    
    /**
     * 合并订单
     * @param orderList
     * @return 返回合并后的提示信息
     */
    String merge(List<Order> orderList,String curUser) throws Exception;
    
    String split(List<Order> orderList,String curUser) throws Exception; 
    
    void batchEditOrder(String []column,String[] columnValue,Integer id[]);
    
    List<Order> findAll();
    
    List<Order> find(OrderParam param);
    /**
     * 查询订单状态 : 已生成包裹或之后的订单Id(不含取消订单)
     * @return
     */
    List<Integer> findAfterGepackOrderId();
    /**
	 * 查询自动合并操作中所有可合并的订单
	 * @return
	 */
    List<Order> findMergeOrder(Integer userId);
    
	Page<Order> findPage(PageRequest pageRequest,OrderParam param);
	
	/**
	 * 获取已导入跟踪号需要二次标发的ebaby订单分页数据
	 * @param pageRequest
	 * @return
	 */
	Page<Order> findEbabyTwoSendPage(PageRequest pageRequest,OrderParam param);
	
	void addOrderBuyerInfo(OrderBuyerInfo buyinfo);
	
	void updateOrderBuyerInfo(OrderBuyerInfo buyinfo);
	
	OrderBuyerInfo getBuyInfoByOrderId(Integer orderId);
	
	/**
	 * 取消订单行
	 * @param orderId
	 * @param sku
	 * @param cancelAmount
	 */
	void cancelOrderItem(Integer itemId, Integer cancelAmount,String curUser) throws Exception;
	
	void cancelOrder(Integer orderId,String curUser) throws Exception;
	
	void stopOrder(Integer orderId,String curUser,String msg);
	
	public void matchingShipping();
	
	/**
	 * 根据订单Id查询运费列表
	 * @param orderId
	 * @return
	 */
	List<OrderShippingFee> getShipFee(Integer orderId);
	
	/**
	 * 设置发货方式
	 * @param id 同步下来的发货方式Id
	 * @param orderId 订单id
	 * @return
	 */
	boolean setShipping(Integer id);
	
	/**
	 * 导出发货清单
	 * @param ids
	 * @return
	 */
	List<ExportOrder> exportInvoice(OrderParam param);
	/**
	 * 导出订单
	 * @param ids
	 */
	List<ExportOrder> expirtOrder(OrderParam param,List<Integer> ids);
	Integer expirtOrderCount(OrderParam param,List<Integer> ids);
	/**
	 * 销售订单
	 * @param param
	 * @return
	 */
	List<ExportOrder> exportSale(OrderParam param,List<Integer> ids);
	
	/**
	 * 缺货订单
	 * @return
	 */
	List<StockOutSku>  exportStockOut();
	
	/**
	 * 批量设置物流
	 * @param id
	 */
	void batchSetShip(Integer id);
	
	/**
	 * 查询订单头
	 * @param param
	 * @return
	 */
	 List<Order> findOrder(@Param("param") OrderParam param);
	 
	 /**
	  * 导入订单
	  * @return 返回同步订单数  (-1:订单同步正在进行中) 
	  */
	 public Integer batchInsertOrders();
	 
	 /**
	  * 平台sku转换
	  */
	 //public void itemSku2SystemSku() throws Exception;
	 
		
	/**
	 * 锁定库存
	 *
	 * 此函数 将订单状态由 [2带锁定] 改 为  [状态3 已经锁定 ]
	 * 
	 * 
	 */
	void inventoryLock();
	void inventoryLockNew();
	
	/***
	 * 导入跟踪号
	 * @param request
	 */
	void importTrackFile(MultipartFile file, TrackNumber tNumber,Integer type)throws FileImportException ;
	
	/***
	 * 申请顺邮宝的账号
	 * @param orderIds
	 * @param type
	 * @return
	 */
	public List<TrackerApplyResult> sybTrackerNumber(List<Integer> orderIds,Integer type);
	

	
	public BigDecimal countShiFee(Order order,String shipName);
	
	public BigDecimal countByTrackNumber(String trackNumber);
	
	/**
	 * 导入物流退回订单
	 * @param file
	 * @param returnType
	 * @throws FileImportException
	 */
	public List<Map<String,String>> importOrderReturn(MultipartFile file,Integer returnType,String userName)throws FileImportException;
	
	/***
	 * 此方法暂时不使用
	 * 买家及订单数量
	 * @return
	 */
	public List<Map<String,String>> sellerAdOrder();
	
	/**
	 * 统计所查询订单的sku数量及订单金额
	 * @param param
	 * @return
	 */
	public OrderStatistic  orderStatistic(OrderParam param);
	
	public  List<ExportOrder> findOrderFee(OrderParam param);
	
	Integer findOrderFeeCount(OrderParam param);
	
	public List<Integer> uploadOrderFile(MultipartFile file)throws FileImportException;
	/**
	 * 订单申请退款,暂时只支持ebay订单
	 * @param refund
	 */
	public void applyRefund(OrderRefund refund,Order order);
	/**
	 * 读取smt订单买家留言
	 * @param orderSn
	 * @return
	 */
	public List<String> readSmtOrderMessage(String orderSn);
	
	public void generatePackage3();
	
	/**
	 * 代理商账号下的卖家的订单数量
	 */
	public Integer orderNumbers(OrderParam param);   //订单数量
}
