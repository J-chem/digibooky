package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DefaultBookRepository implements BookRepository {
    private final ConcurrentHashMap<String, Book> books;

    public DefaultBookRepository() {
        books = new ConcurrentHashMap<>();
    }

    @Override
    public Collection<Book> getAll() {
        return null;
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
    public String save(Book book) {
        books.put(book.getId(), book);
        return book.getId();
    }
}
