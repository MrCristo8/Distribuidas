/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author wason
 */
public class ClientPersistance
{
    private static ClientPersistance uniqueInstance;

    private ClientPersistance()
    {
    }

    public static ClientPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new ClientPersistance();
        }
        return uniqueInstance;
    }

    public String persistObject(model.WbCrClient obj_in)
    {
        return new ObjectDAO().persistObject(obj_in);
    }

    public String deleteObject(model.WbCrClient obj_in, int id)
    {
        return new ObjectDAO().deleteObject(obj_in, id);
    }

    public ArrayList<model.WbCrClient> getAll()
    {
        ArrayList<model.WbCrClient> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrClient(), "WbCrClient").forEach(x ->
        {
            obj_arr.add((model.WbCrClient) x);
        });
        return obj_arr;
    }
}
