package com.automationrhapsody.reststub.data;

import lombok.Data;

@Data
public class Book {
    private String isbn;
    private String name;
    private String author;

    public Book(String isbn, String name, String author) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
    }

    public String getIsbn() {
        return "ISBN: " + isbn;
    }
}