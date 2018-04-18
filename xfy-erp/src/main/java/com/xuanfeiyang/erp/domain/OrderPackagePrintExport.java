package com.xuanfeiyang.erp.domain;

import java.util.Date;

/**
 * 打印工作台数据导出
 * @author Administrator
 *
 */
public class OrderPackagePrintExport {
	private String id;
	private String orderId;
	private Date printTime;
	private String selfSeller;
	private String shippingName;
	private String trackNumber;
	private String isSend;
	private String printedFlag;
	private String statusName;
	private String packageWeight;
	private String shippingFee;
	private String sku;
	private String goodsName;
	private String price;
	private String totalPrice;
	private String storeName;
	private Integer orderAmount;
	private Integer packageAmount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSelfSeller() {
		return selfSeller;
	}
	public void setSelfSeller(String selfSeller) {
		this.selfSeller = selfSeller;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}
	public String getIsSend() {
		return isSend;
	}
	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}
	public String getPrintedFlag() {
		return printedFlag;
	}
	public void setPrintedFlag(String printedFlag) {
		this.printedFlag = printedFlag;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getPackageWeight() {
		return packageWeight;
	}
	public void setPackageWeight(String packageWeight) {
		this.packageWeight = packageWeight;
	}
	public String getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(String shippingFee) {
		this.shippingFee = shippingFee;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Integer getPackageAmount() {
		return packageAmount;
	}
	public void setPackageAmount(Integer packageAmount) {
		this.packageAmount = packageAmount;
	}
	public Date getPrintTime() {
		return printTime;
	}
	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}
}
