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
@Table(name = "WB_CR_CITY")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WbCrCity.findAll", query = "SELECT w FROM WbCrCity w")
    , @NamedQuery(name = "WbCrCity.findByCityId", query = "SELECT w FROM WbCrCity w WHERE w.cityId = :cityId")
    , @NamedQuery(name = "WbCrCity.findByCityName", query = "SELECT w FROM WbCrCity w WHERE w.cityName = :cityName")
})
public class WbCrCity implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CITY_ID")
    private Integer cityId;
    @Size(max = 45)
    @Column(name = "CITY_NAME")
    private String cityName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityId")
    private Collection<WbCrBill> wbCrBillCollection;

    public WbCrCity()
    {
    }

    public WbCrCity(Integer cityId, String cityName)
    {
        this.cityId = cityId;
        this.cityName = cityName;
    }
    
    

    public WbCrCity(Integer cityId)
    {
        this.cityId = cityId;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
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
        hash += (cityId != null ? cityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrCity))
        {
            return false;
        }
        WbCrCity other = (WbCrCity) object;
        if ((this.cityId == null && other.cityId != null) || (this.cityId != null && !this.cityId.equals(other.cityId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrCity[ cityId=" + cityId + " ]";
    }
    
}
