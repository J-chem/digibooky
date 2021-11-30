package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return bookConverter.convertBookToBookDTO(book);
    }

    @Override
    public BookDTO getBookByTitle(String title) {
        Book book = bookRepository.getByTitle(title);
        return bookConverter.convertBookToBookDTO(book);
    }

    @Override
    public BookDTO save(CreateBookDTO createBookDTO) {
        Book newBook = bookConverter.convertCreateBookDTOToBook(createBookDTO);
        Book savedBook = bookRepository.save(newBook);
        return bookConverter.convertBookToBookDTO(savedBook);
    }

    @Override
    public String lendBook(User user, String isbn) {
        Book book = bookRepository.getByISBN(isbn);
        assertLentOutStatus(book.isLentOut());

        bookRepository.updateLendOutStatus(book.getId());
        BookLentData bookLentData = new BookLentData(user.getId(), isbn);
        return bookRepository.lendBook(bookLentData);
    }

    private void assertLentOutStatus(boolean isLentOut) {
        if(isLentOut) {
           throw new UnsupportedOperationException("Implement this in Service LendBook");
        }
    }


}