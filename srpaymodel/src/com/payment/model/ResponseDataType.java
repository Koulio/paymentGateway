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



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseDataType", propOrder = {
    "result",
    "description",
    "otherData",
    "transactionResponseData"
})
public class ResponseDataType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Result")
    protected int result;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "OtherData")
    protected OtherDataType otherData;
    @XmlElement(name = "TransactionResponseData")
    protected TransactionResponseDataType transactionResponseData;

	/**
     * Gets the value of the result property.
     * 
     */
    public int getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     */
    public void setResult(int value) {
        this.result = value;
    }

    /**
     * Gets the value of the description property.
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
     * Sets the value of the description property.
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
     * Gets the value of the otherData property.
     * 
     * @return
     *     possible object is
     *     {@link OtherDataType }
     *     
     */
    public OtherDataType getOtherData() {
        return otherData;
    }

    /**
     * Sets the value of the otherData property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherDataType }
     *     
     */
    public void setOtherData(OtherDataType value) {
        this.otherData = value;
    }

    
    public TransactionResponseDataType getTransactionResponseData() {
		return transactionResponseData;
	}

	public void setTransactionResponseData(
			TransactionResponseDataType transactionResponseData) {
		this.transactionResponseData = transactionResponseData;
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
            int theResult;
            theResult = this.getResult();
            strategy.appendField(locator, this, "result", buffer, theResult);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            OtherDataType theOtherData;
            theOtherData = this.getOtherData();
            strategy.appendField(locator, this, "otherData", buffer, theOtherData);
        }
        return buffer;
    }

}
