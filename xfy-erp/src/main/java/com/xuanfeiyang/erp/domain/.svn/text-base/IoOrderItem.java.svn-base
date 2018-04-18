package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

public class IoOrderItem {
	private Integer id;

	private String orderNo;

	private String goodsSku;

	private String goodsName;
	
	private BigDecimal goodsCost;
	
	private BigDecimal goodsWeight;

	private Integer buyCount;
	
	private Integer testType;

	private Integer testCount;

	private Integer qualifiedCount;

	private Integer storeId;

	private Integer storeShelfId;

	private Integer unqualifiedCount;

	private String reason;
	
	private Integer unqualifiedStoreId;

	private Integer unqualifiedShelfId;

	private Integer statCount;

	private BigDecimal statPrice;

	private BigDecimal statAmount;

	private Date lastUpdatedTime;
	
	private Integer receivedCount; 	//已交数量
	
	private BigDecimal packingMaterialFee;		//SKU包装费
	
	////////////////////////////////////////////////
	private Integer remainCount;
	
	private String startDate;
	private String endDate;
	private String storeShelf;
	private String storeName;			//合格仓库
	private Integer typeDetail;
	private String typeDetailName;
	private Integer type;
	private String typeName;
	
	
	private Date orderCreated;			//入库单制作时间
	private String createUserName;		//入库单制单人
	private String buyUserName;			//采购员
	private String buyOrderNo;			//采购单号
	private String shelfName;			//合格货位
	private String unqualifiedShelf;	//不合格货位
	
	private String supplierName;		//供应商名称
	
	private Float score;				//供应商分数				
	
	private String oldSku; 
	
	private Integer itemId;				//采购单itemId
	
	private String buyUser;
	
	private String goodsStatus; //产品状态
	
	private String dateTimeFrom;// 时间开始
	private String dateTimeTo; // 时间结束
	private Integer typeDetail2;
	private String accountName;//账号名
	
	private BigDecimal skuTotalWeight;				//SKU总重量
	
	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getDevelopUser() {
		return developUser;
	}

	public void setDevelopUser(String developUser) {
		this.developUser = developUser;
	}

	public String getDevelopTime() {
		return developTime;
	}

	public void setDevelopTime(String developTime) {
		this.developTime = developTime;
	}

	private String developUser;//产品开发人
	private String developTime;//产品开发时间
	
	public String getOldSku() {
		return oldSku;
	}

	public void setOldSku(String oldSku) {
		this.oldSku = oldSku;
	}

	public BigDecimal getGoodsCost() {
		return goodsCost;
	}

	public void setGoodsCost(BigDecimal goodsCost) {
		this.goodsCost = goodsCost;
	}

	/**
	 * 已交数量 =合格数量+不合格数量
	 * @return
	 */
	public Integer getReceivedCount() {
		qualifiedCount = qualifiedCount == null ? 0 : qualifiedCount;
		unqualifiedCount = unqualifiedCount == null ? 0 : unqualifiedCount;
		receivedCount = qualifiedCount+unqualifiedCount;
		return receivedCount;
	}
	
	/**
	 * 合格率
	 * @return
	 */
	public String getHgl(){
		if (null==unqualifiedCount || null==testCount || testCount==0){
			return "";
		}
		Float hgl = (1f- Float.valueOf(unqualifiedCount)/Float.valueOf(testCount));
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(4);
		return nf.format(hgl*100).concat("%");
	}
	
	/**
	 * 不良率
	 * @return
	 */
	public String getBll(){
		if (null==unqualifiedCount || null==testCount || testCount==0){
			return null;
		}
		Float hgl = (1f- Float.valueOf(unqualifiedCount)/Float.valueOf(testCount));
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(4);
        return nf.format(100-hgl*100);
	}
	
	/**
	 * 供应商分数
	 * @return
	 */
	public Float getScore(){
		String bll = getBll();
		if (null!=bll){
			Float temp = Float.parseFloat(bll);
			if (temp.floatValue()==0){
				score = 100f;
			}else if(temp<10){
				score = 90f;
			}else if (temp<20){
				score = 80f;
			}else if (temp<30){
				score =70f;
			}else{
				score = 60f;
			}
		}
		return score==null?0:score;
	}
	

