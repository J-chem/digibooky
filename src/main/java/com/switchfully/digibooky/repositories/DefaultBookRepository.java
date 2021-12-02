package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
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
                .filter(book -> transformToLowerCaseAndNoSpaces(book.getTitle()).contains(transformToLowerCaseAndNoSpaces(title)))
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
        if (lastname == null && firstname == null) {
            throw new IllegalArgumentException("Both params can't be null!");
        }

        if (lastname == null) {
            return books.values()
                    .stream()
                    .filter(book -> transformToLowerCaseAndNoSpaces(book.getAuthor().getFirstName())
                            .contains(transformToLowerCaseAndNoSpaces(firstname)))
                    .toList();
        }
        if (firstname == null) {
            return books.values()
                    .stream()
                    .filter(book -> transformToLowerCaseAndNoSpaces(book.getAuthor().getLastName())
                            .contains(transformToLowerCaseAndNoSpaces(lastname)))
                    .toList();
        }
        return books.values()
                .stream()
                .filter((book -> transformToLowerCaseAndNoSpaces(book.getAuthor().getFirstName())
                        .contains(transformToLowerCaseAndNoSpaces(firstname)) &&
                        transformToLowerCaseAndNoSpaces(book.getAuthor().getLastName())
                        .contains(transformToLowerCaseAndNoSpaces(lastname))))
                .toList();
    }

    private String transformToLowerCaseAndNoSpaces(String param) {
        return param.toLowerCase().replaceAll("\\s", "");
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
        return (LocalDate.now().compareTo(bookLentData.getDueDate()) <= 0) ? "You're on time" : "You are late with your books";
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
                .map(BookLentData::getBookId)
                .collect(Collectors.toList());
    }

    @Override
    public LocalDate getDueDate(String bookId) {
        return lentData.values()
                .stream()
                .filter(lentData -> lentData.getBookId().equals(bookId))
                .map(lentData -> lentData.getDueDate())
                .sorted()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Due date not found"));
    }

    @Override
    public List<Book> getBy(boolean isOverDue) {
        Map<Boolean, List<BookLentData>> dueAndOverDueBooks = collectByOverDueAndNotOverDue();
        return getBooksByDueAndNotOverDue(isOverDue, dueAndOverDueBooks);
    }

    private List<Book> getBooksByDueAndNotOverDue(boolean isOverDue, Map<Boolean, List<BookLentData>> dueAndOverDueBooks) {
        return dueAndOverDueBooks.get(isOverDue)
                .stream()
                .map(BookLentData::getBookId)
                .flatMap(id -> books.values()
                        .stream()
                        .filter(book -> book.getId().equals(id)))
                .toList();
    }

    private Map<Boolean, List<BookLentData>> collectByOverDueAndNotOverDue() {
        return lentData.values()
                .stream()
                .collect(Collectors.partitioningBy(date -> date.getDueDate().compareTo(LocalDate.now()) < 0));
    }
}
