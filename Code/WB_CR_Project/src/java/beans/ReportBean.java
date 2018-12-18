/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.Report;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ArticleByClient;
import model.ArticleByMovement;
import model.ClientReport;
import model.SalesPerCity;

/**
 * @author csrm1
 */
@ManagedBean()
@SessionScoped
public class ReportBean implements Serializable {

    private Map<String, String> options;
    private String option;
    private List<SalesPerCity> sales;
    private List<ArticleByMovement> ammount;
    private List<ArticleByClient> clientSales;
    private List<ClientReport> articlesByClient;

    @PostConstruct
    public void init() {
        if (options == null) {
            options = new LinkedHashMap<>();
        }
        if (option == null) {
            option = new String();
        }
        if (options.isEmpty()) {
            options.put("Balance per article", "Balance per article");
            options.put("Sales per city", "Sales per city");
            options.put("Article by movement", "Article by movement");
            options.put("Article by client", "Article by client");
        }
        if (articlesByClient == null) {
            articlesByClient = new ArrayList<>();
        }
        if (clientSales == null) {
            clientSales = new ArrayList<>();
        }
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setSales(List<SalesPerCity> sales) {
        this.sales = sales;
    }

    public List<SalesPerCity> getSales() {
        sales = Report.getInstance().getSalesPerCity();
        return sales;
    }

    public List<ArticleByMovement> getAmmount() {
        ammount = Report.getInstance().getArticleByMovement();
        return ammount;
    }

    public void setAmmount(List<ArticleByMovement> ammount) {
        this.ammount = ammount;
    }

    public List<ArticleByClient> getClientSales() {
        clientSales = Report.getInstance().getArticleByClient();
        return clientSales;
    }

    public void setClientSales(List<ArticleByClient> clientSales) {
        this.clientSales = clientSales;
    }

    public List<ClientReport> getArticlesByClient() {
        if (articlesByClient.isEmpty()) {
            articlesByClient = Report.getInstance().getClireports();
        }
        return articlesByClient;
    }

    public void setArticlesByClient(List<ClientReport> articlesByClient) {
        this.articlesByClient = articlesByClient;
    }

}
