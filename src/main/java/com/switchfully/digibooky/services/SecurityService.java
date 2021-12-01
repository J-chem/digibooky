package com.switchfully.digibooky.services;

import com.switchfully.digibooky.custom.exceptions.UnauthorizedException;
import com.switchfully.digibooky.custom.exceptions.UnknownUserException;
import com.switchfully.digibooky.custom.exceptions.WrongPasswordException;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.repositories.UserRepository;
import com.switchfully.digibooky.security.Features;
import com.switchfully.digibooky.security.Role;
import com.switchfully.digibooky.security.SecureUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAuthorization(String authorization, Features feature) {
        SecureUser usernamePassword = getUsernamePassword(authorization);
        User user = userRepository.getUser(usernamePassword.getUsername());
        if(user == null) {
            logger.error("Unknown user" + usernamePassword.getUsername());
            throw new UnknownUserException();
        }
        if(!doesPasswordMatch(usernamePassword.getPassword(), user.getPassword())) {
            logger.error("Password does not match for user " + usernamePassword.getUsername());
            throw new WrongPasswordException();
        }
        if(!canHaveAccessTo(feature, user.getRole())) {
            logger.error("User " + usernamePassword.getUsername() + " does not have access to " + feature);
            throw new UnauthorizedException();
        }
    }

    private boolean canHaveAccessTo(Features feature, Role role) {
        return role.getListOfFeatures().contains(feature);
    }

    private boolean doesPasswordMatch(String givenPassword, String userPassword) {
        return givenPassword.equals(userPassword);
    }

    private SecureUser getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new SecureUser(username, password);
    }
}
