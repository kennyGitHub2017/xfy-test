
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
 *         &lt;element name="GetAPACShippingPackageStatusRequest" type="{http://api.apacshipping.ebay.com/}GetAPACShippingPackageStatusRequest" minOccurs="0"/&gt;
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
    "getAPACShippingPackageStatusRequest"
})
@XmlRootElement(name = "GetAPACShippingPackageStatus")
public class GetAPACShippingPackageStatus {

    @XmlElement(name = "GetAPACShippingPackageStatusRequest")
    protected GetAPACShippingPackageStatusRequest getAPACShippingPackageStatusRequest;

    /**
     * 获取getAPACShippingPackageStatusRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GetAPACShippingPackageStatusRequest }
     *     
     */
    public GetAPACShippingPackageStatusRequest getGetAPACShippingPackageStatusRequest() {
        return getAPACShippingPackageStatusRequest;
    }

    /**
     * 设置getAPACShippingPackageStatusRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GetAPACShippingPackageStatusRequest }
     *     
     */
    public void setGetAPACShippingPackageStatusRequest(GetAPACShippingPackageStatusRequest value) {
        this.getAPACShippingPackageStatusRequest = value;
    }

}
