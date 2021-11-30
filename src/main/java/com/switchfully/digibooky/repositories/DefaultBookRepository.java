package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.EmptyBooksListException;
import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import com.switchfully.digibooky.repositories.validators.Validator;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import static com.switchfully.digibooky.repositories.validators.Validator.*;

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
        assertBooksIsEmpty(books);
        return books.values().stream().toList();
    }

    @Override
    public Book getById(String id) {
        assertBooksIsEmpty(books);
        assertStringNotNull(id, "id");
        if (!books.containsKey(id)){
            throw new ObjectNotFoundException("Book not found");
        }
        return books.get(id);
    }

    @Override
    public Book getByTitle(String title) {
        assertBooksIsEmpty(books);
        assertStringNotNull(title, "title");
        throw new ObjectNotFoundException();
    }

    @Override
    public Book getByISBN(String isbn) {
        assertBooksIsEmpty(books);
        assertStringNotNull(isbn, "isbn");
        return books.values()
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("This book doesn't exist"));
    }

    @Override
    public Book getByAuthor(Author author) {
        throw new UnsupportedOperationException("not implemented: getByAuthor");
    }

    @Override
    public Book save(Book book) {
        books.put(book.getId(), book);
        return book;
    }

    @Override
    public String lendBook(BookLentData bookLentData) {
        assertBooksIsEmpty(books);
        lentData.put(bookLentData.getLendingId(), bookLentData);
        return bookLentData.getLendingId();
    }

    @Override
    public void updateLendOutStatus(String id) {
        assertBooksIsEmpty(books);
        assertStringNotNull(id, "id");
        Book book = books.get(id);
        book.setLentOut(!book.isLentOut());
    }

}
