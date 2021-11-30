package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public List<Book> getAllBooks() {
        return books.values().stream().toList();
    }

    @Override
    public Book getById(String id) {
        return books.get(id);
    }

    @Override
    public Book getByTitle(String title) {
        throw new ObjectNotFoundException();
    }

    @Override
    public Book getByISBN() {
        throw new UnsupportedOperationException("not implemented: getByISBN");
    }

    @Override
    public Book getByAuthor() {
        throw new UnsupportedOperationException("not implemented: getByAuthor");
    }

    @Override
    public Book save(Book book) {
        books.put(book.getId(), book);
        return book;
    }

    @Override
    public String lendBook(BookLentData bookLentData) {
        lentData.put(bookLentData.getLendingId(), bookLentData);
        // update isLentOut
        return bookLentData.getLendingId();
    }
}
