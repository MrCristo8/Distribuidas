/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.WbCrMovement;

/**
 *
 * @author wason
 */
@Stateless
public class WbCrMovementFacade extends AbstractFacade<WbCrMovement>
{

    @PersistenceContext(unitName = "CR_WB_SecondTermPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public WbCrMovementFacade()
    {
        super(WbCrMovement.class);
    }
    
}
