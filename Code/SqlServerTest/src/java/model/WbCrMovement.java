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
@Table(name = "WB_CR_MOVEMENT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WbCrMovement.findAll", query = "SELECT w FROM WbCrMovement w")
    , @NamedQuery(name = "WbCrMovement.findByMovementId", query = "SELECT w FROM WbCrMovement w WHERE w.movementId = :movementId")
    , @NamedQuery(name = "WbCrMovement.findByMovementName", query = "SELECT w FROM WbCrMovement w WHERE w.movementName = :movementName")
    , @NamedQuery(name = "WbCrMovement.findByMovementDirection", query = "SELECT w FROM WbCrMovement w WHERE w.movementDirection = :movementDirection")
})
public class WbCrMovement implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVEMENT_ID")
    private Integer movementId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "MOVEMENT_NAME")
    private String movementName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MOVEMENT_DIRECTION")
    private String movementDirection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wbCrMovement")
    private Collection<WbCrStock> wbCrStockCollection;

    public WbCrMovement()
    {
    }

    public WbCrMovement(Integer movementId)
    {
        this.movementId = movementId;
    }

    public WbCrMovement(Integer movementId, String movementName, String movementDirection)
    {
        this.movementId = movementId;
        this.movementName = movementName;
        this.movementDirection = movementDirection;
    }

    public Integer getMovementId()
    {
        return movementId;
    }

    public void setMovementId(Integer movementId)
    {
        this.movementId = movementId;
    }

    public String getMovementName()
    {
        return movementName;
    }

    public void setMovementName(String movementName)
    {
        this.movementName = movementName;
    }

    public String getMovementDirection()
    {
        return movementDirection;
    }

    public void setMovementDirection(String movementDirection)
    {
        this.movementDirection = movementDirection;
    }

    @XmlTransient
    public Collection<WbCrStock> getWbCrStockCollection()
    {
        return wbCrStockCollection;
    }

    public void setWbCrStockCollection(Collection<WbCrStock> wbCrStockCollection)
    {
        this.wbCrStockCollection = wbCrStockCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (movementId != null ? movementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrMovement))
        {
            return false;
        }
        WbCrMovement other = (WbCrMovement) object;
        if ((this.movementId == null && other.movementId != null) || (this.movementId != null && !this.movementId.equals(other.movementId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrMovement[ movementId=" + movementId + " ]";
    }
    
}
