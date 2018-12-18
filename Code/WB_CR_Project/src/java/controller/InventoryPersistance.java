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
import model.WbCrInventory;

/**
 *
 * @author wason
 */
public class InventoryPersistance
{
    private static InventoryPersistance uniqueInstance;

    private InventoryPersistance()
    {
    }

    public static InventoryPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new InventoryPersistance();
        }
        return uniqueInstance;
    }

    public String persistObject(ArrayList<WbCrInventory> obj_in)
    {
        return new ObjectDAO().persistManyObject(obj_in);
    }

    public String deleteObject(int id)
    {
        return new ObjectDAO().deleteObject(new WbCrInventory(), id);
    }

    public ArrayList<WbCrInventory> getAll()
    {
        ArrayList<WbCrInventory> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrCity(), "WbCrInventory").forEach(x ->
        {
            obj_arr.add((WbCrInventory) x);
        });
        return obj_arr;
    }

    public String updateObject(WbCrInventory updated_record)
    {
        String msg = "OK";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        /*WbCrInventory obj_in = em1.find(WbCrInventory.class, updated_record.getCityId());
        try
        {
            em1.getTransaction().begin();
            obj_in.setBillId(updated_record.getBillId());
            obj_in.setBillDate(updated_record.getBillDate());
            obj_in.setCityId(updated_record.getCityId());
            obj_in.setClientId(updated_record.getClientId());
            em1.getTransaction().commit();
            msg = "OK";

        } catch (Exception ex)
        {
            msg = ex.toString();
            em1.getTransaction().rollback();
        }*/
        em1.close();
        factory.close();
        return msg;
    }
}
