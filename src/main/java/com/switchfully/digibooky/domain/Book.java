package com.switchfully.digibooky.domain;

import java.util.UUID;

public class Book {
    private String id;
    private String isbn;
    private String title;
    private Author author;

    public Book(String isbn, String title, Author author) {
        this.id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
}
