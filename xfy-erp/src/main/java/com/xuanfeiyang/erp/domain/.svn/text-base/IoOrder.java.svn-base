package com.xuanfeiyang.erp.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xuanfeiyang.erp.App;

public class IoOrder {

	private Integer id;

	private String orderNo; // 入库单号

	private Integer createdUserId;

	@JsonFormat(pattern=App.PATTERN_DATETIME, timezone=App.TIMEZONE)
	private Date createdTime;

	@JsonFormat(pattern=App.PATTERN_DATETIME, timezone=App.TIMEZONE)
	private Date lastUpdatedTime;

	private Integer auditStatus; // 审核状态（0-未审核,1-已审核）

	private Integer auditUserId;

	@JsonFormat(pattern=App.PATTERN_DATETIME, timezone=App.TIMEZONE)
	private Date auditTime;

	private Integer storeId;

	private String note;

	private Integer type; // 类型(0-入库，1-出库)

	private Integer typeDetail;

	private Integer sellOrderId;

	private String buyOrderNo; // 采购单号
	
	private Integer buyOrderId;	//采购单Id

	private String createdUserName;
	
	private String auditUserName;
	
	@DateTimeFormat(pattern=App.PATTERN_DATE)
	private Date ioDate;
	
	private List<IoOrderItem> items;
	
	private String typeName; //出入库类型名
	private String storeName;//仓库名
	
	private String purchaseOrderType;	//采购单类型
	private Integer purchaseOrderStatus;	//采购单状态
	private Date purchaseDate;				//采购日期
	private String purchaseBuyer;			//采购人
	
	private String serialNumber;	//流水号
	private String accountName; //账号名称

	public List<IoOrderItem> getItems() {
		return items;
	}

	public void setItems(List<IoOrderItem> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getTypeDetail() {
		return typeDetail;
	}

	public void setTypeDetail(Integer typeDetail) {
		this.typeDetail = typeDetail;
	}

	public Integer getSellOrderId() {
		return sellOrderId;
	}

	public void setSellOrderId(Integer sellOrderId) {
		this.sellOrderId = sellOrderId;
	}

	public String getBuyOrderNo() {
		return buyOrderNo;
	}

	public void setBuyOrderNo(String buyOrderNo) {
		this.buyOrderNo = buyOrderNo;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getIoDate() {
		return ioDate;
	}

	public void setIoDate(Date ioDate) {
		this.ioDate = ioDate;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getPurchaseOrderType() {
		return purchaseOrderType;
	}

	public void setPurchaseOrderType(String purchaseOrderType) {
		this.purchaseOrderType = purchaseOrderType;
	}

	public Integer getPurchaseOrderStatus() {
		return purchaseOrderStatus;
	}

	public void setPurchaseOrderStatus(Integer purchaseOrderStatus) {
		this.purchaseOrderStatus = purchaseOrderStatus;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseBuyer() {
		return purchaseBuyer;
	}

	public void setPurchaseBuyer(String purchaseBuyer) {
		this.purchaseBuyer = purchaseBuyer;
	}

	public Integer getBuyOrderId() {
		return buyOrderId;
	}

	public void setBuyOrderId(Integer buyOrderId) {
		this.buyOrderId = buyOrderId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
}
