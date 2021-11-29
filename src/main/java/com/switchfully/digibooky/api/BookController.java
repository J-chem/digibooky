package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.repositories.DefaultBookRepository;
import com.switchfully.digibooky.services.DefaultBookService;
import com.switchfully.digibooky.services.dtos.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private DefaultBookRepository bookRepository;
    private DefaultBookService bookService;

    public BookController(DefaultBookRepository bookRepository, DefaultBookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }


    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getAllBooks(){
        return bookService.convertListOfBookInBookDto(bookRepository.getAll());
    }
}
