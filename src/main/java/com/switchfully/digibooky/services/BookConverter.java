package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookConverter {

    public BookDTO convertBookToBookDTO(Book book) {
        return new BookDTO()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setIsbn(book.getIsbn())
                .setAuthor(book.getAuthor());
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











    // I DON'T THINK WE WILL NEED THIS ONE,
    // AS BookDTO is Return type, and
    // CreateBookDTO is (argument)/Parameter type

//    public Book convertBookDTOToBook(BookDTO bookDTO) {
//        return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor());
//    }

}
