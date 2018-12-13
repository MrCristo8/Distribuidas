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
public class MovementPersistance
{
    private static MovementPersistance uniqueInstance;

    private MovementPersistance()
    {
    }

    public static MovementPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new MovementPersistance();
        }
        return uniqueInstance;
    }

    public String persistObject(model.WbCrMovement obj_in)
    {
        return new ObjectDAO().persistObject(obj_in);
    }

    public String deleteObject(model.WbCrMovement obj_in, int id)
    {
        return new ObjectDAO().deleteObject(obj_in, id);
    }

    public ArrayList<model.WbCrMovement> getAll()
    {
        ArrayList<model.WbCrMovement> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrMovement(), "WbCrMovement").forEach(x ->
        {
            obj_arr.add((model.WbCrMovement) x);
        });
        return obj_arr;
    }
}
