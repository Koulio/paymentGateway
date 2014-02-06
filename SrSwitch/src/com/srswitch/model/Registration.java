/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srswitch.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lax
 */
@Entity
@Table(name = "registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"),
    @NamedQuery(name = "Registration.findByRegistrationId", query = "SELECT r FROM Registration r WHERE r.registrationId = :registrationId"),
    @NamedQuery(name = "Registration.findByMsisdn", query = "SELECT r FROM Registration r WHERE r.msisdn = :msisdn"),
    @NamedQuery(name = "Registration.findByImeiNum", query = "SELECT r FROM Registration r WHERE r.imeiNum = :imeiNum"),
    @NamedQuery(name = "Registration.findByImsiNum", query = "SELECT r FROM Registration r WHERE r.imsiNum = :imsiNum"),
    @NamedQuery(name = "Registration.findByMsisdnImeiImsi", query = "SELECT r FROM Registration r WHERE r.imsiNum = :imsiNum and r.imeiNum = :imeiNum and r.msisdn = :msisdn")})
public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "registrationId")
    private Integer registrationId;
    @Basic(optional = false)
    @Column(name = "msisdn")
    private String msisdn;
    @Basic(optional = false)
    @Column(name = "imeiNum")
    private String imeiNum;
    @Basic(optional = false)
    @Column(name = "imsiNum")
    private String imsiNum;
    @Basic(optional = false)
    @Column(name = "authToken")
    private String authToken;
    @Basic(optional = false)
    @Column(name = "deleteFlag")
    private short deleteFlag;
    @Basic(optional = false)
    @Column(name = "auditLogonId")
    private int auditLogonId;
    @Basic(optional = false)
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "termId", referencedColumnName = "termId")
    @OneToOne(optional = false)
    private Terminal termId;

    public Registration() {
    }

    public Registration(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Registration(Integer registrationId, String msisdn, String imeiNum, String imsiNum, String authToken, short deleteFlag, int auditLogonId, Date timestamp) {
        this.registrationId = registrationId;
        this.msisdn = msisdn;
        this.imeiNum = imeiNum;
        this.imsiNum = imsiNum;
        this.authToken = authToken;
        this.deleteFlag = deleteFlag;
        this.auditLogonId = auditLogonId;
        this.timestamp = timestamp;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImeiNum() {
        return imeiNum;
    }

    public void setImeiNum(String imeiNum) {
        this.imeiNum = imeiNum;
    }

    public String getImsiNum() {
        return imsiNum;
    }

    public void setImsiNum(String imsiNum) {
        this.imsiNum = imsiNum;
    }

    public short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getAuditLogonId() {
        return auditLogonId;
    }

    public void setAuditLogonId(int auditLogonId) {
        this.auditLogonId = auditLogonId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Terminal getTermId() {
        return termId;
    }

    public void setTermId(Terminal termId) {
        this.termId = termId;
    }

    @PrePersist
	@PreUpdate
    public void updateTimeStamp() {
		 java.util.Date date= new java.util.Date();
		 timestamp = (new Timestamp(date.getTime()));
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrationId != null ? registrationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.registrationId == null && other.registrationId != null) || (this.registrationId != null && !this.registrationId.equals(other.registrationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.paymentgateway.model.Registration[ registrationId=" + registrationId + " ]";
    }

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getAuthToken() {
		return authToken;
	}
    
}
