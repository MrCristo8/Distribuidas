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
public class WbCrBilldetailPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ARTICLE_ID")
    private int articleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_ID")
    private int billId;

    public WbCrBilldetailPK() {
    }

    public WbCrBilldetailPK(int articleId, int billId) {
        this.articleId = articleId;
        this.billId = billId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) articleId;
        hash += (int) billId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WbCrBilldetailPK)) {
            return false;
        }
        WbCrBilldetailPK other = (WbCrBilldetailPK) object;
        if (this.articleId != other.articleId) {
            return false;
        }
        if (this.billId != other.billId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.WbCrBilldetailPK[ articleId=" + articleId + ", billId=" + billId + " ]";
    }
    
}
