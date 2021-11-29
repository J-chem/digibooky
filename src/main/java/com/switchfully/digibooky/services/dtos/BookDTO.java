package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.Author;


public class BookDTO {
    private String id;
    private String isbn;
    private String title;
    private Author author;

    public String getId() {
        return id;
    }

    public BookDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public BookDTO setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
