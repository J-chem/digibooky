package com.switchfully.digibooky.api;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

//    @LocalServerPort
//    private int port;
//
//    private Author author;
//    private CreateBookDTO createBookDTO;
//
//
//    @BeforeEach
//    void beforeEach() {
//        author = new Author("test", "test");
//        createBookDTO = new CreateBookDTO()
//                .setAuthor(author)
//                .setIsbn("test")
//                .setTitle("test");
//    }
//
//    @Test
//    void saveBookTest() {
//        BookDTO bookDTO = RestAssured
//                .given()
//                .body(createBookDTO)
//                .accept(JSON)
//                .contentType(JSON)
//                .when()
//                .port(port)
//                .post("/books")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.CREATED.value())
//                .extract()
//                .as(BookDTO.class);
//
//        assertThat(bookDTO.getId()).isNotBlank();
//        assertThat(bookDTO.getTitle()).isEqualTo("test");
//        assertThat(bookDTO.getIsbn()).isEqualTo("test");
//        assertThat(bookDTO.getAuthor()).isEqualTo(author);
//    }
}