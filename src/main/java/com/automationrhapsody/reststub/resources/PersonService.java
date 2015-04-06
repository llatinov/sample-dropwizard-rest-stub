package com.automationrhapsody.reststub.resources;

import com.automationrhapsody.reststub.data.Person;
import com.automationrhapsody.reststub.persistence.PersonDB;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonService {

    public PersonService() {
    }

    @GET
    @Timed
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") int id) {
        return PersonDB.getById(id);
    }

    @GET
    @Timed
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    public String removePerson() {
        PersonDB.remove();
        return "Last person remove. Total count: " + PersonDB.getCount();
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons() {
        return PersonDB.getAll();
    }

    @POST
    @Timed
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(Person person) {
        return PersonDB.save(person);
    }
}
