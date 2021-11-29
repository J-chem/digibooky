package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(Mockito.class)
class DefaultBookServiceTest {

//  private Book book;
// // @Mock
//  private DefaultBookService defaultBookService;
//
//  @BeforeEach
//  void setUp(){
//      book = new Book("jkljf12", "Marie", new Author("Marie", "Julie"));
//  }
//
//    @Test
//    void givenBookCheckThatBookDTOHasSameFields(){
//        BookDTO newBookDto = defaultBookService.convertBookinBookDto(book);
//        assertThat(newBookDto.getAuthor()).isEqualTo(book.getAuthor());
//        assertThat(newBookDto.getIsbn()).isEqualTo(book.getIsbn());
//        assertThat(newBookDto.getTitle()).isEqualTo(book.getTitle());
//        assertThat(newBookDto.getId()).isEqualTo(book.getId());
//    }
}