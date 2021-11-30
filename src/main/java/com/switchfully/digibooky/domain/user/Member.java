package com.switchfully.digibooky.domain.user;

public class Member extends User {
    public Member(String socialSecurityNumber,
                  String firstName,
                  String lastName,
                  String email,
                  Address address) {
        super(socialSecurityNumber, firstName, lastName, email, address);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
