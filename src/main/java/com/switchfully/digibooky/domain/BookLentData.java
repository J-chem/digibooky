package com.switchfully.digibooky.domain;

import java.time.LocalDate;
import java.util.UUID;

public class BookLentData {

    private final String lendingId;
    private final String userId;
    private final String isbn;
    private final LocalDate dueDate;

    public BookLentData(String userId, String isbn) {
        this.lendingId = UUID.randomUUID().toString();
        this.userId = userId;
        this.isbn = isbn;
        this.dueDate = LocalDate.now().plusWeeks(3);
    }

    public String getLendingId() {
        return lendingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
