/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.WbCrUserController;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import model.WbCrUser;

/**
 *
 * @author csrm1
 */
public class WbCrUserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        WbCrUserController controller = (WbCrUserController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrUser");
        return controller.getJpaController().find(id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof WbCrUser) {
            WbCrUser o = (WbCrUser) object;
            return o.getUserId() == null ? "" : o.getUserId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.WbCrUser");
        }
    }
    
}
