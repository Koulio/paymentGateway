/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srswitch.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lax
 */
@Entity
@Table(name = "terminal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terminal.findAll", query = "SELECT t FROM Terminal t"),
    @NamedQuery(name = "Terminal.findByTermId", query = "SELECT t FROM Terminal t WHERE t.termId = :termId"),
    @NamedQuery(name = "Terminal.findByTermCode", query = "SELECT t FROM Terminal t WHERE t.termCode = :termCode"),
    @NamedQuery(name = "Terminal.findByDeleteFlag", query = "SELECT t FROM Terminal t WHERE t.deleteFlag = :deleteFlag"),
    @NamedQuery(name = "Terminal.findByAuditLogonId", query = "SELECT t FROM Terminal t WHERE t.auditLogonId = :auditLogonId"),
    @NamedQuery(name = "Terminal.findByTimeStamp", query = "SELECT t FROM Terminal t WHERE t.timeStamp = :timeStamp"),
    @NamedQuery(name = "Terminal.findByUniquecode", query = "SELECT t FROM Terminal t WHERE t.uniquecode = :uniquecode"),
    @NamedQuery(name = "Terminal.findByUniquecodeassigned", query = "SELECT t FROM Terminal t WHERE t.uniquecodeassigned = :uniquecodeassigned"),
    @NamedQuery(name = "Terminal.findByStatusFlag", query = "SELECT t FROM Terminal t WHERE t.statusFlag = :statusFlag")})
public class Terminal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "termId")
    private Integer termId;
    @Basic(optional = false)
    @Column(name = "termCode")
    private String termCode;
    @Basic(optional = false)
    @Column(name = "deleteFlag")
    private boolean deleteFlag;
    @Basic(optional = false)
    @Column(name = "auditLogonId")
    private int auditLogonId;
    @Basic(optional = false)
    @Column(name = "timeStamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Column(name = "uniquecode")
    private String uniquecode;
    @Basic(optional = false)
    @Column(name = "uniquecodeassigned")
    private char uniquecodeassigned;
    @Basic(optional = false)
    @Column(name = "statusFlag")
    private short statusFlag;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "termId")
    private Registration registration;

    public Terminal() {
    }

    public Terminal(Integer termId) {
        this.termId = termId;
    }

    public Terminal(Integer termId, String termCode, boolean deleteFlag, int auditLogonId, Date timeStamp, char uniquecodeassigned, short statusFlag) {
        this.termId = termId;
        this.termCode = termCode;
        this.deleteFlag = deleteFlag;
        this.auditLogonId = auditLogonId;
        this.timeStamp = timeStamp;
        this.uniquecodeassigned = uniquecodeassigned;
        this.statusFlag = statusFlag;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getAuditLogonId() {
        return auditLogonId;
    }

    public void setAuditLogonId(int auditLogonId) {
        this.auditLogonId = auditLogonId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUniquecode() {
        return uniquecode;
    }

    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode;
    }

    public char getUniquecodeassigned() {
        return uniquecodeassigned;
    }

    public void setUniquecodeassigned(char uniquecodeassigned) {
        this.uniquecodeassigned = uniquecodeassigned;
    }

    public short getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(short statusFlag) {
        this.statusFlag = statusFlag;
    }

    @XmlTransient
    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @PrePersist
	@PreUpdate
    public void updateTimeStamp() {
		 java.util.Date date= new java.util.Date();
		 timeStamp = (new Timestamp(date.getTime()));
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (termId != null ? termId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terminal)) {
            return false;
        }
        Terminal other = (Terminal) object;
        if ((this.termId == null && other.termId != null) || (this.termId != null && !this.termId.equals(other.termId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.paymentgateway.model.Terminal[ termId=" + termId + " ]";
    }
    
}
