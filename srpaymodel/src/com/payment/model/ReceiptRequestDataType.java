package com.payment.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceiptRequestDataType", propOrder = {
		"approvalCode",
		"otherDataType",
		"isSendSMS",
		"isSendEmail",
		"mobileNum",
		"emailId"
})
public class ReceiptRequestDataType implements Serializable{

	private static final long serialVersionUID = -7809220306577584791L;

	@XmlElement(name = "approvalCode", required = true)
	protected String approvalCode;

	@XmlElement(name = "otherDataType", required = true)
	protected OtherDataType otherDataType;
	
	@XmlElement(name = "isSendSMS", required = true)
	protected boolean isSendSMS;
	
	@XmlElement(name = "isSendEmail", required = true)
	protected boolean isSendEmail;
	
	@XmlElement(name = "mobileNum", required = true)
	protected String mobileNum;
	
	@XmlElement(name = "emailId", required = true)
	protected String emailId;
	
	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public OtherDataType getOtherDataType() {
		return otherDataType;
	}

	public void setOtherDataType(OtherDataType otherDataType) {
		this.otherDataType = otherDataType;
	}

	public boolean isSendSMS() {
		return isSendSMS;
	}

	public void setSendSMS(boolean isSendSMS) {
		this.isSendSMS = isSendSMS;
	}

	public boolean isSendEmail() {
		return isSendEmail;
	}

	public void setSendEmail(boolean isSendEmail) {
		this.isSendEmail = isSendEmail;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

}
