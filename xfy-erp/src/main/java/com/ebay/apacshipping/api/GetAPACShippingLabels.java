
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
 *         &lt;element name="GetAPACShippingLabelRequest" type="{http://api.apacshipping.ebay.com/}GetAPACShippingLabelsRequest" minOccurs="0"/&gt;
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
@XmlRootElement(name = "GetAPACShippingLabels")
public class GetAPACShippingLabels {

    @XmlElement(name = "GetAPACShippingLabelRequest")
    protected GetAPACShippingLabelsRequest getAPACShippingLabelRequest;

    /**
     * 获取getAPACShippingLabelRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GetAPACShippingLabelsRequest }
     *     
     */
    public GetAPACShippingLabelsRequest getGetAPACShippingLabelRequest() {
        return getAPACShippingLabelRequest;
    }

    /**
     * 设置getAPACShippingLabelRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GetAPACShippingLabelsRequest }
     *     
     */
    public void setGetAPACShippingLabelRequest(GetAPACShippingLabelsRequest value) {
        this.getAPACShippingLabelRequest = value;
    }

}
