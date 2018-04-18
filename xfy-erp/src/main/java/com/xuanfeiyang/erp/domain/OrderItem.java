package com.xuanfeiyang.erp.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderItem  implements Cloneable,Serializable {
	
	private static final Logger logger = LoggerFactory
			.getLogger(OrderItem.class);	
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer orderId;

	private String orderSn;

	private String itemId;

	private String itemTitle;

	private String itemPic;

	private String itemUrl;

	private String sku;

	private String itemSku;

	private BigDecimal itemPrice;

	private Integer itemQuantity;

	private Integer packageAmount;

	private Integer lockAmount;

	private Integer cancelAmount;

	private Integer shipmentAmount;
	
	private Integer amountBeforelock;

	private Date createdTime;
	
	private BigDecimal  packingMaterialFee;			//单个sku包装费
	
	private String message;						   //smt买家留言
	
	private String orderSrn;					//子订单编号

	// /////////////////////////////////
	private Integer countInstore; // 仓库数量
	private String skuName;
	private BigDecimal itemWeight; // sku重量
	private Integer itemPurchasedCount; // sku在途数量
	private BigDecimal purchasePrice;	//采购价
	private String skuStatus;			//sku状态
	private BigDecimal platShipfee;		//平台运费

	private String oldSku;
	
	private BigDecimal ebayFee;		//平台费用
	
	private String orderLimitId;
	
	private BigDecimal itemCost;			//单个sku成本

	public OrderItem() {
	}

	public OrderItem(String itemId, String itemTitle, String itemPic,
			String itemUrl, String sku, String itemSku, BigDecimal itemPrice,BigDecimal platFee,
			Integer itemQuantity,String ordersn,String orderLimitId) {
		super();
		this.itemId = itemId;
		this.itemTitle = itemTitle;
		this.itemPic = itemPic;
		this.itemUrl = itemUrl;
		this.sku = sku;
		this.itemSku = itemSku;
		this.itemPrice = itemPrice;
		this.ebayFee = platFee;
		this.itemQuantity = itemQuantity;
		this.orderSn = ordersn;
		this.orderLimitId = orderLimitId;
	}



	public BigDecimal getEbayFee() {
		return ebayFee;
	}

	public void setEbayFee(BigDecimal ebayFee) {
		this.ebayFee = ebayFee;
	}

	public String getOrderLimitId() {
		return orderLimitId;
	}

	public void setOrderLimitId(String orderLimitId) {
		this.orderLimitId = orderLimitId;
	}

	public String getOldSku() {
		return oldSku;
	}

	public void setOldSku(String oldSku) {
		this.oldSku = oldSku;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle == null ? null : itemTitle.trim();
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic == null ? null : itemPic.trim();
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku == null ? null : itemSku.trim();
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Integer getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(Integer packageAmount) {
        this.packageAmount = packageAmount;
    }

    public Integer getLockAmount() {
        return lockAmount;
    }

    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }

    public Integer getCancelAmount() {
        return cancelAmount;
    }

    public void setCancelAmount(Integer cancelAmount) {
        this.cancelAmount = cancelAmount;
    }

    public Integer getShipmentAmount() {
        return shipmentAmount;
    }

    public void setShipmentAmount(Integer shipmentAmount) {
        this.shipmentAmount = shipmentAmount;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getCountInstore() {
		return countInstore;
	}

	public void setCountInstore(Integer countInstore) {
		this.countInstore = countInstore;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public BigDecimal getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(BigDecimal itemWeight) {
		this.itemWeight = itemWeight;
	}

	public Integer getItemPurchasedCount() {
		return itemPurchasedCount;
	}

	public void setItemPurchasedCount(Integer itemPurchasedCount) {
		this.itemPurchasedCount = itemPurchasedCount;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getSkuStatus() {
		return skuStatus;
	}

	public void setSkuStatus(String skuStatus) {
		this.skuStatus = skuStatus;
	}

	public BigDecimal getPlatShipfee() {
		return platShipfee;
	}

	public void setPlatShipfee(BigDecimal platShipfee) {
		this.platShipfee = platShipfee;
	}

	public BigDecimal getPackingMaterialFee() {
		return packingMaterialFee;
	}

	public void setPackingMaterialFee(BigDecimal packingMaterialFee) {
		this.packingMaterialFee = packingMaterialFee;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrderSrn() {
		return orderSrn;
	}

	public void setOrderSrn(String orderSrn) {
		this.orderSrn = orderSrn;
	}
	
	public BigDecimal getItemCost() {
		return itemCost;
	}

	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}
	

	public Integer getAmountBeforelock() {
		return amountBeforelock;
	}

	public void setAmountBeforelock(Integer amountBeforelock) {
		this.amountBeforelock = amountBeforelock;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
	        Object o = null;
	        try
	        {
	            if (this != null)
	            {
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                ObjectOutputStream oos = new ObjectOutputStream(baos);
	                oos.writeObject(this);
	                oos.close();
	                ByteArrayInputStream bais = new ByteArrayInputStream(baos
	                        .toByteArray());
	                ObjectInputStream ois = new ObjectInputStream(bais);
	                o = ois.readObject();
	                ois.close();
	            }
	        } catch (IOException e)
	        {
	            logger.error(e.getMessage());
	        } catch (ClassNotFoundException e)
	        {
	            logger.error(e.getMessage());
	        }
	        return o;
	}
}