package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Book;

import java.util.Collection;

public interface BookRepository {
    public Collection<Book> getAll();
    public Book getById();
    public Book getByISBN();
    public Book getByTitle();
    public Book getByAuthor();
    public Book save();

}
