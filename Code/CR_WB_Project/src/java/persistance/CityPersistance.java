/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import model.CR_WB_City;
import persistance_unit.Custom_PU;

/**
 *
 * @author wason
 */
public class CityPersistance implements Persistance<CR_WB_City>
{

    private ArrayList<CR_WB_City> cityList;
    private static CityPersistance uniqueInstance;
    private static final String TABLE_NAME = "CR_WB_CITY";

    public static CityPersistance getInstnace()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new CityPersistance();
        }
        return uniqueInstance;
    }

    public CityPersistance()
    {
        cityList = new ArrayList<>();
    }

    @Override
    public ArrayList<CR_WB_City> getObjectList()
    {
        return cityList;
    }

    @Override
    public String LoadObjects()
    {
        cityList = new ArrayList<>();
        String msg = "";
        try
        {
            Custom_PU.GetObjList(new CR_WB_City(), TABLE_NAME).forEach(x
                    -> cityList.add((CR_WB_City) x));
        } catch (IllegalAccessException | InstantiationException | SQLException e)
        {
            msg = e.getMessage();
        }
        return msg;
    }

    @Override
    public String UpdateOnDatabase()
    {
        try
        {
            cityList.forEach((CR_WB_City x) ->
            {
                try
                {
                    switch (x.getState())
                    {
                        case "UPDATED":
                            Custom_PU.UpdateObject(x, TABLE_NAME);
                            break;
                        case "DELETED":
                            Custom_PU.DeleteObject(x, TABLE_NAME);
                            break;
                        case "CREATED":
                            Custom_PU.PersistObject(x, TABLE_NAME);
                            break;
                        default:
                            break;
                    }
                } catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            });

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "Success";
    }
}