package com.xuanfeiyang.erp.enums;
/**
 * 平台账户类型枚举
 * @author bernard
 *
 */
public enum AccountEnum {
	AMAZON("亚马逊", "amazon"), SMT("速买通", "smt"), EBAY("易贝", "ebay");
	private String name;
	private String value;

	private AccountEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public static String getName(String value) {
        for (AccountEnum c : AccountEnum.values()) {
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
