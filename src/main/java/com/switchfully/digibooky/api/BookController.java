package com.switchfully.digibooky.api;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.repositories.DefaultBookRepository;
import com.switchfully.digibooky.services.BookService;
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
    public String postNewBook(@RequestBody Book createBookDto){
        return bookService.save(createBookDto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookByTitle(@RequestParam String title){
        if (title.equals("1")) {
            BookDTO bookDto =  new BookDTO();
            bookDto.setTitle("Umpa lumpa");
            bookDto.setId("1");
            bookDto.setAuthor(new Author("Jan", "D.P"));
            return bookDto;
        }
        else throw  new ObjectNotFoundException("Could not find in Database");
    }



    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> getAllBooks(){
        return bookService.convertListOfBookInBookDto(bookRepository.getAll());
    }
}
