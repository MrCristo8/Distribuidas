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
 * @author wason
 */
@Entity
@Table(name = "WB_CR_STOCK")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WbCrStock.findAll", query = "SELECT w FROM WbCrStock w")
    , @NamedQuery(name = "WbCrStock.findByArticleId", query = "SELECT w FROM WbCrStock w WHERE w.wbCrStockPK.articleId = :articleId")
    , @NamedQuery(name = "WbCrStock.findByMovementId", query = "SELECT w FROM WbCrStock w WHERE w.wbCrStockPK.movementId = :movementId")
    , @NamedQuery(name = "WbCrStock.findByStrockNumber", query = "SELECT w FROM WbCrStock w WHERE w.strockNumber = :strockNumber")
    , @NamedQuery(name = "WbCrStock.findByStockDate", query = "SELECT w FROM WbCrStock w WHERE w.stockDate = :stockDate")
    , @NamedQuery(name = "WbCrStock.findByStock", query = "SELECT w FROM WbCrStock w WHERE w.stock = :stock")
})
public class WbCrStock implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WbCrStockPK wbCrStockPK;
    @Column(name = "STROCK_NUMBER")
    private Integer strockNumber;
    @Column(name = "STOCK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stockDate;
    @Column(name = "STOCK")
    private Integer stock;
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WbCrArticle wbCrArticle;
    @JoinColumn(name = "MOVEMENT_ID", referencedColumnName = "MOVEMENT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WbCrMovement wbCrMovement;

    public WbCrStock()
    {
    }

    public WbCrStock(WbCrStockPK wbCrStockPK)
    {
        this.wbCrStockPK = wbCrStockPK;
    }

    public WbCrStock(int articleId, int movementId)
    {
        this.wbCrStockPK = new WbCrStockPK(articleId, movementId);
    }

    public WbCrStockPK getWbCrStockPK()
    {
        return wbCrStockPK;
    }

    public void setWbCrStockPK(WbCrStockPK wbCrStockPK)
    {
        this.wbCrStockPK = wbCrStockPK;
    }

    public Integer getStrockNumber()
    {
        return strockNumber;
    }

    public void setStrockNumber(Integer strockNumber)
    {
        this.strockNumber = strockNumber;
    }

    public Date getStockDate()
    {
        return stockDate;
    }

    public void setStockDate(Date stockDate)
    {
        this.stockDate = stockDate;
    }

    public Integer getStock()
    {
        return stock;
    }

    public void setStock(Integer stock)
    {
        this.stock = stock;
    }

    public WbCrArticle getWbCrArticle()
    {
        return wbCrArticle;
    }

    public void setWbCrArticle(WbCrArticle wbCrArticle)
    {
        this.wbCrArticle = wbCrArticle;
    }

    public WbCrMovement getWbCrMovement()
    {
        return wbCrMovement;
    }

    public void setWbCrMovement(WbCrMovement wbCrMovement)
    {
        this.wbCrMovement = wbCrMovement;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (wbCrStockPK != null ? wbCrStockPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrStock))
        {
            return false;
        }
        WbCrStock other = (WbCrStock) object;
        if ((this.wbCrStockPK == null && other.wbCrStockPK != null) || (this.wbCrStockPK != null && !this.wbCrStockPK.equals(other.wbCrStockPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.WbCrStock[ wbCrStockPK=" + wbCrStockPK + " ]";
    }
    
}
