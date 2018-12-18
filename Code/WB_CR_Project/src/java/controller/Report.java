/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.ArticleByClient;
import model.ArticleByMovement;
import model.ClientReport;
import model.SalesPerCity;

/**
 *
 * @author csrm1
 */
public class Report {

    private static Report uniqueInstance;
    private List<ClientReport> clireports;
    private List<String> clients;
    private List<String> articles;

    public Report() {
        init();
    }
    
    public void init() {
        if (clireports == null) {
            clireports = new ArrayList<>();
        }
        if (clients == null) {
            clients = new ArrayList<>();
            clients.add("Aricles/Clients");
        }
        if (articles == null) {
            articles = new ArrayList<>();
        }
    }

    public static Report getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Report();
        }
        return uniqueInstance;
    }

    public List<SalesPerCity> getSalesPerCity() {
        return new ObjectDAO().getSalesPerCity();
    }

    public List<ArticleByMovement> getArticleByMovement() {
        return new ObjectDAO().getArticleByMovement();
    }

    public List<ArticleByClient> getArticleByClient() {
        return new ObjectDAO().getArticleByClient();
    }

    public static Report getUniqueInstance() {
        return uniqueInstance;
    }

    public static void setUniqueInstance(Report uniqueInstance) {
        Report.uniqueInstance = uniqueInstance;
    }

    public List<ClientReport> getClireports() {
        HashMap<String, Double> map;
        ArticleByClient compare;
        for (String client : getClients()) {
            map = new HashMap<>();
            for (String article : getArticles()) {
                compare = new ArticleByClient(client, article);
                if (getArticleByClient().contains(compare)) {
                    int id = getArticleByClient().indexOf(compare);
                    map.put(article, getArticleByClient().get(id).getDollarsSold());
                } else {
                    map.put(article, 0.0);
                }
            }
            clireports.add(new ClientReport(client, map));
        }
        return clireports;
    }

    public void setClireports(List<ClientReport> clireports) {
        this.clireports = clireports;
    }

    public List<String> getClients() {
        getArticleByClient().stream().filter((articleByClient) -> (!clients.contains(articleByClient.getClientName()))).forEachOrdered((articleByClient) -> {
            clients.add(articleByClient.getClientName());
        });
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }

    public List<String> getArticles() {
        getArticleByClient().stream().filter((articleByClient) -> (!articles.contains(articleByClient.getArticleName()))).forEachOrdered((articleByClient) -> {
            articles.add(articleByClient.getArticleName());
        });
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }

}
