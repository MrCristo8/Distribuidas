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
public class WB_CR_ARTICLE_CLIENT extends CR_WB_Model implements Serializable
{

    @RelatedColumn("client_name")
    private String client_name;
    @RelatedColumn("article_name")
    private String article_name;
    @RelatedColumn("total_sold")
    private Float total_sold;

    public WB_CR_ARTICLE_CLIENT()
    {
        this.state = "PERSISTED";
    }

    public String getClient_name()
    {
        return client_name;
    }

    public void setClient_name(String client_name)
    {
        this.client_name = client_name;
    }

    public String getArticle_name()
    {
        return article_name;
    }

    public void setArticle_name(String article_name)
    {
        this.article_name = article_name;
    }

    public Float getTotal_sold()
    {
        return total_sold;
    }

    public void setTotal_sold(Float total_sold)
    {
        this.total_sold = total_sold;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.client_name);
        hash = 29 * hash + Objects.hashCode(this.article_name);
        hash = 29 * hash + Objects.hashCode(this.total_sold);
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
        final WB_CR_ARTICLE_CLIENT other = (WB_CR_ARTICLE_CLIENT) obj;
        if (!Objects.equals(this.client_name, other.client_name))
        {
            return false;
        }
        if (!Objects.equals(this.article_name, other.article_name))
        {
            return false;
        }
        return Objects.equals(this.total_sold, other.total_sold);
    }

    @Override
    public String toString()
    {
        return "WB_CR_ARTICLE_CLIENT{" + "client_name=" + client_name + ", article_name=" + article_name + ", total_sold=" + total_sold + '}';
    }

}
