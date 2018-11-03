/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import model.CR_WB_Client;
import persistance_unit.Custom_PU;

/**
 *
 * @author wason
 */
public class ClientPersistance {

    private final ArrayList<CR_WB_Client> clientList;
    private static ClientPersistance uniqueInstance;
    private static final String TABLE_NAME = "CR_WB_CLIENT";

    public static ClientPersistance getInstnace() {
        if (uniqueInstance == null) {
            uniqueInstance = new ClientPersistance();
        }
        return uniqueInstance;
    }

    public ClientPersistance() {
        clientList = new ArrayList<>();
    }

    public ArrayList<CR_WB_Client> getClientList() {
        return clientList;
    }

    public String LoadArticles() {
        String msg = "";
        try {
            Custom_PU.GetObjList(new CR_WB_Client(), TABLE_NAME).forEach(x
                    -> clientList.add((CR_WB_Client) x));
        } catch (IllegalAccessException | InstantiationException | SQLException e) {
            msg = e.getMessage();
        }
        return msg;
    }
}
