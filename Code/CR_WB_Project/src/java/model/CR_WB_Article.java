/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import persistance_unit.RelatedColumn;

/**
 *
 * @author wason
 */
public class CR_WB_Article
{

    @RelatedColumn("ARTICLE_ID")
    private int article_id;
    @RelatedColumn("ARTICLE_NAME")
    private String article_name;
    @RelatedColumn("ARTICLE_PRICE")
    private float article_price;
    @RelatedColumn("ARTICLE_STOCK")
    private int article_stock;
    private String state;

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

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

}
