/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srswitch.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nitin.malik
 */
@Entity
@Table(name = "cardrouting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cardrouting.findAll", query = "SELECT c FROM Cardrouting c"),
    @NamedQuery(name = "Cardrouting.findByRecordId", query = "SELECT c FROM Cardrouting c WHERE c.recordId = :recordId"),
    @NamedQuery(name = "Cardrouting.findByBinStart", query = "SELECT c FROM Cardrouting c WHERE c.binStart = :binStart"),
    @NamedQuery(name = "Cardrouting.findByBinEnd", query = "SELECT c FROM Cardrouting c WHERE c.binEnd = :binEnd"),
    @NamedQuery(name = "Cardrouting.findByProcessModuleName", query = "SELECT c FROM Cardrouting c WHERE c.processModuleName = :processModuleName"),
    @NamedQuery(name = "Cardrouting.findByProcessLoyaltyFlag", query = "SELECT c FROM Cardrouting c WHERE c.processLoyaltyFlag = :processLoyaltyFlag"),
    @NamedQuery(name = "Cardrouting.findByDeleteFlag", query = "SELECT c FROM Cardrouting c WHERE c.deleteFlag = :deleteFlag"),
    @NamedQuery(name = "Cardrouting.findByAuditLogonId", query = "SELECT c FROM Cardrouting c WHERE c.auditLogonId = :auditLogonId"),
    @NamedQuery(name = "Cardrouting.findByTimeStamp", query = "SELECT c FROM Cardrouting c WHERE c.timeStamp = :timeStamp")})
public class Cardrouting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "recordId")
    private Integer recordId;
    @Basic(optional = false)
    @Column(name = "binStart")
    private String binStart;
    @Basic(optional = false)
    @Column(name = "binEnd")
    private String binEnd;
    @Basic(optional = false)
    @Column(name = "processModuleName")
    private String processModuleName;
    @Basic(optional = false)
    @Column(name = "processLoyaltyFlag")
    private boolean processLoyaltyFlag;
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
    @JoinColumn(name = "serviceEndpointId", referencedColumnName = "serviceEndpointId")
    @ManyToOne
    private Serviceendpoint serviceEndpointId;

    public Cardrouting() {
    }

    public Cardrouting(Integer recordId) {
        this.recordId = recordId;
    }

    public Cardrouting(Integer recordId, String binStart, String binEnd, String processModuleName, boolean processLoyaltyFlag, boolean deleteFlag, int auditLogonId, Date timeStamp) {
        this.recordId = recordId;
        this.binStart = binStart;
        this.binEnd = binEnd;
        this.processModuleName = processModuleName;
        this.processLoyaltyFlag = processLoyaltyFlag;
        this.deleteFlag = deleteFlag;
        this.auditLogonId = auditLogonId;
        this.timeStamp = timeStamp;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getBinStart() {
        return binStart;
    }

    public void setBinStart(String binStart) {
        this.binStart = binStart;
    }

    public String getBinEnd() {
        return binEnd;
    }

    public void setBinEnd(String binEnd) {
        this.binEnd = binEnd;
    }

    public String getProcessModuleName() {
        return processModuleName;
    }

    public void setProcessModuleName(String processModuleName) {
        this.processModuleName = processModuleName;
    }

    public boolean getProcessLoyaltyFlag() {
        return processLoyaltyFlag;
    }

    public void setProcessLoyaltyFlag(boolean processLoyaltyFlag) {
        this.processLoyaltyFlag = processLoyaltyFlag;
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

    public Serviceendpoint getServiceEndpointId() {
        return serviceEndpointId;
    }

    public void setServiceEndpointId(Serviceendpoint serviceEndpointId) {
        this.serviceEndpointId = serviceEndpointId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cardrouting)) {
            return false;
        }
        Cardrouting other = (Cardrouting) object;
        if ((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "namedentities.Cardrouting[ recordId=" + recordId + " ]";
    }
    
}
