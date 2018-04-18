package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AgentRebate {
	private Integer id;
	private Integer userId;
	private Integer orderId;
	private BigDecimal amount;		//流水金额
	private BigDecimal currentDeposit;		//当前余额
	private Integer type;					//0:收入-订单交易成功  1:支出-订单取消返点退回  2:支出-提款
	private Date createdTime;
	///--------------------------
	private String sellerName;
	private String accountName;
	private String platForm;
	private String userName;
	private String contacts;
	private Integer count;
	
	

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCurrentDeposit() {
		return currentDeposit;
	}

	public void setCurrentDeposit(BigDecimal currentDeposit) {
		this.currentDeposit = currentDeposit;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPlatForm() {
		return platForm;
	}

	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}