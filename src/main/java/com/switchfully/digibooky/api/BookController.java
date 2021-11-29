package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String postNewBook(@RequestBody Book createBookDto){
        return bookService.save(createBookDto);
    }
}
