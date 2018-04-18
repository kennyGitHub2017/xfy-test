package com.xuanfeiyang.erp.param;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserLoginLogParams {
	
	private Integer sellerId;
	
	private Integer userId;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date loginTimeStart;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date loginTimeEnd;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Date getLoginTimeStart() {
		return loginTimeStart;
	}

	public void setLoginTimeStart(Date loginTimeStart) {
		this.loginTimeStart = loginTimeStart;
	}

	public Date getLoginTimeEnd() {
		return loginTimeEnd;
	}

	public void setLoginTimeEnd(Date loginTimeEnd) {
		this.loginTimeEnd = loginTimeEnd;
	}
	
	
}
