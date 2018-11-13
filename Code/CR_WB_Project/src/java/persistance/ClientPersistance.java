/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import model.CR_WB_Client;
import persistance_unit.Custom_PU;

/**
 *
 * @author wason
 */
public class ClientPersistance implements Persistance<CR_WB_Client>
{

    private final ArrayList<CR_WB_Client> clientList;
    private static ClientPersistance uniqueInstance;
    private static final String TABLE_NAME = "CR_WB_CLIENT";

    public static ClientPersistance getInstnace()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new ClientPersistance();
        }
        return uniqueInstance;
    }

    public ClientPersistance()
    {
        clientList = new ArrayList<>();
    }

    @Override
    public ArrayList<CR_WB_Client> getObjectList()
    {
        return clientList;
    }

    @Override
    public String LoadObjects()
    {
        String msg = "";
        try
        {
            Custom_PU.GetObjList(new CR_WB_Client(), TABLE_NAME).forEach(x
                    -> clientList.add((CR_WB_Client) x));
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
            clientList.forEach((CR_WB_Client x) ->
            {
                try
                {
                    if (x.getState().equals("UPDATED"))
                    {
                        Custom_PU.UpdateObject(x, TABLE_NAME);

                    } else if (x.getState().equals("DELETED"))
                    {
                        Custom_PU.DeleteObject(x, TABLE_NAME);
                    }
                    else if (x.getState().equals("CREATED"))
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
