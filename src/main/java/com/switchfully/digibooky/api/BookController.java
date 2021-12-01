package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.security.Features;
import com.switchfully.digibooky.security.Role;
import com.switchfully.digibooky.security.SecureUser;
import com.switchfully.digibooky.services.BookService;
import com.switchfully.digibooky.services.SecurityService;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final SecurityService securityService;
    private final BookService bookService;

    public BookController(SecurityService securityService, BookService bookService) {
        this.securityService = securityService;
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
    public List<BookDTO> getByAuthor(@RequestParam(required = false, name = "lastname") String lastname,
                                     @RequestParam(required = false, name = "firstname") String firstname) {
        return bookService.getByAuthor(firstname, lastname);
    }

    @GetMapping(produces = "application/json", params = "lendOutByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBookLendOutByUserID(@RequestParam String lendOutByUser, @RequestHeader String authorization){

        return bookService.getAllBooksLendOutByUser(lendOutByUser);
    }

    // POST MAPPINGS
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO postNewBook(@RequestBody CreateBookDTO createBookDto,
                               @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Features.REGISTER_NEW_BOOK);
        return bookService.save(createBookDto);
    }

    @PostMapping(path = "/{id}/lendOut", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String lendABook(@PathVariable("id") String id,
                            @RequestHeader String authorization) {
        User user = securityService.validateAuthorization(authorization, Features.LEND_A_BOOK);
        return bookService.lendBook(user, id);
    }

    @PostMapping(path = "/{lendId}/returnBook", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String returnBook(@PathVariable String lendId) {
        return bookService.returnBook(lendId);
    }


}
