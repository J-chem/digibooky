package com.switchfully.digibooky.services.dtos;

import com.switchfully.digibooky.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
    private String UserId;
    private String firsName;
    private String lastName;
    private Role role;
}
