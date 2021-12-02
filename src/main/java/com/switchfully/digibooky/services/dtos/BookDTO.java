package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.Author;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private String id;
    private String isbn;
    private String title;
    private Author author;
    private boolean isLentOut;
    private LocalDate dueDate;
}
