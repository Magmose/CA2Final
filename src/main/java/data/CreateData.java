package data;

import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateData {

    EntityManagerFactory emf;

    public CreateData(EntityManagerFactory emf) {
        this.emf = emf;
    }

    
    public Address createAddress() {
        EntityManager em = emf.createEntityManager();
        DataGenerator dg = new DataGenerator(emf);
        Address address = dg.getGeneratedAddress();
        
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return address;
    }

    public void createData(int amount) {
        DataGenerator dg = new DataGenerator(emf);
        EntityManager em = emf.createEntityManager();

        //Person person = new Person("hej@hej.dk", "hej", "hej");
        List<Person> persons = dg.getGeneratedPersons(amount);
        try {
            em.getTransaction().begin();

            for (int i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);

                //Address 
                Address address = dg.getGeneratedAddress();
                
                //Phone 
                Phone phone1 = dg.getGeneratedPhone();
                Phone phone2 = dg.getGeneratedPhone();

                //Hobby 
                Hobby hobby1 = dg.getGeneratedHobby();
                Hobby hobby2 = dg.getGeneratedHobby();

                phone1.setPerson(person);
                phone2.setPerson(person);
                person.addNumbers(phone1);
                person.addNumbers(phone2);

                address.addPersons(person);
                person.setAddress(address);

                hobby2.addPersons(person);
                hobby1.addPersons(person);
                person.addHobbies(hobby1);
                person.addHobbies(hobby2);

                em.persist(person);

                //em.persist(address);

                em.persist(phone1);
                em.persist(phone2);

            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CreateData facade = new CreateData(emf);

        facade.createData(100);
    }
}
