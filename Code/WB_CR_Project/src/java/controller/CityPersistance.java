/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    public String updateObject(model.WbCrCity updated_record)
    {
        String msg = "OK";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        model.WbCrCity obj_in = em1.find(model.WbCrCity.class, updated_record.getCityId());
        try
        {
            em1.getTransaction().begin();
            obj_in.setCityId(updated_record.getCityId());
            obj_in.setCityName(updated_record.getCityName());
            em1.getTransaction().commit();
            msg = "OK";

        } catch (Exception ex)
        {
            msg = ex.toString();
            em1.getTransaction().rollback();
        }
        em1.close();
        factory.close();
        return msg;
    }
}
