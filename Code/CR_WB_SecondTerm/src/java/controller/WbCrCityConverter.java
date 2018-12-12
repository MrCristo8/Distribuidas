/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import model.WbCrCity;

/**
 *
 * @author wason
 */
public class WbCrCityConverter implements Converter
{

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string)
    {
        if (string == null || string.length() == 0)
        {
            return null;
        }
        Integer id = new Integer(string);
        WbCrCityController controller = (WbCrCityController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrCity");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object)
    {
        if (object == null)
        {
            return null;
        }
        if (object instanceof WbCrCity)
        {
            WbCrCity o = (WbCrCity) object;
            return o.getCityId() == null ? "" : o.getCityId().toString();
        } else
        {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.WbCrCity");
        }
    }
    
}
