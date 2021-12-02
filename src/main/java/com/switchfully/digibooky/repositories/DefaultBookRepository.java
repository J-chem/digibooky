package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import lombok.NonNull;
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
    public Book getById(@NonNull String id) {
        assertDataManagementMapIsNotEmpty(books);

        if (!books.containsKey(id)) {
            throw new ObjectNotFoundException("Book not found");
        }
        return books.get(id);
    }

    @Override
    public List<Book> getByTitle(@NonNull String title) {
        assertDataManagementMapIsNotEmpty(books);

        return books.values()
                .stream()
                .filter(book -> transformToLowerCaseAndNoSpaces(book.getTitle()).contains(transformToLowerCaseAndNoSpaces(title)))
                .toList();
    }

    @Override
    public List<Book> getByISBN(@NonNull String isbn) {
        assertDataManagementMapIsNotEmpty(books);

        return books.values()
                .stream()
                .filter(book -> book.getIsbn().contains(isbn))
                .toList();
    }

    @Override
    public List<Book> getByAuthor(String firstname, String lastname) {
        assertDataManagementMapIsNotEmpty(books);
        assertAllParamsNotNull(firstname, lastname);

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
    public void updateDueDate(@NonNull String bookId,
                              @NonNull LocalDate dueDate) {
        assertDataManagementMapIsNotEmpty(books);

        updateLendOutStatus(bookId);
        var book = books.get(bookId);
        book.setDueDate(dueDate);
        books.put(bookId, book);
    }

    @Override
    public void updateLendOutStatus(@NonNull String bookId) {
        assertDataManagementMapIsNotEmpty(books);

        Book book = books.get(bookId);
        book.setLentOut(!book.isLentOut());
    }

    @Override
    public String returnBook(@NonNull String lendId) {
        assertDataManagementMapIsNotEmpty(lentData);

        BookLentData bookLentData = lentData.get(lendId);
        return (LocalDate.now().compareTo(bookLentData.getDueDate()) <= 0) ? "You're on time" : "You are late whit your books";
    }

    @Override
    public String returnBookIdFromLendData(@NonNull String lendId) {
        assertDataManagementMapIsNotEmpty(lentData);

        BookLentData bookLentData = lentData.get(lendId);
        if (bookLentData == null) {
            throw new NoSuchElementException("There are no books to show");
        }
        return bookLentData.getBookId();
    }

    @Override
    public List<String> getAllLendedBooksIDByUser(@NonNull String lendOutByUser) {
        return lentData.values().stream()
                .filter(data -> data.getUserId().equals(lendOutByUser))
                .map(BookLentData::getBookId)
                .collect(Collectors.toList());
    }

    @Override
    public LocalDate getDueDate(@NonNull String bookId) {
        return lentData.values()
                .stream()
                .filter(lentData -> lentData.getBookId().equals(bookId))
                .map(BookLentData::getDueDate)
                .sorted()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Due date not found"));
    }

    @Override
    public List<Book> getBy(boolean isOverDue) {
        return books.values()
                .stream()
                .collect(Collectors.partitioningBy(book -> book.getDueDate().compareTo(LocalDate.now()) < 0))
                .get(isOverDue)
                .stream()
                .toList();
    }
}
