package beans;
// Generated Dec 13, 2018 12:47:24 AM by Hibernate Tools 4.3.1

import controller.UserPersistance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.WbCrUser;

/**
 * @author csrm1
 */
@ManagedBean()
@SessionScoped
public class UserBean implements java.io.Serializable {

    private String userName;
    private String userPassword;
    private String userPermission;
    private List<String> permission;
    private WbCrUser current;

    public UserBean() {
        current = new WbCrUser(0, "", "", "");
        permission=new ArrayList<>();
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

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    public WbCrUser getCurrent() {
        return current;
    }

    public void setCurrent(WbCrUser current) {
        this.current = current;
    }

    public void login() {
        List<WbCrUser> users = UserPersistance.getInstance().getAll();
        WbCrUser user = new WbCrUser(getUserName(), getUserPassword());
        if (users.contains(user)) {
            try {
                int id = users.indexOf(user);
                user = users.get(id);
                String[] dbPermission = user.getUserPermission().split(",");
                for (String dbpermission : dbPermission) {
                    if (!permission.contains(dbpermission)) {
                        permission.add(dbpermission);
                    }
                }
                Collections.sort(permission);
                FacesContext.getCurrentInstance().getExternalContext().redirect("layout.xhtml");
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
            System.out.println("NO");
        }
    }

    public void Add() {
        int id = 1;
        List<WbCrUser> users = UserPersistance.getInstance().getAll();
        for (WbCrUser user : users) {
            if (user.getUserId() != id) {
                continue;
            }
            id++;
        }
        current.setUserId(id);
        UserPersistance.getInstance().persistObject(current);
        current = new WbCrUser();
    }

    public void Delete() {
        List<WbCrUser> users = UserPersistance.getInstance().getAll();
        if (users.contains(current)) {
            int id = users.indexOf(current);
            id = users.get(id).getUserId();
            UserPersistance.getInstance().deleteObject(id);
            current = new WbCrUser();
        } else {
            System.out.println("NO");
        }
    }

    public void Update() {
        List<WbCrUser> users = UserPersistance.getInstance().getAll();
        WbCrUser user = new WbCrUser(current.getUserName(), current.getUserPassword());
        if (users.contains(user)) {
            int id = users.indexOf(user);
            id = users.get(id).getUserId();
            current.setUserId(id);
            UserPersistance.getInstance().updateObject(current);
            current = new WbCrUser();
        } else {
            System.out.println("NO");
        }
    }
}
