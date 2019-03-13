/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.google.gson.Gson;
import entity.Person;
import facade.DBFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
    DBFacade db = new DBFacade();
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
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    @GET
    @Path("phone/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByPhone(@PathParam("phone") String phone) {
        Person p = db.getPersonByPhoneNumber(phone);
        return gson.toJson(p);
    }
    @GET
    @Path("hobby/{hobbyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsFromHobby(@PathParam("hobbyName") String name) {
        List<Person> list = db.getAllPersonsByHobby(name);
        return gson.toJson(list);
    }
    @GET
    @Path("hobby/count/{hobbyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonCountFromHobby(@PathParam("hobbyName") String name) {
        int count = db.getPersonCountWithGivenHobby(name);
        return gson.toJson(count);
    }
    @GET
    @Path("city/{cityName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsFromCity(@PathParam("cityName") String name) {
        List<Person> list = db.getAllPersonsByCity(name);
        return gson.toJson(list);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(String content) {
        Person p = gson.fromJson(content, Person.class);
        return Response.ok().entity(gson.toJson(p)).build();
    }
}
