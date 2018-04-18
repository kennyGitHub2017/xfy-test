
package com.hlt.ws.service.hop.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getTransportWayListResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getTransportWayListResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="error" type="{http://service.hop.service.ws.hlt.com/}HopHopError" minOccurs="0"/&gt;
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="transportWays" type="{http://service.hop.service.ws.hlt.com/}transportWay" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTransportWayListResponse", propOrder = {
    "error",
    "success",
    "transportWays"
})
public class GetTransportWayListResponse {

    protected HopHopError error;
    protected Boolean success;
    @XmlElement(nillable = true)
    protected List<TransportWay> transportWays;

    /**
     * 获取error属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HopHopError }
     *     
     */
    public HopHopError getError() {
        return error;
    }

    /**
     * 设置error属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HopHopError }
     *     
     */
    public void setError(HopHopError value) {
        this.error = value;
    }

    /**
     * 获取success属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * 设置success属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSuccess(Boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the transportWays property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transportWays property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransportWays().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransportWay }
     * 
     * 
     */
    public List<TransportWay> getTransportWays() {
        if (transportWays == null) {
            transportWays = new ArrayList<TransportWay>();
        }
        return this.transportWays;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetTransportWayListResponse [error=");
		builder.append(error);
		builder.append(", success=");
		builder.append(success);
		builder.append(", transportWays=");
		builder.append(transportWays);
		builder.append("]");
		return builder.toString();
	}

}
