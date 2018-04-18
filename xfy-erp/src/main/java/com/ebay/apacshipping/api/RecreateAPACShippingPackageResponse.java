
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
 *         &lt;element name="RecreateAPACShippingPackageResult" type="{http://api.apacshipping.ebay.com/}RecreateAPACShippingPackageResponse" minOccurs="0"/&gt;
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
    "recreateAPACShippingPackageResult"
})
@XmlRootElement(name = "RecreateAPACShippingPackageResponse")
public class RecreateAPACShippingPackageResponse {

    @XmlElement(name = "RecreateAPACShippingPackageResult")
    protected RecreateAPACShippingPackageResponse2 recreateAPACShippingPackageResult;

    /**
     * 获取recreateAPACShippingPackageResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RecreateAPACShippingPackageResponse2 }
     *     
     */
    public RecreateAPACShippingPackageResponse2 getRecreateAPACShippingPackageResult() {
        return recreateAPACShippingPackageResult;
    }

    /**
     * 设置recreateAPACShippingPackageResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RecreateAPACShippingPackageResponse2 }
     *     
     */
    public void setRecreateAPACShippingPackageResult(RecreateAPACShippingPackageResponse2 value) {
        this.recreateAPACShippingPackageResult = value;
    }

}
