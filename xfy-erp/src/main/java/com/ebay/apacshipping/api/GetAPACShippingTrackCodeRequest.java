
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GetAPACShippingTrackCodeRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GetAPACShippingTrackCodeRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://api.apacshipping.ebay.com/}BaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EBayItemID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EBayTransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAPACShippingTrackCodeRequest", propOrder = {
    "eBayItemID",
    "eBayTransactionID"
})
public class GetAPACShippingTrackCodeRequest
    extends BaseRequest
{

    @XmlElement(name = "EBayItemID")
    protected String eBayItemID;
    @XmlElement(name = "EBayTransactionID")
    protected String eBayTransactionID;

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

}
