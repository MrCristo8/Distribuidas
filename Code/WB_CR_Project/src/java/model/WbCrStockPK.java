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
public class WbCrStockPK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "ARTICLE_ID")
    private int articleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVEMENT_ID")
    private int movementId;

    public WbCrStockPK()
    {
    }

    public WbCrStockPK(int articleId, int movementId)
    {
        this.articleId = articleId;
        this.movementId = movementId;
    }

    public int getArticleId()
    {
        return articleId;
    }

    public void setArticleId(int articleId)
    {
        this.articleId = articleId;
    }

    public int getMovementId()
    {
        return movementId;
    }

    public void setMovementId(int movementId)
    {
        this.movementId = movementId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) articleId;
        hash += (int) movementId;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrStockPK))
        {
            return false;
        }
        WbCrStockPK other = (WbCrStockPK) object;
        if (this.articleId != other.articleId)
        {
            return false;
        }
        if (this.movementId != other.movementId)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrStockPK[ articleId=" + articleId + ", movementId=" + movementId + " ]";
    }
    
}
