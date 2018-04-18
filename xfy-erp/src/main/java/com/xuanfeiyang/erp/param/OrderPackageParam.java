package com.xuanfeiyang.erp.param;

public class OrderPackageParam {
	
	private String orderNo;//订单编号
	private String packageNo;//包裹编号
	private String shippingName;//物流方式
	private String cDateFrom; //时间从
	private String cDateTo; //时间到
	private String cPrintFrom;	//打印时间从
	private String cPrintTo;	//打印时间到
	private String cRejectFrom;	//物流退回时间从
	private String cRejectTo;	//物流退回时间到
	private String goodsSku;//商品SKU
	private String goodsName;//商品名称
	private Integer status;//状态
	private Integer printedFlag;//是否打印
	private Integer isMix;//是否混合包裹
	private Integer scanFlag;//是否扫描
	private Integer traceNumberFlag;	//是否已申请跟踪号
	private Integer orderId;//订单Id
	
	private Integer isTrackNumber;//是否有跟踪号
	private Integer isShip;//是否有运输方式
	private Integer isNote;//是都有说明
	private Integer isMarkShip;//是否标
	private Integer suspendFlag;//是否暂停
	private String trackNumber;//追踪号
	private String scannedFrom;//扫描从
	private String scannedTo;//扫描到
	private String printedTime;//打印时间
	private Integer selfSeller;	//是否自营卖家 
	private String  orderNumber;//订单编号id
	private String scannedUser;//扫描人
	
	

	public String getPrintedTime() {
		return printedTime;
	}
	public void setPrintedTime(String printedTime) {
		this.printedTime = printedTime;
	}
	public String getScannedFrom() {
		return scannedFrom;
	}
	public void setScannedFrom(String scannedFrom) {
		this.scannedFrom = scannedFrom;
	}
	public String getScannedTo() {
		return scannedTo;
	}
	public void setScannedTo(String scannedTo) {
		this.scannedTo = scannedTo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	
	public String getcDateFrom() {
		return cDateFrom;
	}
	public void setcDateFrom(String cDateFrom) {
		this.cDateFrom = cDateFrom;
	}
	public String getcDateTo() {
		return cDateTo;
	}
	public void setcDateTo(String cDateTo) {
		this.cDateTo = cDateTo;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPrintedFlag() {
		return printedFlag;
	}
	public void setPrintedFlag(Integer printedFlag) {
		this.printedFlag = printedFlag;
	}
	public Integer getScanFlag() {
		return scanFlag;
	}
	public void setScanFlag(Integer scanFlag) {
		this.scanFlag = scanFlag;
	}
	public Integer getIsMix() {
		return isMix;
	}
	public void setIsMix(Integer isMix) {
		this.isMix = isMix;
	}
	public Integer getTraceNumberFlag() {
		return traceNumberFlag;
	}
	public void setTraceNumberFlag(Integer traceNumberFlag) {
		this.traceNumberFlag = traceNumberFlag;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getcPrintFrom() {
		return cPrintFrom;
	}
	public void setcPrintFrom(String cPrintFrom) {
		this.cPrintFrom = cPrintFrom;
	}
	public String getcPrintTo() {
		return cPrintTo;
	}
	public void setcPrintTo(String cPrintTo) {
		this.cPrintTo = cPrintTo;
	}
	public String getcRejectFrom() {
		return cRejectFrom;
	}
	public void setcRejectFrom(String cRejectFrom) {
		this.cRejectFrom = cRejectFrom;
	}
	public String getcRejectTo() {
		return cRejectTo;
	}
	public void setcRejectTo(String cRejectTo) {
		this.cRejectTo = cRejectTo;
	}
	public Integer getIsShip() {
		return isShip;
	}
	public void setIsShip(Integer isShip) {
		this.isShip = isShip;
	}
	public Integer getIsNote() {
		return isNote;
	}
	public void setIsNote(Integer isNote) {
		this.isNote = isNote;
	}
	public Integer getIsMarkShip() {
		return isMarkShip;
	}
	public void setIsMarkShip(Integer isMarkShip) {
		this.isMarkShip = isMarkShip;
	}
	public Integer getSuspendFlag() {
		return suspendFlag;
	}
	public void setSuspendFlag(Integer suspendFlag) {
		this.suspendFlag = suspendFlag;
	}
	public Integer getIsTrackNumber() {
		return isTrackNumber;
	}
	public void setIsTrackNumber(Integer isTrackNumber) {
		this.isTrackNumber = isTrackNumber;
	}
	public String getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}
	public Integer getSelfSeller() {
		return selfSeller;
	}
	public void setSelfSeller(Integer selfSeller) {
		this.selfSeller = selfSeller;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getScannedUser() {
		return scannedUser;
	}
	public void setScannedUser(String scannedUser) {
		this.scannedUser = scannedUser;
	}
	
	
}
