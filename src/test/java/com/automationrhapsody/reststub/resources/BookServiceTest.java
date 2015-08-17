package com.automationrhapsody.reststub.resources;

import com.automationrhapsody.reststub.data.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookServiceTest {
    private static List<Book> testBooks;
    private static BookService bookService;

    @BeforeClass
    public static void setupData() {
        testBooks = new ArrayList<>();
        testBooks.add(new Book("1492251275", "The Purloined Letter", "Edgar Allen Poe"));
        testBooks.add(new Book("1495375749", "The black cat", "Edgar Allen Poe"));
        testBooks.add(new Book("1467746150", "The Raven", "Edgar Allen Poe"));

        bookService = new BookService();
    }

    @Test
    public void testGetBooks() {
        List<Book> books = bookService.getBooks();
        // Verify size
        assertEquals(testBooks.size(), books.size());
        // Verify books
        for (int i = 0; i < books.size(); i++) {
            assertEquals(testBooks.get(i), books.get(i));
        }
    }
}
