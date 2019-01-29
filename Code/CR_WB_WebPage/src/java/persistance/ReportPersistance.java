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
 * @author csrm1
 */
public class ReportPersistance {

    private static ReportPersistance uniqueInstance;
    private Contract<querys.WB_CR_ARTICLE_CLIENT> articleClient_Iface;
    private Contract<querys.WB_CR_ARTICLE_MOVEMENT> articleMovement_Iface;
    private Contract<querys.WB_CR_SALES_CITY> salesCity_Iface;
    private ArrayList<querys.WB_CR_ARTICLE_MOVEMENT> articleMovement_List;
    private ArrayList<querys.WB_CR_SALES_CITY> salesCity_List;
    private ArrayList<querys.WB_CR_ARTICLE_CLIENT> articleClient_List;

    private ReportPersistance() {
        try {
            articleMovement_List = new ArrayList<>();
            salesCity_List = new ArrayList<>();
            articleClient_List = new ArrayList<>();
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            articleClient_Iface = (Contract) reg.lookup("rmi://localhost:1099/QueryAPI");
            articleMovement_Iface = (Contract) reg.lookup("rmi://localhost:1099/QueryAPI");
            salesCity_Iface = (Contract) reg.lookup("rmi://localhost:1099/QueryAPI");
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ReportPersistance getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ReportPersistance();
        }
        return uniqueInstance;
    }

    public ArrayList<querys.WB_CR_ARTICLE_CLIENT> getArticleClient() {
        if (articleClient_List.isEmpty()) {
            try {
                loadArticleClient();
            } catch (RemoteException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return articleClient_List;
    }

    public void loadArticleClient() throws RemoteException {
        articleClient_List = articleClient_Iface.getObjectList();
    }

    public ArrayList<querys.WB_CR_ARTICLE_MOVEMENT> getArticleMovement() {
        if (articleMovement_List.isEmpty()) {
            try {
                loadArticleMovement();
            } catch (RemoteException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return articleMovement_List;
    }

    public void loadArticleMovement() throws RemoteException {
        articleMovement_List = articleMovement_Iface.getObjectList();
    }

    public ArrayList<querys.WB_CR_SALES_CITY> getSalesCity() {
        if (salesCity_List.isEmpty()) {
            try {
                loadArticleClient();
            } catch (RemoteException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return salesCity_List;
    }

    public void loadSalesCity() throws RemoteException {
        salesCity_List = salesCity_Iface.getObjectList();
    }
}
