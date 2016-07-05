package com.automationrhapsody.reststub.persistence;

import com.automationrhapsody.reststub.data.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDB {
    private static final List<Book> BOOKS = new ArrayList<>();

    static {
        BOOKS.add(new Book("1492251275", "The Purloined Letter", "Edgar Allen Poe"));
        BOOKS.add(new Book("1495375749", "The black cat", "Edgar Allen Poe"));
        BOOKS.add(new Book("1467746150", "The Raven", "Edgar Allen Poe"));
    }

    public static List<Book> getAll() {
        return BOOKS;
    }
}
