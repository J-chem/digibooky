package com.switchfully.digibooky.services;

import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.DefaultUserRepository;
import com.switchfully.digibooky.services.dtos.CreateUserDTO;
import com.switchfully.digibooky.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService{

    private final UserConverter userConverter;
    private final DefaultUserRepository defaultUserRepository;

    @Autowired
    public DefaultUserService(UserConverter userConverter, DefaultUserRepository defaultUserRepository) {
        this.userConverter = userConverter;
        this.defaultUserRepository = defaultUserRepository;
    }

    @Override
    public UserDTO save(CreateUserDTO createUserDTO) {
        User user  = userConverter.convertCreateUserDtoToUser(createUserDTO);
        User savedUser = defaultUserRepository.save(user);
        return userConverter.convertUserToUserDTO(savedUser);
    }

}
