package com.xuanfeiyang.erp.enums;

public enum GoodsSupplierTypeEnum {
	CHANGSHANG("厂商", 1), MAOYISHANG("贸易商", 2), DIANSHANG("电商", 3),TAOBAOSHANG("淘宝商", 4);
	private String name;
	private Integer value;

	private GoodsSupplierTypeEnum(String name, Integer value) {
		this.name = name;
		this.value = value;
	}
	
	public static String getName(Integer value) {
        for (GoodsSupplierTypeEnum c : GoodsSupplierTypeEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.name;
            }
        }
        return null;
    }
	
	public static Integer getValue(String name) {
        for (GoodsSupplierTypeEnum c : GoodsSupplierTypeEnum.values()) {
            if (c.getName().equals(name)) {
                return c.getValue();
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
