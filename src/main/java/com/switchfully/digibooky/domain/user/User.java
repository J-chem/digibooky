package com.switchfully.digibooky.domain.user;

public abstract class User {
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

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
