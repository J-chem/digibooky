package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.security.Role;

import java.util.Objects;

public class CreateUserDTO {
    private final Role role;
    private final Address address;
    private final String socialSecurityNumber;
    private final String firstName;
    private final String lastName;
    private final String email;

    /**
     * Created an extra private constructor, otherwise Postman can't read the JSON.
     */
    private CreateUserDTO(Role role, Address address, String socialSecurityNumber, String firstName, String lastName, String email) {
        this.role = Objects.requireNonNull(role);
        this.address = address;
        this.socialSecurityNumber = Objects.requireNonNull(socialSecurityNumber);
        this.firstName = firstName;
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {return lastName;}

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
