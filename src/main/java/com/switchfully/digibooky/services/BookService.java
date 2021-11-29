package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.BookDTO;

import java.util.Collection;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;

public interface BookService {
    public BookDTO convertBookinBookDto(Book book);
    public Book convertBookDtoInBook(BookDTO bookDTO);
    public Collection<BookDTO> convertListOfBookInBookDto(Collection<Book> booksList);

    String save(Book createBookDTO);
}
