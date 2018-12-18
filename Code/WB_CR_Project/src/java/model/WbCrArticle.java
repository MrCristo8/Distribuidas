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
 * @author csrm1
 */
@Entity
@Table(name = "WB_CR_ARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WbCrArticle.findAll", query = "SELECT w FROM WbCrArticle w")
    , @NamedQuery(name = "WbCrArticle.findByArticleId", query = "SELECT w FROM WbCrArticle w WHERE w.articleId = :articleId")
    , @NamedQuery(name = "WbCrArticle.findByArticleName", query = "SELECT w FROM WbCrArticle w WHERE w.articleName = :articleName")
    , @NamedQuery(name = "WbCrArticle.findByArticlePrice", query = "SELECT w FROM WbCrArticle w WHERE w.articlePrice = :articlePrice")
    , @NamedQuery(name = "WbCrArticle.findByArticleStock", query = "SELECT w FROM WbCrArticle w WHERE w.articleStock = :articleStock")})
public class WbCrArticle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARTICLE_ID")
    private Integer articleId;
    @Size(max = 50)
    @Column(name = "ARTICLE_NAME")
    private String articleName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ARTICLE_PRICE")
    private Double articlePrice;
    @Column(name = "ARTICLE_STOCK")
    private Integer articleStock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wbCrArticle")
    private Collection<WbCrBilldetail> wbCrBilldetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wbCrArticle")
    private Collection<WbCrInventory> wbCrInventoryCollection;

    public WbCrArticle() {
    }

    public WbCrArticle(Integer articleId, String articleName, Double articlePrice, Integer articleStock) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articlePrice = articlePrice;
        this.articleStock = articleStock;
    }

    public WbCrArticle(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Double getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(Double articlePrice) {
        this.articlePrice = articlePrice;
    }

    public Integer getArticleStock() {
        return articleStock;
    }

    public void setArticleStock(Integer articleStock) {
        this.articleStock = articleStock;
    }

    @XmlTransient
    public Collection<WbCrBilldetail> getWbCrBilldetailCollection() {
        return wbCrBilldetailCollection;
    }

    public void setWbCrBilldetailCollection(Collection<WbCrBilldetail> wbCrBilldetailCollection) {
        this.wbCrBilldetailCollection = wbCrBilldetailCollection;
    }

    @XmlTransient
    public Collection<WbCrInventory> getWbCrInventoryCollection() {
        return wbCrInventoryCollection;
    }

    public void setWbCrInventoryCollection(Collection<WbCrInventory> wbCrInventoryCollection) {
        this.wbCrInventoryCollection = wbCrInventoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleId != null ? articleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrArticle)) {
            return false;
        }
        WbCrArticle other = (WbCrArticle) object;
        if ((this.articleId == null && other.articleId != null) || (this.articleId != null && !this.articleId.equals(other.articleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WbCrArticle[ articleId=" + articleId + " ]";
    }
    
}
