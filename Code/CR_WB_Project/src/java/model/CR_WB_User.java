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
public class CR_WB_User extends CR_WB_Model
{

    @RelatedColumn("ID")
    @TableID("ID")
    private Integer user_id;
    @RelatedColumn("USER_NAME")
    private String user_name;
    @RelatedColumn("USER_PWD")
    private String user_pwd;

    public CR_WB_User()
    {
        this.state = "PERSISTED";
    }

    public Integer getUser_id()
    {
        return user_id;
    }

    public void setUser_id(Integer user_id)
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

    public String getUser_pwd()
    {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd)
    {
        this.user_pwd = user_pwd;
    }

    public CR_WB_User(Integer id, String user_name, String user_pwd, String state)
    {
        this.user_id = id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.state = state;
    }

    public CR_WB_User(String user_name, String user_pwd)
    {
        this.user_name = user_name;
        this.user_pwd = user_pwd;
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
        final CR_WB_User other = (CR_WB_User) obj;
        if (!Objects.equals(this.user_name, other.user_name))
        {
            return false;
        }
        return Objects.equals(this.user_pwd, other.user_pwd);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.user_name);
        hash = 83 * hash + Objects.hashCode(this.user_pwd);
        return hash;
    }

}
