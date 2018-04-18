package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SellerDeposit {
	
	private Integer sellerId;
	
	private BigDecimal deposit;
	
	private Date createdTime;
	
	private Date lastUpdatedTime;
	
	private Date createdTimeFrom;
	
	private Date createdTimeTo;

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Date getCreatedTimeFrom() {
		return createdTimeFrom;
	}

	public void setCreatedTimeFrom(Date createdTimeFrom) {
		this.createdTimeFrom = createdTimeFrom;
	}

	public Date getCreatedTimeTo() {
		return createdTimeTo;
	}

	public void setCreatedTimeTo(Date createdTimeTo) {
		this.createdTimeTo = createdTimeTo;
	}
	
	
}
