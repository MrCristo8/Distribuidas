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
public class CR_WB_City extends CR_WB_Model
{
    @TableID("CITY_ID")
    @RelatedColumn("CITY_ID")
    private Integer city_id;
    @RelatedColumn("CITY_NAME")
    private String city_name;

    public Integer getCity_id()
    {
        return city_id;
    }

    public void setCity_id(Integer city_id)
    {
        this.city_id = city_id;
    }

    public String getCity_name()
    {
        return city_name;
    }

    public void setCity_name(String city_name)
    {
        this.city_name = city_name;
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
        final CR_WB_City other = (CR_WB_City) obj;
        return Objects.equals(this.city_id, other.city_id);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.city_id);
        return hash;
    }

    public CR_WB_City(Integer city_id, String city_name, String state)
    {
        this.city_id = city_id;
        this.city_name = city_name;
        this.state = state;
    }

    public CR_WB_City(Integer city_id)
    {
        this.city_id = city_id;
    }
    
    public CR_WB_City()
    {
        this.state="PERSISTED";
    }
    
}
