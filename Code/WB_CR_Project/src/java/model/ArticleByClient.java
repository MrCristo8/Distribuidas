/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author csrm1
 */
public class ArticleByClient {
    private String clientName, articleName;
    private Double dollarsSold;

    public ArticleByClient(String clientName, String articleName, Double dollarsSold) {
        this.clientName = clientName;
        this.articleName = articleName;
        this.dollarsSold = dollarsSold;
    }

    public ArticleByClient(String clientName, String articleName) {
        this.clientName = clientName;
        this.articleName = articleName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Double getDollarsSold() {
        return dollarsSold;
    }

    public void setDollarsSold(Double dollarsSold) {
        this.dollarsSold = dollarsSold;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.clientName);
        hash = 73 * hash + Objects.hashCode(this.articleName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArticleByClient other = (ArticleByClient) obj;
        if (!Objects.equals(this.clientName, other.clientName)) {
            return false;
        }
        if (!Objects.equals(this.articleName, other.articleName)) {
            return false;
        }
        return true;
    }
    
}
