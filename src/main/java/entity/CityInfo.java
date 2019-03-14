/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Magnus
 */
@Entity
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String ZIP;
    private String CITY;

    @OneToMany(mappedBy = "cityInfo")
    private final List<Address> addresss = new ArrayList();
    
    public CityInfo() {
    }

    public CityInfo(String zipCode, String city) {
        this.ZIP = zipCode;
        this.CITY = city;
    }

     public List<Address> getAddress() {
        return addresss;
    }

    public void addAdress(Address adress) {
        this.addresss.add(adress);
    }
    
    public String getZipCode() {
        return ZIP;
    }

    public void setZipCode(String zipCode) {
        this.ZIP = zipCode;
    }

    public String getCity() {
        return CITY;
    }

    public void setCity(String city) {
        this.CITY = city;
    }
  
}
