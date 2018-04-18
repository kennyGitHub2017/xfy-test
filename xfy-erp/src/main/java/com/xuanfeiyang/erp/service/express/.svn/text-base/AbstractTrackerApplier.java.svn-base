package com.xuanfeiyang.erp.service.express;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.OrderBuyerInfo;
import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;
import com.xuanfeiyang.erp.service.TrackerApplyResult;

/**
 * 申请跟踪号
 * 
 * @author Adam
 *
 */
public abstract class AbstractTrackerApplier implements TrackerApplier {

	private static Logger logger = LoggerFactory.getLogger(AbstractTrackerApplier.class);
	
	/**
	 * 申请跟踪号
	 * 
	 * @param order
	 * @return
	 */
	@Override
	public TrackerApplyResult apply(Order order) {
		
		
		try {
			this.validateOrder(order);
		} catch (Exception e) {
			TrackerApplyResult result = new TrackerApplyResult();
			result.setOrderId(order != null ? order.getId() : null);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			return result;
		}
		
		try {
			return this.doApply(order);
		} catch (Exception e) {
			logger.error("申请跟踪号出错", e);
			
			TrackerApplyResult result = new TrackerApplyResult();
			result.setOrderId(order.getId());
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			return result;
		}
	}
	
	/**
	 * 申请跟踪号具体逻辑
	 * 
	 * @param order
	 * @return
	 */
	public abstract TrackerApplyResult doApply(Order order) throws Exception;
	
	private void validateOrder(Order order) {
		checkNotNull(order, "订单对象不能为空");
		checkNotNull(order.getId(), "订单ID为空");
		//checkNotNull(order.getOrderSn(), "订单平台ID为空");
		
		checkArgument(StringUtils.isNotBlank(order.getShippingName()), "发货方式不能为空");
		checkArgument(StringUtils.isBlank(order.getTrackNumber()), "已有跟踪号 %s，不能再申请", order.getTrackNumber());
		
		OrderBuyerInfo buyer = order.getBuyinfo();
		checkNotNull(buyer, "收货信息不能为空");
		
		checkArgument(StringUtils.isNotBlank(buyer.getShippingName()), "收货人姓名不能为空");
		checkArgument(StringUtils.isNotBlank(buyer.getShippingStreet1())
				|| StringUtils.isNotBlank(buyer.getShippingStreet2()), "收货人街道地址不能为空");
		checkArgument(StringUtils.isNotBlank(buyer.getShippingPostcode()), "收货人邮编不能为空");
		checkArgument(StringUtils.isNotBlank(buyer.getShippingCity())
				|| StringUtils.isNotBlank(buyer.getShippingState()), "收货人省份城市不能全为空");
		checkArgument(StringUtils.isNotBlank(buyer.getShippingCountry())
				|| StringUtils.isNotBlank(buyer.getShippingCountryName()), "收货人国家不能为空");
		
		List<OrderItem> items = order.getItems();
		checkArgument(items != null && items.size() > 0, "缺少订单行");
		
		for (OrderItem temp : items) {
			OrderItemWithShippingInfo item = (OrderItemWithShippingInfo) temp;
			
			checkNotNull(item.getWeight(), "商品重量不能为空");
			checkArgument(StringUtils.isNotBlank(item.getDeclarationNameCn()), "缺少中文申报名称");
			checkArgument(StringUtils.isNotBlank(item.getDeclarationNameEn()), "缺少英文申报名称");
		}
	}
}
