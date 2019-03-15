/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Simon Bojesen
 */
public class PersonsInCityDTO {
    private String personFirstName;
    private String personLastName;
    private String personEmail;

    public PersonsInCityDTO(String personFirstname, String personLastname, String personEmail) {
        this.personFirstName = personFirstname;
        this.personLastName = personLastname;
        this.personEmail = personEmail;
    }

    public String getPersonFirstname() {
        return personFirstName;
    }

    public void setPersonFirstname(String personFirstname) {
        this.personFirstName = personFirstname;
    }

    public String getPersonLastname() {
        return personLastName;
    }

    public void setPersonLastname(String personLastname) {
        this.personLastName = personLastname;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    
    
    
}
