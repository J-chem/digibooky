package com.switchfully.digibooky.services;

import com.switchfully.digibooky.custom.exceptions.NotUniqueException;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.DefaultUserRepository;
import com.switchfully.digibooky.services.dtos.CreateUserDTO;
import com.switchfully.digibooky.services.dtos.UserDTO;
import com.switchfully.digibooky.services.regex.UserPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.regex.Pattern;

@Service
public class DefaultUserService implements UserService {
    private final Function<User, String> getSsnUser = user -> user.getSocialSecurityNumber().trim().toLowerCase();
    private final Function<User, String> getEmailUser = user -> user.getEmail().trim().toLowerCase();

    private final UserConverter userConverter;
    private final DefaultUserRepository defaultUserRepository;

    @Autowired
    public DefaultUserService(UserConverter userConverter, DefaultUserRepository defaultUserRepository) {
        this.userConverter = userConverter;
        this.defaultUserRepository = defaultUserRepository;
    }

    @Override
    public UserDTO save(CreateUserDTO createUserDTO) {
        if (canUserBeSaved(createUserDTO)) {
            User user = userConverter.convertCreateUserDtoToUser(createUserDTO);
            User savedUser = defaultUserRepository.save(user);
            return userConverter.convertUserToUserDTO(savedUser);
        }
        throw new NotUniqueException("Social security number or email is not correct: "
                + createUserDTO.getSocialSecurityNumber() + " " + createUserDTO.getEmail());
    }

    private boolean canUserBeSaved(CreateUserDTO createUserDTO) {
        return checkEmailUser(createUserDTO) && checkUserSocialSecurityNumber(createUserDTO)
                && validateEmail(createUserDTO);
    }

    private boolean checkEmailUser(CreateUserDTO userToCheck) {
        return defaultUserRepository.getUsersById()
                .values()
                .stream()
                .map(getEmailUser)
                .noneMatch(email -> email.equals(userToCheck.getEmail()));
    }

    private boolean checkUserSocialSecurityNumber(CreateUserDTO userToCheck) {
        return defaultUserRepository.getUsersById()
                .values()
                .stream()
                .map(getSsnUser)
                .noneMatch(ssn -> userToCheck.getSocialSecurityNumber().equals(ssn));
    }

    private boolean validateEmail(CreateUserDTO createUserDTO) {
//        return Pattern.matches(String.valueOf(UserPattern.emailPattern), createUserDTO.getEmail());
        return UserPattern.emailPattern.matcher(createUserDTO.getEmail()).matches();
    }

}
