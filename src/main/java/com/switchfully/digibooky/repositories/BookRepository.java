package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;

import java.util.Collection;

public interface BookRepository {
    Collection<Book> getAll();
    Book getById();
    Book getByISBN();
    Book getByTitle();
    Book getByAuthor();
    String save(Book book);
    String lendBook(BookLentData bookLentData);
}
