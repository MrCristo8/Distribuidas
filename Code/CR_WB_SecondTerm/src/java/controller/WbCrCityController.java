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
import managedbeans.WbCrCityFacade;
import model.WbCrCity;

/**
 *
 * @author wason
 */
public class WbCrCityController
{

    public WbCrCityController()
    {
        pagingInfo = new PagingInfo();
        converter = new WbCrCityConverter();
    }
    private WbCrCity wbCrCity = null;
    private List<WbCrCity> wbCrCityItems = null;
    private WbCrCityFacade jpaController = null;
    private WbCrCityConverter converter = null;
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

    public WbCrCityFacade getJpaController()
    {
        if (jpaController == null)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (WbCrCityFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrCityJpa");
        }
        return jpaController;
    }

    public SelectItem[] getWbCrCityItemsAvailableSelectMany()
    {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getWbCrCityItemsAvailableSelectOne()
    {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public WbCrCity getWbCrCity()
    {
        if (wbCrCity == null)
        {
            wbCrCity = (WbCrCity) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrCity", converter, null);
        }
        if (wbCrCity == null)
        {
            wbCrCity = new WbCrCity();
        }
        return wbCrCity;
    }

    public String listSetup()
    {
        reset(true);
        return "wbCrCity_list";
    }

    public String createSetup()
    {
        reset(false);
        wbCrCity = new WbCrCity();
        return "wbCrCity_create";
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
            getJpaController().create(wbCrCity);
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
                JsfUtil.addSuccessMessage("WbCrCity was successfully created.");
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
        return scalarSetup("wbCrCity_detail");
    }

    public String editSetup()
    {
        return scalarSetup("wbCrCity_edit");
    }

    private String scalarSetup(String destination)
    {
        reset(false);
        wbCrCity = (WbCrCity) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrCity", converter, null);
        if (wbCrCity == null)
        {
            String requestWbCrCityString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrCity");
            JsfUtil.addErrorMessage("The wbCrCity with id " + requestWbCrCityString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit()
    {
        String wbCrCityString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrCity);
        String currentWbCrCityString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrCity");
        if (wbCrCityString == null || wbCrCityString.length() == 0 || !wbCrCityString.equals(currentWbCrCityString))
        {
            String outcome = editSetup();
            if ("wbCrCity_edit".equals(outcome))
            {
                JsfUtil.addErrorMessage("Could not edit wbCrCity. Try again.");
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
            getJpaController().edit(wbCrCity);
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
                JsfUtil.addSuccessMessage("WbCrCity was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrCity");
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
                JsfUtil.addSuccessMessage("WbCrCity was successfully deleted.");
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

    public List<WbCrCity> getWbCrCityItems()
    {
        if (wbCrCityItems == null)
        {
            getPagingInfo();
            wbCrCityItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return wbCrCityItems;
    }

    public String next()
    {
        reset(false);
        getPagingInfo().nextPage();
        return "wbCrCity_list";
    }

    public String prev()
    {
        reset(false);
        getPagingInfo().previousPage();
        return "wbCrCity_list";
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
        wbCrCity = null;
        wbCrCityItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem)
        {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value)
    {
        WbCrCity newWbCrCity = new WbCrCity();
        String newWbCrCityString = converter.getAsString(FacesContext.getCurrentInstance(), null, newWbCrCity);
        String wbCrCityString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrCity);
        if (!newWbCrCityString.equals(wbCrCityString))
        {
            createSetup();
        }
    }

    public Converter getConverter()
    {
        return converter;
    }
    
}
