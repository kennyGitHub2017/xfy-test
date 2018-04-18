package com.xuanfeiyang.erp.domain;

import java.util.Date;

public class GoodsInventoryLock {
	private Integer id;

	private String goodsSku;

	private Integer lockCount;

	private Integer saleOrderId;

	private Date lastUpdatedTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsSku() {
		return goodsSku;
	}

	public void setGoodsSku(String goodsSku) {
		this.goodsSku = goodsSku == null ? null : goodsSku.trim();
	}

	public Integer getLockCount() {
		return lockCount;
	}

	public void setLockCount(Integer lockCount) {
		this.lockCount = lockCount;
	}

	public Integer getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(Integer saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
}