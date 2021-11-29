package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.repositories.DefaultBookRepository;
import com.switchfully.digibooky.services.BookService;
import com.switchfully.digibooky.services.DefaultBookService;
import com.switchfully.digibooky.services.dtos.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final DefaultBookRepository bookRepository;
    private final BookService bookService;

    public BookController(DefaultBookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String postNewBook(Book createBookDto){
        return bookService.save(createBookDto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getAllBooks(){
        return bookService.convertListOfBookInBookDto(bookRepository.getAll());
    }
}
