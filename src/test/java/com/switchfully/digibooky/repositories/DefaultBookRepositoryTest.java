package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.EmptyBooksListException;
import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.domain.user.User.Builder;
import com.switchfully.digibooky.security.Role;
import com.switchfully.digibooky.services.BookConverter;
import com.switchfully.digibooky.services.DefaultBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DefaultBookRepositoryTest {

    private DefaultBookRepository bookRepository;
    private DefaultBookService bookService;
    private BookConverter bookConverter;
    private User user;
    private Book book1;
    private Book book2;
    //private Book book3;
    private Author author1;
    private Author author2;
    //private Author author3;
    private String id1;

    @BeforeEach
    void beforeEach() {
        user = new Builder("Maxim",
                "Verbeeck",
                new Address.Builder()
                        .withCity("NY")
                        .withPostalCode(2000)
                        .withStreetNumber(13)
                        .withStreetName("Walibi")
                        .build(),
                Role.ADMIN)
                .withEmail("banana@hotmail.com")
                .withSocialSecurityNumber("12345")
                .withPassword("monkey")
                .withUsername("HulaBaloo")
                .build();
        bookConverter = new BookConverter();
        bookRepository = new DefaultBookRepository();
        bookService = new DefaultBookService(bookRepository, bookConverter);
        author1 = new Author("test", "test");
        author2 = new Author("tEst2", "test2");

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

        @BeforeEach
        void setUp() {
            bookRepository.save(book1);
        }

        @Test
        void whenUpdateLendStatus_thenBookDBIsUpd() {
            bookRepository.updateDueDate(book1.getId(), LocalDate.now());
            Book book = bookRepository.getById(id1);
            assertThat(book.isLentOut()).isTrue();
            assertThat(book.getDueDate()).isEqualTo(LocalDate.now());

            bookRepository.updateDueDate(book1.getId(), null);
            book = bookRepository.getById(id1);
            assertThat(book.isLentOut()).isFalse();
            assertThat(book.getDueDate()).isNull();
        }


        @Test
        void updateBook() {
            Book bookToUpdate = new Book(book1.getIsbn(), book1.getTitle(), book1.getAuthor());
            Book bookUpdated = bookRepository.updateBook(bookToUpdate);
            assertThat(bookUpdated).isEqualTo(bookToUpdate);
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

        @Test
        @DisplayName("Lending a book")
        void whenLendingABook_thenReturnsLendingID_noBooksInTheCollection() {
            BookLentData bookLentData = new BookLentData("test", "test");
            String lendingId = bookLentData.getLendingId();
            assertThatThrownBy(() -> bookRepository.lendBook(bookLentData))
                    .isInstanceOf(EmptyBooksListException.class)
                    .hasMessage("List of books is empty");
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
        void whenGetByISBN_bookDoesntExist_trows() { //todo: naming!
            bookRepository.save(book2);
            //String isbn = book1.getIsbn();
            //assertThat(bookRepository.getByISBN(isbn)).isEmpty();
            assertThat(bookRepository.getByISBN("somethingthatdoesntexist").isEmpty());
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

    @Nested
    @DisplayName("Return a book")
    class returnABook {

        @Test
        void returnABookOnTime() {
            bookRepository.save(book1);
            String lendingId = bookRepository.lendBook(new BookLentData("UserId", book1.getId()));

            assertThat(bookRepository.returnBook(lendingId)).isEqualTo("You're on time");
        }

        @Test
        void returnABookToLate() {
            bookRepository.save(book1);
            BookLentData lendingBookData = new BookLentData("UserId", book1.getId());
            lendingBookData.setDate(LocalDate.now().minusDays(21));
            String lendingId = bookRepository.lendBook(lendingBookData);

            assertThat(bookRepository.returnBook(lendingId)).isEqualTo("You are late whit your books");
        }
    }

    @Nested
    @DisplayName("return book id from lend data")
    class returnBookIdFromLendData {
        String lendId;

        @BeforeEach
        void setup() {
            bookRepository.save(book1);
            lendId = bookRepository.lendBook(new BookLentData("User1", book1.getId()));
        }

        @Test
        void getBookId() {
            assertThat(bookRepository.returnBookIdFromLendData(lendId)).isEqualTo(book1.getId());
        }

        @Test
        void getBookId_LenderIdDoesntExist() {
            assertThatThrownBy(() -> bookRepository.returnBookIdFromLendData("someId"))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage("There are no books to show");
        }
    }

    @Nested
    @DisplayName("get all lended books by UserID")
    class GetLendedBooksByUserId {
        @BeforeEach
        void setup() {
            bookRepository.save(book1);
            bookRepository.lendBook(new BookLentData("User1", book1.getId()));
        }

        @Test
        @DisplayName("Get all lended books by UserId")
        void givenUserGetAllLendedBooks() {
            assertThat(bookRepository.getAllLendedBooksIDByUser("User1")).contains(book1.getId());
        }

        @Test
        @DisplayName("Get all lended books by UserId no books are lent out by user")
        void givenUserGetAllLendedBooks_NoBooksAreLedOutByUser() {
            assertThat(bookRepository.getAllLendedBooksIDByUser("anotherUser")).isEmpty();
        }
    }

    @Nested
    @DisplayName("Get books by author")
    class GetByAuthor {
        @BeforeEach
        void beforeEach() {
            bookRepository.save(book1);
            bookRepository.save(book2);
        }

        @Test
        @DisplayName("Get by first and last name (parameters are not null)")
        void whenGettingABookByFirstAndLastnameNotNull__returnListOfBooksDTO() {
            assertThat(bookRepository.getByAuthor("test", "2")).isEqualTo(List.of(book2));
        }

        @Test
        @DisplayName("Get by first and last name (both params are null)")
        void whenGettingABookByFirstAndLastnameAreNull__returnEmptyList() {
            assertThatThrownBy(() -> bookRepository.getByAuthor(null, null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Both params can't be null!");
        }

        @Test
        @DisplayName("Get by firstname (lastname is null")
        void whenGettingABookByFirstName_lastNameIsNull_returnExpectedBooklist() {
            assertThat(bookRepository.getByAuthor("test2", null)).isEqualTo(List.of(book2));
        }

        @Test
        @DisplayName("Get by firstname (lastname is null) - Ignores Upper Cases")
        void whenGettingABookByFirstName_lastNameIsNull_returnExpectedBooklist_IgnoresUpperCase() {
            assertThat(bookRepository.getByAuthor("TeSt2", null)).isEqualTo(List.of(book2));
            System.out.println(book2.getAuthor().getFirstName() + " " + book2.getAuthor().getLastName());
        }

        @Test
        @DisplayName("Get by firstname (lastname is null) - Ignores Upper Cases 2")
        void whenGettingABookByFirstName_lastNameIsNull_returnExpectedBooklist_IgnoresUpperCase2() {
            System.out.println(book2.getAuthor().getFirstName() + " " + book2.getAuthor().getLastName());
            assertThat(bookRepository.getByAuthor("TEST2", null)).isEqualTo(List.of(book2));
        }

        @Test
        @DisplayName("Get by lastname (firstname is null")
        void whenGettingABookByLastName_fistNameIsNull_returnExpectedBookList() {
            assertThat(bookRepository.getByAuthor(null, "test2")).isEqualTo(List.of(book2));
        }
    }

    @Nested
    @DisplayName("Get by author when list of books empty")
    class emptyBookList {
        @Test
        @DisplayName("Get by author when list of books empty")
        void whenGetByAuthor_listOfBooksIsEmpty_trows() {
            assertThatThrownBy(() -> bookRepository.getByAuthor("test", "test"))
                    .isInstanceOf(EmptyBooksListException.class)
                    .hasMessage("List of books is empty");
        }
    }

    @Nested
    @DisplayName("Get due date")
    class GetDueDate {
        @BeforeEach
        void setup() {
            bookRepository.save(book1);
            bookRepository.lendBook(new BookLentData("User1", book1.getId()));
        }

        @Test
        void getDueDateForLentBook() {
            assertThat(bookRepository.getDueDate(book1.getId())).isEqualTo(LocalDate.now().plusDays(21));
        }

        @Test
        void getNullDueDateForNoLentBook() {
            assertThatThrownBy(() -> bookRepository.getDueDate(book2.getId())).isInstanceOf(NoSuchElementException.class).hasMessage("Due date not find.");
        }
    }

    @Nested
    @DisplayName("Get over due books")
    class GetOverDueBooks {
        @BeforeEach
        void setUp() {
            bookRepository.save(book1);
            bookService.lendBook(user, book1.getId());
        }

        @Test
        void getOverDueBooks_ReturnsEmptyList() {
            assertThat(bookRepository.getBy(true)).isEmpty();
        }

        @Test
        void getNotOverDueBooks_returnsListWithBook1() {
            assertThat(bookRepository.getBy(false)).containsExactly(book1);
        }
    }
}