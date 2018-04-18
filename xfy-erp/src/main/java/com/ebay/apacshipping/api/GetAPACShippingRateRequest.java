
package com.ebay.apacshipping.api;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GetAPACShippingRateRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GetAPACShippingRateRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://api.apacshipping.ebay.com/}BaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FromAddress" type="{http://api.apacshipping.ebay.com/}Address" minOccurs="0"/&gt;
 *         &lt;element name="ShipToAddress" type="{http://api.apacshipping.ebay.com/}ShipToAddress" minOccurs="0"/&gt;
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="InsuranceType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="InsuranceAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="MailType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAPACShippingRateRequest", propOrder = {
    "service",
    "fromAddress",
    "shipToAddress",
    "weight",
    "length",
    "height",
    "width",
    "insuranceType",
    "insuranceAmount",
    "mailType"
})
public class GetAPACShippingRateRequest
    extends BaseRequest
{

    @XmlElement(name = "Service")
    protected String service;
    @XmlElement(name = "FromAddress")
    protected Address fromAddress;
    @XmlElement(name = "ShipToAddress")
    protected ShipToAddress shipToAddress;
    @XmlElement(name = "Weight", required = true)
    protected BigDecimal weight;
    @XmlElement(name = "Length")
    protected double length;
    @XmlElement(name = "Height")
    protected double height;
    @XmlElement(name = "Width")
    protected double width;
    @XmlElement(name = "InsuranceType")
    protected int insuranceType;
    @XmlElement(name = "InsuranceAmount", required = true)
    protected BigDecimal insuranceAmount;
    @XmlElement(name = "MailType")
    protected int mailType;

    /**
     * 获取service属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * 设置service属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

    /**
     * 获取fromAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getFromAddress() {
        return fromAddress;
    }

    /**
     * 设置fromAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setFromAddress(Address value) {
        this.fromAddress = value;
    }

    /**
     * 获取shipToAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ShipToAddress }
     *     
     */
    public ShipToAddress getShipToAddress() {
        return shipToAddress;
    }

    /**
     * 设置shipToAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ShipToAddress }
     *     
     */
    public void setShipToAddress(ShipToAddress value) {
        this.shipToAddress = value;
    }

    /**
     * 获取weight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 设置weight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

    /**
     * 获取length属性的值。
     * 
     */
    public double getLength() {
        return length;
    }

    /**
     * 设置length属性的值。
     * 
     */
    public void setLength(double value) {
        this.length = value;
    }

    /**
     * 获取height属性的值。
     * 
     */
    public double getHeight() {
        return height;
    }

    /**
     * 设置height属性的值。
     * 
     */
    public void setHeight(double value) {
        this.height = value;
    }

    /**
     * 获取width属性的值。
     * 
     */
    public double getWidth() {
        return width;
    }

    /**
     * 设置width属性的值。
     * 
     */
    public void setWidth(double value) {
        this.width = value;
    }

    /**
     * 获取insuranceType属性的值。
     * 
     */
    public int getInsuranceType() {
        return insuranceType;
    }

    /**
     * 设置insuranceType属性的值。
     * 
     */
    public void setInsuranceType(int value) {
        this.insuranceType = value;
    }

    /**
     * 获取insuranceAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    /**
     * 设置insuranceAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInsuranceAmount(BigDecimal value) {
        this.insuranceAmount = value;
    }

    /**
     * 获取mailType属性的值。
     * 
     */
    public int getMailType() {
        return mailType;
    }

    /**
     * 设置mailType属性的值。
     * 
     */
    public void setMailType(int value) {
        this.mailType = value;
    }

}
