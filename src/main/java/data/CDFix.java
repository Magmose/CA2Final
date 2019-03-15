package data;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.PersonInfoDTO;
import entity.Phone;
import java.util.List;
import java.util.Random;
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
        } catch (Exception e) {
            Query q = em.createQuery("SELECT a FROM Address a WHERE a.street = :street AND a.additionalInfo = :additionalInfo");
            q.setParameter("street", address.getStreet());
            q.setParameter("additionalInfo", address.getAdditionalInfo());
            address = (Address) q.getSingleResult();
//            e.printStackTrace();
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
        } catch (Exception e) {
            Query q = em.createQuery("SELECT ph FROM Phone ph WHERE ph.number = :number");
            q.setParameter("number", phone.getNumber());
            phone = (Phone) q.getSingleResult();
//            e.printStackTrace();
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
        } catch (Exception e) {
            hobby = em.find(Hobby.class, hobby.getName());
            System.out.println(hobby.getName());
//            e.printStackTrace();
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
            em.merge(person);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void setAdressToPerson(Person person, Address address) {
        EntityManager em = emf.createEntityManager();
        person.setAddress(address);

        try {
            em.getTransaction().begin();
            em.merge(person);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void setHobbyToPerson(Person person, Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        person.addHobbies(hobby);
        hobby.addPersons(person);
        try {
            em.getTransaction().begin();
            em.merge(person);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void setZipToAddress(Address address, CityInfo cityInfo) {
        EntityManager em = emf.createEntityManager();
          cityInfo.addAdress(address);
          address.setCityInfo(cityInfo);
        try {
            em.getTransaction().begin();
            em.merge(cityInfo);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<CityInfo> getAllCityInfoBetween() {
        EntityManager em = emf.createEntityManager();

        try {
            Query q = em.createQuery("SELECT ci FROM CityInfo ci WHERE ci.ZIP BETWEEN :startZip AND :endZip ");
            q.setParameter("startZip", "2800");
            q.setParameter("endZip", "2900");
            return (List<CityInfo>) q.getResultList();
        } finally {
            em.close();
        }

    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CDFix facade = new CDFix(emf);
        DataGenerator dg = new DataGenerator();
        List<Person> persons = dg.getGeneratedPersons(100);


        autoGen(persons, dg, facade);
    }

    public void createDtoPerson(PersonInfoDTO personDTO){
        Person person = new Person(personDTO.getEmail(), personDTO.getFirstName(), personDTO.getLastName());
        Address address = new Address(personDTO.getStreet(), personDTO.getAdditionalInfo());
        CityInfo cityInfo = new CityInfo(personDTO.getZIP(), personDTO.getCITY());
        Hobby hobby = new Hobby(personDTO.getName(), personDTO.getDescriptionHobby());
        Phone phone = new Phone(personDTO.getNumber(), personDTO.getDescriptionPhone());
        createPerson(person);
        createAddress(address);
        createPhone(phone);
        createHobby(hobby);
        
        setAdressToPerson(person, address);
        setHobbyToPerson(person, hobby);
        setPhoneToPerson(person, phone);
        setZipToAddress(address, cityInfo);
    }
    private static void autoGen(List<Person> persons, DataGenerator dg, CDFix facade) {
        List<CityInfo> ci = facade.getAllCityInfoBetween();
        Random r = new Random();
        
        for (int i = 0; i < persons.size(); i++) {
        int randomNumber = r.nextInt(ci.size());
            Person person = persons.get(i);
            Address address = dg.getGeneratedAddress();
            Phone phone = dg.getGeneratedPhone();
            Phone phone2 = dg.getGeneratedPhone();
            Hobby hobby = dg.getGeneratedHobby();
            Hobby hobby2 = dg.getGeneratedHobby();

            facade.createPerson(person);

            address = facade.createAddress(address);
            facade.setAdressToPerson(person, address);

            hobby = facade.createHobby(hobby);
            hobby2 = facade.createHobby(hobby2);
            facade.setHobbyToPerson(person, hobby);
            facade.setHobbyToPerson(person, hobby2);

            phone = facade.createPhone(phone);
            phone2 = facade.createPhone(phone2);
            facade.setPhoneToPerson(person, phone);
            facade.setPhoneToPerson(person, phone2);

            facade.setZipToAddress(address, ci.get(randomNumber));
        }

    }


}