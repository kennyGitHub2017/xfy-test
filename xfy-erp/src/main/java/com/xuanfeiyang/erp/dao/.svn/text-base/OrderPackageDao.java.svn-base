package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.OrderPackage;
import com.xuanfeiyang.erp.param.OrderPackageParam;

/**
 * 包裹
 * @author kenny
 *
 */
public interface OrderPackageDao {
	
	int deleteByOrderId(@Param("orderId")Integer orderId);
	
	/**
	 * 包裹列表
	 * @param pageRequest
	 * @param param
	 * @return
	 */
	Page<OrderPackage> findPage(PageRequest pageRequest,@Param("param")OrderPackageParam param);
	Page<OrderPackage> printFindPage(PageRequest pageRequest,@Param("param")OrderPackageParam param);
	
	/**
	 * 根据订单Id 查询 此订单的包裹数量
	 * @param orderId
	 * @return
	 */
	int countOrderId(Integer orderId);
	
	/***
	 * 根据包裹ID修改状态
	 * @param state
	 * @param id
	 * @return
	 */
	int updateState(@Param("state")Integer state,@Param("id")String id);
	
	/**
	 * 根据 包裹ID查询
	 * @param id
	 * @return
	 */
	public OrderPackage getById(@Param("id")String id);
	
	/**
	 * 添加
	 * @param orderPackage
	 */
	void insert(OrderPackage orderPackage);
	
	/**
	 *  根据系统订单id查询
	 * @param orderId
	 * @return
	 */
	public List<OrderPackage> findByOrderId(Integer orderId);
	
	/**
	 * 通过包裹ID查询出对应的订单ID
	 * @param packageIds
	 * @return
	 */
	public List<Integer> findOrderIdsByIds(List<String> packageIds);
	
	/**
	 * 通过跟踪号查询包裹信息
	 * @param trackNumber
	 * @return
	 */
	public OrderPackage getByTrackNumber(String trackNumber);
	
	/**
	 * 包裹跟踪号导入
	 * @param packageList
	 * @return
	 */
	public int traceNumberImport(@Param("packages")List<OrderPackage> packageList);
	
	/**
	 * 物流退回导入
	 * @param packageList
	 * @return
	 */
	public int rejectPackBytraceNumber(@Param("packages")List<String> packageList);
	
	/**
	 * 选择性更新
	 * @param orderPackage
	 */
	public void update(OrderPackage orderPackage);
	
	/**
	 * 获得ID最大的一条
	 * @return
	 */
	public OrderPackage findMaxById();
	
	/***
	 * 查询满足匹配运输方式的对象
	 * @return
	 */
	public List<OrderPackage> findNoShip();
	/**
	 * 统计包裹状态
	 * @param status  包裹状态
	 * @param orderId  可为空
	 * @return
	 */
	public Integer countPackageStatus(@Param("status")Integer status,@Param("orderId")Integer orderId);
	
	/***
	 * 根据状态 和 条件查询
	 * @param param
	 * @return
	 */
	public List<OrderPackage> find(@Param("param")OrderPackageParam param,@Param("ids")List<String> ids);
	
	
	public Integer findCount(@Param("param")OrderPackageParam param,@Param("ids")List<String> ids);
	
	
	/***
	 * 
	 * 根据跟踪号 修改
	 * @param orderPackage
	 * @return
	 */
	public Integer updateByTrackNo(OrderPackage orderPackage);

	/**
	 * 将指定订单上的跟踪号和发货方式更新到包裹上
	 * 
	 * @param orderId
	 */
	public void updateTrackNumberAndShippingNameByOrderId(@Param("orderId") Integer orderId);
}
