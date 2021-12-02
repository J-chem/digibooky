package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.security.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class CreateUserDTO {
    private final Role role;
    private final Address address;
    private final String username;
    private final String password;
    private final String socialSecurityNumber;
    private final String firstName;
    private final String lastName;
    private final String email;

    /**
     * Created an extra private constructor, otherwise Postman can't read the JSON.
     */
    private CreateUserDTO(Role role, Address address, String username, String password, String socialSecurityNumber, String firstName, String lastName, String email) {
        this.username =  Objects.requireNonNull(username);
        this.password =  Objects.requireNonNull(password);
        this.role = Objects.requireNonNull(role);
        this.address = address;
        this.socialSecurityNumber = Objects.requireNonNull(socialSecurityNumber);
        this.firstName = firstName;
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
    }

}
