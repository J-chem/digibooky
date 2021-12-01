package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.security.Role;

public class UserDTO {

    private String UserId; 
    private String firsName;
    private String lastName;
    private Role role;

    public UserDTO(String userId, String firsName, String lastName, Role role) {
        UserId = userId;
        this.firsName = firsName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getFirsName() {
        return firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public String getUserId() {
        return UserId;
    }
}
