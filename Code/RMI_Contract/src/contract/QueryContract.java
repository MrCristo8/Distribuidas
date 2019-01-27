/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contract;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author wason
 */
public interface QueryContract extends Remote
{

    public ArrayList<querys.WB_CR_ARTICLE_MOVEMENT> getArticleByMovement() throws RemoteException;

    public ArrayList<querys.WB_CR_SALES_CITY> getSalesByCity() throws RemoteException;

    public ArrayList<querys.WB_CR_ARTICLE_CLIENT> getArticleSalesByClient() throws RemoteException;
}
