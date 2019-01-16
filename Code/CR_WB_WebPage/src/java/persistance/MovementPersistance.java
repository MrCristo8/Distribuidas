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
public class MovementPersistance
{

    private static MovementPersistance uniqueInstance;
    private Contract<model.WB_CR_MOVEMENT> iface;
    private ArrayList<model.WB_CR_MOVEMENT> object_list;

    private MovementPersistance()
    {
        try
        {
            object_list = new ArrayList<>();
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            iface = (Contract) reg.lookup("rmi://localhost:1099/MovementAPI");
        } catch (RemoteException | NotBoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static MovementPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new MovementPersistance();
        }
        return uniqueInstance;
    }

    public ArrayList<model.WB_CR_MOVEMENT> getObjectList()
    {
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