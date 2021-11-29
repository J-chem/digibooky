package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DefaultBookRepositoryTest {

    private DefaultBookRepository bookRepository;
    private Book book1;
    private Book book2;
    private Author author1;
    private Author author2;

    @BeforeEach
    void beforeEach() {
        bookRepository = new DefaultBookRepository();
        author1 = new Author("test", "test");
        author2 = new Author("test2", "test2");
        book1 = new Book("test", "test", author1);
        book2 = new Book("test2", "test2", author2);
    }

    @Test
    void whenSavingABook_thenReturnBookId() {
        var id = idOfBook1();
        assertThat(bookRepository.save(book1)).isEqualTo(book1);
    }

    private String idOfBook1() {
        return book1.getId();
    }


}