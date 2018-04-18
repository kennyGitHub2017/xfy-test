package com.xuanfeiyang.erp.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import com.xuanfeiyang.annotations.Column;

public class Order  implements Cloneable,Serializable{
	private static final Logger logger = LoggerFactory
			.getLogger(Order.class);	
	private static final long serialVersionUID = 1L;
	
	@Column(Desc="订单编号")
	private Integer id;
	
	private String orderNo;
	
	@Column(Desc="Paypal交易号")
	private String paypaltransid;
	
	@Column(Desc="SELL RECORD NUMBER")
	private String srn;
	
	@Column(Desc="站点")
	private String site;
	
	@Column(Desc="所属平台")
	private String orderPlatform;

	@Column(Desc="平台订单ID")
	private String orderSn;
	
	@Column(Desc="支付状态")
	private String payStatus;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(Desc="下单时间")
	private Date orderSaleTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(Desc="支付时间")
	private Date orderPaidTime;
	
	@Column(Desc="订单类型")
	private Short orderType;

	@Column(Desc="订单状态")
	private Short orderStatus;
	
	@Column(Desc="支付币种")
	private String currency;

	@Column(Desc="订单金额")
	private BigDecimal amount;

	@Column(Desc="是否标发")
	private Short isSend;

	@Column(Desc="标发平台")
	private String sendPlatform;

	@Column(Desc="是否打印")
	private Short printedFlag;

	@Column(Desc="订单卖家")
	private Integer accountId;
	
	@Column(Desc="运输方式")
	private String shippingName;			//订单运输方式
	
	@Column(Desc="扫描时间")
	private Date scannedTime;

	@Column(Desc="跟踪号")
	private String trackNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(Desc="发货时间")
	private Date shippedTime;

	@Column(Desc="包裹计算重量")
	private BigDecimal calcWeight;

	@Column(Desc="包裹实际重量")
	private BigDecimal packageWeight;

	@Column(Desc="包裹运费")
	private BigDecimal shippingFee;

	@Column(Desc="订单成本")
	private BigDecimal cost;

	@Column(Desc="订单利润")
	private BigDecimal profit;

	@Column(Desc="成交费")
	private BigDecimal strikeCost;
	
	@Column(Desc="退款金额")
	private BigDecimal refundFee;

	@Column(Desc="退款原因")
	private String refundReason;

	@Column(Desc="订单备注")
	private String note;

	@Column(Desc="创建时间")
	private Date createdTime;

	@Column(Desc="更新时间")
	private Date lastUpdatedTime;
	
	@Column(Desc="是否混合")
	private Short mixedFlag;
	
	@Column(Desc="是否补发")
	private Short reissuedFlag;
	
	@Column(Desc="是否合并")
	private Short  combine;
	
	@Column(Desc="已合并订单ID")
	private String combineOrders;
	
	@Column(Desc="是否暂停")
	private Integer suspend;
	
	@Column(Desc="取消时间")
	private String cancelDate;
	
	@Column(Desc="平台发货方式")
	private String platShippinMethod;		//平台发货方式
	
	@Column(Desc="平台运费")
	private String platShippingFee;			//平台运费
	
	@Column(Desc="买家留言")
	private String buyerNote;				//买家留言
	
	@Column(Desc="paypal 费用")
	private BigDecimal paypalFee;
	
	private BigDecimal exchangeRate;		//订单币种兑换率(RMB)
	
	private Integer version;			
	
	private Integer stopFlag;				//是否停止交易
	
	//////////////////////////////////////
	private List<OrderItem> items;
	@Column
	private OrderBuyerInfo buyinfo;
	
	private String orderNote;
	
	private Integer copyOrderId;
	
	private boolean merge;		//用于订单合并逻辑判断变量
	
	private String accountName;
	
	private String accountRealName;	//卖家名称
	
	private BigDecimal packageShipfee;//包裹称重运费
	
	private BigDecimal packingMaterialFee;
	
	private Integer returnType;//退货类型
	private String returnNote;//退货备注
	
	// 跟踪号参考字段
	private String trackNumberRef;
	
	//卖家qq
	private String sellerQq;
	
	public String getTrackNumberRef() {
		return trackNumberRef;
	}

