package com.payment.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CouponDetailsType", propOrder = {
		"",
})
public class CouponDetailsType implements Serializable{

	private static final long serialVersionUID = 6521215819643790121L;

	@XmlElement(name= "" , required = false)
	protected Integer couponType;
	
	@XmlElement(name= "" , required = false)
	protected String couponTypeDesc;
	
	@XmlElement(name= "" , required = false)
	protected String couponName;
    
	@XmlElement(name= "" , required = false)
    protected String couponSKU;
    
	@XmlElement(name= "" , required = false)
    protected String couponCode;
       
	@XmlElement(name= "" , required = false)
    protected Float value1;
    
	@XmlElement(name= "" , required = false)
    protected Float value2;
    
	@XmlElement(name= "" , required = false)
    protected Float totalProductQuantity;
    
	@XmlElement(name= "" , required = false)
    protected Float freeProductQuantity;

	@XmlElement(name= "" , required = false)
    protected String startDate;
    
	@XmlElement(name= "" , required = false)
    protected String expiryDate; 
    
	@XmlElement(name= "" , required = false)
    protected Integer couponValueType;
    
	@XmlElement(name= "" , required = false)
    protected String couponValueDesc;
	
	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getCouponTypeDesc() {
		return couponTypeDesc;
	}

	public void setCouponTypeDesc(String couponTypeDesc) {
		this.couponTypeDesc = couponTypeDesc;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponSKU() {
		return couponSKU;
	}

	public void setCouponSKU(String couponSKU) {
		this.couponSKU = couponSKU;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Float getValue1() {
		return value1;
	}

	public void setValue1(Float value1) {
		this.value1 = value1;
	}

	public Float getValue2() {
		return value2;
	}

	public void setValue2(Float value2) {
		this.value2 = value2;
	}

	public Float getTotalProductQuantity() {
		return totalProductQuantity;
	}

	public void setTotalProductQuantity(Float totalProductQuantity) {
		this.totalProductQuantity = totalProductQuantity;
	}

	public Float getFreeProductQuantity() {
		return freeProductQuantity;
	}

	public void setFreeProductQuantity(Float freeProductQuantity) {
		this.freeProductQuantity = freeProductQuantity;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getCouponValueType() {
		return couponValueType;
	}

	public void setCouponValueType(Integer couponValueType) {
		this.couponValueType = couponValueType;
	}

	public String getCouponValueDesc() {
		return couponValueDesc;
	}

	public void setCouponValueDesc(String couponValueDesc) {
		this.couponValueDesc = couponValueDesc;
	}

	
}
