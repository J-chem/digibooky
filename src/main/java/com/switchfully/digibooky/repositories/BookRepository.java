package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Book;

import java.util.Collection;

public interface BookRepository {
    Collection<Book> getAll();
    Book getById(String id);
    Book getByISBN();
    Book getByTitle();
    Book getByAuthor();
    String save(Book book);

}
