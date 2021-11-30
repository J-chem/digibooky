package com.switchfully.digibooky.repositories.validators;

import org.springframework.stereotype.Component;

@Component
public class Validator {
    public static void assertStringNotNull(String arg, String param) {
        if (arg == null){
            throw new IllegalArgumentException("The " + param + " can't be null");
        }
    }

}
