/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
    private int bill_id;
    @ManyToOne(Table = "WB_CR_ARTICLE", Column = "article_id")
    private WB_CR_ARTICLE article;
    @ManyToOne(Table = "WB_CR_BILL", Column = "bill_id")
    private WB_CR_BILL bill;
    @RelatedColumn("detail_ammount")
    private int detail_ammount;
    @RelatedColumn("article_id")
    private int article_id;

    public int getBill_id()
    {
        return bill_id;
    }

    public void setBill_id(int bill_id)
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

    public int getDetail_ammount()
    {
        return detail_ammount;
    }

    public void setDetail_ammount(int detail_ammount)
    {
        this.detail_ammount = detail_ammount;
    }

    public int getArticle_id()
    {
        return article_id;
    }

    public void setArticle_id(int article_id)
    {
        this.article_id = article_id;
    }

    public WB_CR_BILLDETAIL(int bill_id, int article_id)
    {
        this.bill_id = bill_id;
        this.article_id = article_id;
    }

    public WB_CR_BILLDETAIL()
    {
        this.state = "PERSISTED";
    }

    public WB_CR_BILLDETAIL(int bill_id, int detail_ammount, int article_id, String state)
    {
        this.bill_id = bill_id;
        this.detail_ammount = detail_ammount;
        this.article_id = article_id;
        this.state = state;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
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
