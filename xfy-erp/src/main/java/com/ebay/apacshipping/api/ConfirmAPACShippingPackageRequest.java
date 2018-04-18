
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>ConfirmAPACShippingPackageRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ConfirmAPACShippingPackageRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://api.apacshipping.ebay.com/}BaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TrackCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PickUpRequestDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfirmAPACShippingPackageRequest", propOrder = {
    "trackCode",
    "pickUpRequestDateTime"
})
public class ConfirmAPACShippingPackageRequest
    extends BaseRequest
{

    @XmlElement(name = "TrackCode")
    protected String trackCode;
    @XmlElement(name = "PickUpRequestDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickUpRequestDateTime;

    /**
     * 获取trackCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackCode() {
        return trackCode;
    }

    /**
     * 设置trackCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackCode(String value) {
        this.trackCode = value;
    }

    /**
     * 获取pickUpRequestDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPickUpRequestDateTime() {
        return pickUpRequestDateTime;
    }

    /**
     * 设置pickUpRequestDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPickUpRequestDateTime(XMLGregorianCalendar value) {
        this.pickUpRequestDateTime = value;
    }

}
