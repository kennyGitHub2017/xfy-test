package com.xuanfeiyang.erp.service.express.sf;


public class OrderDTO {

	@Column(Mapper = "orderid")
	private String orderId;

	@Column(Mapper = "express_type")
	private String expressType;

	@Column(Mapper = "j_company")
	private String j_company;

	@Column(Mapper = "j_contact")
	private String j_contact;

	@Column(Mapper = "j_tel")
	private String j_tel;

	@Column(Mapper = "j_mobile")
	private String j_mobile;

	@Column(Mapper = "j_address")
	private String j_address;

	@Column(Mapper = "d_company")
	private String d_company;

	@Column(Mapper = "d_contact")
	private String d_contact;

	@Column(Mapper = "d_tel")
	private String d_tel;

	@Column(Mapper = "d_mobile")
	private String d_mobile;

	@Column(Mapper = "d_address")
	private String d_address;

	@Column(Mapper = "parcel_quantity")
	private String parcelQuantity;

	@Column(Mapper = "j_province")
	private String j_province;

	@Column(Mapper = "j_city")
	private String j_city;

	@Column(Mapper = "d_province")
	private String d_province;

	@Column(Mapper = "d_city")
	private String d_city;

	@Column(Mapper = "j_country")
	private String j_country;

	@Column(Mapper = "j_post_code")
	private String j_post_code;

	@Column(Mapper = "d_country")
	private String d_country;

	@Column(Mapper = "d_post_code")
	private String d_post_code;

	@Column(Mapper = "cargo_total_weight")
	private String cargoTotalWeight;

	@Column(Mapper = "returnsign")
	private String returnsign;

	@Column(Mapper = "d_email")
	private String d_email;

	@Column(Mapper = "operate_flag")
	private String operateFlag;
	
	@Column(Mapper = "j_county")
	private String j_county;

	@Column(Mapper = "d_county")
	private String d_county;

	@Column(Mapper = "declared_value")
	private String declared_value;
	
	@Column(Mapper = "declared_value_currency")
	private String declared_value_currency;

	@Column(Mapper = "d_deliverycode")
	private String d_deliverycode;
	
	@Column(Mapper = "custid")
	private String custid;
	
	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getD_deliverycode() {
		return d_deliverycode;
	}

	public String getDeclared_value_currency() {
		return declared_value_currency;
	}

	public void setDeclared_value_currency(String declared_value_currency) {
		this.declared_value_currency = declared_value_currency;
	}

	public void setD_deliverycode(String d_deliverycode) {
		this.d_deliverycode = d_deliverycode;
	}

	public String getDeclared_value() {
		return declared_value;
	}

	public void setDeclared_value(String declared_value) {
		this.declared_value = declared_value;
	}

	public String getD_county() {
		return d_county;
	}

	public void setD_county(String d_county) {
		this.d_county = d_county;
	}

	public String getJ_county() {
		return j_county;
	}

	public void setJ_county(String j_county) {
		this.j_county = j_county;
	}

	public String getOperateFlag() {
		return operateFlag;
	}

	public void setOperateFlag(String operateFlag) {
		this.operateFlag = operateFlag;
	}

	public String getD_email() {
		return d_email;
	}

	public void setD_email(String dEmail) {
		this.d_email = dEmail;
	}

	public String getReturnsign() {
		return returnsign;
	}

	public void setReturnsign(String returnsign) {
		this.returnsign = returnsign;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	public String getJ_company() {
		return j_company;
	}

	public void setJ_company(String j_company) {
		this.j_company = j_company;
	}

	public String getJ_contact() {
		return j_contact;
	}

	public void setJ_contact(String j_contact) {
		this.j_contact = j_contact;
	}

	public String getJ_tel() {
		return j_tel;
	}

	public void setJ_tel(String j_tel) {
		this.j_tel = j_tel;
	}

	public String getJ_mobile() {
		return j_mobile;
	}

	public void setJ_mobile(String j_mobile) {
		this.j_mobile = j_mobile;
	}

	public String getJ_address() {
		return j_address;
	}

	public void setJ_address(String j_address) {
		this.j_address = j_address;
	}

	public String getD_company() {
		return d_company;
	}

	public void setD_company(String d_company) {
		this.d_company = d_company;
	}

	public String getD_contact() {
		return d_contact;
	}

	public void setD_contact(String d_contact) {
		this.d_contact = d_contact;
	}

	public String getD_tel() {
		return d_tel;
	}

	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}

	public String getD_mobile() {
		return d_mobile;
	}

	public void setD_mobile(String d_mobile) {
		this.d_mobile = d_mobile;
	}

	public String getD_address() {
		return d_address;
	}

	public void setD_address(String d_address) {
		this.d_address = d_address;
	}

	public String getParcelQuantity() {
		return parcelQuantity;
	}

	public void setParcelQuantity(String parcelQuantity) {
		this.parcelQuantity = parcelQuantity;
	}

	public String getJ_province() {
		return j_province;
	}

	public void setJ_province(String j_province) {
		this.j_province = j_province;
	}

	public String getJ_city() {
		return j_city;
	}

	public void setJ_city(String j_city) {
		this.j_city = j_city;
	}

	public String getD_province() {
		return d_province;
	}

	public void setD_province(String d_province) {
		this.d_province = d_province;
	}

	public String getD_city() {
		return d_city;
	}

	public void setD_city(String d_city) {
		this.d_city = d_city;
	}

	public String getJ_country() {
		return j_country;
	}

	public void setJ_country(String j_country) {
		this.j_country = j_country;
	}

	public String getJ_post_code() {
		return j_post_code;
	}

	public void setJ_post_code(String j_post_code) {
		this.j_post_code = j_post_code;
	}

	public String getD_country() {
		return d_country;
	}

	public void setD_country(String d_country) {
		this.d_country = d_country;
	}

	public String getD_post_code() {
		return d_post_code;
	}

	public void setD_post_code(String d_post_code) {
		this.d_post_code = d_post_code;
	}

	public String getCargoTotalWeight() {
		return cargoTotalWeight;
	}

	public void setCargoTotalWeight(String cargoTotalWeight) {
		this.cargoTotalWeight = cargoTotalWeight;
	}

}
