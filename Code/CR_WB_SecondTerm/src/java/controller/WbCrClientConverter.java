/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import model.WbCrClient;

/**
 *
 * @author wason
 */
public class WbCrClientConverter implements Converter
{

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string)
    {
        if (string == null || string.length() == 0)
        {
            return null;
        }
        Integer id = new Integer(string);
        WbCrClientController controller = (WbCrClientController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrClient");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object)
    {
        if (object == null)
        {
            return null;
        }
        if (object instanceof WbCrClient)
        {
            WbCrClient o = (WbCrClient) object;
            return o.getClientId() == null ? "" : o.getClientId().toString();
        } else
        {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.WbCrClient");
        }
    }
    
}
