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
    
    public String updateObject(model.WbCrMovement updated_record)
    {
        String msg = "OK";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        model.WbCrMovement obj_in = em1.find(model.WbCrMovement.class, updated_record.getMovementId());
        try
        {
            em1.getTransaction().begin();
            obj_in.setMovementId(updated_record.getMovementId());
            obj_in.setMovementName(updated_record.getMovementName());
            obj_in.setMovementDirection(updated_record.getMovementDirection());            
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
