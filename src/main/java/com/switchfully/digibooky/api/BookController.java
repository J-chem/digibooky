package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.security.Features;
import com.switchfully.digibooky.services.BookService;
import com.switchfully.digibooky.services.SecurityService;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import com.switchfully.digibooky.services.dtos.UpdateBookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        securityService.validateAuthorization(authorization, Features.CONSULT_LENDING);
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

    //PUT MAPPING
    @PutMapping(path = "/{bookId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO putBook(@PathVariable String bookId,
                            @RequestBody UpdateBookDTO updateBookDTO,
                            @RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Features.UPDATE_A_BOOK);
        if(!updateBookDTO.getId().equals(bookId)){
            throw new IllegalArgumentException("Book id don't corresponds whit the book");
        }

        return bookService.updateBook(updateBookDTO);
    }

}
