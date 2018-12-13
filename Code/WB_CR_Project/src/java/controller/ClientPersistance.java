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
import model.WbCrClient;

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

    public String deleteObject( int id)
    {
        return new ObjectDAO().deleteObject(new WbCrClient(), id);
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

    public String updateObject(model.WbCrClient updated_record)
    {
        String msg = "OK";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        model.WbCrClient obj_in = em1.find(model.WbCrClient.class, updated_record.getClientId());
        try
        {
            em1.getTransaction().begin();
            obj_in.setClientId(updated_record.getClientId());
            obj_in.setClientDni(updated_record.getClientDni());
            obj_in.setClientName(updated_record.getClientName());
            obj_in.setClientAddress(updated_record.getClientAddress());
            em1.getTransaction().commit();
            msg = "OK";

        } catch (Exception ex)
        {
            msg = ex.toString();
            if(em1.getTransaction().isActive())
                em1.getTransaction().rollback();
        }
        em1.close();
        factory.close();
        return msg;
    }
}
