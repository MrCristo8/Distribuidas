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
public class MovementPersistance
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

    public ArrayList<CR_WB_Movement> getMovementList()
    {
        return movementList;
    }

    public String LoadMovements()
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
}
