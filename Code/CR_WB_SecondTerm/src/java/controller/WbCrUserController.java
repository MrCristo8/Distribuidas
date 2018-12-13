/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import model.WbCrUser;
import managedbeans.WbCrUserFacade;
import model.util.JsfUtil;
import model.util.PagingInfo;

/**
 *
 * @author csrm1
 */
public class WbCrUserController {

    public WbCrUserController() {
        pagingInfo = new PagingInfo();
        converter = new WbCrUserConverter();
    }
    private WbCrUser wbCrUser = null;
    private List<WbCrUser> wbCrUserItems = null;
    private WbCrUserFacade jpaController = null;
    private WbCrUserConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "CR_WB_SecondTermPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public WbCrUserFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (WbCrUserFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrUserJpa");
        }
        return jpaController;
    }

    public SelectItem[] getWbCrUserItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getWbCrUserItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public WbCrUser getWbCrUser() {
        if (wbCrUser == null) {
            wbCrUser = (WbCrUser) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrUser", converter, null);
        }
        if (wbCrUser == null) {
            wbCrUser = new WbCrUser();
        }
        return wbCrUser;
    }

    public String listSetup() {
        reset(true);
        return "wbCrUser_list";
    }

    public String createSetup() {
        reset(false);
        wbCrUser = new WbCrUser();
        return "wbCrUser_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(wbCrUser);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("WbCrUser was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("wbCrUser_detail");
    }

    public String editSetup() {
        return scalarSetup("wbCrUser_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        wbCrUser = (WbCrUser) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWbCrUser", converter, null);
        if (wbCrUser == null) {
            String requestWbCrUserString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrUser");
            JsfUtil.addErrorMessage("The wbCrUser with id " + requestWbCrUserString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String wbCrUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrUser);
        String currentWbCrUserString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrUser");
        if (wbCrUserString == null || wbCrUserString.length() == 0 || !wbCrUserString.equals(currentWbCrUserString)) {
            String outcome = editSetup();
            if ("wbCrUser_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit wbCrUser. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(wbCrUser);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("WbCrUser was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentWbCrUser");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("WbCrUser was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        /*if ((ERROR)) {
            return relatedControllerOutcome;
        }*/
        return listSetup();
    }

    public List<WbCrUser> getWbCrUserItems() {
        if (wbCrUserItems == null) {
            getPagingInfo();
            wbCrUserItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return wbCrUserItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "wbCrUser_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "wbCrUser_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        wbCrUser = null;
        wbCrUserItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        WbCrUser newWbCrUser = new WbCrUser();
        String newWbCrUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, newWbCrUser);
        String wbCrUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, wbCrUser);
        if (!newWbCrUserString.equals(wbCrUserString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
    public void login() {       
        if(getWbCrUserItems().contains(wbCrUser)){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
        next();
    }
}
