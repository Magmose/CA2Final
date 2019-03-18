/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

/**
 *
 * @author Simon Bojesen
 */
public class PhoneDTO {
    private String personFirstName;
    private String number;

    public PhoneDTO(String personFirstName, String number) {
        this.personFirstName = personFirstName;
        this.number = number;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
    
}
