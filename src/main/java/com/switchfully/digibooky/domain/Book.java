package com.switchfully.digibooky.domain;

import java.util.UUID;

public class Book {
    private final String id;
    private final String isbn;
    private final String title;
    private final Author author;
    private boolean isLentOut;

    public Book(String isbn, String title, Author author) {
        this.id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isLentOut = false;
    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean isLentOut() {
        return isLentOut;
    }

    public void setLentOut(boolean isLentOut) {
        this.isLentOut = isLentOut;
    }
}
