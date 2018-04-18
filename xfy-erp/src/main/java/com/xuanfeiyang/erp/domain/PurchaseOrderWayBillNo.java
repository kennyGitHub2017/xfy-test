package com.xuanfeiyang.erp.domain;

import java.util.Date;
/**
 * 采购单运单号
 * @author Administrator
 *
 */
public class PurchaseOrderWayBillNo {
	private String orderNo;
	private String waybillNo;
	private String logisticsCompany;
	private Date createdTime;
	
	/////////////////////////////
	
	private Boolean firstWaybillNo;
	private String waybillNoNew;
	private String logisticsCompanyNew;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getWaybillNo() {
		return waybillNo;
	}
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	public String getLogisticsCompany() {
		return logisticsCompany;
	}
	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getWaybillNoNew() {
		return waybillNoNew;
	}
	public void setWaybillNoNew(String waybillNoNew) {
		this.waybillNoNew = waybillNoNew;
	}
	public String getLogisticsCompanyNew() {
		return logisticsCompanyNew;
	}
	public void setLogisticsCompanyNew(String logisticsCompanyNew) {
		this.logisticsCompanyNew = logisticsCompanyNew;
	}

	public Boolean getFirstWaybillNo() {
		return firstWaybillNo;
	}

	public void setFirstWaybillNo(Boolean firstWaybillNo) {
		this.firstWaybillNo = firstWaybillNo;
	}
}
