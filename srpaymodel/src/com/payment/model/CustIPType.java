//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.21 at 04:20:53 PM IST 
//


package com.payment.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Java class for CustIPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustIPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IPAdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MACAdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustIPType", propOrder = {
    "ipAdd",
    "macAdd"
})
public class CustIPType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "IPAdd")
    protected String ipAdd;
    @XmlElement(name = "MACAdd")
    protected String macAdd;

    /**
     * Gets the value of the ipAdd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPAdd() {
        return ipAdd;
    }

    /**
     * Sets the value of the ipAdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPAdd(String value) {
        this.ipAdd = value;
    }

    /**
     * Gets the value of the macAdd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMACAdd() {
        return macAdd;
    }

    /**
     * Sets the value of the macAdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMACAdd(String value) {
        this.macAdd = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theIPAdd;
            theIPAdd = this.getIPAdd();
            strategy.appendField(locator, this, "ipAdd", buffer, theIPAdd);
        }
        {
            String theMACAdd;
            theMACAdd = this.getMACAdd();
            strategy.appendField(locator, this, "macAdd", buffer, theMACAdd);
        }
        return buffer;
    }

}
