/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import persistance_unit.RelatedColumn;
import persistance_unit.TableID;

/**
 *
 * @author wason
 */
public class WB_CR_CITY extends CR_WB_Model implements Serializable
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
        final WB_CR_CITY other = (WB_CR_CITY) obj;
        return Objects.equals(this.city_id, other.city_id);
    }

    @Override
    public int hashCode()
    {
        Integer hash = 7;
        hash = 47 * hash + Objects.hashCode(this.city_id);
        return hash;
    }

    public WB_CR_CITY(Integer city_id, String city_name, String state)
    {
        this.city_id = city_id;
        this.city_name = city_name;
        this.state = state;
    }

    public WB_CR_CITY(Integer city_id)
    {
        this.city_id = city_id;
    }

    public WB_CR_CITY()
    {
        this.state = "PERSISTED";
    }

    @Override
    public String toString()
    {
        return "WB_CR_CITY{" + "city_id=" + city_id + ", city_name=" + city_name + ", state=" + state + '}';
    }

}
