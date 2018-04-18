
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
 *         &lt;element name="VerifyAPACShippingUserRequest" type="{http://api.apacshipping.ebay.com/}VerifyAPACShippingUserRequest" minOccurs="0"/&gt;
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
    "verifyAPACShippingUserRequest"
})
@XmlRootElement(name = "VerifyAPACShippingUser")
public class VerifyAPACShippingUser {

    @XmlElement(name = "VerifyAPACShippingUserRequest")
    protected VerifyAPACShippingUserRequest verifyAPACShippingUserRequest;

    /**
     * 获取verifyAPACShippingUserRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VerifyAPACShippingUserRequest }
     *     
     */
    public VerifyAPACShippingUserRequest getVerifyAPACShippingUserRequest() {
        return verifyAPACShippingUserRequest;
    }

    /**
     * 设置verifyAPACShippingUserRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VerifyAPACShippingUserRequest }
     *     
     */
    public void setVerifyAPACShippingUserRequest(VerifyAPACShippingUserRequest value) {
        this.verifyAPACShippingUserRequest = value;
    }

}
