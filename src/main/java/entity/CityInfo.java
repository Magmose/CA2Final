package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    @OneToMany(mappedBy = "cityInfo", cascade = CascadeType.MERGE)
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

