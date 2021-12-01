package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.services.dtos.CreateUserDTO;
import com.switchfully.digibooky.services.dtos.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User convertCreateUserDtoToUser(CreateUserDTO createUserDto) {
        return new User.Builder(createUserDto.getFirstName(),createUserDto.getLastName(),createUserDto.getAddress(),createUserDto.getRole() )
                .withEmail(createUserDto.getEmail())
                .withSocialSecurityNumber(createUserDto.getSocialSecurityNumber())
                .withPassword(createUserDto.getPassword())
                .withUsername(createUserDto.getUsername())
                .build();
    }

    public UserDTO convertUserToUserDTO(User savedUser) {
        return new UserDTO(savedUser.getFirstName(), savedUser.getLastName(), savedUser.getRole());
    }
}
