package com.switchfully.digibooky.services;

import com.switchfully.digibooky.services.dtos.CreateUserDTO;
import com.switchfully.digibooky.services.dtos.UserDTO;

public interface UserService {

    UserDTO save(CreateUserDTO createUserDTO);
}
