package com.switchfully.digibooky.domain.user;

public class Address {
    private String streetName;
    private int streetNumber;
    private int postalCode;
    private String city;

    public Address(String streetName, int streetNumber, int postalCode, String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }
}
