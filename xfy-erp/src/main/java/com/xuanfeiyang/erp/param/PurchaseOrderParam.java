package com.xuanfeiyang.erp.param;

public class PurchaseOrderParam {
    private String  orderNo;
    private String waybillNo;			//运单编号
    private String goodsSku;
    private String goodsName;
    private String purchaseDateFrom;		//采购日期从
    private String purchaseDateTo;			//采购日期到
    private String deliveryDateFrom;		//要求交货日期从
    private String deliveryDateTo;			//要求交货日期到
    private String status;
    private Integer supplierId;
    private String buyUserName;			//采购员
    private String payMethod;
	private Integer firstCategory;
	private Integer secondCategory;
	private Integer thirdCategory;
	private String startDate;//开始时间
	private String endDate;//结束时间
	private String store; //仓库
	private String storeShelf;//仓位
	private Integer isSample;	//是否样品单
	
	private String paidDateFrom;	//付款日期从
	private String paidDateTo;		//付款日期到
	
	private String payStatus;		//付款状态
	
	private Integer arrived;		//采购单是否到货 1:到货
	
	private String orderStr;;
	
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
	public String getPurchaseDateFrom() {
		return purchaseDateFrom;
	}
	public void setPurchaseDateFrom(String purchaseDateFrom) {
		this.purchaseDateFrom = purchaseDateFrom;
	}
	public String getPurchaseDateTo() {
		return purchaseDateTo;
	}
	public void setPurchaseDateTo(String purchaseDateTo) {
		this.purchaseDateTo = purchaseDateTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getBuyUserName() {
		return buyUserName;
	}
	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
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
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getStoreShelf() {
		return storeShelf;
	}
	public void setStoreShelf(String storeShelf) {
		this.storeShelf = storeShelf;
	}
	public String getWaybillNo() {
		return waybillNo;
	}
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	public String getDeliveryDateFrom() {
		return deliveryDateFrom;
	}
	public void setDeliveryDateFrom(String deliveryDateFrom) {
		this.deliveryDateFrom = deliveryDateFrom;
	}
	public String getDeliveryDateTo() {
		return deliveryDateTo;
	}
	public void setDeliveryDateTo(String deliveryDateTo) {
		this.deliveryDateTo = deliveryDateTo;
	}
	public Integer getIsSample() {
		return isSample;
	}
	public void setIsSample(Integer isSample) {
		this.isSample = isSample;
	}
	public String getOrderStr() {
		return orderStr;
	}
	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
	
	public String getPaidDateFrom() {
		return paidDateFrom;
	}
	public void setPaidDateFrom(String paidDateFrom) {
		this.paidDateFrom = paidDateFrom;
	}
	public String getPaidDateTo() {
		return paidDateTo;
	}
	public void setPaidDateTo(String paidDateTo) {
		this.paidDateTo = paidDateTo;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public Integer getArrived() {
		return arrived;
	}
	public void setArrived(Integer arrived) {
		this.arrived = arrived;
	}
}