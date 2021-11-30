package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.EmptyBooksListException;
import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.custom.exceptions.EmptyBooksListException;
import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Nested
    @DisplayName("Create a book")
    class CreateABook {
        @Test
        @DisplayName("Create a book")
        void whenSavingABook_thenReturnBookId() {
            assertThat(bookRepository.save(book1)).isEqualTo(book1);
        }
    }

    @Nested
    @DisplayName("Get by id")
    class GetById {
        @Test
        @DisplayName("Id is in the list of books")
        void whenGetById_thenReturnIdFromBook1() {
            bookRepository.save(book1);
            assertThat(bookRepository.getById(id1)).isEqualTo(book1);
        }

        @Test
        @DisplayName("Id is null")
        void whenGetByIdWithNullId_thenThrowsIllegalArgumentException() {
            bookRepository.save(book1);
            assertThatThrownBy(() -> bookRepository.getById(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("The id can't be null");
        }

        @Test
        @DisplayName("Id is not in the list of books")
        void whenGetByIdWithNoExistingId_thenThrowsObjectNotFoundException() {
            bookRepository.save(book1);
            assertThatThrownBy(() -> bookRepository.getById("njnfjnfj"))
                    .isInstanceOf(ObjectNotFoundException.class)
                    .hasMessage("Book not found");
        }
    }


    @Nested
    @DisplayName("Get all books")
    class GetAllBooks {
        @Test
        @DisplayName("Get all books")
        void whenGetAllBooks_thenReturnsAListWithAllBooks() {
            bookRepository.save(book1);
            bookRepository.save(book2);
            assertThat(bookRepository.getAllBooks()).containsOnly(book1, book2);
        }

        @Test
        @DisplayName("List of books is empty")
        void whenGetAllBooksWithEmptyListOfBooks_thenThrowsEmptyBooksListException() {
            assertThatThrownBy(() -> bookRepository.getAllBooks())
                    .isInstanceOf(EmptyBooksListException.class)
                    .hasMessage("List of books is empty");
        }
    }


    @Nested
    @DisplayName("Update a book")
    class UpdateBook {
        @Test
        @DisplayName("Update a book")
        void whenUpdateLendStatus_thenBookDBIsUpdated() {
            bookRepository.save(book1);

            bookRepository.updateLendOutStatus(id1);
            Book book = bookRepository.getById(id1);
            assertThat(book.isLentOut()).isTrue();

            bookRepository.updateLendOutStatus(id1);
            book = bookRepository.getById(id1);
            assertThat(book.isLentOut()).isFalse();
        }
    }

    @Nested
    @DisplayName("Lending a book")
    class LendingBook {
        @Test
        @DisplayName("Lending a book")
        void whenLendingABook_thenReturnsLendingID() {
            bookRepository.save(book1);
            BookLentData bookLentData = new BookLentData("test", "test");
            String lendingId = bookLentData.getLendingId();
            assertThat(bookRepository.lendBook(bookLentData)).isEqualTo(lendingId);
        }
    }

    @Nested
    @DisplayName("Get books by ISBN")
    class GetByISBN {
        @Test
        @DisplayName("Get by ISBN")
        void whenGetByISBN_thenReturnBook1() {
            bookRepository.save(book1);
            String isbn = book1.getIsbn();
            assertThat(bookRepository.getByISBN(isbn)).isEqualTo(List.of(book1));
        }

        @Test
        @DisplayName("Book doesn't exist")
        void whenGetByISBN_bookDoesntExist_trows() {
            bookRepository.save(book2);
            String isbn = book1.getIsbn();
            assertThat(bookRepository.getByISBN(isbn)).isEmpty();
        }

        @Test
        @DisplayName("List of books empty")
        void whenGetByISBN_listOfBooksIsEmpty_trows() {
            String isbn = book1.getIsbn();
            assertThatThrownBy(() -> bookRepository.getByISBN(isbn))
                    .isInstanceOf(EmptyBooksListException.class)
                    .hasMessage("List of books is empty");
        }
    }

}