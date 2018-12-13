/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import controller.ObjectDAO;

/**
 *
 * @author wason
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable
{

    /**
     * Creates a new instance of UserBean
     */
    private String userName;
    private String userPwd;

    public UserBean()
    {

    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPwd()
    {
        return userPwd;
    }

    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }
    
    public void login()
    {
        controller.ObjectDAO persistance_obj = new ObjectDAO();
        System.out.println(persistance_obj.getAllObjects(new model.WbCrUser(), "WbCrUser").size());
    }

}
