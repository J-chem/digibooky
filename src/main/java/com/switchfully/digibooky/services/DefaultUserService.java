package com.switchfully.digibooky.services;

import com.switchfully.digibooky.custom.exceptions.NotUniqueException;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.DefaultUserRepository;
import com.switchfully.digibooky.repositories.UserRepository;
import com.switchfully.digibooky.services.dtos.CreateUserDTO;
import com.switchfully.digibooky.services.dtos.UserDTO;
import com.switchfully.digibooky.services.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    private final UserConverter userConverter;
    private final DefaultUserRepository defaultUserRepository;
    private final UserValidator userValidator;

    @Autowired
    public DefaultUserService(UserConverter userConverter, DefaultUserRepository defaultUserRepository, UserValidator userValidator) {
        this.userConverter = userConverter;
        this.defaultUserRepository = defaultUserRepository;
        this.userValidator = userValidator;
    }

    @Override
    public UserDTO save(CreateUserDTO createUserDTO) {
        if (userValidator.canUserBeSaved(createUserDTO, defaultUserRepository)) {
            User user = userConverter.convertCreateUserDtoToUser(createUserDTO);
            User savedUser = defaultUserRepository.save(user);
            return userConverter.convertUserToUserDTO(savedUser);
        }
        throw new NotUniqueException("Social security number or email is not correct: "
                + createUserDTO.getSocialSecurityNumber() + " " + createUserDTO.getEmail());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = defaultUserRepository.getAllUsers();
        return userList.stream()
                .map(userConverter::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

}
