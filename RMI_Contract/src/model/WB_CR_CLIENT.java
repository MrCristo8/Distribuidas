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
public class WB_CR_CLIENT extends CR_WB_Model implements Serializable
{

    @TableID("CLIENT_ID")
    @RelatedColumn("CLIENT_ID")
    private Integer client_id;
    @RelatedColumn("CLIENT_DNI")
    private String client_dni;
    @RelatedColumn("CLIENT_NAME")
    private String client_name;
    @RelatedColumn("CLIENT_ADDRESS")
    private String client_address;

    public Integer getClient_id()
    {
        return client_id;
    }

    public void setClient_id(Integer client_id)
    {
        this.client_id = client_id;
    }

    public String getClient_dni()
    {
        return client_dni;
    }

    public void setClient_dni(String client_dni)
    {
        this.client_dni = client_dni;
    }

    public String getClient_name()
    {
        return client_name;
    }

    public void setClient_name(String client_name)
    {
        this.client_name = client_name;
    }

    public String getClient_address()
    {
        return client_address;
    }

    public void setClient_address(String client_address)
    {
        this.client_address = client_address;
    }

    public WB_CR_CLIENT(Integer client_id)
    {
        this.client_id = client_id;
    }

    public WB_CR_CLIENT(Integer client_id, String client_dni, String client_name, String client_address, String state)
    {
        this.client_id = client_id;
        this.client_dni = client_dni;
        this.client_name = client_name;
        this.client_address = client_address;
        this.state = state;
    }

    public WB_CR_CLIENT()
    {
        this.state = "PERSISTED";
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
        final WB_CR_CLIENT other = (WB_CR_CLIENT) obj;
        return Objects.equals(this.client_id, other.client_id);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.client_id);
        return hash;
    }

    @Override
    public String toString()
    {
        return "WB_CR_CLIENT{" + "client_id=" + client_id + ", client_dni=" + client_dni + ", client_name=" + client_name + ", client_address=" + client_address + '}';
    }

}
