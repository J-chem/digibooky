package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import com.switchfully.digibooky.services.dtos.UpdateBookDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookConverter {

    public BookDTO convertBookToBookDTO(Book book) {
        return new BookDTO()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setIsbn(book.getIsbn())
                .setAuthor(book.getAuthor())
                .setLentOut(book.isLentOut());
    }

    public Book convertCreateBookDTOToBook(CreateBookDTO createBookDTO) {
        return new Book(
                createBookDTO.getIsbn(),
                createBookDTO.getTitle(),
                createBookDTO.getAuthor());
    }

    public List<BookDTO> convertListOfBookInBookDto(List<Book> booksList) {
        return booksList.stream()
                .map(this::convertBookToBookDTO)
                .toList();
    }

}
