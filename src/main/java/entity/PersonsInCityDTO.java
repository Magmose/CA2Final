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
    private String personFirstname;
    private String personLastname;
    private String personEmail;

    public PersonsInCityDTO(String personFirstname, String personLastname, String personEmail) {
        this.personFirstname = personFirstname;
        this.personLastname = personLastname;
        this.personEmail = personEmail;
    }

    public String getPersonFirstname() {
        return personFirstname;
    }

    public void setPersonFirstname(String personFirstname) {
        this.personFirstname = personFirstname;
    }

    public String getPersonLastname() {
        return personLastname;
    }

    public void setPersonLastname(String personLastname) {
        this.personLastname = personLastname;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    
    
    
}
