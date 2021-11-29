package com.switchfully.digibooky.services;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import com.switchfully.digibooky.services.dtos.BookDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO convertBookinBookDto(Book book) {
        return new BookDTO()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setIsbn(book.getIsbn())
                .setAuthor(book.getAuthor());
    }

    @Override
    public Book convertBookDtoInBook(BookDTO bookDTO) {
        return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor());
    }

    @Override
    public Book convertCreateBookDtoInBook(CreateBookDTO createBookDTO) {
        return new Book(createBookDTO.getIsbn(), createBookDTO.getTitle(), createBookDTO.getAuthor());
    }

    @Override
    public Collection<BookDTO> convertListOfBookInBookDto(Collection<Book> booksList) {
        return booksList.stream()
                .map(book -> convertBookinBookDto(book))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO save(CreateBookDTO createBookDTO) {
        Book newBook = convertCreateBookDtoInBook(createBookDTO);
        return convertBookinBookDto(bookRepository.save(newBook));
    }


    @Override
    public String lendBook(User user, BookDTO bookDTO) {
        BookLentData bookLentData = new BookLentData(user.getId(), bookDTO.getIsbn());
//        if(bookRepository.getByISBN(bookDTO.getIsbn()).isLentOut()) {
//            throw new ObjectNotFoundException();
//        }
        return bookRepository.lendBook(bookLentData);
    }

    @Override
    public Book getBookByTitle(String title) {
        return null;
    }
}