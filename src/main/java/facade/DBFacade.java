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
import javax.persistence.Query;

/**
 *
 * @author Simon Bojesen
 */
public class DBFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public Person getPersonByPhoneNumber(Phone phone) {
        EntityManager em = emf.createEntityManager();
        try {
            return (Person) em.createQuery("SELECT p FROM Person AS p WHERE :number MEMBER OF p.numbers").setParameter("number", phone.getNumber()).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<Person> getAllPersonsByHobby (Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            return (List<Person>) em.createQuery("SELECT p FROM Person AS p WHERE :hobby MEMBER OF p.hobbies").setParameter("hobby", hobby.getName()).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Person> getAllPersonsByCity (CityInfo city) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Address> adressesInCity = city.getAddress();
            return (List<Person>) em.createQuery("SELECT p FROM Person AS p WHERE p.address IN :cityadresses").setParameter("cityadresses", adressesInCity).getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getPersonCountWithGivenHobby (Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        try {
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
}
