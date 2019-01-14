/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryrmi_api;

import api.ArticleApi;
import api.CityApi;
import api.ClientApi;
import api.MovementApi;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author wason
 */
public class InventoryRMI_API
{

    public InventoryRMI_API() throws RemoteException
    {
        try
        {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("rmi://localhost:1099/ArticleAPI", new ArticleApi());
            reg.rebind("rmi://localhost:1099/CityAPI", new CityApi());
            reg.rebind("rmi://localhost:1099/ClientAPI", new ClientApi());
            reg.rebind("rmi://localhost:1099/MovementAPI", new MovementApi());
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