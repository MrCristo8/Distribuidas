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
import model.WbCrBill;

/**
 *
 * @author csrm1
 */
public class BillPersistance {
    private static BillPersistance uniqueInstance;

    private BillPersistance()
    {
    }

    public static BillPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new BillPersistance();
        }
        return uniqueInstance;
    }

    public String persistObject(WbCrBill obj_in)
    {
        return new ObjectDAO().persistObject(obj_in);
    }

    public String deleteObject( int id)
    {
        return new ObjectDAO().deleteObject(new WbCrBill(), id);
    }

    public ArrayList<WbCrBill> getAll()
    {
        ArrayList<WbCrBill> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrCity(), "WbCrBill").forEach(x ->
        {
            obj_arr.add((WbCrBill) x);
        });
        return obj_arr;
    }

    public String updateObject(WbCrBill updated_record)
    {
        String msg = "OK";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        WbCrBill obj_in = em1.find(WbCrBill.class, updated_record.getCityId());
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
        }
        em1.close();
        factory.close();
        return msg;
    }
}
