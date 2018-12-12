/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import model.WbCrArticle;

/**
 *
 * @author wason
 */
public class WbCrArticleConverter implements Converter
{

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string)
    {
        if (string == null || string.length() == 0)
        {
            return null;
        }
        Integer id = new Integer(string);
        WbCrArticleController controller = (WbCrArticleController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "wbCrArticle");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object)
    {
        if (object == null)
        {
            return null;
        }
        if (object instanceof WbCrArticle)
        {
            WbCrArticle o = (WbCrArticle) object;
            return o.getArticleId() == null ? "" : o.getArticleId().toString();
        } else
        {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.WbCrArticle");
        }
    }
    
}
