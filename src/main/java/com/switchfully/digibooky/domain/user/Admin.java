package com.switchfully.digibooky.domain.user;

public class Admin extends User {
    public Admin(String socialSecurityNumber,
                 String firstName,
                 String lastName,
                 String email,
                 Address address) {
        super(socialSecurityNumber, firstName, lastName, email, address);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
