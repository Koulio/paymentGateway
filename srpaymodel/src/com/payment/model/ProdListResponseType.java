package com.payment.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProdListResponseType", propOrder = {
    "result",
    "description",
    "productGroups",
    "generalCouponDetails"
})
public class ProdListResponseType {

	@XmlElement(name = "Result")
	protected int result;
	 
	@XmlElement(name = "Description", required = true)
	protected String description;
	
	@XmlElement(name = "ProductGroups", required = true)
	protected List<ProductGroupsType> productGroups;
	
	@XmlElement(name= "GeneralCouponDetails" , required = false)
	protected List<CouponDetailsType> generalCouponDetails;
	
	public List<CouponDetailsType> getGeneralCouponDetails() {
		return generalCouponDetails;
	}

	public void setGeneralCouponDetails(List<CouponDetailsType> generalCouponDetails) {
		this.generalCouponDetails = generalCouponDetails;
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductGroupsType> getProductGroups() {
		
		if(this.productGroups == null){
			return new ArrayList<ProductGroupsType>();
		}
		return this.productGroups;
	}

	public void setProductGroups(List<ProductGroupsType> productGroups) {
		this.productGroups = productGroups;
	}

}
