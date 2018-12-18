/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "WB_CR_BILLDETAIL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WbCrBilldetail.findAll", query = "SELECT w FROM WbCrBilldetail w")
    , @NamedQuery(name = "WbCrBilldetail.findByArticleId", query = "SELECT w FROM WbCrBilldetail w WHERE w.wbCrBilldetailPK.articleId = :articleId")
    , @NamedQuery(name = "WbCrBilldetail.findByBillId", query = "SELECT w FROM WbCrBilldetail w WHERE w.wbCrBilldetailPK.billId = :billId")
    , @NamedQuery(name = "WbCrBilldetail.findByDetailAmount", query = "SELECT w FROM WbCrBilldetail w WHERE w.detailAmount = :detailAmount")
})
public class WbCrBilldetail implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WbCrBilldetailPK wbCrBilldetailPK;
    @Column(name = "DETAIL_AMOUNT")
    private Integer detailAmount;
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WbCrArticle wbCrArticle;
    @JoinColumn(name = "BILL_ID", referencedColumnName = "BILL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WbCrBill wbCrBill;

    public WbCrBilldetail()
    {
    }

    public WbCrBilldetail(WbCrBilldetailPK wbCrBilldetailPK)
    {
        this.wbCrBilldetailPK = wbCrBilldetailPK;
    }

    public WbCrBilldetail(int articleId, int billId)
    {
        this.wbCrBilldetailPK = new WbCrBilldetailPK(articleId, billId);
    }

    public WbCrBilldetail(WbCrBilldetailPK wbCrBilldetailPK, Integer detailAmount) {
        this.wbCrBilldetailPK = wbCrBilldetailPK;
        this.detailAmount = detailAmount;
    }

    public WbCrBilldetailPK getWbCrBilldetailPK() {
        return wbCrBilldetailPK;
    }

    public void setWbCrBilldetailPK(WbCrBilldetailPK wbCrBilldetailPK)
    {
        this.wbCrBilldetailPK = wbCrBilldetailPK;
    }

    public Integer getDetailAmount()
    {
        return detailAmount;
    }

    public void setDetailAmount(Integer detailAmount)
    {
        this.detailAmount = detailAmount;
    }

    public WbCrArticle getWbCrArticle()
    {
        return wbCrArticle;
    }

    public void setWbCrArticle(WbCrArticle wbCrArticle)
    {
        this.wbCrArticle = wbCrArticle;
    }

    public WbCrBill getWbCrBill()
    {
        return wbCrBill;
    }

    public void setWbCrBill(WbCrBill wbCrBill)
    {
        this.wbCrBill = wbCrBill;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (wbCrBilldetailPK != null ? wbCrBilldetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrBilldetail))
        {
            return false;
        }
        WbCrBilldetail other = (WbCrBilldetail) object;
        return !((this.wbCrBilldetailPK == null && other.wbCrBilldetailPK != null) || (this.wbCrBilldetailPK != null && !this.wbCrBilldetailPK.equals(other.wbCrBilldetailPK)));
    }

    @Override
    public String toString()
    {
        return "model.WbCrBilldetail[ wbCrBilldetailPK=" + wbCrBilldetailPK + " ]";
    }
    
}
