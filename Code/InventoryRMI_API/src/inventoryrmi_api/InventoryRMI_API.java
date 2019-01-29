/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryrmi_api;

import api.ArticleApi;
import api.BillApi;
import api.BillDetailApi;
import api.CityApi;
import api.ClientApi;
import api.InventoryApi;
import api.InventoryDetailApi;
import api.MessageApi;
import api.MovementApi;
import api.QueryApi;
import api.UserApi;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author wason
 */
public class InventoryRMI_API
{

    private static final String IP = "localhost";

    public InventoryRMI_API() throws RemoteException
    {
        try
        {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("rmi://" + IP + ":1099/ArticleAPI", new ArticleApi());
            reg.rebind("rmi://" + IP + ":1099/CityAPI", new CityApi());
            reg.rebind("rmi://" + IP + ":1099/ClientAPI", new ClientApi());
            reg.rebind("rmi://" + IP + ":1099/MovementAPI", new MovementApi());
            reg.rebind("rmi://" + IP + ":1099/BillAPI", new BillApi());
            reg.rebind("rmi://" + IP + ":1099/InventoryAPI", new InventoryApi());
            reg.rebind("rmi://" + IP + ":1099/InventoryDetailAPI", new InventoryDetailApi());
            reg.rebind("rmi://" + IP + ":1099/BillDetailAPI", new BillDetailApi());
            reg.rebind("rmi://" + IP + ":1099/UserAPI", new UserApi());
            reg.rebind("rmi://" + IP + ":1099/QueryAPI", new QueryApi());
            reg.rebind("rmi://" + IP + ":1099/MessagesAPI", new MessageApi());
            System.out.println("Active Server");
        } catch (RemoteException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        try
        {
            InventoryRMI_API inventoryRMI_API = new InventoryRMI_API();

        } catch (RemoteException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
