package com.switchfully.digibooky.services;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.BookLentData;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import org.springframework.stereotype.Service;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public String save(Book createBookDTO) {
        return bookRepository.save(createBookDTO);
    }

    @Override
    public String lendBook(User user, BookDTO bookDTO) {
        BookLentData bookLentData = new BookLentData(user.getId(), bookDTO.getIsbn());
//        if(bookRepository.getByISBN(bookDTO.getIsbn()).isLentOut()) {
//            throw new ObjectNotFoundException();
//        }
        return bookRepository.lendBook(bookLentData);
    }
}