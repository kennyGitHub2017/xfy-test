
package com.ebay.apacshipping.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EnumSeverity的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="EnumSeverity"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Failure"/&gt;
 *     &lt;enumeration value="Success"/&gt;
 *     &lt;enumeration value="PartialFailure"/&gt;
 *     &lt;enumeration value="Warning"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EnumSeverity")
@XmlEnum
public enum EnumSeverity {

    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("PartialFailure")
    PARTIAL_FAILURE("PartialFailure"),
    @XmlEnumValue("Warning")
    WARNING("Warning");
    private final String value;

    EnumSeverity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumSeverity fromValue(String v) {
        for (EnumSeverity c: EnumSeverity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
