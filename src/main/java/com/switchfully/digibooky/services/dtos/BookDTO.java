package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.Author;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookDTO {
    private String id;
    private String isbn;
    private String title;
    private Author author;
    private boolean isLentOut;
    private LocalDate dueDate;

    public BookDTO setId(String id) {
        this.id = id;
        return this;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDTO setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public BookDTO setLentOut(boolean lentOut) {
        isLentOut = lentOut;
        return this;
    }

    public BookDTO setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
        return this;
    }
}
