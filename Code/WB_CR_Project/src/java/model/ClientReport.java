/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;

/**
 *
 * @author csrm1
 */
public class ClientReport {
    private String clientName;
    private Map<String,Double> atributes;

    public ClientReport() {
    }

    public ClientReport(String clientName, Map<String, Double> atributes) {
        this.clientName = clientName;
        this.atributes = atributes;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Map<String, Double> getAtributes() {
        return atributes;
    }

    public void setAtributes(Map<String, Double> atributes) {
        this.atributes = atributes;
    }
}
