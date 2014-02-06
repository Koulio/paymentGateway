/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srswitch.model;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nitin.malik
 */
@Entity
@Table(name = "serviceendpoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serviceendpoint.findAll", query = "SELECT s FROM Serviceendpoint s"),
    @NamedQuery(name = "Serviceendpoint.findByServiceEndpointId", query = "SELECT s FROM Serviceendpoint s WHERE s.serviceEndpointId = :serviceEndpointId"),
    @NamedQuery(name = "Serviceendpoint.findByServiceName", query = "SELECT s FROM Serviceendpoint s WHERE s.serviceName = :serviceName"),
    @NamedQuery(name = "Serviceendpoint.findByProcessingCode", query = "SELECT s FROM Serviceendpoint s WHERE s.processingCode = :processingCode"),
    @NamedQuery(name = "Serviceendpoint.findByPublishedStr", query = "SELECT s FROM Serviceendpoint s WHERE s.publishedStr = :publishedStr"),
    @NamedQuery(name = "Serviceendpoint.findByServiceNamespace", query = "SELECT s FROM Serviceendpoint s WHERE s.serviceNamespace = :serviceNamespace"),
    @NamedQuery(name = "Serviceendpoint.findByType", query = "SELECT s FROM Serviceendpoint s WHERE s.type = :type"),
    @NamedQuery(name = "Serviceendpoint.findByServiceUrl", query = "SELECT s FROM Serviceendpoint s WHERE s.serviceUrl = :serviceUrl"),
    @NamedQuery(name = "Serviceendpoint.findByPort", query = "SELECT s FROM Serviceendpoint s WHERE s.port = :port"),
    @NamedQuery(name = "Serviceendpoint.findByServiceDescription", query = "SELECT s FROM Serviceendpoint s WHERE s.serviceDescription = :serviceDescription"),
    @NamedQuery(name = "Serviceendpoint.findByDeleteFlag", query = "SELECT s FROM Serviceendpoint s WHERE s.deleteFlag = :deleteFlag"),
    @NamedQuery(name = "Serviceendpoint.findByAuditLogonId", query = "SELECT s FROM Serviceendpoint s WHERE s.auditLogonId = :auditLogonId"),
    @NamedQuery(name = "Serviceendpoint.findByTimestamp", query = "SELECT s FROM Serviceendpoint s WHERE s.timestamp = :timestamp")})
public class Serviceendpoint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "serviceEndpointId")
    private Integer serviceEndpointId;
    @Basic(optional = false)
    @Column(name = "serviceName")
    private String serviceName;
    @Basic(optional = false)
    @Column(name = "processingCode")
    private String processingCode;
    @Basic(optional = false)
    @Column(name = "publishedStr")
    private String publishedStr;
    @Column(name = "serviceNamespace")
    private String serviceNamespace;
    @Basic(optional = false)
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @Column(name = "serviceUrl")
    private String serviceUrl;
    @Basic(optional = false)
    @Column(name = "port")
    private int port;
    @Column(name = "serviceDescription")
    private String serviceDescription;
    @Column(name = "deleteFlag")
    private Short deleteFlag;
    @Column(name = "auditLogonId")
    private Integer auditLogonId;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceEndpointId")
    private Collection<Cardrouting> cardroutingCollection;

    public Serviceendpoint() {
    }

    public Serviceendpoint(Integer serviceEndpointId) {
        this.serviceEndpointId = serviceEndpointId;
    }

    public Serviceendpoint(Integer serviceEndpointId, String serviceName, String processingCode, String publishedStr, int type, String serviceUrl, int port) {
        this.serviceEndpointId = serviceEndpointId;
        this.serviceName = serviceName;
        this.processingCode = processingCode;
        this.publishedStr = publishedStr;
        this.type = type;
        this.serviceUrl = serviceUrl;
        this.port = port;
    }

    public Integer getServiceEndpointId() {
        return serviceEndpointId;
    }

    public void setServiceEndpointId(Integer serviceEndpointId) {
        this.serviceEndpointId = serviceEndpointId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public String getPublishedStr() {
        return publishedStr;
    }

    public void setPublishedStr(String publishedStr) {
        this.publishedStr = publishedStr;
    }

    public String getServiceNamespace() {
        return serviceNamespace;
    }

    public void setServiceNamespace(String serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getAuditLogonId() {
        return auditLogonId;
    }

    public void setAuditLogonId(Integer auditLogonId) {
        this.auditLogonId = auditLogonId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<Cardrouting> getCardroutingCollection() {
        return cardroutingCollection;
    }

    public void setCardroutingCollection(Collection<Cardrouting> cardroutingCollection) {
        this.cardroutingCollection = cardroutingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceEndpointId != null ? serviceEndpointId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serviceendpoint)) {
            return false;
        }
        Serviceendpoint other = (Serviceendpoint) object;
        if ((this.serviceEndpointId == null && other.serviceEndpointId != null) || (this.serviceEndpointId != null && !this.serviceEndpointId.equals(other.serviceEndpointId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.srswitch.model.Serviceendpoint[ serviceEndpointId=" + serviceEndpointId + " ]";
    }
    
}
