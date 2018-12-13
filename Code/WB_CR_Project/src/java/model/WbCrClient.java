/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "WB_CR_CLIENT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WbCrClient.findAll", query = "SELECT w FROM WbCrClient w")
    , @NamedQuery(name = "WbCrClient.findByClientId", query = "SELECT w FROM WbCrClient w WHERE w.clientId = :clientId")
    , @NamedQuery(name = "WbCrClient.findByClientDni", query = "SELECT w FROM WbCrClient w WHERE w.clientDni = :clientDni")
    , @NamedQuery(name = "WbCrClient.findByClientName", query = "SELECT w FROM WbCrClient w WHERE w.clientName = :clientName")
    , @NamedQuery(name = "WbCrClient.findByClientAddress", query = "SELECT w FROM WbCrClient w WHERE w.clientAddress = :clientAddress")
})
public class WbCrClient implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_ID")
    private Integer clientId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CLIENT_DNI")
    private String clientDni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CLIENT_NAME")
    private String clientName;
    @Size(max = 200)
    @Column(name = "CLIENT_ADDRESS")
    private String clientAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wbCrClient")
    private Collection<WbCrBill> wbCrBillCollection;

    public WbCrClient()
    {
    }

    public WbCrClient(Integer clientId)
    {
        this.clientId = clientId;
    }

    public WbCrClient(Integer clientId, String clientDni, String clientName)
    {
        this.clientId = clientId;
        this.clientDni = clientDni;
        this.clientName = clientName;
    }

    public WbCrClient(Integer clientId, String clientDni, String clientName, String clientAddress) {
        this.clientId = clientId;
        this.clientDni = clientDni;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId = clientId;
    }

    public String getClientDni()
    {
        return clientDni;
    }

    public void setClientDni(String clientDni)
    {
        this.clientDni = clientDni;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientAddress()
    {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress)
    {
        this.clientAddress = clientAddress;
    }

    @XmlTransient
    public Collection<WbCrBill> getWbCrBillCollection()
    {
        return wbCrBillCollection;
    }

    public void setWbCrBillCollection(Collection<WbCrBill> wbCrBillCollection)
    {
        this.wbCrBillCollection = wbCrBillCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrClient))
        {
            return false;
        }
        WbCrClient other = (WbCrClient) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrClient[ clientId=" + clientId + " ]";
    }
    
}
