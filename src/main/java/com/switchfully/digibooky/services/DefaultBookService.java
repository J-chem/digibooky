package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.BookDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import org.springframework.stereotype.Service;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO convertBookinBookDto(Book book) {
        return new BookDTO(book);
    }

    @Override
    public Book convertBookDtoInBook(BookDTO bookDTO) {
        return new Book(bookDTO);
    }

    @Override
    public Collection<BookDTO> convertListOfBookInBookDto(Collection<Book> booksList) {
        return booksList.stream().map(BookDTO::new).collect(Collectors.toList());
    }
}




    @Override
    public String save(Book createBookDTO) {
        return bookRepository.save(createBookDTO);
    }
}