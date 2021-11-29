package com.switchfully.digibooky.domain;

import com.switchfully.digibooky.services.dtos.BookDTO;

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

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}
