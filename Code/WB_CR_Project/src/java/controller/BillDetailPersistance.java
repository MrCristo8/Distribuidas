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
import model.WbCrBilldetail;
import model.WbCrBilldetailPK;

/**
 *
 * @author csrm1
 */
public class BillDetailPersistance {
    private static BillDetailPersistance uniqueInstance;

    private BillDetailPersistance()
    {
    }

    public static BillDetailPersistance getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new BillDetailPersistance();
        }
        return uniqueInstance;
    }

    public String persistObject(WbCrBilldetail obj_in)
    {
        return new ObjectDAO().persistObject(obj_in);
    }

    public String deleteObject( WbCrBilldetailPK id)
    {
        return new ObjectDAO().deleteObject(new WbCrBilldetail(), id);
    }

    public ArrayList<WbCrBilldetail> getAll()
    {
        ArrayList<WbCrBilldetail> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrCity(), "WbCrBilldetail").forEach(x ->
        {
            obj_arr.add((WbCrBilldetail) x);
        });
        return obj_arr;
    }

    public String updateObject(WbCrBilldetail updated_record)
    {
        String msg = "OK";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        WbCrBilldetail obj_in = em1.find(WbCrBilldetail.class, updated_record.getWbCrBilldetailPK());
        try
        {
            em1.getTransaction().begin();
            obj_in.setDetailAmount(updated_record.getDetailAmount());
            obj_in.setWbCrArticle(updated_record.getWbCrArticle());
            obj_in.setWbCrBill(updated_record.getWbCrBill());
            obj_in.setWbCrBilldetailPK(updated_record.getWbCrBilldetailPK());
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
