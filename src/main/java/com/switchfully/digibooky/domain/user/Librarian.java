package com.switchfully.digibooky.domain.user;

public class Librarian extends User {
    public Librarian(String socialSecurityNumber, String firstName, String lastName, String email, Address address) {
        super(socialSecurityNumber, firstName, lastName, email, address);
    }
}
