package com.switchfully.digibooky.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
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

    public void setDate(LocalDate date) {
        dueDate = date;
    }
}
