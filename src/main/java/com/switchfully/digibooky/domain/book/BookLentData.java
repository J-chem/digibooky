package com.switchfully.digibooky.domain.book;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class BookLentData {

    private static final int TIME_YOU_CAN_LENT_A_BOOK = 3;

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

    public void setDate(LocalDate date) {
        dueDate = date;
    }
}
