package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    Book getById(String id);
    Book getByTitle(String title);
    Book getByISBN(String isbn);
    Book getByAuthor(Author author);
    Book save(Book book);
    String lendBook(BookLentData bookLentData);
    void updateLendOutStatus(String id);
}
