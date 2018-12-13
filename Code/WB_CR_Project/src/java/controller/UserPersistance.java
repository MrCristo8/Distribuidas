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
public class UserPersistance
{
    private static UserPersistance uniqueInstance;

    private UserPersistance()
    {
    }

    public static UserPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new UserPersistance();
        }
        return uniqueInstance;
    }

    public String persistObject(model.WbCrUser obj_in)
    {
        return new ObjectDAO().persistObject(obj_in);
    }

    public String deleteObject(model.WbCrUser obj_in, int id)
    {
        return new ObjectDAO().deleteObject(obj_in, id);
    }

    public ArrayList<model.WbCrUser> getAll()
    {
        ArrayList<model.WbCrUser> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrUser(), "WbCrUser").forEach(x ->
        {
            obj_arr.add((model.WbCrUser) x);
        });
        return obj_arr;
    }
}
