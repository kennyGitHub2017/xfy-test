package com.xuanfeiyang.erp.enums;
/**
 * 平台账户状态枚举
 * @author bernard
 *
 */
public enum AccountStatusEnum {
	UNENABLE("未启用", "0"), NORMAL("正常", "1"), DELETED("已删除", "2");
	private String name;
	private String value;

	private AccountStatusEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public static String getName(String value) {
        for (AccountStatusEnum c : AccountStatusEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.name;
            }
        }
        return null;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
