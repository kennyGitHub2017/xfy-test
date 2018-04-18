
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>BaseResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BaseResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Ack" type="{http://api.apacshipping.ebay.com/}EnumAck"/&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NotificationList" type="{http://api.apacshipping.ebay.com/}NotificationList" minOccurs="0"/&gt;
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="InvocationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseResponse", propOrder = {
    "version",
    "ack",
    "message",
    "notificationList",
    "timestamp",
    "invocationID"
})
@XmlSeeAlso({
    AddAPACShippingPackageResponse2 .class,
    CancelAPACShippingPackageResponse2 .class,
    ConfirmAPACShippingPackageResponse2 .class,
    GetAPACShippingLabelResponse2 .class,
    GetAPACShippingLabelsResponse2 .class,
    GetAPACShippingPackageResponse2 .class,
    GetAPACShippingPackageStatusResponse2 .class,
    GetAPACShippingRateResponse2 .class,
    VerifyAPACShippingUserResponse2 .class,
    RecreateAPACShippingPackageResponse2 .class,
    GetAPACShippingTrackCodeResponse2 .class
})
public class BaseResponse {

    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "Ack", required = true)
    @XmlSchemaType(name = "string")
    protected EnumAck ack;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "NotificationList")
    protected NotificationList notificationList;
    @XmlElement(name = "Timestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(name = "InvocationID")
    protected String invocationID;

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
     * 获取ack属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EnumAck }
     *     
     */
    public EnumAck getAck() {
        return ack;
    }

    /**
     * 设置ack属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EnumAck }
     *     
     */
    public void setAck(EnumAck value) {
        this.ack = value;
    }

    /**
     * 获取message属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * 获取notificationList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NotificationList }
     *     
     */
    public NotificationList getNotificationList() {
        return notificationList;
    }

    /**
     * 设置notificationList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NotificationList }
     *     
     */
    public void setNotificationList(NotificationList value) {
        this.notificationList = value;
    }

    /**
     * 获取timestamp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * 设置timestamp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * 获取invocationID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvocationID() {
        return invocationID;
    }

    /**
     * 设置invocationID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvocationID(String value) {
        this.invocationID = value;
    }

}
