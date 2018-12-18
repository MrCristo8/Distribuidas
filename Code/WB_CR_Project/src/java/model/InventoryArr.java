/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author wason
 */
public class InventoryArr
{
    private WbCrArticle article;
    private WbCrMovement movement;
    private int ammount;

    public WbCrArticle getArticle()
    {
        return article;
    }

    public void setArticle(WbCrArticle article)
    {
        this.article = article;
    }

    public WbCrMovement getMovement()
    {
        return movement;
    }

    public void setMovement(WbCrMovement movement)
    {
        this.movement = movement;
    }

    public int getAmmount()
    {
        return ammount;
    }

    public void setAmmount(int ammount)
    {
        this.ammount = ammount;
    }

    public InventoryArr(WbCrArticle article, WbCrMovement movement, int ammount)
    {
        this.article = article;
        this.movement = movement;
        this.ammount = ammount;
    }
    
    
}
