package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.repositories.DefaultBookRepository;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;

    private Author author;
    private CreateBookDTO createBookDTO;

    @BeforeEach
    void beforeEach() {
        author = new Author("test", "test");
        createBookDTO = new CreateBookDTO()
                .setAuthor(author)
                .setIsbn("test")
                .setTitle("test");
    }

    @Test
    void saveBookTest() {
        BookDTO bookDTO = RestAssured
                .given()
                .body(createBookDTO)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/books")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(BookDTO.class);

        assertThat(bookDTO.getId()).isNotBlank();
        assertThat(bookDTO.getTitle()).isEqualTo("test");
        assertThat(bookDTO.getIsbn()).isEqualTo("test");
        assertThat(bookDTO.getAuthor()).isEqualTo(author);
    }
}