/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import model.WB_CR_BILLDETAIL;

/**
 *
 * @author wason
 */
public class TempArrays
{

    private static TempArrays uniqueInstance;

    private ArrayList<model.WB_CR_BILLDETAIL> tempBillDetailArr;

    private TempArrays()
    {
        tempBillDetailArr = new ArrayList<>();
    }

    public static TempArrays getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new TempArrays();
        }
        return uniqueInstance;
    }

    public ArrayList<WB_CR_BILLDETAIL> getTempBillDetailArr()
    {
        return tempBillDetailArr;
    }

}
