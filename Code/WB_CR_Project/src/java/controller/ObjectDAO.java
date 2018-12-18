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
        em1.close();
        factory.close();
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
        String sql = "SELECT city.CITY_NAME, SUM(dollarsSold) AS dollarsSold "
                + "FROM(SELECT SUM(article.article_price*detail.DETAIL_AMOUNT) AS dollarsSold "
                + "FROM WB_CR_ARTICLE article,WB_CR_BILLDETAIL detail "
                + "WHERE article.ARTICLE_ID=detail.ARTICLE_ID GROUP BY article.ARTICLE_ID) AS dollarsSold, WB_CR_CITY city, WB_CR_BILLDETAIL detail "
                + "WHERE city.CITY_ID=detail.CITY_ID GROUP BY city.CITY_NAME;";
        Query query = em1.createNativeQuery(sql);
        List resultList = query.getResultList();
        List<SalesPerCity> sales =new ArrayList<>();
        for (Object result : resultList) {
            Object[] field = (Object[]) result;
            sales.add(new SalesPerCity(field[0].toString(), Double.parseDouble(field[1].toString())));
        }
        return sales;
    }

    public List<ArticleByClient> getArticleByClient() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WB_CR_ProjectPU");
        EntityManager em1 = factory.createEntityManager();
        String sql = "SELECT client.CLIENT_NAME, article.ARTICLE_NAME, SUM(detail.DETAIL_AMOUNT*article.ARTICLE_PRICE) as DollarsSold "
                + "FROM WB_CR_CLIENT client, WB_CR_BILLDETAIL detail, WB_CR_ARTICLE article "
                + "WHERE client.CLIENT_ID=detail.CLIENT_ID AND detail.ARTICLE_ID=article.ARTICLE_ID GROUP BY CLIENT_NAME, ARTICLE_NAME;";
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
        String sql = "SELECT article.ARTICLE_NAME, mov.MOVEMENT_NAME, COUNT(mov.MOVEMENT_NAME) AS Quantity "
                + "FROM WB_CR_ARTICLE article, WB_CR_STOCK stock , WB_CR_MOVEMENT mov "
                + "WHERE article.ARTICLE_ID=stock.ARTICLE_ID AND stock.MOVEMENT_ID=mov.MOVEMENT_ID GROUP BY ARTICLE_NAME, MOVEMENT_NAME;";
        Query query = em1.createNativeQuery(sql);
        List resultList = query.getResultList();
        List<ArticleByMovement> ammount = new ArrayList<>();
        for (Object result : resultList) {
            Object[] field = (Object[]) result;
            ammount.add(new ArticleByMovement(field[0].toString(), field[1].toString(), Integer.parseInt(field[2].toString())));
        }
        return ammount;
    }
}
