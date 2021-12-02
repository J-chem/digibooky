package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.domain.book.BookLentData;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    Book getById(String id);
    List<Book> getByTitle(String title);
    List<Book> getByISBN(String isbn);
    List<Book> getByAuthor(String firstname, String lastname);
    Book save(Book book);
    String lendBook(BookLentData bookLentData);
    String returnBook(String lendId);
    String returnBookIdFromLendData(String lendId);
    List<String> getAllLendedBooksIDByUser(String lendOutByUser);
    LocalDate getDueDate(String bookId);
    List<Book> getBy(boolean isOverDue);
    Book updateBook(Book bookToBeUpdated);
    void updateDueDate(String bookid, LocalDate dueDate);
}
