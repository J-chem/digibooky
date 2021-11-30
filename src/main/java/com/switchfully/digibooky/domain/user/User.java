package com.switchfully.digibooky.domain.user;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return socialSecurityNumber.equals(user.socialSecurityNumber) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                address.equals(user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialSecurityNumber, firstName, lastName, email, address);
    }
}
