package com.xuanfeiyang.erp.enums;

public enum OrderStatusEnum {
	NOAUDIT(1, "未审核"),
	AUDITED(2, "待锁定"),
	LOCKED(3, "已锁定"),
	WAITSEND(4, "待发货"),
	ALL_PACKAGE(5, "已生成包裹"),
	CANCEL(6, "取消"),
	SHIPPED(7, "发货"),
	RETURN(8,"退货"),
	HANDLERETURN(9,"已处理退货");
	private Integer code;
	private String desc;
	
	OrderStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getStatusName(Short status) {
        for (OrderStatusEnum c : OrderStatusEnum.values()) {
            if (c.code.equals(status.intValue())) {
                return c.desc;
            }
        }
        return null;
    }

	public Integer code() {
		return code;
	}

	public String desc() {
		return desc;
	}
}
