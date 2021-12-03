package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.domain.book.BookLentData;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.switchfully.digibooky.repositories.validators.Validator.assertAllParamsNotNull;
import static com.switchfully.digibooky.repositories.validators.Validator.assertDataManagementMapIsNotEmpty;

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
        assertAllParamsNotNull(id);

        if (!books.containsKey(id)) {
            throw new ObjectNotFoundException("Book not found");
        }
        return books.get(id);
    }

    @Override
    public List<Book> getByTitle(String title) {
        assertDataManagementMapIsNotEmpty(books);
        assertAllParamsNotNull(title);

        return books.values()
                .stream()
                .filter(book -> transformToLowerCaseAndNoSpaces(book.getTitle()).contains(transformToLowerCaseAndNoSpaces(title)))
                .toList();
    }

    @Override
    public List<Book> getByISBN(String isbn) {
        assertDataManagementMapIsNotEmpty(books);
        assertAllParamsNotNull(isbn);

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
    public void updateDueDate( String bookId,
                               LocalDate dueDate) {
        assertDataManagementMapIsNotEmpty(books);

        var book = books.get(bookId);
        book.setDueDate(dueDate);
        book.setLentOut(!book.isLentOut());
        books.put(bookId, book);
    }



    @Override
    public String returnBook( String lendId) {
        assertDataManagementMapIsNotEmpty(lentData);

        BookLentData bookLentData = lentData.get(lendId);
        return (LocalDate.now().compareTo(bookLentData.getDueDate()) <= 0) ? "You're on time" : "You are late whit your books";
    }

    @Override
    public String returnBookIdFromLendData( String lendId) {
        assertDataManagementMapIsNotEmpty(lentData);

        BookLentData bookLentData = lentData.get(lendId);
        if (bookLentData == null) {
            throw new NoSuchElementException("There are no books to show");
        }
        return bookLentData.getBookId();
    }

    @Override
    public List<String> getAllLendedBooksIDByUser( String lendOutByUser) {
        return lentData.values().stream()
                .filter(data -> data.getUserId().equals(lendOutByUser))
                .map(BookLentData::getBookId)
                .collect(Collectors.toList());
    }

    @Override
    public LocalDate getDueDate( String bookId) {
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
                .collect(Collectors.partitioningBy(book -> {
                    var date = book.getDueDate();
                    boolean result = false;
                    if (date != null) {
                        result = date.compareTo(LocalDate.now()) < 0;
                    }
                    return result;
                }))
                .get(isOverDue)
                .stream()
                .toList();
    }

    @Override
    public Book updateBook(Book bookToBeUpdated) {
        books.put(bookToBeUpdated.getId(), bookToBeUpdated);
        return bookToBeUpdated;
    }


}
