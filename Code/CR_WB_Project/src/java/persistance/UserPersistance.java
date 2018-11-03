/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import model.CR_WB_User;
import persistance_unit.Custom_PU;

/**
 *
 * @author wason
 */
public class UserPersistance
{
    private ArrayList<CR_WB_User> userList;
    private static UserPersistance uniqueInstance;
    private static final String TABLE_NAME = "CR_WB_USERS";

    public static UserPersistance getInstnace()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new UserPersistance();
        }
        return uniqueInstance;
    }

    public UserPersistance()
    {
        userList = new ArrayList<>();
    }

    public ArrayList<CR_WB_User> GetUserList()
    {
        return userList;
    }

    public String LoadUsers()
    {
        String msg = "";
        try
        {
            Custom_PU.GetObjList(new CR_WB_User(), TABLE_NAME).forEach(x
                    -> userList.add((CR_WB_User) x));
        } catch (IllegalAccessException | InstantiationException | SQLException e)
        {
            msg = e.getMessage();
        }
        return msg;
    }
}
