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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wason
 */
public class ArticlePersistance
{

    private static ArticlePersistance uniqueInstance;
    private Contract<model.WB_CR_ARTICLE> iface;
    private ArrayList<model.WB_CR_ARTICLE> object_list;

    private ArticlePersistance()
    {
        try
        {
            object_list = new ArrayList<>();
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            iface = (Contract) reg.lookup("rmi://localhost:1099/ArticleAPI");
        } catch (RemoteException | NotBoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static ArticlePersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new ArticlePersistance();
        }
        return uniqueInstance;
    }

    public ArrayList<model.WB_CR_ARTICLE> getObjectList()
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
