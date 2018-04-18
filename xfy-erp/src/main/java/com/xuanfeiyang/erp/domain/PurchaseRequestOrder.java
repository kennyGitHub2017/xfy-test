package com.xuanfeiyang.erp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PurchaseRequestOrder {
    private Integer id;

    private String orderNo;					//请购单号

    private Integer createdUserId;			//请购单创建人

    private Date createdTime;				

    private Date lastUpdatedTime;

    private Short type;				//类型(1-手工增加、2-库存预警、3-订单生成)

    private String note;			//备注

    private Integer supplierId;			//供应商id

    private Integer buyUserId;			//采购人id
    
    private Short status;				//状态(1-待转采购单、2-已转采购单)

    private Integer sellOrderId;		//销售订单编号（如果有）
    
    private String purchaseOrderId;			//采购单Id
    
    private String purchaseOrderNo;			//采购单号
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliveryDate;					//交货日期
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date purchaseDate;					//采购日期
    
    /////////////////////以下非库表字段,供查询页面使用////////////////////////////////////////////
    
    private String goodsSku;
    
    private String goodsName;
    
    private String goodsUnit;
    
    private Integer goodsCount;
    
    private Float goodsCost;
    
    private String createdUserName;			//申请人
    
    private String buyuserName;			//采购人
    
    private String supplierName;		//供应商名称
    
    private String color;
    private String goodsSize;
    private String model;
    private String rules;
    private Integer imgCount;
    private String imgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(Integer buyUserId) {
        this.buyUserId = buyUserId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(Integer sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getGoodsSku() {
		return goodsSku;
	}

	public void setGoodsSku(String goodsSku) {
		this.goodsSku = goodsSku;
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

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Float getGoodsCost() {
		return goodsCost;
	}

	public void setGoodsCost(Float goodsCost) {
		this.goodsCost = goodsCost;
	}

	public Double getGoodsTotal() {
		if (null==goodsCost || null==goodsCount){
			return null;
		}
		return Double.valueOf(goodsCost)*goodsCount;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public String getBuyuserName() {
		return buyuserName;
	}

	public void setBuyuserName(String buyuserName) {
		this.buyuserName = buyuserName;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public Integer getImgCount() {
		return imgCount;
	}

	public void setImgCount(Integer imgCount) {
		this.imgCount = imgCount;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
	
}