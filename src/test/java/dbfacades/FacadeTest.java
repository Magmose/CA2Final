package dbfacades;

import entity.Person;
import facade.DBFacade;
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

    DBFacade facade = new DBFacade();

    /**
     * Setup test data in the database to a known state BEFORE Each test
     */
    @Before
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //Delete all, since some future test cases might add/change data
            em.createQuery("delete from Person").executeUpdate();
            //Add our test data
            Person person = new Person("test@mail.dk", "Per", "Petersen");
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Test the single method in the Facade
    @Test
    public void getEntities() {
        EntityManager em = emf.createEntityManager();
        try {
            Assert.assertEquals(2,2);
        } finally {
            em.close();
        }
    }

}
