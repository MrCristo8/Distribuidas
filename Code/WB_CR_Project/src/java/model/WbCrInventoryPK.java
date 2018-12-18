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
 * @author csrm1
 */
@Embeddable
public class WbCrInventoryPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "INVENTORY_ID")
    private int inventoryId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVEMENT_ID")
    private int movementId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARTICLE_ID")
    private int articleId;

    public WbCrInventoryPK() {
    }

    public WbCrInventoryPK(int inventoryId, int movementId, int articleId) {
        this.inventoryId = inventoryId;
        this.movementId = movementId;
        this.articleId = articleId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getMovementId() {
        return movementId;
    }

    public void setMovementId(int movementId) {
        this.movementId = movementId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) inventoryId;
        hash += (int) movementId;
        hash += (int) articleId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrInventoryPK)) {
            return false;
        }
        WbCrInventoryPK other = (WbCrInventoryPK) object;
        if (this.inventoryId != other.inventoryId) {
            return false;
        }
        if (this.movementId != other.movementId) {
            return false;
        }
        if (this.articleId != other.articleId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WbCrInventoryPK[ inventoryId=" + inventoryId + ", movementId=" + movementId + ", articleId=" + articleId + " ]";
    }
    
}
