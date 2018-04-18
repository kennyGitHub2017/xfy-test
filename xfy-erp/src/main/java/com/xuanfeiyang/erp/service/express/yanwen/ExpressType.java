package com.xuanfeiyang.erp.service.express.yanwen;

import java.io.Serializable;
import java.util.Random;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 燕文XML映射对象
 * 
 * @author Administrator
 *
 */
@XmlRootElement(name = "ExpressType")
public class ExpressType implements Serializable {
	private static final long serialVersionUID = 8108263195129371375L;

	private String epcode;// 运单号，部分发货方式必填
	private String userid;// 客户号
	private String channel;// 发货方式
	private String userOrderNumber;// 客户订单号
	private String sendDate;// 发货日期

	private String quantity;// 货品数量
	private String packageNo;// 包裹号
	private String insure;// 是否需要保险
	private String memo;// 备注。会出现在拣货单上

	private Receiver receiver;// 收件人
	private GoodsName goodsName;// 商品名

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

	@XmlElement(name = "Channel")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	@XmlElement(name = "Quantity")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

	@XmlElement(name = "Receiver")
	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	@XmlElement(name = "GoodsName")
	public GoodsName getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(GoodsName goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		return "ExpressType [epcode=" + epcode + ", userid=" + userid + ", channel=" + channel + ", userOrderNumber=" + userOrderNumber
				+ ", sendDate=" + sendDate + ", quantity=" + quantity + ", packageNo=" + packageNo + ", insure=" + insure + ", memo="
				+ memo + ", receiver=" + receiver + ", goodsName=" + goodsName + "]";
	}

	public static ExpressType mock() {
		ExpressType expressType = new ExpressType();

		expressType.setEpcode("");
		expressType.setUserid("100000");
		expressType.setChannel("燕邮宝挂号");
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}
		expressType.setUserOrderNumber(sb.toString());
		expressType.setSendDate("2016-02-07T00:00:00");

		Receiver receiver = new Receiver();
		receiver.setUserid("100000");
		receiver.setName("xfy-user");
		receiver.setPhone("1236548");
		receiver.setMobile("18616881688");
		receiver.setEmail("xfy_test@163.com ");
		receiver.setCompany("xfy");
		receiver.setCountry("俄罗斯");
		receiver.setPostcode("253400");
		receiver.setState("FL");
		receiver.setCity("City");
		receiver.setAddress1("address1");
		receiver.setAddress2("address2");
		expressType.setReceiver(receiver);

		expressType.setQuantity("1");
		expressType.setPackageNo("");
		expressType.setInsure("");
		expressType.setMemo("memo");

		GoodsName goodsName = new GoodsName();
		goodsName.setUserid("100000");
		goodsName.setNameCh("中文商品名");
		goodsName.setNameEn("goodsName");
		goodsName.setWeight("123");
		goodsName.setDeclaredValue("125");
		goodsName.setDeclaredCurrency("USD");
		goodsName.setMoreGoodsName("goodsName");
		// goodsName.setHsCode("HsCode123456789");
		expressType.setGoodsName(goodsName);

		return expressType;
	}
}
