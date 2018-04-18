package com.xuanfeiyang.erp.param;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SysInformationParams {
	
	private String keywords;
	
	private Integer type;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date lastUpdatedTimeStart;

	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date lastUpdatedTimeEnd;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getLastUpdatedTimeStart() {
		return lastUpdatedTimeStart;
	}

	public void setLastUpdatedTimeStart(Date lastUpdatedTimeStart) {
		this.lastUpdatedTimeStart = lastUpdatedTimeStart;
	}

	public Date getLastUpdatedTimeEnd() {
		return lastUpdatedTimeEnd;
	}

	public void setLastUpdatedTimeEnd(Date lastUpdatedTimeEnd) {
		this.lastUpdatedTimeEnd = lastUpdatedTimeEnd;
	}

	
}
