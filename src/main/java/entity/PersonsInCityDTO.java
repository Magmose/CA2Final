/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.List;

/**
 *
 * @author Simon Bojesen
 */
public class PersonsInCityDTO {
    private String zip;
    private List<String> personFirstname;

    public PersonsInCityDTO(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<String> getPersonFirstname() {
        return personFirstname;
    }

    public void setPersonFirstname(List<String> personFirstname) {
        this.personFirstname = personFirstname;
    }
    
    
}
