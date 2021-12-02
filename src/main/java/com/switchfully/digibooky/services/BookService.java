package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.book.Book;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import com.switchfully.digibooky.services.dtos.UpdateBookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getById(String id);
    List<BookDTO> getBookByTitle(String title);
    List<BookDTO> getByISBN(String isbn);
    List<BookDTO> getByAuthor(String firstname, String lastname);
    BookDTO save(CreateBookDTO createBookDTO);
    String lendBook(User user, String ISBN);
    String returnBook(String lendId);
    List<BookDTO> getAllBooksLendOutByUser(String lendOutByUser);
    BookDTO updateBook(UpdateBookDTO updateBookDTO);
    List<BookDTO> getBy(boolean isOverDue);
    BookDTO deleteBook(String id);
    BookDTO restoreBook(String id);
}
