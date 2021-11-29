package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DefaultBookRepository implements BookRepository {
    private final ConcurrentHashMap<String, Book> books;
    private final ConcurrentHashMap<String, BookLentData> lentData;

    public DefaultBookRepository() {
        books = new ConcurrentHashMap<>();
        lentData = new ConcurrentHashMap<>();
    }

    @Override
    public Collection<Book> getAll() {
        throw new UnsupportedOperationException("getAll not implemented");
    }

    @Override
    public Book getById() {
        throw new UnsupportedOperationException("getById not implemented");
    }

    @Override
    public Book getByISBN() {
        throw new UnsupportedOperationException("getByISBN not implemented");
    }

    @Override
    public Book getByTitle() {
        throw new UnsupportedOperationException("getByTitle not implemented");
    }

    @Override
    public Book getByAuthor() {
        throw new UnsupportedOperationException("getByAuthor not implemented");
    }

    @Override
    public String save(Book book) {
        books.put(book.getId(), book);
        return book.getId();
    }

    @Override
    public String lendBook(BookLentData bookLentData) {
        lentData.put(bookLentData.getLendingId(), bookLentData);
        return bookLentData.getLendingId();
    }




}
