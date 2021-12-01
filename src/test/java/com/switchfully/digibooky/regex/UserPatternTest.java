package com.switchfully.digibooky.regex;

import com.switchfully.digibooky.services.regex.UserPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserPatternTest {
    List<String> correctMailList;
    List<String> wrongMailList;

    @BeforeEach
    public void setup() {
        correctMailList = List.of( "maxim@banana.com","jan@hotmail.com","jochem@gmail.be");
        wrongMailList = List.of("lalalala", "tisIsNotAMail", "$^ùµ$^@momomo");
    }

    @Test
    @DisplayName("when given wrong string, regex will return false")
    void whenRegexMatchesWrongStringThenReturnFalse() {
        wrongMailList.forEach(email -> assertFalse(Pattern.matches(String.valueOf(UserPattern.emailPattern), email)));
    }

    @Test
    @DisplayName("when given correct string, regex will return false")
    void whenRegexMatchesCorrectStringThenReturnFalse() {
        correctMailList.forEach(email -> assertTrue(Pattern.matches(String.valueOf(UserPattern.emailPattern), email)));
    }
}
