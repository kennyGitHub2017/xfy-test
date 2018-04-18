package com.xuanfeiyang.erp.param;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseQueryParams {
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	} 
	
	
}
