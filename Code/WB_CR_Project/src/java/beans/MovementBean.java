/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author wason
 */
@Named(value = "movementBean")
@Dependent
public class MovementBean
{

    /**
     * Creates a new instance of MovementBean
     */
    public MovementBean()
    {
    }
    
}
