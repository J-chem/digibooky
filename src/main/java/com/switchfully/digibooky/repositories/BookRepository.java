package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public interface BookRepository {
    Collection<Book> getAll();
    Book getById(String id);
    Book getByISBN();
    Book getByTitle();
    Book getByAuthor();
    Book save(Book book);
    String lendBook(BookLentData bookLentData);
}
