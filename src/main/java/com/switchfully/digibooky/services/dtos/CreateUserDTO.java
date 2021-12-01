package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.security.Role;

public class CreateUserDTO {
    private final Role role;
    private final Address address;
    private final String socialSecurityNumber;
    private final String firstName;
    private final String lastName;
    private final String email;

    private CreateUserDTO(Builder builder) {
        this.socialSecurityNumber = builder.socialSecurityNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.address = builder.address;
        this.role = builder.role;
    }

    /**
     * Created an extra private constructor, otherwise Postman can't read the JSON.
     */
    private CreateUserDTO(Role role, Address address, String socialSecurityNumber, String firstName, String lastName, String email) {
        this.role = role;
        this.address = address;
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static class Builder {
        private final String firstName;
        private final Address address;
        private final String lastName;
        private final Role role;

        private String socialSecurityNumber;
        private String email;

        public Builder(String firstName, Address address, String lastName, Role role) {
            this.firstName = firstName;
            this.address = address;
            this.lastName = lastName;
            this.role = role;
        }

        public Builder withSocialSecurityNumber(String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CreateUserDTO build() {
            return new CreateUserDTO(this);
        }
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Role getRole() {
        return role;
    }
}
