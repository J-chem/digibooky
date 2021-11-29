package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.BookDTO;

import java.util.Collection;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;

public interface BookService {
    BookDTO convertBookinBookDto(Book book);
    Book convertBookDtoInBook(BookDTO bookDTO);
    Collection<BookDTO> convertListOfBookInBookDto(Collection<Book> booksList);

    String save(Book createBookDTO);

    Book getBookByTitle(String title);
    String lendBook(User user, BookDTO bookDTO);
}
