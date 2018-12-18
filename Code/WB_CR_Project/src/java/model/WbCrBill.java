/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "WB_CR_BILL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WbCrBill.findAll", query = "SELECT w FROM WbCrBill w")
    , @NamedQuery(name = "WbCrBill.findByBillDate", query = "SELECT w FROM WbCrBill w WHERE w.billDate = :billDate")
    , @NamedQuery(name = "WbCrBill.findByBillId", query = "SELECT w FROM WbCrBill w WHERE w.billId = :billId")
})
public class WbCrBill implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Column(name = "BILL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_ID")
    private Integer billId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wbCrBill")
    private Collection<WbCrBilldetail> wbCrBilldetailCollection;
    @JoinColumn(name = "CITY_ID", referencedColumnName = "CITY_ID")
    @ManyToOne(optional = false)
    private WbCrCity cityId;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(optional = false)
    private WbCrClient clientId;

    public WbCrBill()
    {
    }

    public WbCrBill(Date billDate, Integer billId, WbCrCity cityId, WbCrClient clientId)
    {
        this.billDate = billDate;
        this.billId = billId;
        this.cityId = cityId;
        this.clientId = clientId;
    }
    
    

    public WbCrBill(Integer billId)
    {
        this.billId = billId;
    }

    public Date getBillDate()
    {
        return billDate;
    }

    public void setBillDate(Date billDate)
    {
        this.billDate = billDate;
    }

    public Integer getBillId()
    {
        return billId;
    }

    public void setBillId(Integer billId)
    {
        this.billId = billId;
    }

    @XmlTransient
    public Collection<WbCrBilldetail> getWbCrBilldetailCollection()
    {
        return wbCrBilldetailCollection;
    }

    public void setWbCrBilldetailCollection(Collection<WbCrBilldetail> wbCrBilldetailCollection)
    {
        this.wbCrBilldetailCollection = wbCrBilldetailCollection;
    }

    public WbCrCity getCityId()
    {
        return cityId;
    }

    public void setCityId(WbCrCity cityId)
    {
        this.cityId = cityId;
    }

    public WbCrClient getClientId()
    {
        return clientId;
    }

    public void setClientId(WbCrClient clientId)
    {
        this.clientId = clientId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (billId != null ? billId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrBill))
        {
            return false;
        }
        WbCrBill other = (WbCrBill) object;
        if ((this.billId == null && other.billId != null) || (this.billId != null && !this.billId.equals(other.billId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrBill[ billId=" + billId + " ]";
    }
    
}
