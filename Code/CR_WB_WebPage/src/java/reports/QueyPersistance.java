/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import contract.QueryContract;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import querys.WB_CR_ARTICLE_CLIENT;
import querys.WB_CR_ARTICLE_MOVEMENT;
import querys.WB_CR_SALES_CITY;

/**
 *
 * @author wason
 */
public class QueyPersistance
{

    private static QueyPersistance uniqueInstance;
    private QueryContract iface;

    private QueyPersistance()
    {
        try
        {

            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            iface = (QueryContract) reg.lookup("rmi://localhost:1099/QueryAPI");
        } catch (RemoteException | NotBoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static QueyPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new QueyPersistance();
        }
        return uniqueInstance;
    }

    public ArrayList<WB_CR_ARTICLE_MOVEMENT> getArticleByMovement()
    {
        ArrayList<WB_CR_ARTICLE_MOVEMENT> objList = new ArrayList<>();
        try
        {
            objList = iface.getArticleByMovement();
        } catch (RemoteException ex)
        {
            System.out.println(ex.getMessage());
        }

        return objList;
    }

    public ArrayList<WB_CR_SALES_CITY> getSalesByCity()
    {
        ArrayList<WB_CR_SALES_CITY> objList = new ArrayList<>();
        try
        {
            objList = iface.getSalesByCity();
        } catch (RemoteException ex)
        {
            System.out.println(ex.getMessage());
        }

        return objList;
    }

    public ArrayList<WB_CR_ARTICLE_CLIENT> getArticleSalesByClient()
    {
        ArrayList<WB_CR_ARTICLE_CLIENT> objList = new ArrayList<>();
        try
        {
            objList = iface.getArticleSalesByClient();
        } catch (RemoteException ex)
        {
            System.out.println(ex.getMessage());
        }

        return objList;
    }

}
