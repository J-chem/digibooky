package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
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
    public Book getById(String id) {
        return books.get(id);
    }

    @Override
    public Book getByISBN() {
        return null;
    }

    @Override
    public Book getByTitle() {
        throw new ObjectNotFoundException("");
    }

    @Override
    public Book getByAuthor() {
        return null;
    }

    @Override
    public Book save(Book book) {
        books.put(book.getId(), book);
        return book;
    }

    @Override
    public String lendBook(BookLentData bookLentData) {
        return null;
    }
}
