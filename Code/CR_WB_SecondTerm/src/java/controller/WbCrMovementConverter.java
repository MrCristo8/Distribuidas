/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import model.WbCrMovement;

/**
 *
 * @author wason
 */
public class WbCrMovementConverter implements Converter
{

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string)
    {
        if (string == null || string.length() == 0)
        {
            return null;
        }
        Integer id = new Integer(string);
        WbCrMovementController controller = (WbCrMovementController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrMovement");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object)
    {
        if (object == null)
        {
            return null;
        }
        if (object instanceof WbCrMovement)
        {
            WbCrMovement o = (WbCrMovement) object;
            return o.getMovementId() == null ? "" : o.getMovementId().toString();
        } else
        {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.WbCrMovement");
        }
    }
    
}
