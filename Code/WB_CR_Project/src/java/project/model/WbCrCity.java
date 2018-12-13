package project.model;
// Generated Dec 12, 2018 11:08:37 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * WbCrCity generated by hbm2java
 */
public class WbCrCity  implements java.io.Serializable {


     private int cityId;
     private String cityName;
     private Set wbCrBills = new HashSet(0);

    public WbCrCity() {
    }

	
    public WbCrCity(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }
    public WbCrCity(int cityId, String cityName, Set wbCrBills) {
       this.cityId = cityId;
       this.cityName = cityName;
       this.wbCrBills = wbCrBills;
    }
   
    public int getCityId() {
        return this.cityId;
    }
    
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Set getWbCrBills() {
        return this.wbCrBills;
    }
    
    public void setWbCrBills(Set wbCrBills) {
        this.wbCrBills = wbCrBills;
    }




}

