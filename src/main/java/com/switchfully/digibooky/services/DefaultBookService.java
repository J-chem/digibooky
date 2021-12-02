package com.switchfully.digibooky.services;

import com.switchfully.digibooky.custom.exceptions.BookIsNotAvailableException;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public DefaultBookService(BookRepository bookRepository, BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        return bookConverter.convertListOfBookInBookDto(books);
    }

    @Override
    public BookDTO getById(String id) {
        Book book = bookRepository.getById(id);
        BookDTO bookDTO = bookConverter.convertBookToBookDTO(book);
        if (bookDTO.isLentOut()) {
            bookDTO.setDueDate(bookRepository.getDueDate(bookDTO.getId()));
        }
        return bookDTO;
    }

    @Override
    public List<BookDTO> getByAuthor(String firstname, String lastname) {
        List<Book> books = bookRepository.getByAuthor(firstname, lastname);
        return bookConverter.convertListOfBookInBookDto(books);
    }

    @Override
    public List<BookDTO> getBookByTitle(String title) {
        List<Book> books = bookRepository.getByTitle(title);
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

    @Override
    public String lendBook(User user, String bookId) {
        assertLentOutStatus(bookRepository.getById(bookId).isLentOut());
        BookLentData bookLentData = new BookLentData(user.getId(), bookId);
        bookRepository.updateDueDate(bookId, bookLentData.getDueDate());
        return bookRepository.lendBook(bookLentData);
    }

    @Override
    public String returnBook(String lendId) {
        var bookId = bookRepository.returnBookIdFromLendData(lendId);
        bookRepository.updateLendOutStatus(bookId);
        bookRepository.updateDueDate(bookId, null);
        return bookRepository.returnBook(lendId);
    }

    @Override
    public List<BookDTO> getAllBooksLendOutByUser(String lendOutByUser) {
        List<String> lendBookId = bookRepository.getAllLendedBooksIDByUser(lendOutByUser);
        return lendBookId.stream()
                .map(bookRepository::getById)
                .map(bookConverter::convertBookToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBy(boolean isOverDue) {
        List<Book> books = bookRepository.getBy(isOverDue);
        return bookConverter.convertListOfBookInBookDto(books);
    }

    private void assertLentOutStatus(boolean isLentOut) {
        if (isLentOut) {
            throw new BookIsNotAvailableException("Sorry but this book is not available");
        }
    }
}