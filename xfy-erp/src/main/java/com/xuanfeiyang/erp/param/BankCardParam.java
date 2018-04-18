package com.xuanfeiyang.erp.param;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BankCardParam {
	
	private String bankCardName;   //银行卡名称
	private String branchInfo;    //支行信息
	private String cardNumber;   //银行卡号
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String createdTimeFrom;     //添加时间
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String createdTimeTo; 
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String lastUpdatedTimeFrom;   //修改时间
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String lastUpdatedTimeTo; 
	private Integer userId;      //用户ID
	
	public String getBankCardName() {
		return bankCardName;
	}
	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}
	public String getBranchInfo() {
		return branchInfo;
	}
	public void setBranchInfo(String branchInfo) {
		this.branchInfo = branchInfo;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
	public String getLastUpdatedTimeFrom() {
		return lastUpdatedTimeFrom;
	}
	public void setLastUpdatedTimeFrom(String lastUpdatedTimeFrom) {
		this.lastUpdatedTimeFrom = lastUpdatedTimeFrom;
	}
	public String getLastUpdatedTimeTo() {
		return lastUpdatedTimeTo;
	}
	public void setLastUpdatedTimeTo(String lastUpdatedTimeTo) {
		this.lastUpdatedTimeTo = lastUpdatedTimeTo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
