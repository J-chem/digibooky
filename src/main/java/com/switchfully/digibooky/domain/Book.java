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
    }

//    public Book(BookDTO bookDTO){
//        this.id = bookDTO.getId();
//        this.isbn = bookDTO.getIsbn();
//        this.title = bookDTO.getTitle();
//        this.author = bookDTO.getAuthor();
//    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setLentOut(boolean isLentOut) {
        this.isLentOut = isLentOut;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}
