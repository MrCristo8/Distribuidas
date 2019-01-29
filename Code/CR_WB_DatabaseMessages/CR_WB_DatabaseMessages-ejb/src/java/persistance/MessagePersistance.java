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
public class MessagePersistance
{

    private static MessagePersistance uniqueInstance;
    private Contract<model.WB_CR_MESSAGES> iface;
    private ArrayList<model.WB_CR_MESSAGES> object_list;

    private MessagePersistance()
    {
        try
        {
            object_list = new ArrayList<>();
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            iface = (Contract) reg.lookup("rmi://localhost:1099/MessagesAPI");
        } catch (RemoteException | NotBoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static MessagePersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new MessagePersistance();
        }
        return uniqueInstance;
    }

    public ArrayList<model.WB_CR_MESSAGES> getObjectList()
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
