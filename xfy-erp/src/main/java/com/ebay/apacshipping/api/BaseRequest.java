
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BaseRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BaseRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="APIDevUserID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="APISellerUserToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AppID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AppCert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="APISellerUserID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MessageID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Carrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseRequest", propOrder = {
    "version",
    "apiDevUserID",
    "apiSellerUserToken",
    "appID",
    "appCert",
    "apiSellerUserID",
    "messageID",
    "carrier"
})
@XmlSeeAlso({
    AddAPACShippingPackageRequest.class,
    CancelAPACShippingPackageRequest.class,
    ConfirmAPACShippingPackageRequest.class,
    GetAPACShippingLabelRequest.class,
    GetAPACShippingLabelsRequest.class,
    GetAPACShippingPackageRequest.class,
    GetAPACShippingPackageStatusRequest.class,
    GetAPACShippingRateRequest.class,
    VerifyAPACShippingUserRequest.class,
    RecreateAPACShippingPackageRequest.class,
    GetAPACShippingTrackCodeRequest.class
})
public class BaseRequest {

    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "APIDevUserID")
    protected String apiDevUserID;
    @XmlElement(name = "APISellerUserToken")
    protected String apiSellerUserToken;
    @XmlElement(name = "AppID")
    protected String appID;
    @XmlElement(name = "AppCert")
    protected String appCert;
    @XmlElement(name = "APISellerUserID")
    protected String apiSellerUserID;
    @XmlElement(name = "MessageID")
    protected String messageID;
    @XmlElement(name = "Carrier")
    protected String carrier;

    /**
     * 获取version属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * 获取apiDevUserID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPIDevUserID() {
        return apiDevUserID;
    }

    /**
     * 设置apiDevUserID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPIDevUserID(String value) {
        this.apiDevUserID = value;
    }

    /**
     * 获取apiSellerUserToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPISellerUserToken() {
        return apiSellerUserToken;
    }

    /**
     * 设置apiSellerUserToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPISellerUserToken(String value) {
        this.apiSellerUserToken = value;
    }

    /**
     * 获取appID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppID() {
        return appID;
    }

    /**
     * 设置appID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppID(String value) {
        this.appID = value;
    }

    /**
     * 获取appCert属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppCert() {
        return appCert;
    }

    /**
     * 设置appCert属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppCert(String value) {
        this.appCert = value;
    }

    /**
     * 获取apiSellerUserID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPISellerUserID() {
        return apiSellerUserID;
    }

    /**
     * 设置apiSellerUserID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPISellerUserID(String value) {
        this.apiSellerUserID = value;
    }

    /**
     * 获取messageID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * 设置messageID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageID(String value) {
        this.messageID = value;
    }

    /**
     * 获取carrier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * 设置carrier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrier(String value) {
        this.carrier = value;
    }

}
