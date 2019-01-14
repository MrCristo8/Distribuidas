/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import persistance_unit.ManyToOne;
import persistance_unit.RelatedColumn;
import persistance_unit.TableID;

/**
 *
 * @author wason
 */
public class WB_CR_BILL extends CR_WB_Model implements Serializable
{

    @TableID("bill_id")
    @RelatedColumn("bill_id")
    private int bill_id;
    @RelatedColumn("bill_date")
    private Date bill_date;
    @ManyToOne(Table = "WB_CR_CLIENT", Column = "client_id")
    private WB_CR_CLIENT client;
    @ManyToOne(Table = "WB_CR_CITY", Column = "city_id")
    private WB_CR_CITY city;
    @RelatedColumn("client_id")
    private int client_id;
    @RelatedColumn("city_id")
    private int city_id;

    public WB_CR_BILL()
    {
        this.state = "PERSISTED";
    }

    public WB_CR_BILL(int bill_id)
    {
        this.bill_id = bill_id;
    }

    public int getBill_id()
    {
        return bill_id;
    }

    public void setBill_id(int bill_id)
    {
        this.bill_id = bill_id;
    }

    public Date getBill_date()
    {
        return bill_date;
    }

    public void setBill_date(Date bill_date)
    {
        this.bill_date = bill_date;
    }

    public WB_CR_CLIENT getClient()
    {
        return client;
    }

    public void setClient(WB_CR_CLIENT client)
    {
        this.client = client;
    }

    public WB_CR_CITY getCity()
    {
        return city;
    }

    public void setCity(WB_CR_CITY city)
    {
        this.city = city;
    }

    public int getClient_id()
    {
        return client_id;
    }

    public void setClient_id(int client_id)
    {
        this.client_id = client_id;
    }

    public int getCity_id()
    {
        return city_id;
    }

    public void setCity_id(int city_id)
    {
        this.city_id = city_id;
    }

    public WB_CR_BILL(int bill_id, Date bill_date, int client_id, int city_id, String state)
    {
        this.bill_id = bill_id;
        this.bill_date = bill_date;
        this.client_id = client_id;
        this.city_id = city_id;
        this.state = state;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + this.bill_id;
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
        final WB_CR_BILL other = (WB_CR_BILL) obj;
        return this.bill_id == other.bill_id;
    }

    @Override
    public String toString()
    {
        return "WB_CR_BILL{" + "bill_id=" + bill_id + ", bill_date=" + bill_date + ", client=" + client + ", city=" + city + '}';
    }

}
