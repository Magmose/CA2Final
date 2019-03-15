package dbfacades;

import entity.Address;
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
            //em.createQuery("delete from City").executeUpdate();
            em.createQuery("delete from Hobby").executeUpdate();
            //Add our test data
            Address address = new Address("Street", "StreetInfo");
            Person person = new Person("email", "firstName", "lastName");
            Hobby hobby = new Hobby("name", "description");
            Phone phone = new Phone("number", "description");

            phone.setPerson(person);
            person.addNumbers(phone);

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

//    // Test the single method in the Facade
//    @Test
//    public void getPerson() {
//        Person personDB = facade.getPersonById(testPerson);
//        Assert.assertEquals(testPerson.getEmail(), personDB.getEmail());
//    }
//
//    @Test
//    public void getPersonByPhoneNumber() {
//        Person person = facade.getPersonByPhoneNumber("number");
//        Assert.assertEquals(person, testPerson);
//    }
//
//    @Test
//    public void getPersonsByHobby() {
//        List<Person> persons = facade.getAllPersonsByHobby("name");
//        Assert.assertEquals(2, persons.size());
//    }
//
//    //@Test  Long cannot be cast to java.lang.Integer
//    public void getPersonCountWithGivenHobby() {
//        long count = facade.getPersonCountWithGivenHobby("name");
//        Assert.assertEquals(2, count);
//    }
   
}
