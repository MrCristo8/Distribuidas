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
public class WB_CR_INVENTORY_DETAIL extends CR_WB_Model implements Serializable
{

    @TableID("inventory_id")
    @RelatedColumn("inventory_id")
    private int inventory_id;
    @RelatedColumn("article_id")
    private int article_id;
    @RelatedColumn("article_ammount")
    private int article_ammount;
    @ManyToOne(Table = "WB_CR_ARTICLE", Column = "article_id")
    private WB_CR_ARTICLE article;
    @ManyToOne(Table = "WB_CR_INVENTORY", Column = "inventory_id")
    private WB_CR_INVENTORY inventory;

    public WB_CR_INVENTORY_DETAIL()
    {
    }

    public WB_CR_INVENTORY_DETAIL(int inventory_id, int article_id, int article_ammount, String state)
    {
        this.inventory_id = inventory_id;
        this.article_id = article_id;
        this.article_ammount = article_ammount;
        this.state = state;
    }

    public WB_CR_INVENTORY_DETAIL(int inventory_id, int article_id)
    {
        this.inventory_id = inventory_id;
        this.article_id = article_id;
    }

    public int getInventory_id()
    {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id)
    {
        this.inventory_id = inventory_id;
    }

    public int getArticle_id()
    {
        return article_id;
    }

    public void setArticle_id(int article_id)
    {
        this.article_id = article_id;
    }

    public int getArticle_ammount()
    {
        return article_ammount;
    }

    public void setArticle_ammount(int article_ammount)
    {
        this.article_ammount = article_ammount;
    }

    public WB_CR_ARTICLE getArticle()
    {
        return article;
    }

    public void setArticle(WB_CR_ARTICLE article)
    {
        this.article = article;
    }

    public WB_CR_INVENTORY getInventory()
    {
        return inventory;
    }

    public void setInventory(WB_CR_INVENTORY inventory)
    {
        this.inventory = inventory;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 59 * hash + this.inventory_id;
        hash = 59 * hash + this.article_id;
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
        final WB_CR_INVENTORY_DETAIL other = (WB_CR_INVENTORY_DETAIL) obj;
        if (this.inventory_id != other.inventory_id)
        {
            return false;
        }
        return this.article_id == other.article_id;
    }

    @Override
    public String toString()
    {
        return "WB_CR_INVENTORY_DETAIL{" + "article_ammount=" + article_ammount + ", article=" + article + ", inventory=" + inventory + '}';
    }

}
