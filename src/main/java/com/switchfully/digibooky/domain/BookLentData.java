package com.switchfully.digibooky.domain;

import java.time.LocalDate;
import java.util.UUID;

public class BookLentData {

    public static final int TIME_YOU_CAN_LENT_A_BOOK = 3;
    private final String lendingId;
    private final String userId;
    private final String bookId;
    private LocalDate dueDate;

    public BookLentData(String userId, String bookId) {
        this.lendingId = UUID.randomUUID().toString();
        this.userId = userId;
        this.bookId = bookId;
        this.dueDate = LocalDate.now().plusWeeks(TIME_YOU_CAN_LENT_A_BOOK);
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
