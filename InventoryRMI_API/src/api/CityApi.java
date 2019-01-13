/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import model.WB_CR_CITY;
import persistance_unit.Custom_PU;

/**
 *
 * @author wason
 */
public class CityApi extends UnicastRemoteObject implements contract.Contract<WB_CR_CITY>
{

    private static final String TABLE_NAME = "WB_CR_CITY";

    public CityApi() throws RemoteException
    {
        super();
        Custom_PU.setDB("projectDB");
    }

    @Override
    public ArrayList<WB_CR_CITY> getObjectList() throws RemoteException
    {
        ArrayList<WB_CR_CITY> objectList = new ArrayList<>();
        try
        {
            Custom_PU.GetObjList(new WB_CR_CITY(), TABLE_NAME).forEach(x ->
            {
                WB_CR_CITY obj = (WB_CR_CITY) x;
                obj.setState("PERSISTED");
                objectList.add(obj);
            });
        } catch (IllegalAccessException | InstantiationException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return objectList;
    }

    @Override
    public String UpdateOnDatabase(ArrayList<WB_CR_CITY> updating_list) throws RemoteException
    {
        try
        {
            updating_list.forEach((WB_CR_CITY x) ->
            {
                try
                {
                    switch (x.getState())
                    {
                        case "UPDATED":
                            Custom_PU.UpdateObject(x, TABLE_NAME);
                            break;
                        case "DELETED":
                            Custom_PU.DeleteObject(x, TABLE_NAME);
                            break;
                        case "CREATED":
                            Custom_PU.PersistObject(x, TABLE_NAME);
                            break;
                        default:
                            break;
                    }
                } catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            });

        } catch (Exception e)
        {
            return e.getMessage();
        }
        return "Success";
    }

}
