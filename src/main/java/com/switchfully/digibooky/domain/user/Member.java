package com.switchfully.digibooky.domain.user;

public class Member extends User {
    public Member(String socialSecurityNumber, String firstName, String lastName, String email, Address address) {
        super(socialSecurityNumber, firstName, lastName, email, address);
    }
}
