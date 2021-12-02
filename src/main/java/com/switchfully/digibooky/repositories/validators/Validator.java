package com.switchfully.digibooky.repositories.validators;

import com.switchfully.digibooky.custom.exceptions.EmptyBooksListException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
public class Validator {
    public static void assertAllParamsNotNull(Object... args) {
        if (Arrays.stream(args).allMatch(Objects::isNull)) {
            throw new IllegalArgumentException("All parameters can't be null");
        }
    }

    public static <T, E> void assertDataManagementMapIsNotEmpty(Map<T, E> map) {
        if (map.isEmpty()) {
            throw new EmptyBooksListException("List of books is empty");
        }
    }
}
