/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author csrm1
 */
public class ArticleByMovement {
    private String articleName, movement;
    private Integer quantity;

    public ArticleByMovement(String articleName, String movement, Integer quantity) {
        this.articleName = articleName;
        this.movement = movement;
        this.quantity = quantity;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
