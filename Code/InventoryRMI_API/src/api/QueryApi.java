/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import persistance_unit.Custom_PU;
import querys.WB_CR_ARTICLE_CLIENT;
import querys.WB_CR_ARTICLE_MOVEMENT;
import querys.WB_CR_SALES_CITY;

/**
 *
 * @author wason
 */
public class QueryApi extends UnicastRemoteObject implements contract.QueryContract
{

    private static final String ARTICLE_MOVEMENT_QUERY = "select a.article_name, sum(wb_cr_inventory_detail.article_ammount) as article_ammount\n"
            + "	, wb_cr_movement.movement_name  \n"
            + "	FROM wb_cr_article a inner join wb_cr_inventory_detail ON \n"
            + "	wb_cr_inventory_detail.article_id = a.article_id inner join wb_cr_inventory ON \n"
            + "	wb_cr_inventory.inventory_id = wb_cr_inventory_detail.inventory_id inner join wb_cr_movement ON \n"
            + "	wb_cr_movement.movement_id = wb_cr_inventory.movement_id\n"
            + "	GROUP BY wb_cr_movement.movement_id, a.article_name, wb_cr_movement.movement_name";

    private static final String SALES_CITY_QUERY = "select sum(a.ARTICLE_PRICE*b.DETAIL_AMOUNT) as spent_ammount,\n"
            + " a.ARTICLE_NAME, d.CITY_NAME  from\n"
            + " WB_CR_ARTICLE a inner join WB_CR_BILLDETAIL b on a.ARTICLE_ID=b.ARTICLE_ID \n"
            + " inner join WB_CR_BILL c on b.BILL_ID=c.BILL_ID inner join WB_CR_CITY d on c.CITY_ID=d.CITY_ID\n"
            + " GROUP BY ARTICLE_NAME, CITY_NAME";

    private static final String CLIENT_ARTICLE_QUERY = "select a.CLIENT_NAME, d.ARTICLE_NAME, sum(c.DETAIL_AMOUNT*d.ARTICLE_PRICE) TOTAL_SOLD\n"
            + "	from WB_CR_CLIENT a inner join WB_CR_BILL b on a.CLIENT_ID=b.CLIENT_ID\n"
            + "	inner join WB_CR_BILLDETAIL c on b.BILL_ID = c.BILL_ID inner join WB_CR_ARTICLE d \n"
            + "	on c.ARTICLE_ID=d.ARTICLE_ID group by CLIENT_NAME, ARTICLE_NAME";

    public QueryApi() throws RemoteException
    {
        super();
        Custom_PU.setDB("projectDB");
    }

    @Override
    public ArrayList<WB_CR_ARTICLE_MOVEMENT> getArticleByMovement() throws RemoteException
    {
        ArrayList<WB_CR_ARTICLE_MOVEMENT> object_list = new ArrayList<>();
        try
        {
            Custom_PU.GetQueryObjectList(new WB_CR_ARTICLE_MOVEMENT(), ARTICLE_MOVEMENT_QUERY).forEach(x ->
            {
                WB_CR_ARTICLE_MOVEMENT obj = (WB_CR_ARTICLE_MOVEMENT) x;
                obj.setState("PERSISTED");
                object_list.add(obj);
            });
        } catch (IllegalAccessException | InstantiationException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return object_list;
    }

    @Override
    public ArrayList<WB_CR_SALES_CITY> getSalesByCity() throws RemoteException
    {
        ArrayList<WB_CR_SALES_CITY> object_list = new ArrayList<>();
        try
        {
            Custom_PU.GetQueryObjectList(new WB_CR_SALES_CITY(), SALES_CITY_QUERY).forEach(x ->
            {
                WB_CR_SALES_CITY obj = (WB_CR_SALES_CITY) x;
                obj.setState("PERSISTED");
                object_list.add(obj);
            });
        } catch (IllegalAccessException | InstantiationException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return object_list;
    }

    @Override
    public ArrayList<WB_CR_ARTICLE_CLIENT> getArticleSalesByClient() throws RemoteException
    {
        ArrayList<WB_CR_ARTICLE_CLIENT> object_list = new ArrayList<>();
        try
        {
            Custom_PU.GetQueryObjectList(new WB_CR_ARTICLE_CLIENT(), CLIENT_ARTICLE_QUERY).forEach((Object x) ->
            {
                WB_CR_ARTICLE_CLIENT obj = (WB_CR_ARTICLE_CLIENT) x;
                obj.setState("PERSISTED");
                object_list.add(obj);
            });
        } catch (IllegalAccessException | InstantiationException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return object_list;
    }
}
