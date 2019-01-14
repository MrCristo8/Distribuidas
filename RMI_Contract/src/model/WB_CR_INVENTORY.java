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
public class WB_CR_INVENTORY extends CR_WB_Model implements Serializable
{

    @TableID("inventory_id")
    @RelatedColumn("inventory_id")
    private int inventory_id;
    @RelatedColumn("movement_id")
    private int movement_id;
    @RelatedColumn("inventory_date")
    private Date inventory_date;
    @ManyToOne(Table = "WB_CR_MOVEMENT", Column = "movement_id")
    private WB_CR_MOVEMENT movement;

    public WB_CR_INVENTORY()
    {
        this.state = "PERSISTED";
    }

    public WB_CR_INVENTORY(int inventory_id)
    {
        this.inventory_id = inventory_id;
    }

    public WB_CR_INVENTORY(int inventory_id, int movement_id, Date inventory_date, String state)
    {
        this.inventory_id = inventory_id;
        this.movement_id = movement_id;
        this.inventory_date = inventory_date;
        this.state = state;
    }

    public int getInventory_id()
    {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id)
    {
        this.inventory_id = inventory_id;
    }

    public int getMovement_id()
    {
        return movement_id;
    }

    public void setMovement_id(int movement_id)
    {
        this.movement_id = movement_id;
    }

    public Date getInventory_date()
    {
        return inventory_date;
    }

    public void setInventory_date(Date inventory_date)
    {
        this.inventory_date = inventory_date;
    }

    public WB_CR_MOVEMENT getMovement()
    {
        return movement;
    }

    public void setMovement(WB_CR_MOVEMENT movement)
    {
        this.movement = movement;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 89 * hash + this.inventory_id;
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
        final WB_CR_INVENTORY other = (WB_CR_INVENTORY) obj;
        return this.inventory_id == other.inventory_id;
    }

    @Override
    public String toString()
    {
        return "WB_CR_INVENTORY{" + "inventory_id=" + inventory_id + ", inventory_date=" + inventory_date + ", movement=" + movement + '}';
    }

}
