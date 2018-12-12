/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import controller.util.JsfUtil;
import controller.util.PagingInfo;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import managedbeans.WbCrMovementFacade;
import model.WbCrMovement;

/**
 *
 * @author wason
 */
public class WbCrMovementController
{

    public WbCrMovementController()
    {
        pagingInfo = new PagingInfo();
        converter = new WbCrMovementConverter();
    }
    private WbCrMovement wbCrMovement = null;
    private List<WbCrMovement> wbCrMovementItems = null;
    private WbCrMovementFacade jpaController = null;
    private WbCrMovementConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "CR_WB_SecondTermPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo()
    {
        if (pagingInfo.getItemCount() == -1)
        {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public WbCrMovementFacade getJpaController()
    {
        if (jpaController == null)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (WbCrMovementFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrMovementJpa");
        }
        return jpaController;
    }

    public SelectItem[] getWbCrMovementItemsAvailableSelectMany()
    {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getWbCrMovementItemsAvailableSelectOne()
    {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public WbCrMovement getWbCrMovement()
    {
        if (wbCrMovement == null)
        {
            wbCrMovement = (WbCrMovement) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrMovement", converter, null);
        }
        if (wbCrMovement == null)
        {
            wbCrMovement = new WbCrMovement();
        }
        return wbCrMovement;
    }

    public String listSetup()
    {
        reset(true);
        return "wbCrMovement_list";
    }

    public String createSetup()
    {
        reset(false);
        wbCrMovement = new WbCrMovement();
        return "wbCrMovement_create";
    }

    public String create()
    {
        try
        {
            utx.begin();
        } catch (Exception ex)
        {
        }
        try
        {
            Exception transactionException = null;
            getJpaController().create(wbCrMovement);
            try
            {
                utx.commit();
            } catch (javax.transaction.RollbackException ex)
            {
                transactionException = ex;
            } catch (Exception ex)
            {
            }
            if (transactionException == null)
            {
                JsfUtil.addSuccessMessage("WbCrMovement was successfully created.");
            } else
            {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e)
        {
            try
            {
                utx.rollback();
            } catch (Exception ex)
            {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup()
    {
        return scalarSetup("wbCrMovement_detail");
    }

    public String editSetup()
    {
        return scalarSetup("wbCrMovement_edit");
    }

    private String scalarSetup(String destination)
    {
        reset(false);
        wbCrMovement = (WbCrMovement) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrMovement", converter, null);
        if (wbCrMovement == null)
        {
            String requestWbCrMovementString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrMovement");
            JsfUtil.addErrorMessage("The wbCrMovement with id " + requestWbCrMovementString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit()
    {
        String wbCrMovementString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrMovement);
        String currentWbCrMovementString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrMovement");
        if (wbCrMovementString == null || wbCrMovementString.length() == 0 || !wbCrMovementString.equals(currentWbCrMovementString))
        {
            String outcome = editSetup();
            if ("wbCrMovement_edit".equals(outcome))
            {
                JsfUtil.addErrorMessage("Could not edit wbCrMovement. Try again.");
            }
            return outcome;
        }
        try
        {
            utx.begin();
        } catch (Exception ex)
        {
        }
        try
        {
            Exception transactionException = null;
            getJpaController().edit(wbCrMovement);
            try
            {
                utx.commit();
            } catch (javax.transaction.RollbackException ex)
            {
                transactionException = ex;
            } catch (Exception ex)
            {
            }
            if (transactionException == null)
            {
                JsfUtil.addSuccessMessage("WbCrMovement was successfully updated.");
            } else
            {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e)
        {
            try
            {
                utx.rollback();
            } catch (Exception ex)
            {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove()
    {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrMovement");
        Integer id = new Integer(idAsString);
        try
        {
            utx.begin();
        } catch (Exception ex)
        {
        }
        try
        {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try
            {
                utx.commit();
            } catch (javax.transaction.RollbackException ex)
            {
                transactionException = ex;
            } catch (Exception ex)
            {
            }
            if (transactionException == null)
            {
                JsfUtil.addSuccessMessage("WbCrMovement was successfully deleted.");
            } else
            {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e)
        {
            try
            {
                utx.rollback();
            } catch (Exception ex)
            {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome()
    {
        String relatedControllerOutcome = relatedControllerOutcome();
        /*if ((ERROR))
        {
            return relatedControllerOutcome;
        }*/
        return listSetup();
    }

    public List<WbCrMovement> getWbCrMovementItems()
    {
        if (wbCrMovementItems == null)
        {
            getPagingInfo();
            wbCrMovementItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return wbCrMovementItems;
    }

    public String next()
    {
        reset(false);
        getPagingInfo().nextPage();
        return "wbCrMovement_list";
    }

    public String prev()
    {
        reset(false);
        getPagingInfo().previousPage();
        return "wbCrMovement_list";
    }

    private String relatedControllerOutcome()
    {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try
            {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e)
            {
                throw new FacesException(e);
            } catch (NoSuchMethodException e)
            {
                throw new FacesException(e);
            } catch (IllegalAccessException e)
            {
                throw new FacesException(e);
            } catch (InvocationTargetException e)
            {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem)
    {
        wbCrMovement = null;
        wbCrMovementItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem)
        {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value)
    {
        WbCrMovement newWbCrMovement = new WbCrMovement();
        String newWbCrMovementString = converter.getAsString(FacesContext.getCurrentInstance(), null, newWbCrMovement);
        String wbCrMovementString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrMovement);
        if (!newWbCrMovementString.equals(wbCrMovementString))
        {
            createSetup();
        }
    }

    public Converter getConverter()
    {
        return converter;
    }
    
}
