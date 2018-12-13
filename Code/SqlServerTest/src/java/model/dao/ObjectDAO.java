/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wason
 */
public class ObjectDAO implements DAOIface<Object, Serializable>
{

    @Override
    public String persistObject(Object entity)
    {
        String msg;        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SqlServerTestPU");
        EntityManager em1 = factory.createEntityManager();
        try
        {
            em1.getTransaction().begin();
            em1.persist(entity);
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

    @Override
    public String deleteObject(Object entity, Object id)
    {
        String msg;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SqlServerTestPU");
        EntityManager em1 = factory.createEntityManager();
        Object obj_in = em1.find(entity.getClass(), id);
        try
        {
            em1.getTransaction().begin();
            em1.remove(obj_in);
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

    
    @Override
    public List<?> getAllObjects(Object entity, String query)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SqlServerTestPU");
        EntityManager em1 = factory.createEntityManager();
        List<?> result_list = em1.createNamedQuery(query+".findAll", entity.getClass()).getResultList();
        em1.close();
        factory.close();
        return result_list;
    }

    @Override
    public Object findObject(Object entity, Object id)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SqlServerTestPU");
        EntityManager em1 = factory.createEntityManager();
        try
        {
            return em1.find(entity.getClass(), id);

        } catch (Exception ex)
        {
            System.out.println(ex);
            em1.getTransaction().rollback();
        }
        em1.close();
        factory.close();
        return new Object();
    }

}
