package beans;
// Generated Dec 13, 2018 12:47:24 AM by Hibernate Tools 4.3.1

import controller.ArticlePersistance;
import controller.BillDetailPersistance;
import controller.BillPersistance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import model.WbCrArticle;
import model.WbCrBill;
import model.WbCrBilldetail;
import model.WbCrBilldetailPK;

/**
 * BilldetailBean generated by hbm2java
 */
@ManagedBean()
@SessionScoped
public class BilldetailBean implements java.io.Serializable {

    private int detailAmount;
    private List<WbCrBilldetail> details;
    private List<WbCrBilldetail> billDetailList;
    private ArrayList<WbCrBilldetail> filteredList;
    private String filterString;
    private WbCrBilldetailPK id;
    private WbCrBilldetail current;
    private String articleSelected;
    private ArrayList<WbCrArticle> articleList;

    public BilldetailBean() {
        billDetailList = new ArrayList<>();
    }

    @Column(name = "DETAIL_AMOUNT", nullable = false)
    public int getDetailAmount() {
        return this.detailAmount;
    }

    public void setDetailAmount(int detailAmount) {
        this.detailAmount = detailAmount;
    }

    public List<WbCrBilldetail> getDetails() {
        return details;
    }

    public void setDetails(List<WbCrBilldetail> details) {
        this.details = details;
    }

    public List<WbCrBilldetail> getBillDetailList() {
        return billDetailList;
    }

    public void setBillDetailList(List<WbCrBilldetail> billDetailList) {
        this.billDetailList = billDetailList;
    }

    public ArrayList<WbCrBilldetail> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(ArrayList<WbCrBilldetail> filteredList) {
        this.filteredList = filteredList;
    }

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }

    public WbCrBilldetailPK getId() {
        return id;
    }

    public void setId(WbCrBilldetailPK id) {
        this.id = id;
    }

    public WbCrBilldetail getCurrent() {
        return current;
    }

    public void setCurrent(WbCrBilldetail current) {
        this.current = current;
    }

    public String getArticleSelected() {
        return articleSelected;
    }

    public void setArticleSelected(String articleSelected) {
        this.articleSelected = articleSelected;
    }

    public ArrayList<WbCrArticle> getArticleList() {
        articleList = ArticlePersistance.getInstance().getAll();
        return articleList;
    }

    public void setArticleList(ArrayList<WbCrArticle> articleList) {
        this.articleList = articleList;
    }

    public void filter() {
        if (billDetailList != null && getFilteredList() != null && filterString != null) {
            if (billDetailList.size() >= 1) {
                getFilteredList().clear();
                billDetailList.forEach(x
                        -> {
                    if (x.getWbCrArticle().getArticleName().toUpperCase().contains(filterString.toUpperCase())) {
                        getFilteredList().add(x);
                    }
                });

            }
        }
    }

    public void delete(WbCrBilldetail art) {
        BillDetailPersistance.getInstance().deleteObject(art.getWbCrBilldetailPK());
    }

    public void Change() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("new.xhtml");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Add() {
        BillDetailPersistance.getInstance().persistObject(new WbCrBilldetail(id, detailAmount));
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void prepareUpdate(WbCrBilldetail art_update) {
        setCurrent(art_update);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("update.xhtml");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update() {
        if (BillDetailPersistance.getInstance().updateObject(current).equals("OK")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {

        }
    }

    public void AddToList() {
        WbCrArticle article = ArticlePersistance.getInstance().getAll().get(Integer.parseInt(articleSelected));
        int billId = 1;
        List<WbCrBill> bills = BillPersistance.getInstance().getAll();
        for (WbCrBill bill : bills) {
            if (bill.getBillId() != billId) {
                continue;
            }
            billId++;
        }
        id.setArticleId(article.getArticleId());
        id.setBillId(billId);
        getBillDetailList().add(new WbCrBilldetail(id, detailAmount));
    }
}