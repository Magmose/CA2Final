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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CityInfo)) {
            return false;
        }
        CityInfo other = (CityInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity1.CityInfo[ id=" + id + " ]";
    }

}
