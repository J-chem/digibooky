package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.EmptyBooksListException;
import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DefaultBookRepositoryTest {

    private DefaultBookRepository bookRepository;
    private Book book1;
    private Book book2;
    private Author author1;
    private Author author2;
    private String id1;

    @BeforeEach
    void beforeEach() {
        bookRepository = new DefaultBookRepository();
        author1 = new Author("test", "test");
        author2 = new Author("test2", "test2");
        book1 = new Book("isbn1", "test", author1);
        book2 = new Book("isbn2", "test2", author2);
        id1 = idOfBook1();
    }

    private String idOfBook1() {
        return book1.getId();
    }

    @Test
    void whenSavingABook_thenReturnBookId() {
        assertThat(bookRepository.save(book1)).isEqualTo(book1);
    }

    @Test
    void whenGetById_thenReturnIdFromBook1() {
        bookRepository.save(book1);
        assertThat(bookRepository.getById(id1)).isEqualTo(book1);
    }

    @Test
    void whenUpdateLendStatus_thenBookDBIsUpdated() {
        bookRepository.save(book1);

        bookRepository.updateLendOutStatus(id1);
        Book book = bookRepository.getById(id1);
        assertThat(book.isLentOut()).isTrue();

        bookRepository.updateLendOutStatus(id1);
        book = bookRepository.getById(id1);
        assertThat(book.isLentOut()).isFalse();
    }

    @Test
    void whenLendingABook_thenReturnsLendingID() {
        bookRepository.save(book1);
        BookLentData bookLentData = new BookLentData("test", "test");
        String lendingId = bookLentData.getLendingId();
        assertThat(bookRepository.lendBook(bookLentData)).isEqualTo(lendingId);
    }

    @Test
    void whenGetByISBN_thenReturnBook1() {
        bookRepository.save(book1);
        String isbn = book1.getIsbn();
        assertThat(bookRepository.getByISBN(isbn)).isEqualTo(book1);
    }

    @Test
    void whenGetByISBN_bookDoesntExist_trows() {
        String isbn = book1.getIsbn();
        assertThatThrownBy(() -> bookRepository.getByISBN(isbn))
                .isInstanceOf(EmptyBooksListException.class)
                .hasMessage("List of books is empty");
    }
}