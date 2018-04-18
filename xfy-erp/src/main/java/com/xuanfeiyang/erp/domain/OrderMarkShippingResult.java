package com.xuanfeiyang.erp.domain;

/**
 * 订单标发结果
 * @author Adam
 *
 */
public class OrderMarkShippingResult {
	
	private Integer orderId;
	
	private boolean success;
	
	private String message;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderMarkShippingResult [orderId=");
		builder.append(orderId);
		builder.append(", success=");
		builder.append(success);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
