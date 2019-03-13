package data;

import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataFacade {

    EntityManagerFactory emf;

    public DataFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Person createPerson() {
        EntityManager em = emf.createEntityManager();
        Person person = new Person("hej@hej.dk", "hej", "hej");
        Address address = new Address("gade", "gadeInfo");
        Phone phone = new Phone("25252525", "hejstele");
        Phone phone1 = new Phone("8888888", "kage");
        Hobby hobby = new Hobby("tennis", "spille det");
        Hobby hobby1 = new Hobby("cookies", "spise dem");
        
        phone.setPerson(person);
        phone1.setPerson(person);
        person.addNumbers(phone);
        person.addNumbers(phone1);
        
        address.addPersons(person);
        person.setAddress(address);
        
        hobby.addPersons(person);
        hobby1.addPersons(person);
        person.addHobbies(hobby);
        person.addHobbies(hobby1);
        
        try {
            em.getTransaction().begin();
            em.persist(person);
            
            em.persist(address);
            
            em.persist(phone);
            em.persist(phone1);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return person;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        DataFacade facade = new DataFacade(emf);
        
        facade.createPerson();
    }
}
