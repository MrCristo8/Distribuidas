/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import persistance_unit.ManyToMany;
import persistance_unit.ManyToOne;
import persistance_unit.RelatedColumn;
import persistance_unit.TableID;

/**
 *
 * @author wason
 */
public class WB_CR_BILLDETAIL extends CR_WB_Model implements Serializable
{

    @TableID("bill_id")
    @RelatedColumn("bill_id")
    private Integer bill_id;
    @ManyToOne(Table = "WB_CR_ARTICLE", Column = "article_id")
    private WB_CR_ARTICLE article;
    @ManyToOne(Table = "WB_CR_BILL", Column = "bill_id")
    private WB_CR_BILL bill;
    @RelatedColumn("detail_amount")
    private Integer detail_ammount;
    @ManyToMany(FirstFK = "bill_id", SeccondFK = "article_id")
    @RelatedColumn("article_id")
    private Integer article_id;

    public Integer getBill_id()
    {
        return bill_id;
    }

    public void setBill_id(Integer bill_id)
    {
        this.bill_id = bill_id;
    }

    public WB_CR_ARTICLE getArticle()
    {
        return article;
    }

    public void setArticle(WB_CR_ARTICLE article)
    {
        this.article = article;
    }

    public WB_CR_BILL getBill()
    {
        return bill;
    }

    public void setBill(WB_CR_BILL bill)
    {
        this.bill = bill;
    }

    public Integer getDetail_ammount()
    {
        return detail_ammount;
    }

    public void setDetail_ammount(Integer detail_ammount)
    {
        this.detail_ammount = detail_ammount;
    }

    public Integer getArticle_id()
    {
        return article_id;
    }

    public void setArticle_id(Integer article_id)
    {
        this.article_id = article_id;
    }

    public WB_CR_BILLDETAIL(Integer bill_id, Integer article_id)
    {
        this.bill_id = bill_id;
        this.article_id = article_id;
    }

    public WB_CR_BILLDETAIL()
    {
        this.state = "PERSISTED";
    }

    public WB_CR_BILLDETAIL(Integer bill_id, Integer detail_ammount, Integer article_id, String state)
    {
        this.bill_id = bill_id;
        this.detail_ammount = detail_ammount;
        this.article_id = article_id;
        this.state = state;
    }

    @Override
    public int hashCode()
    {
        Integer hash = 7;
        hash = 47 * hash + this.bill_id;
        hash = 47 * hash + this.article_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final WB_CR_BILLDETAIL other = (WB_CR_BILLDETAIL) obj;
        if (this.bill_id != other.bill_id)
        {
            return false;
        }
        return this.article_id == other.article_id;
    }

    @Override
    public String toString()
    {
        return "WB_CR_BILLDETAIL{" + "article=" + article + ", bill=" + bill + ", detail_ammount=" + detail_ammount + '}';
    }

}
