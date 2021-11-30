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
//    BookDTO getByISBN(String isbn);
//    BookDTO getByAuthor(Author author);
    BookDTO save(CreateBookDTO createBookDTO);
    String lendBook(User user, String ISBN);
}
