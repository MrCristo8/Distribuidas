/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author csrm1
 */
@Entity
@Table(name = "WB_CR_INVENTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WbCrInventory.findAll", query = "SELECT w FROM WbCrInventory w")
    , @NamedQuery(name = "WbCrInventory.findByInventoryId", query = "SELECT w FROM WbCrInventory w WHERE w.wbCrInventoryPK.inventoryId = :inventoryId")
    , @NamedQuery(name = "WbCrInventory.findByMovementId", query = "SELECT w FROM WbCrInventory w WHERE w.wbCrInventoryPK.movementId = :movementId")
    , @NamedQuery(name = "WbCrInventory.findByArticleId", query = "SELECT w FROM WbCrInventory w WHERE w.wbCrInventoryPK.articleId = :articleId")
    , @NamedQuery(name = "WbCrInventory.findByInventoryDate", query = "SELECT w FROM WbCrInventory w WHERE w.inventoryDate = :inventoryDate")
    , @NamedQuery(name = "WbCrInventory.findByInventoryAmmount", query = "SELECT w FROM WbCrInventory w WHERE w.inventoryAmmount = :inventoryAmmount")})
public class WbCrInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WbCrInventoryPK wbCrInventoryPK;
    @Column(name = "INVENTORY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inventoryDate;
    @Column(name = "INVENTORY_AMMOUNT")
    private Integer inventoryAmmount;
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WbCrArticle wbCrArticle;
    @JoinColumn(name = "MOVEMENT_ID", referencedColumnName = "MOVEMENT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WbCrMovement wbCrMovement;

    public WbCrInventory() {
    }

    public WbCrInventory(WbCrInventoryPK wbCrInventoryPK) {
        this.wbCrInventoryPK = wbCrInventoryPK;
    }

    public WbCrInventory(int inventoryId, int movementId, int articleId) {
        this.wbCrInventoryPK = new WbCrInventoryPK(inventoryId, movementId, articleId);
    }

    public WbCrInventoryPK getWbCrInventoryPK() {
        return wbCrInventoryPK;
    }

    public void setWbCrInventoryPK(WbCrInventoryPK wbCrInventoryPK) {
        this.wbCrInventoryPK = wbCrInventoryPK;
    }

    public Date getInventoryDate() {
        return inventoryDate;
    }

    public void setInventoryDate(Date inventoryDate) {
        this.inventoryDate = inventoryDate;
    }

    public Integer getInventoryAmmount() {
        return inventoryAmmount;
    }

    public void setInventoryAmmount(Integer inventoryAmmount) {
        this.inventoryAmmount = inventoryAmmount;
    }

    public WbCrArticle getWbCrArticle() {
        return wbCrArticle;
    }

    public void setWbCrArticle(WbCrArticle wbCrArticle) {
        this.wbCrArticle = wbCrArticle;
    }

    public WbCrMovement getWbCrMovement() {
        return wbCrMovement;
    }

    public void setWbCrMovement(WbCrMovement wbCrMovement) {
        this.wbCrMovement = wbCrMovement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wbCrInventoryPK != null ? wbCrInventoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrInventory)) {
            return false;
        }
        WbCrInventory other = (WbCrInventory) object;
        if ((this.wbCrInventoryPK == null && other.wbCrInventoryPK != null) || (this.wbCrInventoryPK != null && !this.wbCrInventoryPK.equals(other.wbCrInventoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WbCrInventory[ wbCrInventoryPK=" + wbCrInventoryPK + " ]";
    }
    
}
