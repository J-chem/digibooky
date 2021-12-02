package com.switchfully.digibooky.services;

import com.switchfully.digibooky.custom.exceptions.BookIsNotAvailableException;
import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.domain.book.BookLentData;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import com.switchfully.digibooky.services.dtos.UpdateBookDTO;
import com.switchfully.digibooky.services.validators.BookValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final BookValidator bookValidator;

    public DefaultBookService(BookRepository bookRepository, BookConverter bookConverter, BookValidator bookValidator) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.bookValidator = bookValidator;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = filterDeletedBooksFromList(bookRepository.getAllBooks());
        return bookConverter.convertListOfBookInBookDto(books);
    }

    @Override
    public BookDTO getById(String id) {
        Book book = bookRepository.getById(id);
        checkIfBookIsDeleted(book);
        BookDTO bookDTO = bookConverter.convertBookToBookDTO(book);
        if (bookDTO.isLentOut()) {
            bookDTO.setDueDate(bookRepository.getDueDate(bookDTO.getId()));
        }
        return bookDTO;
    }

    @Override
    public BookDTO deleteBook(String id) {
        Book book = bookRepository.getById(id);
        book.setSoftDeleted(true);
        return bookConverter.convertBookToBookDTO(book);
    }

    @Override
    public BookDTO restoreBook(String id) {
        Book book = bookRepository.getById(id);
        book.setSoftDeleted(false);
        return bookConverter.convertBookToBookDTO(book);
    }

    @Override
    public List<BookDTO> getByAuthor(String firstname, String lastname) {
        List<Book> books = filterDeletedBooksFromList(bookRepository.getByAuthor(firstname, lastname));
        return bookConverter.convertListOfBookInBookDto(books);
    }

    @Override
    public List<BookDTO> getBookByTitle(String title) {
        List<Book> books = filterDeletedBooksFromList(bookRepository.getByTitle(title));
        return bookConverter.convertListOfBookInBookDto(books);
    }

    @Override
    public List<BookDTO> getByISBN(String isbn) {
        List<Book> books = bookRepository.getByISBN(isbn);
        return bookConverter.convertListOfBookInBookDto(books);
    }

    @Override
    public BookDTO save(CreateBookDTO createBookDTO) {
        Book newBook = bookConverter.convertCreateBookDTOToBook(createBookDTO);
        Book savedBook = bookRepository.save(newBook);
        return bookConverter.convertBookToBookDTO(savedBook);
    }

    //bookId
    @Override
    public String lendBook(User user, String bookId) {
        checkIfBookIsDeleted(bookRepository.getById(bookId));
        assertLentOutStatus(bookRepository.getById(bookId).isLentOut());
        BookLentData bookLentData = new BookLentData(user.getId(), bookId);
        bookRepository.updateDueDate(bookId, bookLentData.getDueDate());
        return bookRepository.lendBook(bookLentData);
    }

    @Override
    public String returnBook(String lendId) {
        var bookId = bookRepository.returnBookIdFromLendData(lendId);
        bookRepository.updateDueDate(bookId, null);
        return bookRepository.returnBook(lendId);
    }

    @Override
    public List<BookDTO> getAllBooksLendOutByUser(String lendOutByUser) {
        List<String> lendBookId = bookRepository.getAllLendedBooksIDByUser(lendOutByUser);
        return lendBookId.stream()
                .map(bookId -> bookRepository.getById(bookId))
                .filter(book -> !book.isSoftDeleted())
                .map(book -> bookConverter.convertBookToBookDTO(book))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(UpdateBookDTO updateBookDTO) {
        Book bookToBeUpdated = bookRepository.getById(updateBookDTO.getId());
        bookToBeUpdated.setAuthor(updateBookDTO.getAuthor());
        bookToBeUpdated.setTitle(updateBookDTO.getTitle());
        return bookConverter.convertBookToBookDTO(bookRepository.updateBook(bookToBeUpdated));
    }

    @Override
    public List<BookDTO> getBy(boolean isOverDue) {
        List<Book> books = bookRepository.getBy(isOverDue);
        return bookConverter.convertListOfBookInBookDto(books);
    }

    private void checkIfBookIsDeleted(Book book) {
        if (book.isSoftDeleted()) {
            throw new BookIsNotAvailableException("This book is deleted. ");
        }
    }

    public List<Book> filterDeletedBooksFromList(List<Book> books) {
        return books.stream()
                .filter(book -> !book.isSoftDeleted())
                .toList();
    }

    private void assertLentOutStatus(boolean isLentOut) {
        if (isLentOut) {
            throw new BookIsNotAvailableException("Sorry but this book is not available");
        }
    }
}