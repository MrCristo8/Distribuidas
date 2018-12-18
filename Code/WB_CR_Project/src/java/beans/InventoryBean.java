/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.InventoryPersistance;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import model.InventoryArr;
import model.WbCrArticle;
import model.WbCrInventory;
import model.WbCrMovement;

/**
 *
 * @author wason
 */
@Named(value = "inventoryBean")
@SessionScoped
public class InventoryBean implements Serializable
{

    private String inventoryDate;
    private Integer inventoryAmmount;
    private String wbCrarticle;
    private String wbCrMovement;
    private ArrayList<WbCrInventory> billsList;
    private ArrayList<WbCrInventory> filteredList;
    private String filterString;
    private WbCrInventory current;
    private ArrayList<InventoryArr> in_arr;

    public InventoryBean()
    {
    }

    public String getInventoryDate()
    {
        return inventoryDate;
    }

    public void setInventoryDate(String inventoryDate)
    {
        this.inventoryDate = inventoryDate;
    }

    public Integer getInventoryAmmount()
    {
        return inventoryAmmount;
    }

    public void setInventoryAmmount(Integer inventoryAmmount)
    {
        this.inventoryAmmount = inventoryAmmount;
    }

    public String getWbCrarticle()
    {
        return wbCrarticle;
    }

    public void setWbCrarticle(String wbCrarticle)
    {
        this.wbCrarticle = wbCrarticle;
    }

    public ArrayList<InventoryArr> getIn_arr()
    {
        if (in_arr == null)
        {
            in_arr = new ArrayList<>();
        }
        return in_arr;
    }

    public void setIn_arr(ArrayList<InventoryArr> in_arr)
    {
        this.in_arr = in_arr;
    }

    public String getWbCrMovement()
    {
        return wbCrMovement;
    }

    public void setWbCrMovement(String wbCrMovement)
    {
        this.wbCrMovement = wbCrMovement;
    }

    public ArrayList<WbCrInventory> getBillsList()
    {
        billsList = controller.InventoryPersistance.getInstance().getAll();
        return billsList;
    }

    public void setBillsList(ArrayList<WbCrInventory> billsList)
    {
        this.billsList = billsList;
    }

    public ArrayList<WbCrInventory> getFilteredList()
    {
        if (filteredList == null)
        {
            filteredList = new ArrayList<>();
        }
        return filteredList;
    }

    public void setFilteredList(ArrayList<WbCrInventory> filteredList)
    {
        this.filteredList = filteredList;
    }

    public String getFilterString()
    {
        return filterString;
    }

    public void setFilterString(String filterString)
    {
        this.filterString = filterString;
    }

    public WbCrInventory getCurrent()
    {
        return current;
    }

    public void setCurrent(WbCrInventory current)
    {
        this.current = current;
    }

    public void filter()
    {
        if (billsList != null && getFilteredList() != null && filterString != null)
        {
            if (billsList.size() >= 1)
            {
                getFilteredList().clear();
                billsList.forEach(x
                        ->
                {
                    if (x.getArticleId().getArticleName().toUpperCase().contains(filterString.toUpperCase()))
                    {
                        getFilteredList().add(x);
                    }
                });

            }
        }
    }

    public void delete(WbCrInventory art)
    {
        controller.InventoryPersistance.getInstance().deleteObject(art.getInventoryId());
    }

    public void Change()
    {
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("new.xhtml");
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void Add()
    {
        try
        {
            List<WbCrInventory> articles = InventoryPersistance.getInstance().getAll();            
            int id =1;
            for (WbCrInventory article : articles)
            {
                if (article.getInventoryId() != id)
                {
                    continue;
                }
                id++;
            }            
            ArrayList<WbCrInventory> inv_list = new ArrayList<>();                        
            for (InventoryArr x : in_arr)
            {
                
                try
                {
                    inv_list.add(new WbCrInventory(
                            new SimpleDateFormat("yyyy-MM-dd").parse(inventoryDate),
                            x.getAmmount(),
                            id,
                            x.getArticle(),
                            x.getMovement()
                    ));
                    
                } catch (ParseException ex)
                {
                    Logger.getLogger(InventoryBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                id++;
            }
            controller.InventoryPersistance.getInstance().persistObject(inv_list);
            FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        in_arr.clear();

    }

    public void prepareUpdate(WbCrInventory art_update)
    {
        setCurrent(art_update);
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("update.xhtml");
        } catch (IOException ex)
        {
            Logger.getLogger(ArticleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update()
    {
        if (controller.InventoryPersistance.getInstance().updateObject(current).equals("OK"))
        {
            try
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
            } catch (IOException ex)
            {
                Logger.getLogger(ArticleBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {

        }
    }
    public void addItems()
    {
        try
        {
            ArrayList<WbCrArticle> test_arr = controller.ArticlePersistance.getInstance().getAll();
            int index = test_arr.indexOf(new WbCrArticle(Integer.parseInt(wbCrarticle)));
            WbCrArticle art = test_arr.get(index);

            ArrayList<WbCrMovement> test_arr_inv = controller.MovementPersistance.getInstance().getAll();
            index = test_arr_inv.indexOf(new WbCrMovement(Integer.parseInt(wbCrMovement)));
            WbCrMovement inv = test_arr_inv.get(index);

            in_arr.add(new InventoryArr(art, inv, inventoryAmmount));
            FacesContext.getCurrentInstance().getExternalContext().redirect("new.xhtml");
        } catch (IOException ex)
        {
            Logger.getLogger(InventoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
