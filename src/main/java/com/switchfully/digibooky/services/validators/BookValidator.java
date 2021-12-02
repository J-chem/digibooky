package com.switchfully.digibooky.services.validators;

import com.switchfully.digibooky.custom.exceptions.BookIsNotAvailableException;
import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {
    public void assertLentOutStatus(Book book) {
        if (book.isLentOut()) {
            throw new BookIsNotAvailableException("Sorry but this book is not available");
        }
    }
}
