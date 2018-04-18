package com.xuanfeiyang.erp.param;

public class SupplierScoreParam {
	private Integer supplierId;			//供应商Id
	private String supplierName;		//供应商名称
	private String createdTimeFrom;		//入库单创建时间从
	private String createdTimeTo;		//入库单创建时间到
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getCreatedTimeFrom() {
		return createdTimeFrom;
	}
	public void setCreatedTimeFrom(String createdTimeFrom) {
		this.createdTimeFrom = createdTimeFrom;
	}
	public String getCreatedTimeTo() {
		return createdTimeTo;
	}
	public void setCreatedTimeTo(String createdTimeTo) {
		this.createdTimeTo = createdTimeTo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
