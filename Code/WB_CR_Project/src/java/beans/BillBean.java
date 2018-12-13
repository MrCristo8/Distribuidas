package beans;
// Generated Dec 13, 2018 12:47:24 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * BillBean generated by hbm2java
 */

@ManagedBean()
@SessionScoped
public class BillBean  implements java.io.Serializable {

     private CityBean wbCrCity;
     private ClientBean wbCrClient;
     private Date billDate;
     private Set wbCrBilldetails = new HashSet(0);

    public BillBean() {
    }

	
    public BillBean(CityBean wbCrCity, ClientBean wbCrClient, Date billDate) {
        this.wbCrCity = wbCrCity;
        this.wbCrClient = wbCrClient;
        this.billDate = billDate;
    }
    public BillBean(CityBean wbCrCity, ClientBean wbCrClient, Date billDate, Set wbCrBilldetails) {
       this.wbCrCity = wbCrCity;
       this.wbCrClient = wbCrClient;
       this.billDate = billDate;
       this.wbCrBilldetails = wbCrBilldetails;
    }
    
    public CityBean getWbCrCity() {
        return this.wbCrCity;
    }
    
    public void setWbCrCity(CityBean wbCrCity) {
        this.wbCrCity = wbCrCity;
    }
    
    public ClientBean getWbCrClient() {
        return this.wbCrClient;
    }
    
    public void setWbCrClient(ClientBean wbCrClient) {
        this.wbCrClient = wbCrClient;
    }
    
    public Date getBillDate() {
        return this.billDate;
    }
    
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
    
    public Set getWbCrBilldetails() {
        return this.wbCrBilldetails;
    }
    
    public void setWbCrBilldetails(Set wbCrBilldetails) {
        this.wbCrBilldetails = wbCrBilldetails;
    }




}


