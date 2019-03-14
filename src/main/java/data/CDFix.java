package data;

import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CDFix {

    EntityManagerFactory emf;

    public CDFix(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Address createAddress(Address address) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return address;
    }

    public Phone createPhone(Phone phone) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            Query q = em.createQuery("SELECT ph FROM Phone ph WHERE ph.number = :number");
            q.setParameter("number", phone.getNumber());
            phone = (Phone)q.getSingleResult();
        } finally {
            em.close();
        }
        return phone;
    }

    public Hobby createHobby(Hobby hobby) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hobby;
    }

    public Person createPerson(Person person) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        System.out.println(person.getId());
        return person;
    }

    public void setPhoneToPerson(Person person, Phone phone) {
        EntityManager em = emf.createEntityManager();
            phone.setPerson(person);
            person.addNumbers(phone);

        try {
            em.getTransaction().begin();
            System.out.println("merging.................");
//            em.merge(phone);
            em.merge(person);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CDFix facade = new CDFix(emf);

        Address address = new Address("Street", "StreetInfo");
        facade.createAddress(address);

        Person person = new Person("email", "firstName", "lastName");
        System.out.println("person before:"+person);
//        facade.createPerson(person);
        System.out.println("person after:"+person);

        Hobby hobby = new Hobby("name", "description");
        facade.createHobby(hobby);

        Phone phone = new Phone("number", "description");
        Phone phone2 = new Phone("number", "description");

//        phone = facade.createPhone(phone);
//        phone2 = facade.createPhone(phone2);

        facade.setPhoneToPerson(person, phone);
//        phone.setPerson(person);
//        person.addNumbers(phone);
//
//        address.addPersons(person);
//        person.setAddress(address);
//
//        hobby.addPersons(person);
//        person.addHobbies(hobby);
    }

}
