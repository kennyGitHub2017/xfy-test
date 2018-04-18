package com.xuanfeiyang.erp.param;

public class GoodsTransferOrderParam {
	private String orderNo;
	private String goodsSku;
	private String name;				//产品名称
	private String transferDateFrom;  //调拨开始日期
	private String transferDateTo;	//调拨结束日期
	private String userName;		//调拨人员
	private String fromStoreId;
	private String fromStoreShelf;
	private String toStoreId;
	private String toShoreShelf;
	private Integer  firstCategory;			//产品大类
	private Integer secondCategory;			//产品中类
	private Integer thirdCategory;			//产品小类
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTransferDateFrom() {
		if (null!=transferDateFrom && transferDateFrom.trim().length()>0){
			return transferDateFrom.concat(" 00:00:00");
		}
		return null;
	}
	public void setTransferDateFrom(String transferDateFrom) {
		this.transferDateFrom = transferDateFrom;
	}
	public String getTransferDateTo() {
		if (transferDateTo!=null && transferDateTo.trim().length()>0){
			return transferDateTo.concat(" 23:59:59");
		}
		return null;
	}
	public void setTransferDateTo(String transferDateTo) {
		this.transferDateTo = transferDateTo;
	}
	public String getGoodsSku() {
		return goodsSku;
	}
	public void setGoodsSku(String goodsSku) {
		this.goodsSku = goodsSku;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFromStoreId() {
		return fromStoreId;
	}
	public void setFromStoreId(String fromStoreId) {
		this.fromStoreId = fromStoreId;
	}
	public String getFromStoreShelf() {
		return fromStoreShelf;
	}
	public void setFromStoreShelf(String fromStoreShelf) {
		this.fromStoreShelf = fromStoreShelf;
	}
	public String getToStoreId() {
		return toStoreId;
	}
	public void setToStoreId(String toStoreId) {
		this.toStoreId = toStoreId;
	}
	public String getToShoreShelf() {
		return toShoreShelf;
	}
	public void setToShoreShelf(String toShoreShelf) {
		this.toShoreShelf = toShoreShelf;
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
