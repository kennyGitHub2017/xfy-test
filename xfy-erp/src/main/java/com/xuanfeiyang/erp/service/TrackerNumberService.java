package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.service.impl.TrackerApplierHolder;

/**
 * 物流跟踪号服务接口
 * 
 * @author Administrator
 *
 */
public interface TrackerNumberService {
	/**
	 * 为一个订单的跟踪号
	 * 
	 * @param type 申请类型: 参考 {@link TrackerApplierHolder}
	 * @param orderId 订单ID
	 */
	TrackerApplyResult applyOneOrder(String type, Integer orderId);
	
	/**
	 * 多个订单申请跟踪号
	 * 
	 * @param type 申请类型
	 * @param orderIds
	 * @return
	 */
	List<TrackerApplyResult> applyMultiOrders(String type, List<Integer> orderIds);
	
	/**
	 * 为一个包裹申请跟踪号
	 * 
	 * @param type 申请类型: 参考 {@link TrackerApplierHolder}
	 * @param packageId 包裹ID 
	 * @return
	 */
	TrackerApplyResult applyOnePackage(String type, String packageId);
	

	/**
	 * 为一个包裹申请跟踪号
	 * 
	 * @param type 申请类型: 参考 {@link TrackerApplierHolder}
	 * @param packageId 包裹ID 
	 * @return
	 */
	List<TrackerApplyResult> applyMultiPackages(String type, List<String> packageIds);

	/**
	 * 订单级别取消 Apac(eBay亚太物流平台跟踪号)
	 * @param orderids
	 */
	List<TrackerApplyResult> cancelApac(List<Integer> orderids);

	/**
	 * 包裹级别 取消 Apac(eBay亚太物流平台跟踪号)
	 * @param orderids
	 */
	List<TrackerApplyResult> cancelApacForPackage(List<String> packageIds);
	
	/**
	 * 交运(eBay亚太物流平台跟踪号)
	 * 
	 * @param orderids
	 * @return
	 */
	List<TrackerApplyResult> confirmApac(List<Integer> orderids);

	/**
	 * 交运(eBay亚太物流平台跟踪号)
	 * 
	 * @param orderids
	 * @return
	 */
	List<TrackerApplyResult> confirmApacForPackage(List<String> packageIds);

	/**
	 * 重新发货 (eBay亚太物流平台跟踪号)
	 * 
	 * 用于重新发货。重新发货成功之后会返回一个新的跟踪号，并且订单会移动到待交运文件夹里面。只有交运后的订单才能使用此功能。
	 * 
	 * @param orderids
	 */
	List<TrackerApplyResult> recreateApac(List<Integer> orderids);
	
}