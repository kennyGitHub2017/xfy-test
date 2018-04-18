package com.xuanfeiyang.erp.service.express.yanwen;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 创建的订单对象
 * 
 * @author Administrator
 *
 */
@XmlRootElement(name = "CreatedExpress")
public class CreatedExpress implements Serializable {
	private static final long serialVersionUID = -1725952307642233821L;

	private String epcode;// 运单号，部分发货方式必填
	private String userid;// 客户号
	private ChannelType channelType;
	private String channel;// 发货方式
	private String packagee;

	private String userOrderNumber;// 客户订单号
	private String sendDate;// 发货日期
	private Receiver receiver;// 收件人
	private String quantity;// 货品数量
	private GoodsName goodsName;// 商品名

	private String referenceNo;
	private String packageNo;// 包裹号
	private String insure;// 是否需要保险
	private String memo;// 备注。会出现在拣货单上
	private String trackingStatus;

	private boolean isPrint;
	private String createDate;

	@XmlElement(name = "Epcode")
	public String getEpcode() {
		return epcode;
	}

	public void setEpcode(String epcode) {
		this.epcode = epcode;
	}

	@XmlElement(name = "Userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@XmlElement(name = "ChannelType")
	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	@XmlElement(name = "Channel")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@XmlElement(name = "Package")
	public String getPackagee() {
		return packagee;
	}

	public void setPackagee(String packagee) {
		this.packagee = packagee;
	}

	@XmlElement(name = "UserOrderNumber")
	public String getUserOrderNumber() {
		return userOrderNumber;
	}

	public void setUserOrderNumber(String userOrderNumber) {
		this.userOrderNumber = userOrderNumber;
	}

	@XmlElement(name = "SendDate")
	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	@XmlElement(name = "Receiver")
	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	@XmlElement(name = "Quantity")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@XmlElement(name = "GoodsName")
	public GoodsName getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(GoodsName goodsName) {
		this.goodsName = goodsName;
	}

	@XmlElement(name = "ReferenceNo")
	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	@XmlElement(name = "PackageNo")
	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	@XmlElement(name = "Insure")
	public String getInsure() {
		return insure;
	}

	public void setInsure(String insure) {
		this.insure = insure;
	}

	@XmlElement(name = "Memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@XmlElement(name = "TrackingStatus")
	public String getTrackingStatus() {
		return trackingStatus;
	}

	public void setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
	}

	@XmlElement(name = "IsPrint")
	public boolean isPrint() {
		return isPrint;
	}

	public void setPrint(boolean isPrint) {
		this.isPrint = isPrint;
	}

	@XmlElement(name = "CreateDate")
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "CreatedExpress [epcode=" + epcode + ", userid=" + userid + ", channelType=" + channelType + ", channel=" + channel
				+ ", packagee=" + packagee + ", userOrderNumber=" + userOrderNumber + ", sendDate=" + sendDate + ", receiver=" + receiver
				+ ", quantity=" + quantity + ", goodsName=" + goodsName + ", referenceNo=" + referenceNo + ", packageNo=" + packageNo
				+ ", insure=" + insure + ", memo=" + memo + ", trackingStatus=" + trackingStatus + ", isPrint=" + isPrint + ", createDate="
				+ createDate + "]";
	}

}
