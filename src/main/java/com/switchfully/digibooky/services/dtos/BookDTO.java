package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;

import java.util.UUID;

public class BookDTO {
    private String id;
    private String isbn;
    private String title;
    private Author author;

    public BookDTO(String id, String isbn, String title, Author author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public BookDTO(Book book){
        this.id = book.getId();
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.author = book.getAuthor();
    }

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
