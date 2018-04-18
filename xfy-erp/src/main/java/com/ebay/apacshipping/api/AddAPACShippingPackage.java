
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
 *         &lt;element name="AddAPACShippingPackageRequest" type="{http://api.apacshipping.ebay.com/}AddAPACShippingPackageRequest" minOccurs="0"/&gt;
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
    "addAPACShippingPackageRequest"
})
@XmlRootElement(name = "AddAPACShippingPackage")
public class AddAPACShippingPackage {

    @XmlElement(name = "AddAPACShippingPackageRequest")
    protected AddAPACShippingPackageRequest addAPACShippingPackageRequest;

    /**
     * 获取addAPACShippingPackageRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AddAPACShippingPackageRequest }
     *     
     */
    public AddAPACShippingPackageRequest getAddAPACShippingPackageRequest() {
        return addAPACShippingPackageRequest;
    }

    /**
     * 设置addAPACShippingPackageRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AddAPACShippingPackageRequest }
     *     
     */
    public void setAddAPACShippingPackageRequest(AddAPACShippingPackageRequest value) {
        this.addAPACShippingPackageRequest = value;
    }

}
