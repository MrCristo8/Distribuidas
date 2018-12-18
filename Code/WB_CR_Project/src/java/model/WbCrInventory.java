/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "WB_CR_INVENTORY")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WbCrInventory.findAll", query = "SELECT w FROM WbCrInventory w")
    , @NamedQuery(name = "WbCrInventory.findByInventoryDate", query = "SELECT w FROM WbCrInventory w WHERE w.inventoryDate = :inventoryDate")
    , @NamedQuery(name = "WbCrInventory.findByInventoryAmmount", query = "SELECT w FROM WbCrInventory w WHERE w.inventoryAmmount = :inventoryAmmount")
    , @NamedQuery(name = "WbCrInventory.findByInventoryId", query = "SELECT w FROM WbCrInventory w WHERE w.inventoryId = :inventoryId")
})
public class WbCrInventory implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Column(name = "INVENTORY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inventoryDate;
    @Column(name = "INVENTORY_AMMOUNT")
    private Integer inventoryAmmount;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "INVENTORY_ID")
    private Integer inventoryId;
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID")
    @ManyToOne(optional = false)
    private WbCrArticle articleId;
    @JoinColumn(name = "MOVEMENT_ID", referencedColumnName = "MOVEMENT_ID")
    @ManyToOne(optional = false)
    private WbCrMovement movementId;

    public WbCrInventory()
    {
    }

    public WbCrInventory(Integer inventoryId)
    {
        this.inventoryId = inventoryId;
    }

    public Date getInventoryDate()
    {
        return inventoryDate;
    }

    public void setInventoryDate(Date inventoryDate)
    {
        this.inventoryDate = inventoryDate;
    }

    public Integer getInventoryAmmount()
    {
        return inventoryAmmount;
    }

    public void setInventoryAmmount(Integer inventoryAmmount)
    {
        this.inventoryAmmount = inventoryAmmount;
    }

    public Integer getInventoryId()
    {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId)
    {
        this.inventoryId = inventoryId;
    }

    public WbCrArticle getArticleId()
    {
        return articleId;
    }

    public void setArticleId(WbCrArticle articleId)
    {
        this.articleId = articleId;
    }

    public WbCrMovement getMovementId()
    {
        return movementId;
    }

    public void setMovementId(WbCrMovement movementId)
    {
        this.movementId = movementId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (inventoryId != null ? inventoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrInventory))
        {
            return false;
        }
        WbCrInventory other = (WbCrInventory) object;
        if ((this.inventoryId == null && other.inventoryId != null) || (this.inventoryId != null && !this.inventoryId.equals(other.inventoryId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrInventory[ inventoryId=" + inventoryId + " ]";
    }
    
}
