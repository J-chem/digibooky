package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.DefaultBookRepository;
import com.switchfully.digibooky.security.Role;
import com.switchfully.digibooky.services.BookService;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
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

    @GetMapping(path = "?title={title}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable("id") String id) {
        return bookService.getById(id);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(path = "?isbn={ISBN}",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String lendABook(@RequestParam String ISBN) {
        User user = new User.Builder("test", "test",
                new Address.Builder()
                        .withCity("NY")
                        .withPostalCode(2000)
                        .withStreetName("lala")
                        .withStreetNumber(48)
                        .build(),
                        Role.MEMBER).build();

        return bookService.lendBook(user, ISBN);
    }
}
