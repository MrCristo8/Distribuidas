/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import model.CR_WB_Article;
import persistance_unit.Custom_PU;

/**
 *
 * @author wason
 */
public class ArticlePersistance
{

    private ArrayList<CR_WB_Article> articleList;
    private static ArticlePersistance uniqueInstance;
    private static final String TABLE_NAME = "CR_WB_ARTICLE";

    public static ArticlePersistance getInstnace()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new ArticlePersistance();
        }
        return uniqueInstance;
    }

    public ArticlePersistance()
    {
        articleList = new ArrayList<>();
    }

    public ArrayList<CR_WB_Article> getArticleList()
    {
        return articleList;
    }

    public String LoadArticles()
    {
        String msg = "";
        try
        {
            Custom_PU.GetObjList(new CR_WB_Article(), TABLE_NAME).forEach(x
                    -> articleList.add((CR_WB_Article) x));
        } catch (IllegalAccessException | InstantiationException | SQLException e)
        {
            msg = e.getMessage();
        }
        return msg;
    }

}
