package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.BookService;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.BookDTO;
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


}
