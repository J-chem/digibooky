package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.security.Features;
import com.switchfully.digibooky.services.BookService;
import com.switchfully.digibooky.services.SecurityService;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/books", produces = "application/json")
public class BookController {

    private final SecurityService securityService;
    private final BookService bookService;

    public BookController(SecurityService securityService, BookService bookService) {
        this.securityService = securityService;
        this.bookService = bookService;
    }

    // GET MAPPINGS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable("id") String id) {
        return bookService.getById(id);
    }

    @GetMapping(params = {"title"})
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBookByTitle(@RequestParam String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping(params = {"isbn"})
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getByISBN(@RequestParam String isbn) {
        return bookService.getByISBN(isbn);
    }

    @GetMapping(params = {"lastname", "firstname"})
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getByAuthor(@RequestParam(required = false, name = "lastname") String lastname,
                                     @RequestParam(required = false, name = "firstname") String firstname) {
        return bookService.getByAuthor(firstname, lastname);
    }

    @GetMapping(params = "lendOutByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBookLendOutByUserID(@RequestParam String lendOutByUser,
                                                   @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Features.CONSULT_LENDINGS);
        return bookService.getAllBooksLendOutByUser(lendOutByUser);
    }

    @GetMapping(params = "isOverDue")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getOverDue(@RequestParam boolean isOverDue,
                                    @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Features.GET_OVERDUE);
        return bookService.getBy(isOverDue);
    }


    // POST MAPPINGS
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO postNewBook(@RequestBody CreateBookDTO createBookDto,
                               @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Features.REGISTER_NEW_BOOK);
        return bookService.save(createBookDto);
    }

    @PostMapping(path = "/{bookid}/lendOut")
    @ResponseStatus(HttpStatus.CREATED)
    public String lendABook(@PathVariable("bookid") String bookid,
                            @RequestHeader String authorization) {
        User user = securityService.validateAuthorization(authorization, Features.LEND_A_BOOK);
        return bookService.lendBook(user, bookid);
    }

    @PostMapping(path = "/{lendId}/returnBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String returnBook(@PathVariable String lendId) {
        return bookService.returnBook(lendId);
    }

}
