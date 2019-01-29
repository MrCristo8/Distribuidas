/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querys;

import java.io.Serializable;
import java.util.Objects;
import model.CR_WB_Model;
import persistance_unit.RelatedColumn;

/**
 *
 * @author wason
 */
public class WB_CR_ARTICLE_MOVEMENT extends CR_WB_Model implements Serializable
{

    @RelatedColumn("article_name")
    private String article_name;
    @RelatedColumn("article_ammount")
    private Integer article_ammount;
    @RelatedColumn("movement_name")
    private String movement_name;

    public WB_CR_ARTICLE_MOVEMENT()
    {
        this.state = "PERSISTED";
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public Integer getArticle_ammount() {
        return article_ammount;
    }

    public void setArticle_ammount(Integer article_ammount) {
        this.article_ammount = article_ammount;
    }

    public String getMovement_name() {
        return movement_name;
    }

    public void setMovement_name(String movement_name) {
        this.movement_name = movement_name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.article_name);
        hash = 29 * hash + Objects.hashCode(this.article_ammount);
        hash = 29 * hash + Objects.hashCode(this.movement_name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WB_CR_ARTICLE_MOVEMENT other = (WB_CR_ARTICLE_MOVEMENT) obj;
        if (!Objects.equals(this.article_name, other.article_name)) {
            return false;
        }
        if (!Objects.equals(this.movement_name, other.movement_name)) {
            return false;
        }
        if (!Objects.equals(this.article_ammount, other.article_ammount)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return "WB_CR_ARTICLE_MOVEMENT{" + "article_name=" + article_name + ", article_ammount=" + article_ammount + ", movement_name=" + movement_name + '}';
    }

}
