package com.switchfully.digibooky.domain.user;

import java.util.Objects;

public class Address {
    private final String streetName;
    private final int streetNumber;
    private final int postalCode;
    private final String city;

    private Address(Builder builder) {
        this.streetName = builder.streetName;
        this.streetNumber = builder.streetNumber;
        this.postalCode = builder.postalCode;
        this.city = builder.city;
    }

    /**
     * Created an extra private constructor, otherwise Postman can't read the JSON.
     */
    private Address(String streetName, int streetNumber, int postalCode, String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = Objects.requireNonNull(city);
    }

    public static class Builder {
        private String streetName;
        private int streetNumber;
        private int postalCode;
        private String city;

        public Builder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder withStreetNumber(int streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public Builder withPostalCode(int postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
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
