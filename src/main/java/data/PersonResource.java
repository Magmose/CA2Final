/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.google.gson.Gson;
import com.sun.corba.se.impl.util.Version;

import entity.HobbyPersonsDTO;

import entity.Address;
import entity.CityInfo;
import entity.Person;
import entity.PersonsInCityDTO;
import entity.PhoneDTO;
import facade.DBFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Mathias
 */
@Path("person")
public class PersonResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", null);
    DBFacade db = new DBFacade(emf);
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of data.PersonResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByPhone(@PathParam("phone") String phone) {
        Person p = db.getPersonByPhoneNumber(phone);
        PhoneDTO person = new PhoneDTO(p.getFirstName(), phone);
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(gson.toJson(person)).build();
    }

    @GET
    @Path("hobby/{hobbyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsFromHobby(@PathParam("hobbyName") String name) {
        List<Person> list = db.getAllPersonsByHobby(name);
        List<String> firstnames = new ArrayList();
        for (Person p : list) {
            firstnames.add(p.getFirstName());
        }
        HobbyPersonsDTO dto = new HobbyPersonsDTO(name);
        dto.setPersonFirstname(firstnames);
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(gson.toJson(dto)).build();
    }

    @GET
    @Path("hobby/count/{hobbyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonCountFromHobby(@PathParam("hobbyName") String name) {
        long count = db.getPersonCountWithGivenHobby(name);
        return gson.toJson(count);
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) {
        Person p = db.getPersonById(id);
        return gson.toJson(p);
    }

    @GET
    @Path("city/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsFromCity(@PathParam("zipcode") String zip) {
        List<Person> list = db.getAllPersonsByCity(zip);
        List<String> firstnames = new ArrayList();
        for (Person p : list) {
            firstnames.add(p.getFirstName());
        }
        PersonsInCityDTO dto = new PersonsInCityDTO(zip);
        dto.setPersonFirstname(firstnames);
        return gson.toJson(dto);
    }

    @GET
    @Path("zip/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getZipcodesInDK() {
        List<CityInfo> list = db.getZipCodesInDenmark();
        return gson.toJson(list);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(String content) {
        Person p = gson.fromJson(content, Person.class);
        db.addPersonToDB(p);
        return Response.ok().entity(gson.toJson(p)).build();
    }

    @POST
    @Path("address/}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postAddress(String content) {
        Address a = gson.fromJson(content, Address.class);
        db.addAddressToDB(a);
        return Response.ok().entity(gson.toJson(a)).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPerson(String content, @PathParam("id") int id) {
        Person p = gson.fromJson(content, Person.class);
        db.updatePersonInDB(p, id);
        return Response.ok().entity(gson.toJson(p)).build();
    }

    @PUT
    @Path("address/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putAddress(String content, @PathParam("id") int id) {
        Address a = gson.fromJson(content, Address.class);
      db.updateAddressInDB(a,id);
        return Response.ok().entity(gson.toJson(a)).build();
    }

    @DELETE
    @Path("/{id}")
    public void deletePersonById(@PathParam("id") int id) {
        //below method call needs fixing once the method parameter is changed in DBFacade.deletePersonInDB
        db.deletePersonInDB(id);
    }

    @DELETE
    @Path("address/{id}")
    public void deleteAddressById(@PathParam("id") int id) {
        //below method call needs fixing once the method parameter is changed in DBFacade.deletePersonInDB
        db.deleteAddressInDB(id);
    }

}