	public void setScore(Float score) {
		this.score = score;
	}

	public void setReceivedCount(Integer receivedCount) {
		this.receivedCount = receivedCount;
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
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public String getGoodsSku() {
		return goodsSku;
	}

	public void setGoodsSku(String goodsSku) {
		this.goodsSku = goodsSku == null ? null : goodsSku.trim();
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName == null ? null : goodsName.trim();
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getTestCount() {
		return testCount;
	}

	public void setTestCount(Integer testCount) {
		this.testCount = testCount;
	}

	public Integer getQualifiedCount() {
		return qualifiedCount;
	}

	public void setQualifiedCount(Integer qualifiedCount) {
		this.qualifiedCount = (qualifiedCount == null ? 0 : qualifiedCount);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStoreShelfId() {
		return storeShelfId;
	}

	public void setStoreShelfId(Integer storeShelfId) {
		this.storeShelfId = storeShelfId;
	}

	public Integer getUnqualifiedCount() {
		return unqualifiedCount;
	}

	public void setUnqualifiedCount(Integer unqualifiedCount) {
		this.unqualifiedCount = (unqualifiedCount == null ? 0 : unqualifiedCount);
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
	}

	public Integer getUnqualifiedShelfId() {
		return unqualifiedShelfId;
	}

	public void setUnqualifiedShelfId(Integer unqualifiedShelfId) {
		this.unqualifiedShelfId = unqualifiedShelfId;
	}

	public Integer getStatCount() {
		return statCount;
	}

	public void setStatCount(Integer statCount) {
		this.statCount = statCount;
	}

	public BigDecimal getStatPrice() {
		return statPrice;
	}

	public void setStatPrice(BigDecimal statPrice) {
		this.statPrice = statPrice;
	}

	public BigDecimal getStatAmount() {
		return statAmount;
	}

	public void setStatAmount(BigDecimal statAmount) {
		this.statAmount = statAmount;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}

	public Integer getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
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

	public String getStoreShelf() {
		return storeShelf;
	}

	public void setStoreShelf(String storeShelf) {
		this.storeShelf = storeShelf;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getBuyUserName() {
		return buyUserName;
	}

	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}

	public String getBuyOrderNo() {
		return buyOrderNo;
	}

	public void setBuyOrderNo(String buyOrderNo) {
		this.buyOrderNo = buyOrderNo;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getUnqualifiedShelf() {
		return unqualifiedShelf;
	}

	public void setUnqualifiedShelf(String unqualifiedShelf) {
		this.unqualifiedShelf = unqualifiedShelf;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeDetail() {
		return typeDetail;
	}

	public void setTypeDetail(Integer typeDetail) {
		this.typeDetail = typeDetail;
	}

	public String getTypeDetailName() {
		return typeDetailName;
	}

	public void setTypeDetailName(String typeDetailName) {
		this.typeDetailName = typeDetailName;
	}

	public Integer getUnqualifiedStoreId() {
		return unqualifiedStoreId;
	}

	public void setUnqualifiedStoreId(Integer unqualifiedStoreId) {
		this.unqualifiedStoreId = unqualifiedStoreId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getPackingMaterialFee() {
		return packingMaterialFee;
	}

	public void setPackingMaterialFee(BigDecimal packingMaterialFee) {
		this.packingMaterialFee = packingMaterialFee;
	}

	public String getBuyUser() {
		return buyUser;
	}

	public void setBuyUser(String buyUser) {
		this.buyUser = buyUser;
	}

	public String getDateTimeFrom() {
		return dateTimeFrom;
	}

	public void setDateTimeFrom(String dateTimeFrom) {
		this.dateTimeFrom = dateTimeFrom;
	}

	public String getDateTimeTo() {
		return dateTimeTo;
	}

	public void setDateTimeTo(String dateTimeTo) {
		this.dateTimeTo = dateTimeTo;
	}

	public Integer getTypeDetail2() {
		return typeDetail2;
	}

	public void setTypeDetail2(Integer typeDetail2) {
		this.typeDetail2 = typeDetail2;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getSkuTotalWeight() {
		return skuTotalWeight;
	}

	public void setSkuTotalWeight(BigDecimal skuTotalWeight) {
		this.skuTotalWeight = skuTotalWeight;
	}

	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
}