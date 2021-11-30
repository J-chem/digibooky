package com.switchfully.digibooky.repositories.validators;

import com.switchfully.digibooky.custom.exceptions.EmptyBooksListException;
import com.switchfully.digibooky.domain.Author;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class Validator {
    public static void assertStringNotNull(String arg, String param) {
        if (arg == null){
            throw new IllegalArgumentException("The " + param + " can't be null");
        }
    }

    public static <T, E> void assertDataManagementMapIsNotEmpty(ConcurrentHashMap<T, E> map) {
        if (map.isEmpty()){
            throw new EmptyBooksListException("List of books is empty");
        }
    }
}
