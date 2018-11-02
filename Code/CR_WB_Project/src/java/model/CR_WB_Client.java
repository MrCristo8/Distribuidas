/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import persistance_unit.RelatedColumn;

/**
 *
 * @author wason
 */
public class CR_WB_Client
{
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
    
    
}
