/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.Objects;
import persistance_unit.ManyToOne;
import persistance_unit.RelatedColumn;
import persistance_unit.TableID;

/**
 *
 * @author wason
 */
public class CR_WB_Movement extends CR_WB_Model
{
    @TableID("MOVEMENT_ID")
    @RelatedColumn("MOVEMENT_ID")    
    private Integer movement_id;
    @RelatedColumn("ARTICLE_ID")
    private Integer article_id;
    @ManyToOne(Table="CR_WB_ARTICLE",Column="ARTICLE_ID")
    private CR_WB_Article article;
    @RelatedColumn("MOVEMENT_NAME")
    private String movement_name;
    @RelatedColumn("MOVEMENT_DATE")
    private Date movement_date;
    @RelatedColumn("MOVEMENT_AMMOUNT")
    private Integer movement_ammount;
    @RelatedColumn("MOVEMENT_DIRECTION")
    private String movement_direction;

    public Integer getMovement_id()
    {
        return movement_id;
    }

    public void setMovement_id(Integer movement_id)
    {
        this.movement_id = movement_id;
    }

    public Integer getArticle_id()
    {
        return article_id;
    }

    public void setArticle_id(Integer article_id)
    {
        this.article_id = article_id;
    }

    public CR_WB_Article getArticle()
    {
        return article;
    }

    public void setArticle(CR_WB_Article article)
    {
        this.article = article;
    }

    public String getMovement_name()
    {
        return movement_name;
    }

    public void setMovement_name(String movement_name)
    {
        this.movement_name = movement_name;
    }

    public Date getMovement_date()
    {
        return movement_date;
    }

    public void setMovement_date(Date movement_date)
    {
        this.movement_date = movement_date;
    }

    public Integer getMovement_ammount()
    {
        return movement_ammount;
    }

    public void setMovement_ammount(Integer movement_ammount)
    {
        this.movement_ammount = movement_ammount;
    }

    public String getMovement_direction()
    {
        return movement_direction;
    }

    public void setMovement_direction(String movement_direction)
    {
        this.movement_direction = movement_direction;
    }
    
    public CR_WB_Movement()
    {
        this.state = "PERSISTED";
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.movement_id);
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
        final CR_WB_Movement other = (CR_WB_Movement) obj;
        return Objects.equals(this.movement_id, other.movement_id);
    }

    public CR_WB_Movement(Integer movement_id)
    {
        this.movement_id = movement_id;
    }

    public CR_WB_Movement(Integer movement_id, Integer article_id, String movement_name, 
            Date movement_date, Integer movement_ammount, String movement_direction)
    {
        this.movement_id = movement_id;
        this.article_id = article_id;
        this.movement_name = movement_name;
        this.movement_date = movement_date;
        this.movement_ammount = movement_ammount;
        this.movement_direction = movement_direction;
    }

}
