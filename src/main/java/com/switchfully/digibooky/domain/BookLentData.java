package com.switchfully.digibooky.domain;

import java.time.LocalDate;
import java.util.UUID;

public class BookLentData {

    private final String lendingId;
    private final String userId;
    private final String bookId;
    private LocalDate dueDate;

    public BookLentData(String userId, String bookId) {
        this.lendingId = UUID.randomUUID().toString();
        this.userId = userId;
        this.bookId = bookId;
        this.dueDate = LocalDate.now().plusWeeks(3);
    }

    public String getLendingId() {
        return lendingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDate(LocalDate date) {
        dueDate = date;
    }
}
