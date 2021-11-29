package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.Author;

import java.util.UUID;

public class BookDTO {
    private String id;
    private String isbn;
    private String title;
    private Author author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
