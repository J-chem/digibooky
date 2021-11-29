package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.repositories.BookRepository;
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
    public Collection<BookDTO> convertListOfBookInBookDto(Collection<Book> booksList) {
        return booksList.stream()
                .map(book -> convertBookinBookDto(book))
                .collect(Collectors.toList());
    }

    @Override
    public String save(Book createBookDTO) {
        return bookRepository.save(createBookDTO);
    }

    @Override
    public Book getBookByTitle(String title) {
        return null;
    }
}