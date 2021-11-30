package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getById(String id);
    BookDTO getBookByTitle(String title);
//    BookDTO getByISBN();
//    BookDTO getByAuthor();
    BookDTO save(CreateBookDTO createBookDTO);
    String lendBook(User user, BookDTO bookDTO);
}
