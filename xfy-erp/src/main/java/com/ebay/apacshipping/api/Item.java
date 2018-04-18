
package com.ebay.apacshipping.api;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Item"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EBayEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EBayBuyerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EBayItemID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EBayItemTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EBayMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EBaySiteID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="EBayTransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EBayOrderID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrderSalesRecordNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="PaymentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="PayPalEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PayPalMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PostedQTY" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ReceivedAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="SalesRecordNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="SoldDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="SoldPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="SoldQTY" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="SKU" type="{http://api.apacshipping.ebay.com/}SKU" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder = {
    "currencyCode",
    "eBayEmail",
    "eBayBuyerID",
    "eBayItemID",
    "eBayItemTitle",
    "eBayMessage",
    "eBaySiteID",
    "eBayTransactionID",
    "eBayOrderID",
    "note",
    "orderSalesRecordNumber",
    "paymentDate",
    "payPalEmail",
    "payPalMessage",
    "postedQTY",
    "receivedAmount",
    "salesRecordNumber",
    "soldDate",
    "soldPrice",
    "soldQTY",
    "sku"
})
public class Item {

    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "EBayEmail")
    protected String eBayEmail;
    @XmlElement(name = "EBayBuyerID")
    protected String eBayBuyerID;
    @XmlElement(name = "EBayItemID")
    protected String eBayItemID;
    @XmlElement(name = "EBayItemTitle")
    protected String eBayItemTitle;
    @XmlElement(name = "EBayMessage")
    protected String eBayMessage;
    @XmlElement(name = "EBaySiteID")
    protected int eBaySiteID;
    @XmlElement(name = "EBayTransactionID")
    protected String eBayTransactionID;
    @XmlElement(name = "EBayOrderID")
    protected String eBayOrderID;
    @XmlElement(name = "Note")
    protected String note;
    @XmlElement(name = "OrderSalesRecordNumber")
    protected int orderSalesRecordNumber;
    @XmlElement(name = "PaymentDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentDate;
    @XmlElement(name = "PayPalEmail")
    protected String payPalEmail;
    @XmlElement(name = "PayPalMessage")
    protected String payPalMessage;
    @XmlElement(name = "PostedQTY")
    protected int postedQTY;
    @XmlElement(name = "ReceivedAmount", required = true)
    protected BigDecimal receivedAmount;
    @XmlElement(name = "SalesRecordNumber")
    protected int salesRecordNumber;
    @XmlElement(name = "SoldDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar soldDate;
    @XmlElement(name = "SoldPrice", required = true)
    protected BigDecimal soldPrice;
    @XmlElement(name = "SoldQTY")
    protected int soldQTY;
    @XmlElement(name = "SKU")
    protected SKU sku;

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * 获取eBayEmail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBayEmail() {
        return eBayEmail;
    }

    /**
     * 设置eBayEmail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBayEmail(String value) {
        this.eBayEmail = value;
    }

    /**
     * 获取eBayBuyerID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBayBuyerID() {
        return eBayBuyerID;
    }

    /**
     * 设置eBayBuyerID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBayBuyerID(String value) {
        this.eBayBuyerID = value;
    }

    /**
     * 获取eBayItemID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBayItemID() {
        return eBayItemID;
    }

    /**
     * 设置eBayItemID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBayItemID(String value) {
        this.eBayItemID = value;
    }

    /**
     * 获取eBayItemTitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBayItemTitle() {
        return eBayItemTitle;
    }

    /**
     * 设置eBayItemTitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBayItemTitle(String value) {
        this.eBayItemTitle = value;
    }

    /**
     * 获取eBayMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBayMessage() {
        return eBayMessage;
    }

    /**
     * 设置eBayMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBayMessage(String value) {
        this.eBayMessage = value;
    }

    /**
     * 获取eBaySiteID属性的值。
     * 
     */
    public int getEBaySiteID() {
        return eBaySiteID;
    }

    /**
     * 设置eBaySiteID属性的值。
     * 
     */
    public void setEBaySiteID(int value) {
        this.eBaySiteID = value;
    }

    /**
     * 获取eBayTransactionID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBayTransactionID() {
        return eBayTransactionID;
    }

    /**
     * 设置eBayTransactionID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBayTransactionID(String value) {
        this.eBayTransactionID = value;
    }

    /**
     * 获取eBayOrderID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBayOrderID() {
        return eBayOrderID;
    }

    /**
     * 设置eBayOrderID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBayOrderID(String value) {
        this.eBayOrderID = value;
    }

    /**
     * 获取note属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置note属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * 获取orderSalesRecordNumber属性的值。
     * 
     */
    public int getOrderSalesRecordNumber() {
        return orderSalesRecordNumber;
    }

    /**
     * 设置orderSalesRecordNumber属性的值。
     * 
     */
    public void setOrderSalesRecordNumber(int value) {
        this.orderSalesRecordNumber = value;
    }

    /**
     * 获取paymentDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDate() {
        return paymentDate;
    }

    /**
     * 设置paymentDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDate(XMLGregorianCalendar value) {
        this.paymentDate = value;
    }

    /**
     * 获取payPalEmail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayPalEmail() {
        return payPalEmail;
    }

    /**
     * 设置payPalEmail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayPalEmail(String value) {
        this.payPalEmail = value;
    }

    /**
     * 获取payPalMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayPalMessage() {
        return payPalMessage;
    }

    /**
     * 设置payPalMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayPalMessage(String value) {
        this.payPalMessage = value;
    }

    /**
     * 获取postedQTY属性的值。
     * 
     */
    public int getPostedQTY() {
        return postedQTY;
    }

    /**
     * 设置postedQTY属性的值。
     * 
     */
    public void setPostedQTY(int value) {
        this.postedQTY = value;
    }

    /**
     * 获取receivedAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    /**
     * 设置receivedAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReceivedAmount(BigDecimal value) {
        this.receivedAmount = value;
    }

    /**
     * 获取salesRecordNumber属性的值。
     * 
     */
    public int getSalesRecordNumber() {
        return salesRecordNumber;
    }

    /**
     * 设置salesRecordNumber属性的值。
     * 
     */
    public void setSalesRecordNumber(int value) {
        this.salesRecordNumber = value;
    }

    /**
     * 获取soldDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSoldDate() {
        return soldDate;
    }

    /**
     * 设置soldDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSoldDate(XMLGregorianCalendar value) {
        this.soldDate = value;
    }

    /**
     * 获取soldPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    /**
     * 设置soldPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSoldPrice(BigDecimal value) {
        this.soldPrice = value;
    }

    /**
     * 获取soldQTY属性的值。
     * 
     */
    public int getSoldQTY() {
        return soldQTY;
    }

    /**
     * 设置soldQTY属性的值。
     * 
     */
    public void setSoldQTY(int value) {
        this.soldQTY = value;
    }

    /**
     * 获取sku属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SKU }
     *     
     */
    public SKU getSKU() {
        return sku;
    }

    /**
     * 设置sku属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SKU }
     *     
     */
    public void setSKU(SKU value) {
        this.sku = value;
    }

}
