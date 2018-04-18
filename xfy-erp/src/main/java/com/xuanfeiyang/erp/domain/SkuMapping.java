package com.xuanfeiyang.erp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SkuMapping {
	
	private String platformSku;
	
	private String oldSku;
	
	private String newSku;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	public String getPlatformSku() {
		return platformSku;
	}

	public void setPlatformSku(String platformSku) {
		this.platformSku = platformSku;
	}

	public String getOldSku() {
		return oldSku;
	}

	public void setOldSku(String oldSku) {
		this.oldSku = oldSku;
	}

	public String getNewSku() {
		return newSku;
	}

	public void setNewSku(String newSku) {
		this.newSku = newSku;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
