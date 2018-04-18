
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetAPACShippingLabelRequest" type="{http://api.apacshipping.ebay.com/}GetAPACShippingLabelRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getAPACShippingLabelRequest"
})
@XmlRootElement(name = "GetAPACShippingLabel")
public class GetAPACShippingLabel {

    @XmlElement(name = "GetAPACShippingLabelRequest")
    protected GetAPACShippingLabelRequest getAPACShippingLabelRequest;

    /**
     * 获取getAPACShippingLabelRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GetAPACShippingLabelRequest }
     *     
     */
    public GetAPACShippingLabelRequest getGetAPACShippingLabelRequest() {
        return getAPACShippingLabelRequest;
    }

    /**
     * 设置getAPACShippingLabelRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GetAPACShippingLabelRequest }
     *     
     */
    public void setGetAPACShippingLabelRequest(GetAPACShippingLabelRequest value) {
        this.getAPACShippingLabelRequest = value;
    }

}
