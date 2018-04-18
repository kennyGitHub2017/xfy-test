package com.xuanfeiyang.erp.service.express.yanwen;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 燕文XML映射对象
 * 
 * @author Administrator
 *
 */
@XmlRootElement(name = "GoodsName")
public class GoodsName implements Serializable {
	private static final long serialVersionUID = 1565746465361959843L;

	/**
	 * 下面的字段都为必填
	 */
	private String userid;// 客户号
	private String nameCh;// 商品中文品名
	private String nameEn;// 商品英文品名

	private String weight;// 包裹重量
	private String declaredValue;// 申报价值
	private String declaredCurrency;// 申报币种,支持的值：USD,EUR,GBP,CNY

	/**
	 * 下面的字段都为选填
	 */
	private String moreGoodsName;// 多品名. 会出现在拣货单上
	private String hsCode;// 商品海关编码,香港FedEx经济必填

	@XmlElement(name = "Userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@XmlElement(name = "NameCh")
	public String getNameCh() {
		return nameCh;
	}

	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}

	@XmlElement(name = "NameEn")
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@XmlElement(name = "Weight")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@XmlElement(name = "DeclaredValue")
	public String getDeclaredValue() {
		return declaredValue;
	}

	public void setDeclaredValue(String declaredValue) {
		this.declaredValue = declaredValue;
	}

	@XmlElement(name = "DeclaredCurrency")
	public String getDeclaredCurrency() {
		return declaredCurrency;
	}

	public void setDeclaredCurrency(String declaredCurrency) {
		this.declaredCurrency = declaredCurrency;
	}

	@XmlElement(name = "MoreGoodsName")
	public String getMoreGoodsName() {
		return moreGoodsName;
	}

	public void setMoreGoodsName(String moreGoodsName) {
		this.moreGoodsName = moreGoodsName;
	}

	@XmlElement(name = "HsCode")
	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	@Override
	public String toString() {
		return "GoodsName [userid=" + userid + ", nameCh=" + nameCh + ", nameEn=" + nameEn + ", weight=" + weight + ", declaredValue="
				+ declaredValue + ", declaredCurrency=" + declaredCurrency + ", moreGoodsName=" + moreGoodsName + ", hsCode=" + hsCode
				+ "]";
	}

}
