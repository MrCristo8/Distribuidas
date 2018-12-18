/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "WB_CR_USER")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WbCrUser.findAll", query = "SELECT w FROM WbCrUser w")
    , @NamedQuery(name = "WbCrUser.findByUserId", query = "SELECT w FROM WbCrUser w WHERE w.userId = :userId")
    , @NamedQuery(name = "WbCrUser.findByUserName", query = "SELECT w FROM WbCrUser w WHERE w.userName = :userName")
    , @NamedQuery(name = "WbCrUser.findByUserPassword", query = "SELECT w FROM WbCrUser w WHERE w.userPassword = :userPassword")
    , @NamedQuery(name = "WbCrUser.findByUserPermission", query = "SELECT w FROM WbCrUser w WHERE w.userPermission = :userPermission")
})
public class WbCrUser implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;
    @Size(max = 50)
    @Column(name = "USER_NAME")
    private String userName;
    @Size(max = 60)
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Size(max = 200)
    @Column(name = "USER_PERMISSION")
    private String userPermission;

    public WbCrUser()
    {
    }

    public WbCrUser(Integer userId, String userName, String userPassword, String permissions)
    {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPermission = permissions;
    }

    
    public WbCrUser(String userName, String userPassword)
    {
        this.userName = userName;
        this.userPassword = userPassword;
    }
    
    

    public WbCrUser(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getUserPermission()
    {
        return userPermission;
    }

    public void setUserPermission(String userPermission)
    {
        this.userPermission = userPermission;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.userName);
        hash = 97 * hash + Objects.hashCode(this.userPassword);
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
        final WbCrUser other = (WbCrUser) obj;
        if (!Objects.equals(this.userName, other.userName))
        {
            return false;
        }
        if (!Objects.equals(this.userPassword, other.userPassword))
        {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString()
    {
        return "model.WbCrUser[ userId=" + userId + " ]";
    }
    
}
