package com.xuanfeiyang.erp.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserLoginLog {
	private Integer userId;

	private String username;

	private Integer sellerId;

	private String loginIp;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date loginTime;
	
	// 附件字段: 卖家联系方式
	private String sellerContacts;

	public String getSellerContacts() {
		return sellerContacts;
	}

	public void setSellerContacts(String sellerContacts) {
		this.sellerContacts = sellerContacts;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp == null ? null : loginIp.trim();
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}