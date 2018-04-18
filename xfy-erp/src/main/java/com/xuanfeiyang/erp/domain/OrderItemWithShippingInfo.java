package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;

/**
 * 订单行信息，增加了一些发货信息，用来申请跟踪号或其他操作
 * 
 * @author Adam
 *
 */
public class OrderItemWithShippingInfo extends OrderItem {
	
	private static final long serialVersionUID = 5964382264775692235L;
	

	private String declarationNameCn;
	
	private String declarationNameEn;

	private BigDecimal declarationCost;
	
	private BigDecimal weight;
	
	private String customsCode;
	
	private String goodsUnit;
	
	

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getDeclarationNameCn() {
		return declarationNameCn;
	}

	public void setDeclarationNameCn(String declarationNameCn) {
		this.declarationNameCn = declarationNameCn;
	}

	public String getDeclarationNameEn() {
		return declarationNameEn;
	}

	public void setDeclarationNameEn(String declarationNameEn) {
		this.declarationNameEn = declarationNameEn;
	}

	public BigDecimal getDeclarationCost() {
		return declarationCost;
	}

	public void setDeclarationCost(BigDecimal declarationCost) {
		this.declarationCost = declarationCost;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	
}
