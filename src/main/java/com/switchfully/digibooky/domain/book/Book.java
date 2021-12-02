package com.switchfully.digibooky.domain.book;

import com.switchfully.digibooky.domain.book.Author;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Book {
    private final String id;
    private final String isbn;
    private String title;
    private Author author;
    private boolean isLentOut;
    private boolean isSoftDeleted;
    private LocalDate dueDate;

    public Book(String isbn, String title, Author author) {
        this.id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isLentOut = false;
        this.isSoftDeleted = false;
        this.dueDate = null;
    }

    public void setLentOut(boolean isLentOut) {
        this.isLentOut = isLentOut;
    }

    public boolean isSoftDeleted() {
        return isSoftDeleted;
    }

    public void setSoftDeleted(boolean softDeleted) {
        isSoftDeleted = softDeleted;
    }



    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
