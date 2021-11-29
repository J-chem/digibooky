package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.services.dtos.BookDTO;

import java.util.Collection;
import java.util.List;

import com.switchfully.digibooky.services.dtos.CreateBookDTO;

public interface BookService {
    Book convertCreateBookDtoInBook(CreateBookDTO createBookDTO);
    BookDTO convertBookinBookDto(Book book);
    Book convertBookDtoInBook(BookDTO bookDTO);
    List<BookDTO> convertListOfBookInBookDto(List<Book> booksList);

    BookDTO save(CreateBookDTO createBookDTO);
    String lendBook(User user, BookDTO bookDTO);

    Book getBookByTitle(String title);
}
