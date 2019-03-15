package util;

import facade.DBFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", null);
        DBFacade dbf = new DBFacade(emf);
    }
}
