/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author wason
 */
public class ArticlePersistance
{
    private static ArticlePersistance uniqueInstance;
    
    private ArticlePersistance(){}
    
    public static ArticlePersistance getInstance()
    {
        if(uniqueInstance==null)        
            uniqueInstance= new ArticlePersistance();                    
        return uniqueInstance;
    }
    
    public String persistObject(model.WbCrArticle obj_in)
    {
        return new ObjectDAO().persistObject(obj_in);
    }
    public String deleteObject(model.WbCrArticle obj_in, int id)
    {
        return new ObjectDAO().deleteObject(obj_in, id);
    }
    public ArrayList<model.WbCrArticle> getAll()
    {
        ArrayList<model.WbCrArticle> obj_arr = new ArrayList<>();
        new ObjectDAO().getAllObjects(new model.WbCrArticle(), "WbCrArticle").forEach(x ->
        {
            obj_arr.add((model.WbCrArticle) x);
        });
        return obj_arr;
    }    
    
}
