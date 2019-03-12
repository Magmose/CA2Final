package data;

import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataGenrator {

    EntityManagerFactory emf;

    public DataGenrator(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Person> getGeneratedPersons(Integer howMany) {
        List<Person> persons = new ArrayList();

        String[] fName = {"Lasse", "Mads", "Mathias", "Magnus", "Simon", "Rasmus", "Peter", "Thomas", "Lars", "Per", "Jhonny", "Niels-Christian", "Hans-Jørgen", "Helle", "Anne", "Mette", "Jens", "Michael"};
        String[] lName = {"Jacobsen", "Klit", "Madsen", "Rasmussen", "Lynge", "Carlsen", "Hansen", "Madsen", "Petersen", "Hartmann", "Nielsen", "Jensen", "Pedersen", "Andersen", "Larsen"};
        String firstName, lastName;
        for (int i = 0; i < howMany; i++) {
            firstName = fName[(int) (Math.random() * (fName.length - 1))];
            lastName = lName[(int) (Math.random() * (lName.length - 1))];

            Person person = new Person(firstName.toLowerCase() + "@" + lastName.toLowerCase() + ".dk", firstName, lastName);
            
            //Add Address
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
            Address address = getGeneratedAddress();
            try {
                em.getTransaction().begin();

                em.persist(address);
                em.getTransaction().commit();
            } finally {
                em.close();
            }  
            person.setAddress(address);
            
            //Add Phone
            //Phone phone = getGeneratedPhone();
            //person.addNumbers(phone);

            //Add Hobbies
            person.addHobbies(getGeneratedHobby());
            person.addHobbies(getGeneratedHobby());
            persons.add(person);
            //System.out.println(person);
        }
        return persons;
    }

    public Address getGeneratedAddress() {
        Integer number = (int) (Math.random() * 300);
        String[] streetNames = {"Glentevej", "Kollegiebakken", "Cphvej", "Lærkevej", "Birkevej", "Vibevej", "Vinkelvej", "Østergade", "Engvej"};
        String streetName = streetNames[(int) (Math.random() * (streetNames.length - 1))];

        Address address = new Address(streetName, number.toString());
        //System.out.println(address.toString());
        return address;
    }

    public Phone getGeneratedPhone() {

        String number = "";
        for (int i = 0; i < 8; i++) {
            Integer oneNumber = (int) (Math.random() * 9);
            number += oneNumber.toString();
        }
        String[] descs = {"Home Number", "Work Number", "Mobile Number"};
        String desc = descs[(int) (Math.random() * (descs.length - 1))];

        Phone phone = new Phone(number, desc);
        //System.out.println(number);
        return phone;
    }

    public Hobby getGeneratedHobby() {
        String[] names = {"Tennis", "Football", "Gaming", "Cooking", "Bullying", "Programming", "Basketball", "Partying", "Cosplay"};
        String name = names[(int) (Math.random() * (names.length - 1))];
        String[] descs = {"Every Thursday", "Every Friday", "Every Night", "With Friends", "With Work", "With Neigbours", "Every Monday", "With the King"};
        String desc = descs[(int) (Math.random() * (descs.length - 1))];

        Hobby hobby = new Hobby(name, desc);
        //System.out.println(hobby.toString());
        return hobby;
    }

    public static void main(String[] args) {
        //Persistence.generateSchema("pu", null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        DataGenrator dg = new DataGenrator(emf);
        try {
            em.getTransaction().begin();

            List<Person> persons = dg.getGeneratedPersons(50);
            for (int i = 0; i < persons.size(); i++) {
                em.persist(persons.get(i));
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
