package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 包裹
 * 
 * @author kenny
 *
 */
public class OrderPackage {
	private String id;

	private Integer orderId;

	private Short isSend;

	private Short printedFlag;

	private BigDecimal packageWeight;

	private BigDecimal electronWeight;

	private Integer status;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createdTime;

	private String trackNumber;

	private String specifications;

	private String shippingName;

	private Date scannedTime;

	private Date handoverTime;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date printedTime;

	private Date rejectedTime;

	private BigDecimal shippingFee;

	private List<OrderPackageItem> orderPackageItem;

	private Integer scanFlag;// 是否扫描
	private Integer isMix;// 是否混合包裹

	private Order order; // 包裹关联的订单对象

	private String buyerUserId;
	private String buyerEmail;//
	private String note;//备注
	private Date orderPaidTime;//付款时间
	private String orderPlatform;//订单平台
	
	private String scannedUser;
	private String printedUser;
	
	private Integer selfSeller;	//是否自营卖家 
	
	private String statusName;
	
	// 跟踪号参考字段
	private String trackNumberRef;

	public String getTrackNumberRef() {
		return trackNumberRef;
	}

	public void setTrackNumberRef(String trackNumberRef) {
		this.trackNumberRef = trackNumberRef;
	}
	
	public String getPrintedUser() {
		return printedUser;
	}

	public void setPrintedUser(String printedUser) {
		this.printedUser = printedUser;
	}

	public String getScannedUser() {
		return scannedUser;
	}

	public void setScannedUser(String scannedUser) {
		this.scannedUser = scannedUser;
	}

	public String getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Short getIsSend() {
		return isSend;
	}

	public void setIsSend(Short isSend) {
		this.isSend = isSend;
	}

	public Short getPrintedFlag() {
		return printedFlag;
	}

	public void setPrintedFlag(Short printedFlag) {
		this.printedFlag = printedFlag;
	}

	public BigDecimal getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(BigDecimal packageWeight) {
		this.packageWeight = packageWeight;
	}

	public BigDecimal getElectronWeight() {
		return electronWeight;
	}

	public void setElectronWeight(BigDecimal electronWeight) {
		this.electronWeight = electronWeight;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
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

	public Date getHandoverTime() {
		return handoverTime;
	}

	public void setHandoverTime(Date handoverTime) {
		this.handoverTime = handoverTime;
	}

	public BigDecimal getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}

	public List<OrderPackageItem> getOrderPackageItem() {
		return orderPackageItem;
	}

