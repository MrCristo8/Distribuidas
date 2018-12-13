/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author wason
 */
public interface DAOIface<T, Id extends Serializable>
{
    public String persistObject(T entity);
    public String deleteObject(T entity, Object id);    
    public List<?> getAllObjects(T entity, String query);
    public Object findObject(T entity, Object id);
}
