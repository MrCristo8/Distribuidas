/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author csrm1
 */
public class ClientReport {
    private String clientName;
    private HashMap<String,Double> atributes;

    public ClientReport() {
    }

    public ClientReport(String clientName, HashMap<String, Double> atributes) {
        this.clientName = clientName;
        this.atributes = atributes;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public HashMap<String, Double> getAtributes() {
        return atributes;
    }

    public void setAtributes(HashMap<String, Double> atributes) {
        this.atributes = atributes;
    }
}
