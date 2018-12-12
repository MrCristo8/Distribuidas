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
import managedbeans.WbCrClientFacade;
import model.WbCrClient;

/**
 *
 * @author wason
 */
public class WbCrClientController
{

    public WbCrClientController()
    {
        pagingInfo = new PagingInfo();
        converter = new WbCrClientConverter();
    }
    private WbCrClient wbCrClient = null;
    private List<WbCrClient> wbCrClientItems = null;
    private WbCrClientFacade jpaController = null;
    private WbCrClientConverter converter = null;
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

    public WbCrClientFacade getJpaController()
    {
        if (jpaController == null)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (WbCrClientFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrClientJpa");
        }
        return jpaController;
    }

    public SelectItem[] getWbCrClientItemsAvailableSelectMany()
    {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getWbCrClientItemsAvailableSelectOne()
    {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public WbCrClient getWbCrClient()
    {
        if (wbCrClient == null)
        {
            wbCrClient = (WbCrClient) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrClient", converter, null);
        }
        if (wbCrClient == null)
        {
            wbCrClient = new WbCrClient();
        }
        return wbCrClient;
    }

    public String listSetup()
    {
        reset(true);
        return "wbCrClient_list";
    }

    public String createSetup()
    {
        reset(false);
        wbCrClient = new WbCrClient();
        return "wbCrClient_create";
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
            getJpaController().create(wbCrClient);
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
                JsfUtil.addSuccessMessage("WbCrClient was successfully created.");
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
        return scalarSetup("wbCrClient_detail");
    }

    public String editSetup()
    {
        return scalarSetup("wbCrClient_edit");
    }

    private String scalarSetup(String destination)
    {
        reset(false);
        wbCrClient = (WbCrClient) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrClient", converter, null);
        if (wbCrClient == null)
        {
            String requestWbCrClientString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrClient");
            JsfUtil.addErrorMessage("The wbCrClient with id " + requestWbCrClientString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit()
    {
        String wbCrClientString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrClient);
        String currentWbCrClientString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrClient");
        if (wbCrClientString == null || wbCrClientString.length() == 0 || !wbCrClientString.equals(currentWbCrClientString))
        {
            String outcome = editSetup();
            if ("wbCrClient_edit".equals(outcome))
            {
                JsfUtil.addErrorMessage("Could not edit wbCrClient. Try again.");
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
            getJpaController().edit(wbCrClient);
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
                JsfUtil.addSuccessMessage("WbCrClient was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrClient");
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
                JsfUtil.addSuccessMessage("WbCrClient was successfully deleted.");
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

    public List<WbCrClient> getWbCrClientItems()
    {
        if (wbCrClientItems == null)
        {
            getPagingInfo();
            wbCrClientItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return wbCrClientItems;
    }

    public String next()
    {
        reset(false);
        getPagingInfo().nextPage();
        return "wbCrClient_list";
    }

    public String prev()
    {
        reset(false);
        getPagingInfo().previousPage();
        return "wbCrClient_list";
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
        wbCrClient = null;
        wbCrClientItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem)
        {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value)
    {
        WbCrClient newWbCrClient = new WbCrClient();
        String newWbCrClientString = converter.getAsString(FacesContext.getCurrentInstance(), null, newWbCrClient);
        String wbCrClientString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrClient);
        if (!newWbCrClientString.equals(wbCrClientString))
        {
            createSetup();
        }
    }

    public Converter getConverter()
    {
        return converter;
    }
    
}
