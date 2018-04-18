package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 汇率信息
 * @author kenny
 *
 */
public class CurrencyRates {
	
	private Long id;
	private String currency; //货币
	private BigDecimal exchangeRate;//兑换率
	private Date createdTime;//记录添加时间
	private Date lastUpdatedTime; //记录最后更新时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
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
	


}
