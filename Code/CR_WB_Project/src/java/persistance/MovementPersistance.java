/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import model.CR_WB_Movement;
import persistance_unit.Custom_PU;

/**
 *
 * @author wason
 */
public class MovementPersistance implements Persistance<CR_WB_Movement>
{

    private ArrayList<CR_WB_Movement> movementList;
    private static MovementPersistance uniqueInstance;
    private static final String TABLE_NAME = "CR_WB_MOVEMENT";

    public static MovementPersistance getInstnace()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new MovementPersistance();
        }
        return uniqueInstance;
    }

    public MovementPersistance()
    {
        movementList = new ArrayList<>();
    }

    @Override
    public ArrayList<CR_WB_Movement> getObjectList()
    {
        return movementList;
    }

    @Override
    public String LoadObjects()
    {
        String msg = "";
        try
        {
            Custom_PU.GetObjList(new CR_WB_Movement(), TABLE_NAME).forEach(x
                    -> movementList.add((CR_WB_Movement) x));
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
            movementList.forEach((CR_WB_Movement x) ->
            {
                try
                {
                    if (x.getState().equals("UPDATED"))
                    {
                        Custom_PU.UpdateObject(x, TABLE_NAME);

                    } else if (x.getState().equals("DELETED"))
                    {
                        Custom_PU.DeleteObject(x, TABLE_NAME);
                    }
                    else if (x.getState().equals("CREATED"))
                    {
                        Custom_PU.PersistObject(x, TABLE_NAME);
                    }
                } catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            });

        } catch (Exception e)
        {
        }
        return "Success";
    }
}
