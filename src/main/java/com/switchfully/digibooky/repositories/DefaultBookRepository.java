package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DefaultBookRepository implements BookRepository {
    private final ConcurrentHashMap<String, Book> books;

    public DefaultBookRepository() {
        books = new ConcurrentHashMap<>();
    }

    @Override
    public Collection<Book> getAll() {
        return books.values();
    }

    @Override
    public Book getById() {
        return null;
    }

    @Override
    public Book getByISBN() {
        return null;
    }

    @Override
    public Book getByTitle() {
        return null;
    }

    @Override
    public Book getByAuthor() {
        return null;
    }

    @Override
    public Book save() {
        return null;
    }
}
