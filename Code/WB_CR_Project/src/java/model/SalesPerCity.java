/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author csrm1
 */
public class SalesPerCity {

    private String cityName;
    private Double dollarsSold;

    public SalesPerCity() {
    }

    public SalesPerCity(String cityName, Double dollarsSold) {
        this.cityName = cityName;
        this.dollarsSold = dollarsSold;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getDollarsSold() {
        return dollarsSold;
    }

    public void setDollarsSold(Double dollarsSold) {
        this.dollarsSold = dollarsSold;
    }
}
