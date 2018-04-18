package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品库存信息
 * 具体到每个仓库的每个货架
 * @author Adam
 *
 */
public class GoodsInventory {
	
	private Integer id;
	
	private String goodsSku;
	
	private Integer storeId;
	
	private Integer storeShelfId;
	
	private Integer count;
	
	private Date lastUpdatedTime;
	
	// 附件字段:
	// 商品名
	private String goodsName;
	// 商品单位
	private String goodsUnit;
	// 商品仓库
	private String storeName;
	// 商品仓位编码
	private String storeShelfCode;
	// 商品大类名
	private String goodsBaseCategoryName;
	// 商品中类名
	private String goodsMidCategoryName;
	// 商品小类名
	private String goodsCategoryName;
	
	// 历史加权平均价格
	private BigDecimal hisAvgPrice;
	
	public BigDecimal getHisAvgPrice() {
		return hisAvgPrice;
	}

	public void setHisAvgPrice(BigDecimal hisAvgPrice) {
		this.hisAvgPrice = hisAvgPrice;
	}

	public String getGoodsBaseCategoryName() {
		return goodsBaseCategoryName;
	}

	public void setGoodsBaseCategoryName(String goodsBaseCategoryName) {
		this.goodsBaseCategoryName = goodsBaseCategoryName;
	}

	public String getGoodsMidCategoryName() {
		return goodsMidCategoryName;
	}

	public void setGoodsMidCategoryName(String goodsMidCategoryName) {
		this.goodsMidCategoryName = goodsMidCategoryName;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
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
		this.goodsSku = goodsSku;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GoodsInventory [id=");
		builder.append(id);
		builder.append(", goodsSku=");
		builder.append(goodsSku);
		builder.append(", storeId=");
		builder.append(storeId);
		builder.append(", storeShelfId=");
		builder.append(storeShelfId);
		builder.append(", count=");
		builder.append(count);
		builder.append(", lastUpdatedTime=");
		builder.append(lastUpdatedTime);
		builder.append("]");
		return builder.toString();
	}
	
}
