package util;

import entity.Person;
import facade.DBFacade;

public class Tester {
    public static void main(String[] args) {
        DBFacade dbf = new DBFacade();
        Person p = dbf.getPersonByPhoneNumber("48766818");
        System.out.println(p);
    }
}
