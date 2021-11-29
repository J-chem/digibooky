package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.BookDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class DefaultBookService implements BookService {

    @Override
    public BookDTO convertBookinBookDto(Book book) {
        return new BookDTO(book);
    }

    @Override
    public Book convertBookDtoInBook(BookDTO bookDTO) {
        return new Book(bookDTO);
    }

    @Override
    public Collection<BookDTO> convertListOfBookInBookDto(Collection<Book> booksList) {
        return booksList.stream().map(BookDTO::new).collect(Collectors.toList());
    }
}
