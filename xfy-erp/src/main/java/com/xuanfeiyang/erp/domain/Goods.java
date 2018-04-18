package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Goods {
	private Integer id;

	private String goodsSku;

	private String name;

	private String enName;

	private BigDecimal price;

	private BigDecimal cost;

	private String unit;

	private BigDecimal weight;

	private Integer storeId;

	private String storeShelf;

	private Integer storeShelfId;

	private BigDecimal length;

	private BigDecimal width;

	private BigDecimal height;

	private Integer categoryId;
	private Integer baseCategoryId;
	private Integer midCategoryId;

	private String declarationNameEn;

	private String customsCode;

	private String declarationNameCn;

	private BigDecimal declarationCost;

	private String color;

	private String goodsSize;

	private String materil;

	private String brand;

	private Integer supplierId;
	private Integer supplier2Id;
	private Integer supplier3Id;

	private Short status;

	private Integer packingMaterialId;

	private String packingCapacity;

	private Short ispacking;

	private BigDecimal packingFee;

	private String barcode;

	private Short testType;

	private String baseCode;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
	private Date developTime;

	private Integer developUser;

	private Integer saleUser;

	private Integer buyUser;

	private Integer packUser;

	private Integer pickUser;

	private Integer assembleUser;

	private Short isBattery;

	private Short isCopyright;

	private Short isShipping;

	private Short isLiquid;

	private Integer isRegulated;

	private String note;

	private String note1;

	private String note2;

	private String note3;

	private String note4;

	private String note5;

	private Date createdTime;

	private Date lastUpdatedTime;

	private String imgUrl;

	private String supplierName;
	private String supplier2Name;
	private String supplier3Name;

	private String storeName;
	private String storeShelfCode;

	private String categoryName;

	private String statusDesc;

	private String sales7; // 7天销量
	private String sales15;// 15天销量
	private String sales30;// 30天销量
	private String count;// 库存数量
	private String lockCount;// 锁定库存
	private String purchaseCount; // 在途库存
	private String costPrice;// 成本价
	private BigDecimal newCost; // 最新采购价

	private String buyUserName; // 采购人员
	private String pickUserName; // 拣货人员
	private String assembleUserName; // 配货人员
	private String developUserName; // 开发员
	private String packUserName;// 包装人员
	private String oldSku;
	private Integer isMagnetic;// 是否 有磁性
	private String rules;// 规格
	private String model;// 型号
	private String referenceUrl;// 参考链接
	private String ebayReferenceUrl;// 参考链接EBAY
	private String smtReferenceUrl;// 参考链接SMT
	private String amReferenceUrl;// 参考链接AM
	private String materialModel;// 包装型号

	private String openFlag;

	private Integer imgCount;

	private Integer occupy; // 销占数

	private Short belongSelf; // 是否公司sku

	private String itemId;
	private BigDecimal customCost; // sku卖家成本
	private String currency;// 货币
	private String title;
	private String platform;
	private String siteId;
	private String galleryUrl;
	private String accountName;
	private Integer costUpdateType;// 价格更新类型
	private Integer customUpdateType;// 卖家价格更新类型
	private BigDecimal firstCost;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getImgCount() {
		return imgCount;
	}

	public void setImgCount(Integer imgCount) {
		this.imgCount = imgCount;
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreShelfCode() {
		return storeShelfCode;
	}

	public void setStoreShelfCode(String storeShelfCode) {
		this.storeShelfCode = storeShelfCode;
	}

	public Integer getBaseCategoryId() {
		return baseCategoryId;
	}

	public void setBaseCategoryId(Integer baseCategoryId) {
		this.baseCategoryId = baseCategoryId;
	}

	public Integer getMidCategoryId() {
		return midCategoryId;
	}

	public void setMidCategoryId(Integer midCategoryId) {
		this.midCategoryId = midCategoryId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsSku() {
		return goodsSku;
	}

	public void setGoodsSku(String goodsSku) {
		this.goodsSku = goodsSku == null ? null : goodsSku.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName == null ? null : enName.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreShelf() {
		return storeShelf;
	}

	public void setStoreShelf(String storeShelf) {
		this.storeShelf = storeShelf == null ? null : storeShelf.trim();
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getDeclarationNameEn() {
		return declarationNameEn;
	}

	public void setDeclarationNameEn(String declarationNameEn) {
		this.declarationNameEn = declarationNameEn == null ? null
				: declarationNameEn.trim();
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode == null ? null : customsCode.trim();
	}

	public String getDeclarationNameCn() {
		return declarationNameCn;
	}

	public void setDeclarationNameCn(String declarationNameCn) {
		this.declarationNameCn = declarationNameCn == null ? null
				: declarationNameCn.trim();
	}

	public BigDecimal getDeclarationCost() {
		return declarationCost;
	}

	public void setDeclarationCost(BigDecimal declarationCost) {
		this.declarationCost = declarationCost;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color == null ? null : color.trim();
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize == null ? null : goodsSize.trim();
	}

	public String getMateril() {
		return materil;
	}

	public void setMateril(String materil) {
		this.materil = materil == null ? null : materil.trim();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand == null ? null : brand.trim();
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getPackingMaterialId() {
		return packingMaterialId;
	}

	public void setPackingMaterialId(Integer packingMaterialId) {
		this.packingMaterialId = packingMaterialId;
	}

	public String getPackingCapacity() {
		return packingCapacity;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSupplier2Id() {
		return supplier2Id;
	}

	public void setSupplier2Id(Integer supplier2Id) {
		this.supplier2Id = supplier2Id;
	}

	public Integer getSupplier3Id() {
		return supplier3Id;
	}

	public void setSupplier3Id(Integer supplier3Id) {
		this.supplier3Id = supplier3Id;
	}

	public void setPackingCapacity(String packingCapacity) {
		this.packingCapacity = packingCapacity == null ? null : packingCapacity
				.trim();
	}

	public Short getIspacking() {
		return ispacking;
	}

	public void setIspacking(Short ispacking) {
		this.ispacking = ispacking;
	}

	public BigDecimal getPackingFee() {
		return packingFee;
	}

	public void setPackingFee(BigDecimal packingFee) {
		this.packingFee = packingFee;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode == null ? null : barcode.trim();
	}

	public Short getTestType() {
		return testType;
	}

	public void setTestType(Short testType) {
		this.testType = testType;
	}

	public String getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode == null ? null : baseCode.trim();
	}

	public Date getDevelopTime() {
		return developTime;
	}

	public void setDevelopTime(Date developTime) {
		this.developTime = developTime;
	}

	public Integer getDevelopUser() {
		return developUser;
	}

	public void setDevelopUser(Integer developUser) {
		this.developUser = developUser;
	}

	public Integer getSaleUser() {
		return saleUser;
	}

	public void setSaleUser(Integer saleUser) {
		this.saleUser = saleUser;
	}

	public Integer getBuyUser() {
		return buyUser;
	}

	public void setBuyUser(Integer buyUser) {
		this.buyUser = buyUser;
	}

	public Integer getPackUser() {
		return packUser;
	}

	public void setPackUser(Integer packUser) {
		this.packUser = packUser;
	}

	public Integer getPickUser() {
		return pickUser;
	}

	public void setPickUser(Integer pickUser) {
		this.pickUser = pickUser;
	}

	public Integer getAssembleUser() {
		return assembleUser;
	}

	public void setAssembleUser(Integer assembleUser) {
		this.assembleUser = assembleUser;
	}

	public Short getIsBattery() {
		return isBattery;
	}

	public void setIsBattery(Short isBattery) {
		this.isBattery = isBattery;
	}

	public Short getIsCopyright() {
		return isCopyright;
	}

	public void setIsCopyright(Short isCopyright) {
		this.isCopyright = isCopyright;
	}

	public Short getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(Short isShipping) {
		this.isShipping = isShipping;
	}

	public Short getIsLiquid() {
		return isLiquid;
	}

	public void setIsLiquid(Short isLiquid) {
		this.isLiquid = isLiquid;
	}

	public Integer getIsRegulated() {
		return isRegulated;
	}

	public void setIsRegulated(Integer isRegulated) {
		this.isRegulated = isRegulated;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1 == null ? null : note1.trim();
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2 == null ? null : note2.trim();
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3 == null ? null : note3.trim();
	}

	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4 == null ? null : note4.trim();
	}

	public String getNote5() {
		return note5;
	}

	public void setNote5(String note5) {
		this.note5 = note5 == null ? null : note5.trim();
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

	public Integer getStoreShelfId() {
		return storeShelfId;
	}

	public void setStoreShelfId(Integer storeShelfId) {
		this.storeShelfId = storeShelfId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplier2Name() {
		return supplier2Name;
	}

	public void setSupplier2Name(String supplier2Name) {
		this.supplier2Name = supplier2Name;
	}

	public String getSupplier3Name() {
		return supplier3Name;
	}

	public void setSupplier3Name(String supplier3Name) {
		this.supplier3Name = supplier3Name;
	}

	public String getSales7() {
		return sales7;
	}

	public void setSales7(String sales7) {
		this.sales7 = sales7;
	}

	public String getSales15() {
		return sales15;
	}

	public void setSales15(String sales15) {
		this.sales15 = sales15;
	}

	public String getSales30() {
		return sales30;
	}

	public void setSales30(String sales30) {
		this.sales30 = sales30;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getLockCount() {
		return lockCount;
	}

	public void setLockCount(String lockCount) {
		this.lockCount = lockCount;
	}

	public String getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(String purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getBuyUserName() {
		return buyUserName;
	}

	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}

	public String getPickUserName() {
		return pickUserName;
	}

	public void setPickUserName(String pickUserName) {
		this.pickUserName = pickUserName;
	}

	public String getAssembleUserName() {
		return assembleUserName;
	}

	public void setAssembleUserName(String assembleUserName) {
		this.assembleUserName = assembleUserName;
	}

	public String getDevelopUserName() {
		return developUserName;
	}

	public void setDevelopUserName(String developUserName) {
		this.developUserName = developUserName;
	}

	public String getOldSku() {
		return oldSku;
	}

	public void setOldSku(String oldSku) {
		this.oldSku = oldSku;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getIsMagnetic() {
		return isMagnetic;
	}

	public void setIsMagnetic(Integer isMagnetic) {
		this.isMagnetic = isMagnetic;
	}

	public String getReferenceUrl() {
		return referenceUrl;
	}

	public void setReferenceUrl(String referenceUrl) {
		this.referenceUrl = referenceUrl;
	}

	public String getPackUserName() {
		return packUserName;
	}

	public void setPackUserName(String packUserName) {
		this.packUserName = packUserName;
	}

	public BigDecimal getNewCost() {
		return newCost;
	}

	public void setNewCost(BigDecimal newCost) {
		this.newCost = newCost;
	}

	public String getEbayReferenceUrl() {
		return ebayReferenceUrl;
	}

	public void setEbayReferenceUrl(String ebayReferenceUrl) {
		this.ebayReferenceUrl = ebayReferenceUrl;
	}

	public String getSmtReferenceUrl() {
		return smtReferenceUrl;
	}

	public void setSmtReferenceUrl(String smtReferenceUrl) {
		this.smtReferenceUrl = smtReferenceUrl;
	}

	public String getAmReferenceUrl() {
		return amReferenceUrl;
	}

	public void setAmReferenceUrl(String amReferenceUrl) {
		this.amReferenceUrl = amReferenceUrl;
	}

	public Integer getOccupy() {
		return occupy;
	}

	public void setOccupy(Integer occupy) {
		this.occupy = occupy;
	}

	public String getMaterialModel() {
		return materialModel;
	}

	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}

	public BigDecimal getCustomCost() {
		return customCost;
	}

	public void setCustomCost(BigDecimal customCost) {
		this.customCost = customCost;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getGalleryUrl() {
		return galleryUrl;
	}

	public void setGalleryUrl(String galleryUrl) {
		this.galleryUrl = galleryUrl;
	}

	public Short getBelongSelf() {
		return belongSelf;
	}

	public void setBelongSelf(Short belongSelf) {
		this.belongSelf = belongSelf;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getCostUpdateType() {
		return costUpdateType;
	}

	public void setCostUpdateType(Integer costUpdateType) {
		this.costUpdateType = costUpdateType;
	}

	public Integer getCustomUpdateType() {
		return customUpdateType;
	}

	public void setCustomUpdateType(Integer customUpdateType) {
		this.customUpdateType = customUpdateType;
	}

	public BigDecimal getFirstCost() {
		return firstCost;
	}

	public void setFirstCost(BigDecimal firstCost) {
		this.firstCost = firstCost;
	}
	

}