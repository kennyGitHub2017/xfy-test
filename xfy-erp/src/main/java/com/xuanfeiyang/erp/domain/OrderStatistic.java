package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;

public class OrderStatistic {
	
	// 待审核数量
	private int pendingCount;
	
	// 待发货数量
	private int shippingCount;
	
	// 已发货数量
	private int shippedCount;
	
	// 有问题待处理数量
	private int errorCount;
	
	private int skuCount;   //sku统计
	
	private BigDecimal amountCount;    //金额统计

	public int getPendingCount() {
		return pendingCount;
	}

	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}

	public int getShippingCount() {
		return shippingCount;
	}

	public void setShippingCount(int shippingCount) {
		this.shippingCount = shippingCount;
	}

	public int getShippedCount() {
		return shippedCount;
	}

	public void setShippedCount(int shippedCount) {
		this.shippedCount = shippedCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public int getSkuCount() {
		return skuCount;
	}

	public void setSkuCount(int skuCount) {
		this.skuCount = skuCount;
	}

	public BigDecimal getAmountCount() {
		return amountCount;
	}

	public void setAmountCount(BigDecimal amountCount) {
		this.amountCount = amountCount;
	}
}
