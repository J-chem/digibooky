package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.book.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookDTO {
//    private String id;
    private String title;
    private Author author;

//    public UpdateBookDTO setId(String id) {
////        this.id = id;
//        return this;
//    }

    public UpdateBookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public UpdateBookDTO setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
