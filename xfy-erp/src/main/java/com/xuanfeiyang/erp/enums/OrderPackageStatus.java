package com.xuanfeiyang.erp.enums;

public enum OrderPackageStatus {
	
	PENDING(1, "待处理"),
	SHIPPING_MATCHED(2, "已匹配运输方式"),
	TRACKER_APPLIED(3, "已申请跟踪号"),
	PRINTED(4, "已打印"),
	SCANNED(5, "已扫描"),
	SHIPPED(6, "已交接"),
	DELETED(7, "已删除"),
	RETURNED(8, "物流退回"), 
	WAITSEND(11, "待发货");
	
	private Integer code;
	private String desc;
	
	OrderPackageStatus(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer code() {
		return code;
	}

	public String desc() {
		return desc;
	}
	
	public static OrderPackageStatus valueOfCode(int code) {
		OrderPackageStatus[] allStatus = OrderPackageStatus.values();
		for (OrderPackageStatus status : allStatus) {
			if (status.code() == code) {
				return status;
			}
		}
		
		return null;
	}
}
