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
@XmlRootElement(name = "Receiver")
public class Receiver implements Serializable {
	private static final long serialVersionUID = -1365887121321941529L;

	private String userid;// 客户号
	private String name;// 收货人-姓名
	private String phone;// 收货人-座机，手机。 美国专线至少填一项
	private String mobile;// 收货人-座机，手机。 美国专线至少填一项
	private String email;// 收货人-邮箱
	private String company;// 收货人-公司

	private String country;// 收货人-国家
	private CountryType countryType;
	private String postcode;// 收货人-邮编
	private String state;// 收货人-州
	private String city;// 收货人-城市
	private String address1;// 收货人-地址1
	private String address2;// 收货人-地址2

	@XmlElement(name = "Userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "Phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlElement(name = "Mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@XmlElement(name = "Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement(name = "Company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@XmlElement(name = "Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(name = "CountryType")
	public CountryType getCountryType() {
		return countryType;
	}

	public void setCountryType(CountryType countryType) {
		this.countryType = countryType;
	}

	@XmlElement(name = "Postcode")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@XmlElement(name = "State")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlElement(name = "City")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlElement(name = "Address1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@XmlElement(name = "Address2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Override
	public String toString() {
		return "Receiver [userid=" + userid + ", name=" + name + ", phone=" + phone + ", mobile=" + mobile + ", email=" + email
				+ ", company=" + company + ", country=" + country + ", countryType=" + countryType + ", postcode=" + postcode + ", state="
				+ state + ", city=" + city + ", address1=" + address1 + ", address2=" + address2 + "]";
	}

}
