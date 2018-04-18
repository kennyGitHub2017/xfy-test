
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
 *         &lt;element name="GetAPACShippingLabelsResult" type="{http://api.apacshipping.ebay.com/}GetAPACShippingLabelsResponse" minOccurs="0"/&gt;
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
    "getAPACShippingLabelsResult"
})
@XmlRootElement(name = "GetAPACShippingLabelsResponse")
public class GetAPACShippingLabelsResponse {

    @XmlElement(name = "GetAPACShippingLabelsResult")
    protected GetAPACShippingLabelsResponse2 getAPACShippingLabelsResult;

    /**
     * 获取getAPACShippingLabelsResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GetAPACShippingLabelsResponse2 }
     *     
     */
    public GetAPACShippingLabelsResponse2 getGetAPACShippingLabelsResult() {
        return getAPACShippingLabelsResult;
    }

    /**
     * 设置getAPACShippingLabelsResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GetAPACShippingLabelsResponse2 }
     *     
     */
    public void setGetAPACShippingLabelsResult(GetAPACShippingLabelsResponse2 value) {
        this.getAPACShippingLabelsResult = value;
    }

}
