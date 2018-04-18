
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
 *         &lt;element name="GetAPACShippingRateResult" type="{http://api.apacshipping.ebay.com/}GetAPACShippingRateResponse" minOccurs="0"/&gt;
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
    "getAPACShippingRateResult"
})
@XmlRootElement(name = "GetAPACShippingRateResponse")
public class GetAPACShippingRateResponse {

    @XmlElement(name = "GetAPACShippingRateResult")
    protected GetAPACShippingRateResponse2 getAPACShippingRateResult;

    /**
     * 获取getAPACShippingRateResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GetAPACShippingRateResponse2 }
     *     
     */
    public GetAPACShippingRateResponse2 getGetAPACShippingRateResult() {
        return getAPACShippingRateResult;
    }

    /**
     * 设置getAPACShippingRateResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GetAPACShippingRateResponse2 }
     *     
     */
    public void setGetAPACShippingRateResult(GetAPACShippingRateResponse2 value) {
        this.getAPACShippingRateResult = value;
    }

}
