package com.xuanfeiyang.erp.param;

public class PurchaseRequestOrderParam {
	private String orderNo;
	private String goodsSku;
	private String goodsName;
	private String applyDateFrom;
	private String applyDateTo;
	private String applier;
	private String deliveryDateFrom;
	private String deliveryDateTo;
	private Integer status;
	private Integer supplierId;			//供应商ID
	private Integer firstCategory;
	private Integer secondCategory;
	private Integer thirdCategory;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getGoodsSku() {
		return goodsSku;
	}
	public void setGoodsSku(String goodsSku) {
		this.goodsSku = goodsSku;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getApplyDateFrom() {
		if (null!=applyDateFrom && applyDateFrom.trim().length()>0){
			return applyDateFrom.concat(" 00:00:00");
		}
		return null;
	}
	public void setApplyDateFrom(String applyDateFrom) {
		this.applyDateFrom = applyDateFrom;
	}
	public String getApplyDateTo() {
		if (null!=applyDateTo && applyDateTo.trim().length()>0){
			return applyDateTo.concat(" 23:59:59");
		}
		return null;
	}
	public void setApplyDateTo(String applyDateTo) {
		this.applyDateTo = applyDateTo;
	}
	public String getApplier() {
		return applier;
	}
	public void setApplier(String applier) {
		this.applier = applier;
	}
	public String getDeliveryDateFrom() {
		if (null!=deliveryDateFrom && deliveryDateFrom.trim().length()>0){
			return deliveryDateFrom.concat(" 00:00:00");
		}
		return deliveryDateFrom;
	}
	public void setDeliveryDateFrom(String deliveryDateFrom) {
		this.deliveryDateFrom = deliveryDateFrom;
	}
	public String getDeliveryDateTo() {
		if (null!=deliveryDateTo && deliveryDateTo.trim().length()>0){
			return deliveryDateTo.concat(" 23:59:59");
		}
		return deliveryDateTo;
	}
	public void setDeliveryDateTo(String deliveryDateTo) {
		this.deliveryDateTo = deliveryDateTo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getFirstCategory() {
		return firstCategory;
	}
	public void setFirstCategory(Integer firstCategory) {
		this.firstCategory = firstCategory;
	}
	public Integer getSecondCategory() {
		return secondCategory;
	}
	public void setSecondCategory(Integer secondCategory) {
		this.secondCategory = secondCategory;
	}
	public Integer getThirdCategory() {
		return thirdCategory;
	}
	public void setThirdCategory(Integer thirdCategory) {
		this.thirdCategory = thirdCategory;
	}
}