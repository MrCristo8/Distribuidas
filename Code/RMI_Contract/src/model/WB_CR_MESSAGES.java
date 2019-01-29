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
public class WB_CR_MESSAGES extends CR_WB_Model
{

    @TableID("message_id")
    @RelatedColumn("message_id")
    private Integer message_id;
    @RelatedColumn("message_from")
    private String message_from;
    @RelatedColumn("message_body")
    private String message_body;

    public WB_CR_MESSAGES(Integer message_id, String message_from, String message_body, String state)
    {
        this.message_id = message_id;
        this.message_from = message_from;
        this.message_body = message_body;
        this.state = state;
    }

    public WB_CR_MESSAGES()
    {
        this.state = "PERSISTED";
    }

    public Integer getMessage_id()
    {
        return message_id;
    }

    public void setMessage_id(Integer message_id)
    {
        this.message_id = message_id;
    }

    public String getMessage_from()
    {
        return message_from;
    }

    public void setMessage_from(String message_from)
    {
        this.message_from = message_from;
    }

    public String getMessage_body()
    {
        return message_body;
    }

    public void setMessage_body(String message_body)
    {
        this.message_body = message_body;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.message_id);
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
        final WB_CR_MESSAGES other = (WB_CR_MESSAGES) obj;
        return Objects.equals(this.message_id, other.message_id);
    }

    @Override
    public String toString()
    {
        return "WB_CR_MESSAGES{" + "message_id=" + message_id + ", message_from=" + message_from + ", message_body=" + message_body + '}';
    }

}
