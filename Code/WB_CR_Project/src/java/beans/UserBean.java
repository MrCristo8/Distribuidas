package beans;
// Generated Dec 13, 2018 12:47:24 AM by Hibernate Tools 4.3.1

import controller.UserPersistance;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.WbCrUser;

/**
 * UserBean generated by hbm2java
 */
@ManagedBean()
@SessionScoped
public class UserBean implements java.io.Serializable {

    private String userName;
    private String userPassword;
    private String userPermission;

    public UserBean() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPermission() {
        return this.userPermission;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public void login() {
        List<WbCrUser> users = UserPersistance.getInstance().getAll();
        WbCrUser user = new WbCrUser(getUserName(), getUserPassword());
        if (users.contains(user)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("city/city.xhtml");
            } catch (IOException ex) {
                System.out.println("Error: "+ex.getMessage());
            }
        } else {
            System.out.println("NO");
        }
    }
}
