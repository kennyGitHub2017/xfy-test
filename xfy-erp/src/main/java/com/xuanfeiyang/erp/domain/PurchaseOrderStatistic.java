package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;

public class PurchaseOrderStatistic {
	////////////采购入库单汇总 /////////////////////////
	// 采购单数量
	private Integer orderCount;
	// 采购数量
	private Integer buyCount;
	//采购金额
	private BigDecimal costTotal;
	
	// 检验数量
	private Integer testTotal;
	// 不合格数量
	private Integer unQualifiedTotal;
	// 合格数量
	private Integer qualifiedTotal;
	
	//运费
	private Float freightTotal;
	
	
	/////////////出库入流水汇总///////////////
	private Integer totalIn;    // 收入数量汇总
	private Integer totalOut;   //发出数量汇总

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getTestTotal() {
		return testTotal;
	}

	public void setTestTotal(Integer testTotal) {
		this.testTotal = testTotal;
	}

	public Integer getUnQualifiedTotal() {
		return unQualifiedTotal;
	}

	public void setUnQualifiedTotal(Integer unQualifiedTotal) {
		this.unQualifiedTotal = unQualifiedTotal;
	}

	public Integer getQualifiedTotal() {
		return qualifiedTotal;
	}

	public void setQualifiedTotal(Integer qualifiedTotal) {
		this.qualifiedTotal = qualifiedTotal;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getTotalIn() {
		return totalIn;
	}

	public void setTotalIn(Integer totalIn) {
		this.totalIn = totalIn;
	}

	public Integer getTotalOut() {
		return totalOut;
	}

	public void setTotalOut(Integer totalOut) {
		this.totalOut = totalOut;
	}

	public BigDecimal getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}

	public Float getFreightTotal() {
		return freightTotal;
	}

	public void setFreightTotal(Float freightTotal) {
		this.freightTotal = freightTotal;
	}
}
