
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>ConfirmAPACShippingPackageResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ConfirmAPACShippingPackageResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://api.apacshipping.ebay.com/}BaseResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CarrierConfirmId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PickUpConfirmedDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfirmAPACShippingPackageResponse", propOrder = {
    "carrierConfirmId",
    "pickUpConfirmedDateTime"
})
public class ConfirmAPACShippingPackageResponse2
    extends BaseResponse
{

    @XmlElement(name = "CarrierConfirmId")
    protected String carrierConfirmId;
    @XmlElement(name = "PickUpConfirmedDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickUpConfirmedDateTime;

    /**
     * 获取carrierConfirmId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierConfirmId() {
        return carrierConfirmId;
    }

    /**
     * 设置carrierConfirmId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierConfirmId(String value) {
        this.carrierConfirmId = value;
    }

    /**
     * 获取pickUpConfirmedDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPickUpConfirmedDateTime() {
        return pickUpConfirmedDateTime;
    }

    /**
     * 设置pickUpConfirmedDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPickUpConfirmedDateTime(XMLGregorianCalendar value) {
        this.pickUpConfirmedDateTime = value;
    }

}
