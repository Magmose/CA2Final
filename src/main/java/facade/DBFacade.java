/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Simon Bojesen
 */
public class DBFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    public static void main(String[] args) {
        DBFacade dbf = new DBFacade();
     //Test getPersonByPhoneNumber
        /*String tlf = "01412238";
        Person p = dbf.getPersonByPhoneNumber(tlf);
        System.out.println("Personname: " + p.getFirstName());*/
    
        String hobbyname = "Programming";
    }
    public Person getPersonByPhoneNumber(String phonenumber) {
        EntityManager em = emf.createEntityManager();
        try {
            Phone phone = em.find(Phone.class, phonenumber);
            System.out.println("found phonenumber: " + phone.getNumber());
            return (Person) em.createQuery("SELECT p FROM Person AS p WHERE :number MEMBER OF p.numbers").setParameter("number", phone).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<Person> getAllPersonsByHobby (String hobbyname) {
        EntityManager em = emf.createEntityManager();
        try {
            Hobby hobby = em.find(Hobby.class, hobbyname);
            return (List<Person>) em.createQuery("SELECT p FROM Person AS p WHERE :hobby MEMBER OF p.hobbies").setParameter("hobby", hobby).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Person> getAllPersonsByCity (String cityname) {
        EntityManager em = emf.createEntityManager();
        try {
            CityInfo ci = em.find(CityInfo.class, cityname);
            List<Address> adressesInCity = em.createQuery("SELECT a FROM Address AS a WHERE a.cityInfo = :city").setParameter("city", ci).getResultList();
            return (List<Person>) em.createQuery("SELECT p FROM Person AS p WHERE p.address IN :cityadresses").setParameter("cityadresses", adressesInCity).getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getPersonCountWithGivenHobby (String hobbyname) {
        EntityManager em = emf.createEntityManager();
        try {
            Hobby hobby = em.find(Hobby.class, hobbyname);
            return (int) em.createQuery("SELECT count(p.id) FROM Person AS p WHERE :hobby MEMBER OF p.hobbies").setParameter("hobby", hobby).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<CityInfo> getZipCodesInDenmark() {
        EntityManager em = emf.createEntityManager();
        try {
            //zipCodes in Denmark are 4-digit codes
            return (List<CityInfo>) em.createQuery("SELECT c FROM CityInfo AS c WHERE LENGTH(c.zipCode) = 4").getResultList();
        } finally {
            em.close();
        }
    }
    
    public void addPersonToDB(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void updatePersonInDB(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void deletePersonInDB(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void addAddressToDB(Address address) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void updateAddressInDB(Address address) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void deleteAddressInDB(Address address) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
