package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;

public class OrderShippingFee {
	
	private Integer id;
	private Integer orderId;//订单ID
	private BigDecimal shippingFee; //订单运费
	private String shippingName;//运输方式
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	
	

}
