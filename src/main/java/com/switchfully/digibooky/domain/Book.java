package com.switchfully.digibooky.domain;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Book {
    private final String id;
    private final String isbn;
    private String title;
    private Author author;
    private boolean isLentOut;

    public Book(String isbn, String title, Author author) {
        this.id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isLentOut = false;
    }

    public void setLentOut(boolean isLentOut) {
        this.isLentOut = isLentOut;
    }


    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
