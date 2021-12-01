package com.switchfully.digibooky.domain.user;

import com.switchfully.digibooky.security.Role;
import com.switchfully.digibooky.services.dtos.CreateUserDTO;

import javax.validation.constraints.Email;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final String id;
    private final String socialSecurityNumber;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;

    private Role role;
    private String username;
    private String password;

    private User(Builder builder) {
        this.id = UUID.randomUUID().toString();
        this.socialSecurityNumber = builder.socialSecurityNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.address = builder.address;
        this.role = builder.role;
        this.username = builder.username;
        this.password = builder.password;
    }

    /**
     * all final variables inside this static class Builder, are obligated from a <strong>functional</strong> point of view.
     * This says to the application, you cannot make a user without a firstName, a lastname, a role and an address.
     * Everything not final is optional.
     */
    public static class Builder {
        private final String firstName; //obligated
        private final String lastName;
        private final Address address;
        private final Role role;

        private String socialSecurityNumber; //optional
        private String email;
        private String username;
        private String password;

        public Builder(String firstName, String lastName, Address address, Role role) {
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

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(socialSecurityNumber, user.socialSecurityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, socialSecurityNumber);
    }
}
