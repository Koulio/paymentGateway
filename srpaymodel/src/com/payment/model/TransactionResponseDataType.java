package com.payment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionResponseDataType" , propOrder = {
		"refNum",
		"approvalCode"
})
public class TransactionResponseDataType {

	@XmlElement(name = "refNum")
	protected String refNum;
	
	@XmlElement(name = "approvalCode")
	protected String approvalCode;
	
	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}	
}
