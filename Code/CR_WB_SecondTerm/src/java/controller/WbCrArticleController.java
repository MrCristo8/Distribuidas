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
import managedbeans.WbCrArticleFacade;
import model.WbCrArticle;

/**
 *
 * @author wason
 */
public class WbCrArticleController
{

    public WbCrArticleController()
    {
        pagingInfo = new PagingInfo();
        converter = new WbCrArticleConverter();
    }
    private WbCrArticle wbCrArticle = null;
    private List<WbCrArticle> wbCrArticleItems = null;
    private WbCrArticleFacade jpaController = null;
    private WbCrArticleConverter converter = null;
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

    public WbCrArticleFacade getJpaController()
    {
        if (jpaController == null)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (WbCrArticleFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrArticleJpa");
        }
        return jpaController;
    }

    public SelectItem[] getWbCrArticleItemsAvailableSelectMany()
    {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getWbCrArticleItemsAvailableSelectOne()
    {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public WbCrArticle getWbCrArticle()
    {
        if (wbCrArticle == null)
        {
            wbCrArticle = (WbCrArticle) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrArticle", converter, null);
        }
        if (wbCrArticle == null)
        {
            wbCrArticle = new WbCrArticle();
        }
        return wbCrArticle;
    }

    public String listSetup()
    {
        reset(true);
        return "wbCrArticle_list";
    }

    public String createSetup()
    {
        reset(false);
        wbCrArticle = new WbCrArticle();
        return "wbCrArticle_create";
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
            getJpaController().create(wbCrArticle);
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
                JsfUtil.addSuccessMessage("WbCrArticle was successfully created.");
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
        return scalarSetup("wbCrArticle_detail");
    }

    public String editSetup()
    {
        return scalarSetup("wbCrArticle_edit");
    }

    private String scalarSetup(String destination)
    {
        reset(false);
        wbCrArticle = (WbCrArticle) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrArticle", converter, null);
        if (wbCrArticle == null)
        {
            String requestWbCrArticleString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrArticle");
            JsfUtil.addErrorMessage("The wbCrArticle with id " + requestWbCrArticleString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit()
    {
        String wbCrArticleString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrArticle);
        String currentWbCrArticleString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrArticle");
        if (wbCrArticleString == null || wbCrArticleString.length() == 0 || !wbCrArticleString.equals(currentWbCrArticleString))
        {
            String outcome = editSetup();
            if ("wbCrArticle_edit".equals(outcome))
            {
                JsfUtil.addErrorMessage("Could not edit wbCrArticle. Try again.");
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
            getJpaController().edit(wbCrArticle);
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
                JsfUtil.addSuccessMessage("WbCrArticle was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrArticle");
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
                JsfUtil.addSuccessMessage("WbCrArticle was successfully deleted.");
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

    public List<WbCrArticle> getWbCrArticleItems()
    {
        if (wbCrArticleItems == null)
        {
            getPagingInfo();
            wbCrArticleItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return wbCrArticleItems;
    }

    public String next()
    {
        reset(false);
        getPagingInfo().nextPage();
        return "wbCrArticle_list";
    }

    public String prev()
    {
        reset(false);
        getPagingInfo().previousPage();
        return "wbCrArticle_list";
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
        wbCrArticle = null;
        wbCrArticleItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem)
        {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value)
    {
        WbCrArticle newWbCrArticle = new WbCrArticle();
        String newWbCrArticleString = converter.getAsString(FacesContext.getCurrentInstance(), null, newWbCrArticle);
        String wbCrArticleString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrArticle);
        if (!newWbCrArticleString.equals(wbCrArticleString))
        {
            createSetup();
        }
    }

    public Converter getConverter()
    {
        return converter;
    }
    
}
