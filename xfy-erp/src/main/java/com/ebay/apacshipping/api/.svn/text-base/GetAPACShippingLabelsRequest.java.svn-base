
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GetAPACShippingLabelsRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GetAPACShippingLabelsRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://api.apacshipping.ebay.com/}BaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TrackCodeList" type="{http://api.apacshipping.ebay.com/}TrackCodeList" minOccurs="0"/&gt;
 *         &lt;element name="PageSize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAPACShippingLabelsRequest", propOrder = {
    "trackCodeList",
    "pageSize"
})
public class GetAPACShippingLabelsRequest
    extends BaseRequest
{

    @XmlElement(name = "TrackCodeList")
    protected TrackCodeList trackCodeList;
    @XmlElement(name = "PageSize")
    protected int pageSize;

    /**
     * 获取trackCodeList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TrackCodeList }
     *     
     */
    public TrackCodeList getTrackCodeList() {
        return trackCodeList;
    }

    /**
     * 设置trackCodeList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TrackCodeList }
     *     
     */
    public void setTrackCodeList(TrackCodeList value) {
        this.trackCodeList = value;
    }

    /**
     * 获取pageSize属性的值。
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性的值。
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

}
