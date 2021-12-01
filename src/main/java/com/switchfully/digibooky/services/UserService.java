package com.switchfully.digibooky.services;

import com.switchfully.digibooky.services.dtos.CreateUserDTO;
import com.switchfully.digibooky.services.dtos.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(CreateUserDTO createUserDTO);
    List<UserDTO> getAllUsers();
}
