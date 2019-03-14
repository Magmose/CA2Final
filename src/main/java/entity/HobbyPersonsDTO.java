package entity;

import java.util.List;

public class HobbyPersonsDTO {
    private String hobbyName;
    private List<String> personFirstname;

    public HobbyPersonsDTO(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public List<String> getPersonFirstname() {
        return personFirstname;
    }

    public void setPersonFirstname(List<String> personFirstname) {
        this.personFirstname = personFirstname;
    }
    
}