	public void setTrackNumberRef(String trackNumberRef) {
		this.trackNumberRef = trackNumberRef;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(Desc="导入退货时间")
	private Date returnTime;//导入退货时间
	
	public Integer getReturnType() {
		return returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	public String getReturnNote() {
		return returnNote;
	}

	public void setReturnNote(String returnNote) {
		this.returnNote = returnNote;
	}

	public Order(){}	
	
	// 账户信息
	private PlatformAccount platformAccount;
	
	// 发货方式地址配置
	private ShippingAddressConfig shippingAddressConfig;
	
	public Order(Integer id,String orderSn,BigDecimal amount,BigDecimal cost){
		this.id = id;
		this.orderSn = orderSn;
		this.amount = amount;
		this.cost = cost;
	}
	
	public BigDecimal getPaypalFee() {
		return paypalFee;
	}

	public void setPaypalFee(BigDecimal paypalFee) {
		this.paypalFee = paypalFee;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
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

	public String getOrderPlatform() {
		return orderPlatform;
	}

	public void setOrderPlatform(String orderPlatform) {
		this.orderPlatform = orderPlatform == null ? null : orderPlatform
				.trim();
	}

	public String getOrderSn() {
		//如果是复制的订单,格式为copy_原有订单ordersn,则进行还原处理
		/*
		if(null!=this.reissuedFlag && this.reissuedFlag==1){
			return orderSn.substring(5);
		}else{
			return orderSn;
		}
		*/
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn == null ? null : orderSn.trim();
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Date getOrderSaleTime() {
		return orderSaleTime;
	}

	public void setOrderSaleTime(Date orderSaleTime) {
		this.orderSaleTime = orderSaleTime;
	}

	public Date getOrderPaidTime() {
		return orderPaidTime;
	}

	public void setOrderPaidTime(Date orderPaidTime) {
		this.orderPaidTime = orderPaidTime;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public Short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency == null ? null : currency.trim();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Short getIsSend() {
		return isSend;
	}

	public void setIsSend(Short isSend) {
		this.isSend = isSend;
	}

	public String getSendPlatform() {
		return sendPlatform;
	}

	public void setSendPlatform(String sendPlatform) {
		this.sendPlatform = sendPlatform == null ? null : sendPlatform.trim();
	}

	public Short getPrintedFlag() {
		return printedFlag;
	}

	public void setPrintedFlag(Short printedFlag) {
		this.printedFlag = printedFlag;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public Date getScannedTime() {
		return scannedTime;
	}

	public void setScannedTime(Date scannedTime) {
		this.scannedTime = scannedTime;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber == null ? null : trackNumber.trim();
	}

	public Date getShippedTime() {
		return shippedTime;
	}

	public void setShippedTime(Date shippedTime) {
		this.shippedTime = shippedTime;
	}

	public BigDecimal getCalcWeight() {
		return calcWeight;
	}

	public void setCalcWeight(BigDecimal calcWeight) {
		this.calcWeight = calcWeight;
	}

	public BigDecimal getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(BigDecimal packageWeight) {
		this.packageWeight = packageWeight;
	}

	public BigDecimal getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getStrikeCost() {
		return strikeCost;
	}

	public void setStrikeCost(BigDecimal strikeCost) {
		this.strikeCost = strikeCost;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
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

	public Short getMixedFlag() {
		return mixedFlag;
	}

	public void setMixedFlag(Short mixedFlag) {
		this.mixedFlag = mixedFlag;
	}

	public Short getReissuedFlag() {
		return reissuedFlag;
	}

	public void setReissuedFlag(Short reissuedFlag) {
		this.reissuedFlag = reissuedFlag;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getPaypaltransid() {
		return paypaltransid;
	}

	public void setPaypaltransid(String paypaltransid) {
		this.paypaltransid = paypaltransid;
	}

	public String getSrn() {
		return srn;
	}

	public void setSrn(String srn) {
		this.srn = srn;
	}

	public OrderBuyerInfo getBuyinfo() {
		return buyinfo;
	}

	public void setBuyinfo(OrderBuyerInfo buyinfo) {
		this.buyinfo = buyinfo;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public Short getCombine() {
		return combine;
	}

	public void setCombine(Short combine) {
		this.combine = combine;
	}

	public String getCombineOrders() {
		return combineOrders;
	}

	public void setCombineOrders(String combineOrders) {
		this.combineOrders = combineOrders;
	}

	public Integer getCopyOrderId() {
		return copyOrderId;
	}

	public void setCopyOrderId(Integer copyOrderId) {
		this.copyOrderId = copyOrderId;
	}

	public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}

	public ShippingAddressConfig getShippingAddressConfig() {
		return shippingAddressConfig;
	}

	public void setShippingAddressConfig(ShippingAddressConfig shippingAddressConfig) {
		this.shippingAddressConfig = shippingAddressConfig;
	}

	public PlatformAccount getPlatformAccount() {
		return platformAccount;
	}

	public void setPlatformAccount(PlatformAccount platformAccount) {
		this.platformAccount = platformAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getSuspend() {
		return suspend;
	}

	public void setSuspend(Integer suspend) {
		this.suspend = suspend;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getPlatShippinMethod() {
		return platShippinMethod;
	}

	public void setPlatShippinMethod(String platShippinMethod) {
		this.platShippinMethod = platShippinMethod;
	}

	public String getPlatShippingFee() {
		return platShippingFee;
	}

	public void setPlatShippingFee(String platShippingFee) {
		this.platShippingFee = platShippingFee;
	}

	public String getBuyerNote() {
		return buyerNote;
	}

	public void setBuyerNote(String buyerNote) {
		this.buyerNote = buyerNote;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	

	public BigDecimal getPackageShipfee() {
		return packageShipfee;
	}

	public void setPackageShipfee(BigDecimal packageShipfee) {
		this.packageShipfee = packageShipfee;
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

	public BigDecimal getPackingMaterialFee() {
		return packingMaterialFee;
	}

	public void setPackingMaterialFee(BigDecimal packingMaterialFee) {
		this.packingMaterialFee = packingMaterialFee;
	}
	
	private List<Map<String, String>> sellerAdOrder;

	public List<Map<String, String>> getSellerAdOrder() {
		return sellerAdOrder;
	}

	public void setSellerAdOrder(List<Map<String, String>> sellerAdOrder) {
		this.sellerAdOrder = sellerAdOrder;
	}

	public String getAccountRealName() {
		return accountRealName;
	}

	public void setAccountRealName(String accountRealName) {
		this.accountRealName = accountRealName;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getSellerQq() {
		return sellerQq;
	}

	public void setSellerQq(String sellerQq) {
		this.sellerQq = sellerQq;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(Integer stopFlag) {
		this.stopFlag = stopFlag;
	}
}