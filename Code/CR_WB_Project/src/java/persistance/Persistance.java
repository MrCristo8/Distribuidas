/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.util.ArrayList;

/**
 *
 * @author wason
 */
public interface Persistance <T> 
{

    public ArrayList<T> getObjectList();

    public String LoadObjects();

    public String UpdateOnDatabase();    
    
}
