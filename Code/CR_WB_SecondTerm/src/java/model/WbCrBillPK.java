/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author wason
 */
@Embeddable
public class WbCrBillPK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "CITY_ID")
    private int cityId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_ID")
    private int clientId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_ID")
    private int billId;

    public WbCrBillPK()
    {
    }

    public WbCrBillPK(int cityId, int clientId, int billId)
    {
        this.cityId = cityId;
        this.clientId = clientId;
        this.billId = billId;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }

    public int getClientId()
    {
        return clientId;
    }

    public void setClientId(int clientId)
    {
        this.clientId = clientId;
    }

    public int getBillId()
    {
        return billId;
    }

    public void setBillId(int billId)
    {
        this.billId = billId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) cityId;
        hash += (int) clientId;
        hash += (int) billId;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrBillPK))
        {
            return false;
        }
        WbCrBillPK other = (WbCrBillPK) object;
        if (this.cityId != other.cityId)
        {
            return false;
        }
        if (this.clientId != other.clientId)
        {
            return false;
        }
        if (this.billId != other.billId)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrBillPK[ cityId=" + cityId + ", clientId=" + clientId + ", billId=" + billId + " ]";
    }
    
}
