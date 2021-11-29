package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.Author;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.BookDTO;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.*;

class BookControllerTest {

    @Value("${server.port}")
    private int port;

    @BeforeEach
    void beforeEach() {

    }

    @Test
    void saveBookTest() {

        CreateBookDTO book = new CreateBookDTO();
        book.setAuthor(new Author("test", "test"));
        book.setIsbn("test");
        book.setTitle("test");

        BookDTO id =  RestAssured
                .given()
                .body(book)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(8080)
                .post("/books")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(BookDTO.class); // The problem will be here, we need to have the DTO class

//        assertThat(book.getIsbn()).isEqualTo(id);
    }
}