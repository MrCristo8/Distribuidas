/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import model.WB_CR_BILLDETAIL;
import model.WB_CR_USER;

/**
 *
 * @author wason
 */
public class TempArrays {

    private static TempArrays uniqueInstance;

    private ArrayList<model.WB_CR_BILLDETAIL> tempBillDetailArr;
    private WB_CR_USER user;

    private TempArrays() {
        tempBillDetailArr = new ArrayList<>();
        user = new WB_CR_USER();
    }

    public static TempArrays getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TempArrays();
        }
        return uniqueInstance;
    }

    public ArrayList<WB_CR_BILLDETAIL> getTempBillDetailArr() {
        return tempBillDetailArr;
    }

    public WB_CR_USER getUser() {
        return user;
    }

    public void setUser(WB_CR_USER user) {
        this.user = user;
    }
    
}
