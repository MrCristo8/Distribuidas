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
public class WB_CR_USER extends CR_WB_Model implements Serializable
{

    @TableID("user_id")
    @RelatedColumn("user_id")
    private int user_id;
    @RelatedColumn("user_name")
    private String user_name;
    @RelatedColumn("user_password")
    private String user_password;
    @RelatedColumn("user_permission")
    private String user_permission;

    public WB_CR_USER(String user_name, String user_password)
    {
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public WB_CR_USER()
    {
        this.state = "PERSISTED";
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }

    public String getUser_password()
    {
        return user_password;
    }

    public void setUser_password(String user_password)
    {
        this.user_password = user_password;
    }

    public String getUser_permission()
    {
        return user_permission;
    }

    public void setUser_permission(String user_permission)
    {
        this.user_permission = user_permission;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.user_name);
        hash = 61 * hash + Objects.hashCode(this.user_password);
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
        final WB_CR_USER other = (WB_CR_USER) obj;
        if (!Objects.equals(this.user_name, other.user_name))
        {
            return false;
        }
        return Objects.equals(this.user_password, other.user_password);
    }

    @Override
    public String toString()
    {
        return "WB_CR_USER{" + "user_id=" + user_id + ", user_name=" + user_name + ", user_password=" + user_password + ", user_permission=" + user_permission + '}';
    }

}
