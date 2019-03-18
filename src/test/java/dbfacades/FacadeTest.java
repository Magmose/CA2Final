package dbfacades;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.DBFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * UNIT TEST example that mocks away the database with an in-memory database See
 * Persistence unit in persistence.xml in the test packages
 *
 * Use this in your own project by: - Delete everything inside the setUp method,
 * but first, observe how test data is created - Delete the single test method,
 * and replace with your own tests
 *
 */
public class FacadeTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-test", null);

    DBFacade facade = new DBFacade(emf);

    Person testPerson = null;
    Person testPerson2 = null;

    /**
     * Setup test data in the database to a known state BEFORE Each test
     */
    @Before
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //Delete all, since some future test cases might add/change data
            em.createQuery("delete from Phone").executeUpdate();
            em.createQuery("delete from Person").executeUpdate();
            em.createQuery("delete from Address").executeUpdate();
            em.createQuery("delete from CityInfo").executeUpdate();
            em.createQuery("delete from Hobby").executeUpdate();
//            em.createNativeQuery("ALTER TABLE Phone DISABLE CONSTRAINT ALL").executeUpdate();
//            em.createNativeQuery("ALTER TABLE Person DISABLE CONSTRAINT ALL").executeUpdate();
//            em.createNativeQuery("ALTER TABLE Address DISABLE CONSTRAINT ALL").executeUpdate();
//            em.createNativeQuery("ALTER TABLE CityInfo DISABLE CONSTRAINT ALL").executeUpdate();
//            em.createNativeQuery("ALTER TABLE Hobby DISABLE CONSTRAINT ALL").executeUpdate();
//            
//            em.createNativeQuery("TRUNCATE TABLE Phone").executeUpdate();
//            em.createNativeQuery("TRUNCATE TABLE Address").executeUpdate();
//            em.createNativeQuery("TRUNCATE TABLE CityInfo").executeUpdate();
//            em.createNativeQuery("TRUNCATE TABLE Hobby").executeUpdate();
//            em.createNativeQuery("TRUNCATE TABLE Person").executeUpdate();
//            
//            em.createNativeQuery("ALTER TABLE Phone ENABLE CONSTRAINT ALL").executeUpdate();
//            em.createNativeQuery("ALTER TABLE Person ENABLE CONSTRAINT ALL").executeUpdate();
//            em.createNativeQuery("ALTER TABLE Address ENABLE CONSTRAINT ALL").executeUpdate();
//            em.createNativeQuery("ALTER TABLE CityInfo ENABLE CONSTRAINT ALL").executeUpdate();
//            em.createNativeQuery("ALTER TABLE Hobby ENABLE CONSTRAINT ALL").executeUpdate();
            //Add our test data
            Address address = new Address("Street", "StreetInfo");
            Person person = new Person("email", "firstName", "lastName");
            Hobby hobby = new Hobby("name", "description");
            Phone phone = new Phone("number", "description");
            CityInfo city = new CityInfo("2000", "Randers");

            phone.setPerson(person);
            person.addNumbers(phone);
            address.setCityInfo(city);
            address.addPersons(person);
            person.setAddress(address);

            hobby.addPersons(person);
            person.addHobbies(hobby);

            Person person1 = new Person("email1", "firstName1", "lastName1");
            Phone phone1 = new Phone("number1", "description1");

            phone1.setPerson(person1);
            person1.addNumbers(phone1);
            address.addPersons(person1);
            person1.setAddress(address);

            hobby.addPersons(person1);
            person1.addHobbies(hobby);

            testPerson = person;
            testPerson2 = person1;

            em.persist(city);
            em.persist(address);
            em.persist(person);
            em.persist(phone);
            em.persist(hobby);
            em.persist(person1);
            em.persist(phone1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Test the single method in the Facade
    @Test
    public void getPersonByPhoneNumber() {
        Person person = facade.getPersonByPhoneNumber("number");
        Assert.assertEquals(person.getId(), testPerson.getId());
    }

    @Test
    public void getPersonsByHobby() {
        List<Person> persons = facade.getAllPersonsByHobby("name");
        Assert.assertEquals(2, persons.size());
    }

    @Test
    public void getPersonCountWithGivenHobby() {
        long count = facade.getPersonCountWithGivenHobby("name");
        Assert.assertEquals(2, count);
    }

    @Test
    public void getPersonsByCity() {
        List<Person> persons = facade.getAllPersonsByCity("2000");
        Assert.assertEquals(2, persons.size());
    }

    @Test
    public void getAllZipInDK() {
        List<CityInfo> citys = facade.getZipCodesInDenmark();
        Assert.assertEquals(1, citys.size());
    }

    @Test
    public void postPerson() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person p = new Person("simon@simon.dk", "Simon", "Simon");
            facade.addPersonToDB(p);
            em.flush();
            long id = p.getId();
            Person person = em.find(Person.class, id);
            em.getTransaction().commit();
            Assert.assertEquals(p.getId(), person.getId());
        } finally {
            em.close();
        }
    }
    
    @Test
    public void postAddress() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Address a = new Address("Lindholmvej", "14");
            facade.addAddressToDB(a);
            em.flush();
            int id = a.getId();
            Address address = em.find(Address.class, id);
            em.getTransaction().commit();
            Assert.assertEquals(a.getId(), address.getId());
        } finally {
            em.close();
        }
    }
    
//    @Test
//    public void putPerson() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            Person updateData = new Person("karl@karlsen.dk", "Karl", "Karlsen");
//            Address a = em.find(Address.class, 1);
//            updateData.setAddress(a);
//            facade.updatePersonInDB(updateData, 1);
//            Person updated = em.find(Person.class, 1);
//            em.getTransaction().commit();
//            Assert.assertEquals(updateData.getEmail(), updated.getEmail());
//        } finally {
//            em.close();
//        }
//    }
    
    
    
}


