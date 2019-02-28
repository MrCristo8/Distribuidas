/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;
import persistance_unit.RelatedColumn;
import persistance_unit.TableID;

/**
 *
 * @author wason
 */
public class CR_WB_Article extends CR_WB_Model
{
    @TableID("ARTICLE_ID")
    @RelatedColumn("ARTICLE_ID")
    private Integer article_id;
    @RelatedColumn("ARTICLE_NAME")
    private String article_name;
    @RelatedColumn("ARTICLE_PRICE")
    private Float article_price;
    @RelatedColumn("ARTICLE_STOCK")
    private Integer article_stock;   

    public int getArticle_id()
    {
        return article_id;
    }

    public void setArticle_id(int article_id)
    {
        this.article_id = article_id;
    }

    public String getArticle_name()
    {
        return article_name;
    }

    public void setArticle_name(String article_name)
    {
        this.article_name = article_name;
    }

    public float getArticle_price()
    {
        return article_price;
    }

    public void setArticle_price(float article_price)
    {
        this.article_price = article_price;
    }

    public int getArticle_stock()
    {
        return article_stock;
    }

    public void setArticle_stock(int article_stock)
    {
        this.article_stock = article_stock;
    }

    @Override
    public String toString()
    {
        return "CR_WB_Article{" + "article_id=" + article_id + ", article_name="
                + article_name + ", article_price=" + article_price + ", article_stock="
                + article_stock + ", state=" + state + '}';
    }

    public CR_WB_Article()
    {
        state = "PERSISTED";
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.article_id);
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
        final CR_WB_Article other = (CR_WB_Article) obj;
        return Objects.equals(this.article_id, other.article_id);
    }

    public CR_WB_Article(Integer article_id)
    {
        this.article_id = article_id;
    }

    public CR_WB_Article(Integer article_id, String article_name, Float article_price, Integer article_stock, String state)
    {
        this.article_id = article_id;
        this.article_name = article_name;
        this.article_price = article_price;
        this.article_stock = article_stock;
        this.state = state;
    }
}