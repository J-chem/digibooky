package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.services.dtos.CreateBookDTO;

public interface BookService {
    String save(Book createBookDTO);
}
