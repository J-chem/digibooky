package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
        assertDataManagementMapIsNotEmpty(books);
        return books.values().stream().toList();
    }

    @Override
    public Book getById(String id) {
        assertDataManagementMapIsNotEmpty(books);
        assertStringNotNull(id, "id");
        if (!books.containsKey(id)) {
            throw new ObjectNotFoundException("Book not found");
        }
        return books.get(id);
    }

    @Override
    public List<Book> getByTitle(String title) {
        assertDataManagementMapIsNotEmpty(books);
        assertStringNotNull(title, "title");
        return books.values()
                .stream()
                .filter(book -> book.getTitle().contains(title))
                .toList();
    }

    @Override
    public List<Book> getByISBN(String isbn) {
        assertDataManagementMapIsNotEmpty(books);
        assertStringNotNull(isbn, "isbn");
        return books.values()
                .stream()
                .filter(book -> book.getIsbn().contains(isbn))
                .toList();
    }

    @Override
    public List<Book> getByAuthor(String firstname, String lastname) {
        assertDataManagementMapIsNotEmpty(books);
        if (lastname == null || firstname == null) {
            return books.values()
                    .stream()
                    .filter(book -> book.getAuthor().getFirstName().contains(firstname) || book.getAuthor().getFirstName().contains(lastname))
                    .toList();
        }
        return books.values()
                .stream()
                .filter((book -> book.getAuthor().getFirstName().contains(firstname) && book.getAuthor().getLastName().contains(lastname)))
                .toList();
    }


    @Override
    public Book save(Book book) {
        books.put(book.getId(), book);
        return book;
    }

    @Override
    public String lendBook(BookLentData bookLentData) {
        assertDataManagementMapIsNotEmpty(books);
        lentData.put(bookLentData.getLendingId(), bookLentData);
        return bookLentData.getLendingId();
    }

    @Override
    public void updateLendOutStatus(String id) {
        assertDataManagementMapIsNotEmpty(books);
        assertStringNotNull(id, "id");
        Book book = books.get(id);
        book.setLentOut(!book.isLentOut());
    }

    @Override
    public String returnBook(String lendId) {
        assertDataManagementMapIsNotEmpty(lentData);
        BookLentData bookLentData = lentData.get(lendId);
        return (LocalDate.now().compareTo(bookLentData.getDueDate()) <= 0) ? "You're on time" : "You are late whit your books";
    }

    @Override
    public String returnBookIdFromLendData(String lendId){
        assertDataManagementMapIsNotEmpty(lentData);
        BookLentData bookLentData = lentData.get(lendId);
        if(bookLentData ==null){
            throw new NoSuchElementException("There are no books to show");
        }
        return bookLentData.getBookId();
    }

    @Override
    public List<String> getAllLendedBooksIDByUser(String lendOutByUser) {
        return lentData.values().stream()
                .filter(data -> data.getUserId().equals(lendOutByUser))
                .map(book -> book.getBookId())
                .collect(Collectors.toList());
    }

//    public void assertBooksIsEmpty() {
//        if (books.isEmpty()){
//            throw new EmptyBooksListException("List of books is empty");
//        }
//    }



}
