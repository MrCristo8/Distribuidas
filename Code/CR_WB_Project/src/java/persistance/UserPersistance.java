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
public class UserPersistance implements Persistance<CR_WB_User>
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

    @Override
    public ArrayList<CR_WB_User> getObjectList()
    {
        return userList;
    }

    @Override
    public String LoadObjects()
    {
        userList = new ArrayList<>();
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

    @Override
    public String UpdateOnDatabase()
    {
        try
        {
            userList.forEach((CR_WB_User x) ->
            {
                try
                {
                    if (x.getState().equals("UPDATED"))
                    {
                        Custom_PU.UpdateObject(x, TABLE_NAME);

                    } else if (x.getState().equals("DELETED"))
                    {
                        Custom_PU.DeleteObject(x, TABLE_NAME);
                    } else if (x.getState().equals("CREATED"))
                    {
                        Custom_PU.PersistObject(x, TABLE_NAME);
                    }
                } catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            });

        } catch (Exception e)
        {
        }
        return "Success";
    }
}
