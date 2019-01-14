/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import persistance_unit.RelatedColumn;
import persistance_unit.TableID;

/**
 *
 * @author wason
 */
public class WB_CR_MOVEMENT extends CR_WB_Model implements Serializable
{

    @TableID("MOVEMENT_ID")
    @RelatedColumn("MOVEMENT_ID")
    private int movement_id;
    @RelatedColumn("MOVEMENT_NAME")
    private String movement_name;
    @RelatedColumn("MOVEMENT_DIRECTION")
    private String movement_direction;

    public WB_CR_MOVEMENT()
    {
        this.state = "PERSISTED";
    }

    public WB_CR_MOVEMENT(int movement_id, String movement_name, String movement_direction, String state)
    {
        this.movement_id = movement_id;
        this.movement_name = movement_name;
        this.movement_direction = movement_direction;
        this.state = state;
    }

    public WB_CR_MOVEMENT(int movement_id)
    {
        this.movement_id = movement_id;
    }

    public int getMovement_id()
    {
        return movement_id;
    }

    public void setMovement_id(int movement_id)
    {
        this.movement_id = movement_id;
    }

    public String getMovement_name()
    {
        return movement_name;
    }

    public void setMovement_name(String movement_name)
    {
        this.movement_name = movement_name;
    }

    public String getMovement_direction()
    {
        return movement_direction;
    }

    public void setMovement_direction(String movement_direction)
    {
        this.movement_direction = movement_direction;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + this.movement_id;
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
        final WB_CR_MOVEMENT other = (WB_CR_MOVEMENT) obj;
        return this.movement_id == other.movement_id;
    }

    @Override
    public String toString()
    {
        return "WB_CR_MOVEMENT{" + "movement_id=" + movement_id + ", movement_name=" + movement_name + ", movement_direction=" + movement_direction
                + ", state=" + state + '}';
    }

}
