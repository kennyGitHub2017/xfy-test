package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class PurchaseOrder {
    private Integer id;

    private String orderNo;			//采购单号

    private Integer createdUserId;	

    private Date createdTime;

    private Date lastUpdatedTime;

    private Integer auditUserId;		//审核人id

    private Date auditTime;				//审核时间

    private String note;

    private String payMethod;			//支付方式

    private Integer supplierId;			//供应商Id

    private Integer buyUserId;			//采购员id
    
    private Integer purchaseRequestId;		//请购单ID
    
    private Float freight;				//运费
    
    private Integer sellOrderId;		//销售订单编号（如果有）

    private String ioOrderNo;			//入库单号
    @Deprecated
    private String waybillNo;			//运单编号
    @Deprecated
    private String logisticsCompany;		//物流公司

    private Short status;				//状态(1-待审核、2-已审核、3-正常关闭、4-手工结案 -1-异常入库)
    
    private Short type;				//源单类型  1:请购转入 2：手工新增
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date purchaseDate;		//采购日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliveryDate;		//交货日期
    
    private Short isSample;			//是否样本采购单
    
    private String serialNumber;	//流水号
    
    private Date paidTime;				//记录付款时间
    
    private Integer payStatus;			//采购单付款状态   0或null:未付款  1:部分付款   2：全部付款
    
    //////////////////////////////////////////////////////////
    
    private String waybillNos;				//采购单对应的多个运单号
    
    private String logisticsCompanys;		//采购单对应的多个物流公司
    
    private String buyUserName;			//采购员姓名
    
    private String purchaseRequestCreater;	//请购人
    
    private String createdUserName;	//制单人
    
    private String auditUserName;			//审核人姓名
    
    private String supplierName;		//供应商名称
    
    private String goodsSku;
    
    private String oldSku;
    
    private String goodsCategory;
    
    private String goodsName;
    
    private String goodsUnit;
    
    private Integer goodsCount;
    
    private Float goodsCost;
    
    private Integer receivedCount;  //已交数量
    
    private Integer qualifiedCount;	//合格数量
    
    private Integer unQualifiedCount;	//不合格数量
    
    private String storeName; //仓库
	private String storeShelf;//仓位
	
	private Date ioDate;		//入库日期
	
	private String goodsSize;
	private String model;
	private String rules;
	private String color;
	private Integer imgCount;
	private String imgUrl;
	
	private List<PurchaseOrderItem> items;
	
	private GoodsSupplier supplier;

	
	//未交数量:unReceivedCount = 采购数量-已交数量
	public Integer getUnReceivedCount(){
		if (null!=goodsCount && null!=receivedCount){
			return goodsCount- receivedCount;
		}
		return goodsCount;
	}

	//行记录交货完成后（采购数量＝已交数量），行关闭显示‘是’，否则显示‘否’
  	public Boolean getOrderItemClose() {
  		if (null!=receivedCount && goodsCount!=null && goodsCount.equals(receivedCount)){
  			return true;
  		}
  		return false;
  	}
  	
  	//一个采购订单所有行都交货完成后或手动结案，显示‘是’，否则显示‘否’
  	public Boolean getOrderClose() {
  		if (null!=status && (status==3 || status==4)){
  			return true;
  		}
  		return false;
  	}
    
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

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
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

    public Integer getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(Integer sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public String getIoOrderNo() {
        return ioOrderNo;
    }

    public void setIoOrderNo(String ioOrderNo) {
        this.ioOrderNo = ioOrderNo == null ? null : ioOrderNo.trim();
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo == null ? null : waybillNo.trim();
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany == null ? null : logisticsCompany.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

	public Integer getReceivedCount() {
		return receivedCount;
	}

	public void setReceivedCount(Integer receivedCount) {
		this.receivedCount = receivedCount;
	}

	public Integer getQualifiedCount() {
		return qualifiedCount;
	}

	public void setQualifiedCount(Integer qualifiedCount) {
		this.qualifiedCount = qualifiedCount;
	}

	public Integer getUnQualifiedCount() {
		return unQualifiedCount;
	}

	public void setUnQualifiedCount(Integer unQualifiedCount) {
		this.unQualifiedCount = unQualifiedCount;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getPurchaseRequestId() {
		return purchaseRequestId;
	}

	public void setPurchaseRequestId(Integer purchaseRequestId) {
		this.purchaseRequestId = purchaseRequestId;
	}

	public String getBuyUserName() {
		return buyUserName;
	}

	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}

	public Float getFreight() {
		return freight;
	}

	public void setFreight(Float freight) {
		this.freight = freight;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	
	public String getPurchaseRequestCreater() {
		return purchaseRequestCreater;
	}

	public void setPurchaseRequestCreater(String purchaseRequestCreater) {
		this.purchaseRequestCreater = purchaseRequestCreater;
	}
	
	public Float getGoodsTotal(){
		if (this.goodsCost==null ||this.goodsCount==null){
			return null;
		}
		return BigDecimal.valueOf(this.goodsCost).multiply(BigDecimal.valueOf(this.goodsCount)).floatValue();
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreShelf() {
		return storeShelf;
	}

	public void setStoreShelf(String storeShelf) {
		this.storeShelf = storeShelf;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public Date getIoDate() {
		return ioDate;
	}

	public void setIoDate(Date ioDate) {
		this.ioDate = ioDate;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getOldSku() {
		return oldSku;
	}

	public void setOldSku(String oldSku) {
		this.oldSku = oldSku;
	}

	public Short getIsSample() {
		return isSample;
	}

	public void setIsSample(Short isSample) {
		this.isSample = isSample;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	public String getWaybillNos() {
		return waybillNos;
	}

	public void setWaybillNos(String waybillNos) {
		this.waybillNos = waybillNos;
	}

	public String getLogisticsCompanys() {
		return logisticsCompanys;
	}

	public void setLogisticsCompanys(String logisticsCompanys) {
		this.logisticsCompanys = logisticsCompanys;
	}

	public List<PurchaseOrderItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseOrderItem> items) {
		this.items = items;
	}

	public GoodsSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(GoodsSupplier supplier) {
		this.supplier = supplier;
	}
}