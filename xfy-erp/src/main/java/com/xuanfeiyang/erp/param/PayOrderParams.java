package com.xuanfeiyang.erp.param;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayOrderParams {
	
	private String contacts;  //充值人姓名
	
	private String mobile;   //手机号
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private String startDate;   //开始时间
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private String endDate;   //结束时间
	
	private Integer type;   //充值类型：1：支付宝充值，0：微信充值
	
	private BigDecimal amountFrom;  //充值金额
	
	private BigDecimal amountTo;
	
	private Integer status;   //支付状态
	
	private String outTradeNo;  //商户订单号

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getAmountFrom() {
		return amountFrom;
	}

	public void setAmountFrom(BigDecimal amountFrom) {
		this.amountFrom = amountFrom;
	}

	public BigDecimal getAmountTo() {
		return amountTo;
	}

	public void setAmountTo(BigDecimal amountTo) {
		this.amountTo = amountTo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
}
