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
import model.WB_CR_INVENTORY_DETAIL;
import persistance_unit.Custom_PU;

/**
 *
 * @author wason
 */
public class InventoryDetailApi extends UnicastRemoteObject implements contract.Contract<model.WB_CR_INVENTORY_DETAIL>
{

    private static final String TABLE_NAME = "WB_CR_INVENTORY_DETAIL";

    public InventoryDetailApi() throws RemoteException
    {
        super();
        Custom_PU.setDB("projectDB");
    }

    @Override
    public ArrayList<WB_CR_INVENTORY_DETAIL> getObjectList() throws RemoteException
    {
        ArrayList<model.WB_CR_INVENTORY_DETAIL> articleList = new ArrayList<>();
        try
        {
            Custom_PU.GetObjList(new WB_CR_INVENTORY_DETAIL(), TABLE_NAME).forEach(x ->
            {
                WB_CR_INVENTORY_DETAIL obj = (WB_CR_INVENTORY_DETAIL) x;
                obj.setState("PERSISTED");
                articleList.add(obj);
            });
        } catch (IllegalAccessException | InstantiationException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return articleList;
    }

    @Override
    public String UpdateOnDatabase(ArrayList<WB_CR_INVENTORY_DETAIL> updating_list) throws RemoteException
    {
        try
        {
            updating_list.forEach((WB_CR_INVENTORY_DETAIL x) ->
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
