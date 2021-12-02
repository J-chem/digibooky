package com.switchfully.digibooky.domain;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Book {
    private final String id;
    private final String isbn;
    private final String title;
    private final Author author;
    private boolean isLentOut;
    private boolean isSoftDeleted;

    public Book(String isbn, String title, Author author) {
        this.id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isLentOut = false;
        this.isSoftDeleted = false;
    }

    public boolean isSoftDeleted() {
        return isSoftDeleted;
    }

    public void setLentOut(boolean isLentOut) {
        this.isLentOut = isLentOut;
    }

    public void setSoftDeleted(boolean softDeleted) {
        isSoftDeleted = softDeleted;
    }


}
