package com.xuanfeiyang.erp.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 发货方式
 * @author Administrator
 *
 */
public class Shipping {
	
	private Integer id;
	private String shippingName;//发货方式名称
	private String ebayValue;//上传到eBay的名称
	private String smtValue;//上传到速卖通的名称
	private String wishValue;//上传到wish的名称
	private String	country;//联系人国家
	private String	province;//联系人省份
	private String	city;//联系人城市
	private String	name; //联系人姓名
	private String tel; //联系人电话
	private String street;//联系人街道
	private String address;//回邮地址
	private BigDecimal amountMax;//金额区间 max
	private BigDecimal amountMin;//金额区间 min
	private String carrierSn;//物流公司代号
	private String signature;//签名
	private String note; //备注
	private String supportedAccounts;//支持哪些eBay帐号(,分隔、 ',any,'为所有)
	private BigDecimal weightMin; //重量区间 min
	private BigDecimal weightMax;//重量区间 max
	private Integer priority;//优先级
	private String supportedCountries;//包含国家(,分隔、 ',any,'为所有)
	private String supportedSkus;//包含SKU(,分隔、 ',any,'为所有)
	private Integer orderCategoryId;//选择分类
	private Date createdTime;//记录添加时间
	private String ebayShippings;//对应eBay运送方式(,分隔、 ',any,'为所有)
	private Integer  goodsStoreId;//对应仓库
	private Date lastUpdatedTime;//记录最后更新时间
	
	private Integer isBattery;//是否电子
	private Integer isRegulated;//管制
	private Integer isLiquid;//液体
	private Integer isMagnetic;//磁性
	private Integer isCopyright;//侵权
	
	private BigDecimal amountOrder; // 订单的金额
	private BigDecimal weightOrder; // 订单的重量
	private String account;//账号
	private List<String> skus;//订单SKU
	private BigDecimal shipFeeAvg;//运费均价
	private Integer shippFeeType;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getEbayValue() {
		return ebayValue;
	}
	public void setEbayValue(String ebayValue) {
		this.ebayValue = ebayValue;
	}
	public String getSmtValue() {
		return smtValue;
	}
	public void setSmtValue(String smtValue) {
		this.smtValue = smtValue;
	}
	public String getWishValue() {
		return wishValue;
	}
	public void setWishValue(String wishValue) {
		this.wishValue = wishValue;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigDecimal getAmountMax() {
		return amountMax;
	}
	public void setAmountMax(BigDecimal amountMax) {
		this.amountMax = amountMax;
	}
	public BigDecimal getAmountMin() {
		return amountMin;
	}
	public void setAmountMin(BigDecimal amountMin) {
		this.amountMin = amountMin;
	}
	public String getCarrierSn() {
		return carrierSn;
	}
	public void setCarrierSn(String carrierSn) {
		this.carrierSn = carrierSn;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSupportedAccounts() {
		return supportedAccounts;
	}
	public void setSupportedAccounts(String supportedAccounts) {
		this.supportedAccounts = supportedAccounts;
	}
	public BigDecimal getWeightMin() {
		return weightMin;
	}
	public void setWeightMin(BigDecimal weightMin) {
		this.weightMin = weightMin;
	}
	public BigDecimal getWeightMax() {
		return weightMax;
	}
	public void setWeightMax(BigDecimal weightMax) {
		this.weightMax = weightMax;
	}


	public String getSupportedSkus() {
		return supportedSkus;
	}
	public void setSupportedSkus(String supportedSkus) {
		this.supportedSkus = supportedSkus;
	}

	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getEbayShippings() {
		return ebayShippings;
	}
	public void setEbayShippings(String ebayShippings) {
		this.ebayShippings = ebayShippings;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getOrderCategoryId() {
		return orderCategoryId;
	}
	public void setOrderCategoryId(Integer orderCategoryId) {
		this.orderCategoryId = orderCategoryId;
	}
	public Integer getGoodsStoreId() {
		return goodsStoreId;
	}
	public void setGoodsStoreId(Integer goodsStoreId) {
		this.goodsStoreId = goodsStoreId;
	}
	public String getSupportedCountries() {
		return supportedCountries;
	}
	public void setSupportedCountries(String supportedCountries) {
		this.supportedCountries = supportedCountries;
	}
	public Integer getIsBattery() {
		return isBattery;
	}
	public void setIsBattery(Integer isBattery) {
		this.isBattery = isBattery;
	}
	public Integer getIsRegulated() {
		return isRegulated;
	}
	public void setIsRegulated(Integer isRegulated) {
		this.isRegulated = isRegulated;
	}
	public Integer getIsLiquid() {
		return isLiquid;
	}
	public void setIsLiquid(Integer isLiquid) {
		this.isLiquid = isLiquid;
	}
	public Integer getIsMagnetic() {
		return isMagnetic;
	}
	public void setIsMagnetic(Integer isMagnetic) {
		this.isMagnetic = isMagnetic;
	}
	public Integer getIsCopyright() {
		return isCopyright;
	}
	public void setIsCopyright(Integer isCopyright) {
		this.isCopyright = isCopyright;
	}
	public BigDecimal getAmountOrder() {
		return amountOrder;
	}
	public void setAmountOrder(BigDecimal amountOrder) {
		this.amountOrder = amountOrder;
	}
	public BigDecimal getWeightOrder() {
		return weightOrder;
	}
	public void setWeightOrder(BigDecimal weightOrder) {
		this.weightOrder = weightOrder;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public List<String> getSkus() {
		return skus;
	}
	public void setSkus(List<String> skus) {
		this.skus = skus;
	}
	public BigDecimal getShipFeeAvg() {
		return shipFeeAvg;
	}
	public void setShipFeeAvg(BigDecimal shipFeeAvg) {
		this.shipFeeAvg = shipFeeAvg;
	}
	public Integer getShippFeeType() {
		return shippFeeType;
	}
	public void setShippFeeType(Integer shippFeeType) {
		this.shippFeeType = shippFeeType;
	}
	
	
}
