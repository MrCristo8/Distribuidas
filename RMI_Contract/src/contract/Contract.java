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
public interface Contract<T> extends Remote
{

    public ArrayList<T> getObjectList() throws RemoteException;

    public String UpdateOnDatabase(ArrayList<T> updating_list) throws RemoteException;
}
