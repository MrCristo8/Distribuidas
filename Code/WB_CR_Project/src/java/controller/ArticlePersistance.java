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
public class ArticlePersistance
{
    private static ArticlePersistance uniqueInstance;
    
    private ArticlePersistance(){}
    
    public static ArticlePersistance getInstance()
    {
        if(uniqueInstance==null)        
            uniqueInstance= new ArticlePersistance();                    
        return uniqueInstance;
    }
    
    public String persistObject(model.WbCrArticle obj_in)
    {
        return new ObjectDAO().persistObject(obj_in);
    }
    public String deleteObject(int id)
    {
        return new ObjectDAO().deleteObject(new model.WbCrArticle(), id);
    }
    public ArrayList<model.WbCrArticle> getAll()
    {
        ArrayList<model.WbCrArticle> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrArticle(), "WbCrArticle").forEach(x ->
        {
            obj_arr.add((model.WbCrArticle) x);
        });
        return obj_arr;
    }    
    public String updateObject(model.WbCrArticle updated_record)
    {
        String msg = "OK";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        model.WbCrArticle obj_in = em1.find(model.WbCrArticle.class, updated_record.getArticleId());
        try
        {
            em1.getTransaction().begin();
            obj_in.setArticleId(updated_record.getArticleId());
            obj_in.setArticleName(updated_record.getArticleName());
            obj_in.setArticlePrice(updated_record.getArticlePrice());
            obj_in.setArticleStock(updated_record.getArticleStock());
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
