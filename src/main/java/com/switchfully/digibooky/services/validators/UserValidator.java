package com.switchfully.digibooky.services.validators;

import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.UserRepository;
import com.switchfully.digibooky.services.dtos.CreateUserDTO;
import com.switchfully.digibooky.services.regex.UserPattern;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class UserValidator {
    private final Function<User, String> getSsnUser = user -> user.getSocialSecurityNumber().trim().toLowerCase();
    private final Function<User, String> getEmailUser = user -> user.getEmail().trim().toLowerCase();

    private boolean checkEmailUser(CreateUserDTO userToCheck, UserRepository userRepository) {
        return userRepository.getUsersById()
                .values()
                .stream()
                .map(getEmailUser)
                .noneMatch(email -> email.equals(userToCheck.getEmail()));
    }

    private boolean checkUserSocialSecurityNumber(CreateUserDTO userToCheck, UserRepository userRepository) {
        return userRepository.getUsersById()
                .values()
                .stream()
                .map(getSsnUser)
                .noneMatch(ssn -> userToCheck.getSocialSecurityNumber().equals(ssn));
    }

    private boolean validateEmail(CreateUserDTO createUserDTO) {//TODO test
        return UserPattern.emailPattern.matcher(createUserDTO.getEmail()).matches();
    }

    public boolean canUserBeSaved(CreateUserDTO createUserDTO , UserRepository userRepository) {
        return checkEmailUser(createUserDTO, userRepository)
                && checkUserSocialSecurityNumber(createUserDTO, userRepository)
                && validateEmail(createUserDTO);
    }
}
