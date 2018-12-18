/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.ArticleByClient;
import model.ArticleByMovement;
import model.SalesPerCity;

/**
 *
 * @author wason
 */
public class ObjectDAO implements DAOIface<Object, Serializable> {

    @Override
    public String persistObject(Object entity) {
        String msg;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        try {
            em1.getTransaction().begin();
            em1.persist(entity);
            em1.getTransaction().commit();
            msg = "OK";

        } catch (Exception ex) {
            msg = ex.toString();
            em1.getTransaction().rollback();
        }        
        return msg;
    }
    
    public String persistManyObject(ArrayList<?> toPersist) {
        String msg;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        try {
            em1.getTransaction().begin();
            toPersist.forEach(x -> {
                em1.persist(x);
            });
            em1.getTransaction().commit();
            msg = "OK";

        } catch (Exception ex) {
            msg = ex.toString();
            if(em1.getTransaction().isActive())
                em1.getTransaction().rollback();
        }        
        return msg;
    }
    

    @Override
    public String deleteObject(Object entity, Object id) {
        String msg;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        Object obj_in = em1.find(entity.getClass(), id);
        try {
            em1.getTransaction().begin();
            em1.remove(obj_in);
            em1.getTransaction().commit();
            msg = "OK";

        } catch (Exception ex) {
            msg = ex.toString();
            if (em1.getTransaction().isActive()) {
                em1.getTransaction().rollback();
            }
        }
        em1.close();
        factory.close();
        return msg;
    }

    @Override
    public List<?> getAllObjects(Object entity, String query) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        List<?> result_list = em1.createNamedQuery(query + ".findAll", entity.getClass()).getResultList();
        em1.close();
        factory.close();
        return result_list;
    }

    @Override
    public Object findObject(Object entity, Object id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        try {
            return em1.find(entity.getClass(), id);

        } catch (Exception ex) {
            System.out.println(ex);
            em1.getTransaction().rollback();
        }
        em1.close();
        factory.close();
        return new Object();
    }

    public List<SalesPerCity> getSalesPerCity() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        String sql = "select sum(a.ARTICLE_PRICE*b.DETAIL_AMOUNT) as AMMOUNT, a.ARTICLE_NAME, d.CITY_NAME "
                + "from WB_CR_ARTICLE a inner join WB_CR_BILLDETAIL b on a.ARTICLE_ID=b.ARTICLE_ID  "
                + "inner join WB_CR_BILL c on b.BILL_ID=c.BILL_ID inner join WB_CR_CITY d on c.CITY_ID=d.CITY_ID "
                + "GROUP BY ARTICLE_NAME, CITY_NAME;";
        Query query = em1.createNativeQuery(sql);
        List resultList = query.getResultList();
        List<SalesPerCity> sales = new ArrayList<>();
        for (Object result : resultList) {
            Object[] field = (Object[]) result;
            sales.add(new SalesPerCity(field[2].toString(), field[1].toString(), Double.parseDouble(field[0].toString())));
        }
        return sales;
    }

    public List<ArticleByClient> getArticleByClient() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        String sql = "select a.CLIENT_NAME, d.ARTICLE_NAME, sum(c.DETAIL_AMOUNT*d.ARTICLE_PRICE) TOTAL_SOLD from WB_CR_CLIENT a "
                + "inner join WB_CR_BILL b on a.CLIENT_ID=b.CLIENT_ID inner join WB_CR_BILLDETAIL c on b.BILL_ID = c.BILL_ID "
                + "inner join WB_CR_ARTICLE d on c.ARTICLE_ID=d.ARTICLE_ID group by CLIENT_NAME, ARTICLE_NAME;";
        Query query = em1.createNativeQuery(sql);
        List resultList = query.getResultList();
        List<ArticleByClient> clientSales = new ArrayList<>();
        for (Object result : resultList) {
            Object[] field = (Object[]) result;
            clientSales.add(new ArticleByClient(field[0].toString(), field[1].toString(), Double.parseDouble(field[2].toString())));
        }
        return clientSales;
    }

    public List<ArticleByMovement> getArticleByMovement() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        String sql = "select	a.ARTICLE_NAME,	COUNT(c.MOVEMENT_ID) as INVENTORY_COUNT, c.MOVEMENT_NAME "
                + "from WB_CR_ARTICLE a inner join WB_CR_INVENTORY b on a.ARTICLE_ID=b.ARTICLE_ID "
                + "inner join WB_CR_MOVEMENT c on b.MOVEMENT_ID = c.MOVEMENT_ID group by ARTICLE_NAME, MOVEMENT_NAME;";
        Query query = em1.createNativeQuery(sql);
        List resultList = query.getResultList();
        List<ArticleByMovement> ammount = new ArrayList<>();
        for (Object result : resultList) {
            Object[] field = (Object[]) result;
            ammount.add(new ArticleByMovement(field[0].toString(), field[2].toString(), Integer.parseInt(field[1].toString())));
        }
        return ammount;
    }

}
