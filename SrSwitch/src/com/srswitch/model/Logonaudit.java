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
@Table(name = "logonaudit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logonaudit.findAll", query = "SELECT l FROM Logonaudit l"),
    @NamedQuery(name = "Logonaudit.findByLogonAuditId", query = "SELECT l FROM Logonaudit l WHERE l.logonAuditId = :logonAuditId"),
    @NamedQuery(name = "Logonaudit.findByLogonId", query = "SELECT l FROM Logonaudit l WHERE l.logonId = :logonId"),
    @NamedQuery(name = "Logonaudit.findByLogonDatetime", query = "SELECT l FROM Logonaudit l WHERE l.logonDatetime = :logonDatetime"),
    @NamedQuery(name = "Logonaudit.findByClientIPAdd", query = "SELECT l FROM Logonaudit l WHERE l.clientIPAdd = :clientIPAdd"),
    @NamedQuery(name = "Logonaudit.findByLogonAttemptStatus", query = "SELECT l FROM Logonaudit l WHERE l.logonAttemptStatus = :logonAttemptStatus"),
    @NamedQuery(name = "Logonaudit.findByAuditLogonId", query = "SELECT l FROM Logonaudit l WHERE l.auditLogonId = :auditLogonId"),
    @NamedQuery(name = "Logonaudit.findByTimeStamp", query = "SELECT l FROM Logonaudit l WHERE l.timeStamp = :timeStamp")})
public class Logonaudit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "logonAuditId")
    private Integer logonAuditId;
    @Basic(optional = false)
    @Column(name = "logonId")
    private int logonId;
    @Basic(optional = false)
    @Column(name = "logonDatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logonDatetime;
    @Basic(optional = false)
    @Column(name = "clientIPAdd")
    private String clientIPAdd;
    @Basic(optional = false)
    @Column(name = "logonAttemptStatus")
    private boolean logonAttemptStatus;
    @Basic(optional = false)
    @Column(name = "auditLogonId")
    private int auditLogonId;
    @Basic(optional = false)
    @Column(name = "timeStamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    public Logonaudit() {
    }

    public Logonaudit(Integer logonAuditId) {
        this.logonAuditId = logonAuditId;
    }

    public Logonaudit(Integer logonAuditId, int logonId, Date logonDatetime, String clientIPAdd, boolean logonAttemptStatus, int auditLogonId, Date timeStamp) {
        this.logonAuditId = logonAuditId;
        this.logonId = logonId;
        this.logonDatetime = logonDatetime;
        this.clientIPAdd = clientIPAdd;
        this.logonAttemptStatus = logonAttemptStatus;
        this.auditLogonId = auditLogonId;
        this.timeStamp = timeStamp;
    }

    public Integer getLogonAuditId() {
        return logonAuditId;
    }

    public void setLogonAuditId(Integer logonAuditId) {
        this.logonAuditId = logonAuditId;
    }

    public int getLogonId() {
        return logonId;
    }

    public void setLogonId(int logonId) {
        this.logonId = logonId;
    }

    public Date getLogonDatetime() {
        return logonDatetime;
    }

    public void setLogonDatetime(Date logonDatetime) {
        this.logonDatetime = logonDatetime;
    }

    public String getClientIPAdd() {
        return clientIPAdd;
    }

    public void setClientIPAdd(String clientIPAdd) {
        this.clientIPAdd = clientIPAdd;
    }

    public boolean getLogonAttemptStatus() {
        return logonAttemptStatus;
    }

    public void setLogonAttemptStatus(boolean logonAttemptStatus) {
        this.logonAttemptStatus = logonAttemptStatus;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logonAuditId != null ? logonAuditId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logonaudit)) {
            return false;
        }
        Logonaudit other = (Logonaudit) object;
        if ((this.logonAuditId == null && other.logonAuditId != null) || (this.logonAuditId != null && !this.logonAuditId.equals(other.logonAuditId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "namedentities.Logonaudit[ logonAuditId=" + logonAuditId + " ]";
    }
    
}
