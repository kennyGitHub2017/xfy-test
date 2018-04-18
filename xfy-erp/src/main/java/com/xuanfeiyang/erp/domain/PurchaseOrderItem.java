package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseOrderItem {
	private Integer id;

	private String orderNo;

	private String goodsSku;

	private String goodsName;

	private BigDecimal goodsCost;

	private Integer goodsCount;

	private String goodsUnit;

	private Integer goodsCategory;

	private BigDecimal goodsWeight;

	private Date deliveryDate;

	private Integer receivedCount; // 已入库数量/已交数量(合格+不合格）

	private Integer qualifiedCount; // 合格数量

	private Integer unQualifiedCount; // 不合格数量

	// ///////////////////////////////////////////////////////

	private BigDecimal newCost; // 最新采购价
	private Integer purchaseRequestId; // 关联的请购单号
	private String supplierName; // 供应商名称
	private String buyUserName; // 采购员名称
	private String goodsCategoryName; // 产品分类
	private Integer storeId; // 仓库id
	private Integer remainCount; // 采购数量-已接数量
	private String payMethod;
	private String color;
	private String goodsSize;
	private String model;
	private String rules;
	private Integer imgCount;

	private List<StoreShelf> shelfs = new ArrayList<StoreShelf>(); // 该仓库下的所有货位
	private Integer storeShelfId; // 货位id
	private String storeShelf;		//货位号

	// 旧SKU
	private String oldSku;

	public PurchaseOrderItem() {
	}

	public PurchaseOrderItem(String goodsSku, String goodsName,
			BigDecimal goodsCost, Integer goodsCount, String goodsUnit,
			Integer category, BigDecimal goodsWeight, Date deliveryDate,
			String categoryId) {
		super();
		this.goodsSku = goodsSku;
		this.goodsName = goodsName;
		this.goodsCost = goodsCost;
		this.goodsCount = goodsCount;
		this.goodsUnit = goodsUnit;
		this.goodsCategory = category;
		this.goodsWeight = goodsWeight;
		this.deliveryDate = deliveryDate;
		if (null != categoryId && !"".equals(categoryId)) {
			this.goodsCategory = Integer.valueOf(categoryId);
		}
	}

	public String getOldSku() {
		return oldSku;
	}

	public void setOldSku(String oldSku) {
		this.oldSku = oldSku;
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

	public BigDecimal getGoodsCost() {
		return goodsCost;
	}

	public void setGoodsCost(BigDecimal goodsCost) {
		this.goodsCost = goodsCost;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
	}

	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(Integer goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public BigDecimal getNewCost() {
		return newCost;
	}

	public void setNewCost(BigDecimal newCost) {
		this.newCost = newCost;
	}

	public Integer getPurchaseRequestId() {
		return purchaseRequestId;
	}

	public void setPurchaseRequestId(Integer purchaseRequestId) {
		this.purchaseRequestId = purchaseRequestId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getBuyUserName() {
		return buyUserName;
	}

	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public List<StoreShelf> getShelfs() {
		return shelfs;
	}

	public void setShelfs(List<StoreShelf> shelfs) {
		this.shelfs = shelfs;
	}

	public Integer getStoreShelfId() {
		return storeShelfId;
	}

	public void setStoreShelfId(Integer storeShelfId) {
		this.storeShelfId = storeShelfId;
	}

	public Integer getReceivedCount() {
		return receivedCount;
	}

	public void setReceivedCount(Integer receivedCount) {
		this.receivedCount = receivedCount;
	}

	public Integer getQualifiedCount() {
		return qualifiedCount;
	}

	public void setQualifiedCount(Integer qualifiedCount) {
		this.qualifiedCount = qualifiedCount;
	}

	public Integer getUnQualifiedCount() {
		return unQualifiedCount;
	}

	public void setUnQualifiedCount(Integer unQualifiedCount) {
		this.unQualifiedCount = unQualifiedCount;
	}

	/**
	 * 返回未交数量= 采购数量-已接数量
	 * 
	 * @return
	 */
	public Integer getUnReceivedCount() {
		return this.goodsCount - receivedCount;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public Integer getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public Integer getImgCount() {
		return imgCount;
	}

	public void setImgCount(Integer imgCount) {
		this.imgCount = imgCount;
	}

	public String getStoreShelf() {
		return storeShelf;
	}

	public void setStoreShelf(String storeShelf) {
		this.storeShelf = storeShelf;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
}