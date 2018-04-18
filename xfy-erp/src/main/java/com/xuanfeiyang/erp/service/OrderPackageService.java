package com.xuanfeiyang.erp.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.OrderPackage;
import com.xuanfeiyang.erp.domain.OrderPackageItem;
import com.xuanfeiyang.erp.param.OrderPackageParam;

public interface OrderPackageService {
	
	/**
	 * 包裹分页列表
	 * @param pageRequest
	 * @param orderPackage
	 * @return
	 */
	Page<OrderPackage> findPage(PageRequest pageRequest,OrderPackageParam orderPackage);
	Page<OrderPackage> printFindPage(PageRequest pageRequest,OrderPackageParam orderPackage);
	
	/**
	 * 根据包裹Id查询包裹明细
	 * @param id
	 * @return
	 */
	List<OrderPackageItem> findByPackage(String id);
	
	/**
	 * 根据订单id查询包裹明细
	 * @param orderId
	 * @return
	 */
	List<OrderPackageItem> findByOrder(Integer orderId);
	
	/**
	 * 根据订单ID查询此订单的包裹数量
	 * @param id
	 * @return
	 */
	int countByOrderId(Integer orderId);
	
	/**
	 * 根据包裹Id改变状态
	 * @param state
	 * @param id
	 * @return
	 */
	int updateState(Integer state,String id);
	
	/**
	 * 根据包裹ID查询
	 * @param id
	 * @return
	 */
	public OrderPackage getById(String id);

	void generatePackage4();
	/**
	 * 
	 * @param orderId
	 * @param itemsId
	 */
	void generatePackage(Integer orderId,String curUser);
	
	/**
	 * 包裹导入跟踪号
	 * @param list
	 * @return
	 */
	int traceNumberImport(List<OrderPackage> list);
	
	/**
	 * 物流退回导入
	 * @param list
	 * @return
	 */
	int rejectPackBytraceNumber(List<String> list);

	/**
	 * 根据跟踪号查询
	 * @param trackNumber
	 * @return
	 */
	public OrderPackage getByTrackNumber(String trackNumber);

	/**
	 * 确认包裹扫描
	 * @param trackNumber 包裹跟踪号 
	 * @param userId 用户ID
	 * @param userCode 用户编码 
	 */
	OrderPackage confirmScan(String trackNumber, String userCode);
	
	/***
	 * 修改发货方式
	 * @param packageId 包裹ID
	 * @param id 运费ID
	 */
	void updateShipping(String packageId,Integer id);
	
	/**
	 * 批量修改包裹
	 */
	void batcheditPackage(List<OrderPackage> list);
	
	/**
	 * 修改包裹
	 * @param orderPackage
	 */
	public void update(OrderPackage orderPackage);
	
	/**
	 *  订单列表点击标发修改包裹
	 * @param orderId
	 */
	void updateShipInfo(List<Integer> orderId);
	/**
	 * 包裹页面点击标发按钮修改订单
	 * @param pickageId
	 */
	void updateShipInfo2(List<String> pickageId);
	
	/**
	 * 包裹匹配运输方式
	 */
	void matchingShipping2();
	/**
	 * 统计包裹状态
	 * @param status  包裹状态
	 * @param orderId  可为空
	 * @return
	 */
	public Integer countPackageStatus(@Param("status")Integer status,@Param("orderId")Integer orderId);
	
	
	/***
	 * 根据状态和条件查询
	 * @param orderPackage
	 * @return
	 */
	public List<OrderPackage> find(OrderPackageParam orderPackage,List<String> ids);
	/***
	 * 根据状态和条件查询记录数
	 * @param orderPackage
	 * @return
	 */
	public Integer findCount(OrderPackageParam orderPackage,List<String> ids);
	
	public void markPrintFlag(String packageId, String userCode);
	
	public Integer updateByTrackNo(OrderPackage orderPackage);
	
	public void readPackageWeight(String trackNumber, String requestUrl) throws Exception;
	
	public void cancelweighService(String trackNumber);
	
}
