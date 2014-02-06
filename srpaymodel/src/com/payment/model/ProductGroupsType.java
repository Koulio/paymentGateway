package com.payment.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductGroupsType" , propOrder = {
		"groupName",
		"groupDesc",
		"productDetails"
})
public class ProductGroupsType {

	@XmlElement(name = "groupName" , required = true)
	protected String groupName;
	
	@XmlElement(name = "groupDesc" , required = true)
	protected String groupDesc;

	@XmlElement(name = "productDetails" , required = true)
	protected List<ProductDetailsType> productDetails;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	
	public List<ProductDetailsType> getProductDetails() {
		
		if(this.productDetails == null){
			return new ArrayList<ProductDetailsType>();
		}
		return this.productDetails;
	}

	public void setProductDetails(List<ProductDetailsType> productDetails) {
		this.productDetails = productDetails;
	}

	
}
