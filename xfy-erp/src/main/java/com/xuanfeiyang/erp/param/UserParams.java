package com.xuanfeiyang.erp.param;

import com.fasterxml.jackson.annotation.JsonFormat;


public class UserParams {
	
	private String keywords;
	
	private Integer sellerId;
	
	private String sellerFlag;
	
	//代理商申请审核
	private Integer userId;
	
	private Integer status;
	
	private String name;    //代理商名字
	
	private String mobile;   //手机
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone= "GMT+8")
	private String createdTimeFrom;  //申请时间
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String createdTimeTo;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSellerFlag() {
		return sellerFlag;
	}

	public void setSellerFlag(String sellerFlag) {
		this.sellerFlag = sellerFlag;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreatedTimeFrom() {
		return createdTimeFrom;
	}

	public void setCreatedTimeFrom(String createdTimeFrom) {
		this.createdTimeFrom = createdTimeFrom;
	}

	public String getCreatedTimeTo() {
		return createdTimeTo;
	}

	public void setCreatedTimeTo(String createdTimeTo) {
		this.createdTimeTo = createdTimeTo;
	}

}
