package com.payment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductDetailsType" , propOrder = {
		"productCode",
		"productName",
		"productDesc",
		"productSKU",
		"price",
		"discountedPrice",
		"refillQuantity",
		"productImageUrl",
		"isActive",
		"taxDetails",
		"productCouponDetails"
})
public class ProductDetailsType {
	
	@XmlElement(name = "productCode" , required = true)
	protected String productCode;
	
	@XmlElement(name = "productName" , required = true)
	protected String productName;
	
	@XmlElement(name = "productDesc" , required = true)
	protected String productDesc;
	
	@XmlElement(name = "productSKU" , required = true)
	protected String productSKU;
	
	@XmlElement(name = "price" , required = true)
	protected float price;
	
	@XmlElement(name = "discountedPrice" , required = true)
	protected float discountedPrice;
	
	@XmlElement(name = "refillQuantity" , required = true)
	protected float refillQuantity;
	
	@XmlElement(name = "productImageUrl" , required = true)
	protected String productImageUrl;
	
	@XmlElement(name = "isActive" , required = true )
	protected boolean isActive;
	
	@XmlElement(name = "taxDetails" , required = true)
	protected ProductTaxDetailsType taxDetails;
	
	@XmlElement(name= "ProductCouponDetails" , required = false)
	protected List<CouponDetailsType> productCouponDetails;
	
	public List<CouponDetailsType> getProductCouponDetails() {
		return productCouponDetails;
	}
	public void setProductCouponDetails(List<CouponDetailsType> productCouponDetails) {
		this.productCouponDetails = productCouponDetails;
	}
	

	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductSKU() {
		return productSKU;
	}
	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public float getRefillQuantity() {
		return refillQuantity;
	}
	public void setRefillQuantity(float refillQuantity) {
		this.refillQuantity = refillQuantity;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public ProductTaxDetailsType getTaxDetails() {
		return taxDetails;
	}
	public void setTaxDetails(ProductTaxDetailsType taxDetails) {
		this.taxDetails = taxDetails;
	}
	
}
