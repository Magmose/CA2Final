/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Magnus
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "persons", cascade = CascadeType.ALL)
    private final List<Hobby> hobbies = new ArrayList();

    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE)
    private final List<Phone> numbers = new ArrayList();

    @ManyToOne
    private Address address;

    public Person() {
    }

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Phone> getNumbers() {
        return numbers;
    }

    public void addNumbers(Phone phone) {
        this.numbers.add(phone);
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void addHobbies(Hobby hobby) {
        this.hobbies.add(hobby);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if(!address.getPersons().contains(this))
            address.addPersons(this);
        this.address = address;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String res;
        if (this.id == null) {
            res = 0 + ": " + this.firstName + " " + this.lastName + " " + this.address;
        } else {
            res = this.id + ": " + this.firstName + " " + this.lastName + " " + this.address;
        }
        return res;
    }

}
