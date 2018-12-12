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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    , @NamedQuery(name = "WbCrBill.findByCityId", query = "SELECT w FROM WbCrBill w WHERE w.wbCrBillPK.cityId = :cityId")
    , @NamedQuery(name = "WbCrBill.findByClientId", query = "SELECT w FROM WbCrBill w WHERE w.wbCrBillPK.clientId = :clientId")
    , @NamedQuery(name = "WbCrBill.findByBillId", query = "SELECT w FROM WbCrBill w WHERE w.wbCrBillPK.billId = :billId")
    , @NamedQuery(name = "WbCrBill.findByBillDate", query = "SELECT w FROM WbCrBill w WHERE w.billDate = :billDate")
})
public class WbCrBill implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WbCrBillPK wbCrBillPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wbCrBill")
    private Collection<WbCrBilldetail> wbCrBilldetailCollection;
    @JoinColumn(name = "CITY_ID", referencedColumnName = "CITY_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WbCrCity wbCrCity;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WbCrClient wbCrClient;

    public WbCrBill()
    {
    }

    public WbCrBill(WbCrBillPK wbCrBillPK)
    {
        this.wbCrBillPK = wbCrBillPK;
    }

    public WbCrBill(WbCrBillPK wbCrBillPK, Date billDate)
    {
        this.wbCrBillPK = wbCrBillPK;
        this.billDate = billDate;
    }

    public WbCrBill(int cityId, int clientId, int billId)
    {
        this.wbCrBillPK = new WbCrBillPK(cityId, clientId, billId);
    }

    public WbCrBillPK getWbCrBillPK()
    {
        return wbCrBillPK;
    }

    public void setWbCrBillPK(WbCrBillPK wbCrBillPK)
    {
        this.wbCrBillPK = wbCrBillPK;
    }

    public Date getBillDate()
    {
        return billDate;
    }

    public void setBillDate(Date billDate)
    {
        this.billDate = billDate;
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

    public WbCrCity getWbCrCity()
    {
        return wbCrCity;
    }

    public void setWbCrCity(WbCrCity wbCrCity)
    {
        this.wbCrCity = wbCrCity;
    }

    public WbCrClient getWbCrClient()
    {
        return wbCrClient;
    }

    public void setWbCrClient(WbCrClient wbCrClient)
    {
        this.wbCrClient = wbCrClient;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (wbCrBillPK != null ? wbCrBillPK.hashCode() : 0);
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
        if ((this.wbCrBillPK == null && other.wbCrBillPK != null) || (this.wbCrBillPK != null && !this.wbCrBillPK.equals(other.wbCrBillPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrBill[ wbCrBillPK=" + wbCrBillPK + " ]";
    }
    
}
