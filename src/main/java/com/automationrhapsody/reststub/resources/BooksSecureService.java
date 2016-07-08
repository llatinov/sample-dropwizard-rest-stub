package com.automationrhapsody.reststub.resources;

import com.automationrhapsody.reststub.data.Book;
import com.automationrhapsody.reststub.filters.Authenticator;
import com.automationrhapsody.reststub.persistence.BookDB;
import com.codahale.metrics.annotation.Timed;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Authenticator
@Path("/secure/books")
public class BooksSecureService {

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return BookDB.getAll();
    }
}
