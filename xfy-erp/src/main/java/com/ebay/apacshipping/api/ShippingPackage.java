
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ShippingPackage complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ShippingPackage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CollectionInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DeliveryInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Incoterms" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShippingPackage", propOrder = {
    "description",
    "length",
    "height",
    "width",
    "collectionInstructions",
    "deliveryInstructions",
    "incoterms"
})
public class ShippingPackage {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Length")
    protected double length;
    @XmlElement(name = "Height")
    protected double height;
    @XmlElement(name = "Width")
    protected double width;
    @XmlElement(name = "CollectionInstructions")
    protected String collectionInstructions;
    @XmlElement(name = "DeliveryInstructions")
    protected String deliveryInstructions;
    @XmlElement(name = "Incoterms")
    protected String incoterms;

    /**
     * 获取description属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * 获取length属性的值。
     * 
     */
    public double getLength() {
        return length;
    }

    /**
     * 设置length属性的值。
     * 
     */
    public void setLength(double value) {
        this.length = value;
    }

    /**
     * 获取height属性的值。
     * 
     */
    public double getHeight() {
        return height;
    }

    /**
     * 设置height属性的值。
     * 
     */
    public void setHeight(double value) {
        this.height = value;
    }

    /**
     * 获取width属性的值。
     * 
     */
    public double getWidth() {
        return width;
    }

    /**
     * 设置width属性的值。
     * 
     */
    public void setWidth(double value) {
        this.width = value;
    }

    /**
     * 获取collectionInstructions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectionInstructions() {
        return collectionInstructions;
    }

    /**
     * 设置collectionInstructions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectionInstructions(String value) {
        this.collectionInstructions = value;
    }

    /**
     * 获取deliveryInstructions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    /**
     * 设置deliveryInstructions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryInstructions(String value) {
        this.deliveryInstructions = value;
    }

    /**
     * 获取incoterms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncoterms() {
        return incoterms;
    }

    /**
     * 设置incoterms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncoterms(String value) {
        this.incoterms = value;
    }

}
