package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 卖家预扣费用表
 * @author Administrator
 *
 */
public class SellerFeeWithHold {
	private Integer sellerId;
	private Integer orderId;
	private BigDecimal sellerFee;
	private BigDecimal withholdFee;
	private Date createdTime;
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getSellerFee() {
		return sellerFee;
	}
	public void setSellerFee(BigDecimal sellerFee) {
		this.sellerFee = sellerFee;
	}
	public BigDecimal getWithholdFee() {
		return withholdFee;
	}
	public void setWithholdFee(BigDecimal withholdFee) {
		this.withholdFee = withholdFee;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
