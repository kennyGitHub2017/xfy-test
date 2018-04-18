
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AddAPACShippingPackageRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AddAPACShippingPackageRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://api.apacshipping.ebay.com/}BaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrderDetail" type="{http://api.apacshipping.ebay.com/}OrderDetail" minOccurs="0"/&gt;
 *         &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddAPACShippingPackageRequest", propOrder = {
    "orderDetail",
    "service"
})
public class AddAPACShippingPackageRequest
    extends BaseRequest
{

    @XmlElement(name = "OrderDetail")
    protected OrderDetail orderDetail;
    @XmlElement(name = "Service")
    protected String service;

    /**
     * 获取orderDetail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OrderDetail }
     *     
     */
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    /**
     * 设置orderDetail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OrderDetail }
     *     
     */
    public void setOrderDetail(OrderDetail value) {
        this.orderDetail = value;
    }

    /**
     * 获取service属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * 设置service属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

}
