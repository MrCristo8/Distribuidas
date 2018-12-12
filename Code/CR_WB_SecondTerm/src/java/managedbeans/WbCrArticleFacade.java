/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.WbCrArticle;

/**
 *
 * @author wason
 */
@Stateless
public class WbCrArticleFacade extends AbstractFacade<WbCrArticle>
{

    @PersistenceContext(unitName = "CR_WB_SecondTermPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public WbCrArticleFacade()
    {
        super(WbCrArticle.class);
    }
    
}
