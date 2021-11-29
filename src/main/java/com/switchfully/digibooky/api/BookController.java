package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.repositories.DefaultBookRepository;
import com.switchfully.digibooky.services.BookService;
import com.switchfully.digibooky.services.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BookDTO postNewBook(@RequestBody CreateBookDTO createBookDto) {
        return bookService.save(createBookDto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookByTitle(@RequestParam String title) {
        return bookRepository.getByTitle();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable("id") String id) {
        return bookService.convertBookinBookDto(bookRepository.getById(id));
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBooks() {
        return bookService.convertListOfBookInBookDto(bookRepository.getAll());
    }
}
