package com.switchfully.digibooky.domain.user;

import java.util.Objects;

public class Address {
    private final String streetName;
    private final int streetNumber;
    private final int postalCode;
    private final String city;

    public Address(String streetName, int streetNumber, int postalCode, String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetNumber == address.streetNumber &&
                postalCode == address.postalCode &&
                streetName.equals(address.streetName) &&
                city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, postalCode, city);
    }
}
