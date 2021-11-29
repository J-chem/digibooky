package com.switchfully.digibooky.services;

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
    public String save(Book createBookDTO) {
        return bookRepository.save(createBookDTO);
    }
}