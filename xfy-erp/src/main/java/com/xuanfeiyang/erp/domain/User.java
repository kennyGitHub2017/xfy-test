package com.xuanfeiyang.erp.domain;

import java.util.Date;

public class User {
	private Integer id;

	private String username;

	private String password;

	private String realName;

	private Short queryPageSize;

	private String messageAccounts;

	private String orderAccounts;

	private Integer sellerId;

	private Date createdTime;

	private Date lastUpdatedTime;

	private Date lastLogTime;

	private String permission;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public Short getQueryPageSize() {
		return queryPageSize;
	}

	public void setQueryPageSize(Short queryPageSize) {
		this.queryPageSize = queryPageSize;
	}

	public String getMessageAccounts() {
		return messageAccounts;
	}

	public void setMessageAccounts(String messageAccounts) {
		this.messageAccounts = messageAccounts == null ? null : messageAccounts
				.trim();
	}

	public String getOrderAccounts() {
		return orderAccounts;
	}

	public void setOrderAccounts(String orderAccounts) {
		this.orderAccounts = orderAccounts == null ? null : orderAccounts
				.trim();
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
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

	public Date getLastLogTime() {
		return lastLogTime;
	}

	public void setLastLogTime(Date lastLogTime) {
		this.lastLogTime = lastLogTime;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}
}