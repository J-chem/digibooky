package com.switchfully.digibooky.domain.user;

public abstract class User {
    private final String socialSecurityNumber;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;

    public User(String socialSecurityNumber, String firstName, String lastName, String email, Address address) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return socialSecurityNumber;
    }
}
