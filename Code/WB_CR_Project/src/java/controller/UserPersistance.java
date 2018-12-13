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
    
    public String updateObject(model.WbCrUser updated_record)
    {
        String msg = "OK";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        model.WbCrUser obj_in = em1.find(model.WbCrUser.class, updated_record.getUserId());
        try
        {
            em1.getTransaction().begin();
            obj_in.setUserId(updated_record.getUserId());
            obj_in.setUserName(updated_record.getUserName());
            obj_in.setUserPassword(updated_record.getUserPassword());            
            obj_in.setUserPermission(updated_record.getUserPermission());     
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
