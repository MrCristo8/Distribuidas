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
public class CityPersistance
{
    private static CityPersistance uniqueInstance;

    private CityPersistance()
    {
    }

    public static CityPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new CityPersistance();
        }
        return uniqueInstance;
    }

    public String persistObject(model.WbCrCity obj_in)
    {
        return new ObjectDAO().persistObject(obj_in);
    }

    public String deleteObject(model.WbCrCity obj_in, int id)
    {
        return new ObjectDAO().deleteObject(obj_in, id);
    }

    public ArrayList<model.WbCrCity> getAll()
    {
        ArrayList<model.WbCrCity> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrCity(), "WbCrCity").forEach(x ->
        {
            obj_arr.add((model.WbCrCity) x);
        });
        return obj_arr;
    }
}
