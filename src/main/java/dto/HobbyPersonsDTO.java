package dto;

public class HobbyPersonsDTO {
    private String hobbyName;
    private String personFirstName;
    private String personLastName;
    private String personEmail;

    public HobbyPersonsDTO(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public HobbyPersonsDTO(String personFirstName, String personLastName, String personEmail) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.personEmail = personEmail;
    }
    
    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    
}
