package com.switchfully.digibooky.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Author {
    private String firstName;
    private String lastName;
}
