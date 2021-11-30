package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.Author;

public class CreateBookDTO {
    private String isbn;
    private String title;
    private Author author;

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public CreateBookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public CreateBookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateBookDTO setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