	public void setOrderPackageItem(List<OrderPackageItem> orderPackageItem) {
		this.orderPackageItem = orderPackageItem;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getPrintedTime() {
		return printedTime;
	}

	public void setPrintedTime(Date printedTime) {
		this.printedTime = printedTime;
	}

	public Date getRejectedTime() {
		return rejectedTime;
	}

	public void setRejectedTime(Date rejectedTime) {
		this.rejectedTime = rejectedTime;
	}
	
	

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getOrderPaidTime() {
		return orderPaidTime;
	}

	public void setOrderPaidTime(Date orderPaidTime) {
		this.orderPaidTime = orderPaidTime;
	}

	public String getOrderPlatform() {
		return orderPlatform;
	}

	public void setOrderPlatform(String orderPlatform) {
		this.orderPlatform = orderPlatform;
	}

	/**
	 * 供107*54打印格式,收货人信息显示
	 * 
	 * @return
	 */
	public String getPrintReceiverInfo() {
		if (order == null) {
			return null;
		}
		StringBuffer sbf = new StringBuffer();
		String shippName = order.getBuyinfo().getShippingName(); // 买家名称
		String city = order.getBuyinfo().getShippingCity();
		String state = order.getBuyinfo().getShippingState();
		String zip = order.getBuyinfo().getShippingPostcode();
		String country = order.getBuyinfo().getShippingCountry(); // 国家缩写
		String countryName = order.getBuyinfo().getShippingCountryName();
		countryName = StringUtils.isNotBlank(countryName) ? countryName : ""; // 国家
		String address1 = order.getBuyinfo().getShippingStreet1(); // 买家地址1
		String address2 = order.getBuyinfo().getShippingStreet2(); // 买家地址2
		String tel = order.getBuyinfo().getShippingPhone();
		tel = StringUtils.isNotBlank(tel) ? tel : "";
		String mobile = order.getBuyinfo().getShippingMobile();
		mobile = StringUtils.isNotBlank(mobile) ? mobile : "";
		if (StringUtils.isNotBlank(shippName)) {
			sbf.append("To:<strong>").append(shippName).append("</strong><br>");
		}
		if (StringUtils.isNotBlank(address1)) {
			sbf.append(address1 + "<br>");
		}
		if (StringUtils.isNotBlank(address2)) {
			sbf.append(address2 + "<br>");
		}
		if (StringUtils.isNotBlank(city)) {
			sbf.append(city + "<br>");
		}
		if (StringUtils.isNotBlank(state)) {
			sbf.append(state + "<br>");
		}
		if (StringUtils.isNotBlank(zip)) {
			sbf.append(zip + "<br>");
		}
		if (StringUtils.isNotBlank(country)) {
			sbf.append(country + "(" + countryName + ")" + "<br>");
		}
		if (StringUtils.isNotBlank(country)) {
			sbf.append("Tel/Mobile:" + tel + "/" + mobile + "<br>");
		}
		return sbf.toString();
	}

	/**
	 * 供比利时打印格式显示
	 * 
	 * @return
	 */
	public String getBlsContent() {
		if (orderPackageItem == null || orderPackageItem.size() == 0) {
			return "";
		}
		StringBuffer temp = new StringBuffer();
		for (OrderPackageItem opim : orderPackageItem) {
			if (opim.getGoods() != null
					&& StringUtils.isNotBlank(opim.getGoods()
							.getDeclarationNameEn())) {
				temp.append(opim.getGoods().getDeclarationNameEn() + "*"
						+ opim.getOrderAmount() + ",");
			}
		}
		return temp.length() > 0 ? temp.deleteCharAt(temp.length() - 1)
				.toString() : temp.toString();
	}

	/**
	 * 国际eub A4打印,配货信息显示
	 * 
	 * @return
	 */
	public String getPhxx() {
		if (orderPackageItem == null || orderPackageItem.size() == 0) {
			return "";
		}
		StringBuffer temp = new StringBuffer();
		for (OrderPackageItem opim : orderPackageItem) {
			if (opim.getGoods() != null
					&& StringUtils.isNotBlank(opim.getGoods()
							.getDeclarationNameEn())) {
				temp.append(opim.getGoods().getGoodsSku() + "&nbsp;"
						+ opim.getGoods().getName() + "*"
						+ opim.getOrderAmount() + "<br>");
			}
		}
		return temp.toString();
	}

	/**
	 * 供平邮格式打印,描述显示
	 * 
	 * @return
	 */
	public String getDescriptionContent() {
		if (orderPackageItem == null || orderPackageItem.size() == 0) {
			return "";
		}
		StringBuffer temp = new StringBuffer();
		for (OrderPackageItem opim : orderPackageItem) {
			if (opim.getGoods() != null
					&& StringUtils.isNotBlank(opim.getGoods()
							.getDeclarationNameEn())) {
				temp.append(opim.getGoods().getDeclarationNameEn() + "&nbsp;"
						+ opim.getSku() + "*" + opim.getOrderAmount()
						+ "&nbsp;" + opim.getGoods().getDeclarationNameCn());
			}
		}
		return temp.length() > 150 ? temp.substring(0, 150) : temp.toString();
	}

	public Integer getSelfSeller() {
		return selfSeller;
	}

	public void setSelfSeller(Integer selfSeller) {
		this.selfSeller = selfSeller;
	}
	
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusName(){
		if (status==1){
			statusName="待处理";
		}
		else if (status==2){
			statusName= "已匹配运输方式";
		}
		else if (status==3){
			statusName= "已申请跟踪号";
		}
		else if (status==4){
			statusName= "已打印";
		}
		else if (status==5){
			statusName= "已扫描";
		}
		else if (status==6){
			statusName= "已交接";
		}
		else if (status==7){
			statusName= "已删除";
		}
		else if (status==8){
			statusName= "物流退回";
		}else{
			statusName= "";
		}
		return statusName;
	}
}