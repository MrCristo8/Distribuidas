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
public class WB_CR_SALES_CITY extends CR_WB_Model implements Serializable
{

    @RelatedColumn("spent_ammount")
    private Float spent_ammount;

    @RelatedColumn("city_name")
    private String city_name;

    public WB_CR_SALES_CITY()
    {
        this.state = "PERSISTED";
    }

    public Float getSpent_ammount()
    {
        return spent_ammount;
    }

    public void setSpent_ammount(Float spent_ammount)
    {
        this.spent_ammount = spent_ammount;
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
    public int hashCode()
    {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.spent_ammount);
        hash = 31 * hash + Objects.hashCode(this.city_name);
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
        final WB_CR_SALES_CITY other = (WB_CR_SALES_CITY) obj;
        if (!Objects.equals(this.city_name, other.city_name))
        {
            return false;
        }
        return Objects.equals(this.spent_ammount, other.spent_ammount);
    }

    @Override
    public String toString()
    {
        return "WB_CR_SALES_CITY{" + "spent_ammount=" + spent_ammount + ", city_name=" + city_name + '}';
    }

}