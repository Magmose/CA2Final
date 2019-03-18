package dto;

public class PersonInfoDTO {

    //Address
    private String street;
    private String additionalInfo;
    //CityInfo
    private String ZIP;
    private String CITY;
    //Hobby
    private String name;
    private String descriptionHobby;
    //Person
    private String email;
    private String firstName;
    private String lastName;
    //Phone
    private String number;
    private String descriptionPhone;

    public PersonInfoDTO(String street, String additionalInfo, String ZIP, String CITY, String name, String descriptionHobby, String email, String firstName, String lastName, String number, String descriptionPhone) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.ZIP = ZIP;
        this.CITY = CITY;
        this.name = name;
        this.descriptionHobby = descriptionHobby;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.descriptionPhone = descriptionPhone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionHobby() {
        return descriptionHobby;
    }

    public void setDescriptionHobby(String descriptionHobby) {
        this.descriptionHobby = descriptionHobby;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescriptionPhone() {
        return descriptionPhone;
    }

    public void setDescriptionPhone(String descriptionPhone) {
        this.descriptionPhone = descriptionPhone;
    }

}
