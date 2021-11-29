package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.services.dtos.BookDTO;

import java.util.Collection;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;

public interface BookService {
    BookDTO convertBookinBookDto(Book book);
    Book convertBookDtoInBook(BookDTO bookDTO);
    Collection<BookDTO> convertListOfBookInBookDto(Collection<Book> booksList);
    Book convertCreateBookDtoInBook(CreateBookDTO createBookDTO);

    BookDTO save(CreateBookDTO createBookDTO);
    String lendBook(User user, BookDTO bookDTO);

    Book getBookByTitle(String title);
}
