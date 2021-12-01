package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.DefaultBookRepository;
import com.switchfully.digibooky.security.Role;
import com.switchfully.digibooky.services.BookService;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET MAPPINGS
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable("id") String id) {
        return bookService.getById(id);
    }

    @GetMapping(produces = "application/json", params = {"title"})
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBookByTitle(@RequestParam String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping(produces = "application/json", params = {"isbn"})
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getByISBN(@RequestParam String isbn) {
        return bookService.getByISBN(isbn);
    }

    @GetMapping(produces = "application/json", params = {"lastname", "firstname"})
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getByAuthor(@RequestParam(required = false, name="lastname") String lastname,
                                     @RequestParam(required = false, name="firstname")  String firstname) {
       return bookService.getByAuthor(firstname, lastname);
    }

    // POST MAPPINGS
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO postNewBook(@RequestBody CreateBookDTO createBookDto) {
        return bookService.save(createBookDto);
    }

    @PostMapping(path = "/{id}/lendOut", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String lendABook(@PathVariable("id") String id) {
            User user = new User.Builder("test", "test",
                new Address.Builder()
                        .withCity("NY")
                        .withPostalCode(2000)
                        .withStreetName("lala")
                        .withStreetNumber(48)
                        .build(),
                        Role.MEMBER).build();
            return bookService.lendBook(user, id);
    }

    @PostMapping(path = "/{lendId}/returnBook", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String returnBook(@PathVariable String lendId) {
        return bookService.returnBook(lendId);
    }

    @GetMapping(produces = "application/json", params = "lendOutByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBookLendOutByUserID(@RequestParam String lendOutByUser){
        return bookService.getAllBooksLendOutByUser(lendOutByUser);
    }
}
