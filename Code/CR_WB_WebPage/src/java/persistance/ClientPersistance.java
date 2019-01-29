/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import contract.Contract;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author wason
 */
public class ClientPersistance
{

    private static ClientPersistance uniqueInstance;
    private Contract<model.WB_CR_CLIENT> iface;
    private ArrayList<model.WB_CR_CLIENT> object_list;

    private ClientPersistance()
    {
        try
        {
            object_list = new ArrayList<>();
            Registry reg = LocateRegistry.getRegistry(util.Strings.IP, 1099);
            iface = (Contract) reg.lookup("rmi://" + util.Strings.IP + ":1099/ClientAPI");
        } catch (RemoteException | NotBoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static ClientPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new ClientPersistance();
        }
        return uniqueInstance;
    }

    public ArrayList<model.WB_CR_CLIENT> getObjectList()
    {
        if (object_list.isEmpty())
        {
            try
            {
                loadObjectList();
            } catch (RemoteException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return object_list;
    }

    public void loadObjectList() throws RemoteException
    {
        object_list = iface.getObjectList();
    }

    public void updateOnDatabase() throws RemoteException
    {
        System.out.println(iface.UpdateOnDatabase(object_list));
    }
}
