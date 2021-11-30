package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.NotUniqueException;
import com.switchfully.digibooky.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;


@Repository
public class DefaultUserRepository {
    private final Function <User, String> getSsnUser = user -> user.getSocialSecurityNumber().trim().toLowerCase();
    private final Function <User, String> getEmailUser = user -> user.getEmail().trim().toLowerCase();

    private final ConcurrentHashMap<String, User> usersById;

    public DefaultUserRepository() {
        usersById = new ConcurrentHashMap<>();
    }

    public User save(User user) {
        //TODO voorwaarden ook in service
        if(checkUserSocialSecurityNumber(user) && checkEmailUser(user)){
            usersById.put(user.getId(), user);
            return user;
        }
        throw new NotUniqueException("Social security number" + user.getSocialSecurityNumber() +
                "or email is not unique");
    }

    //TODO overzetten naar services
    private boolean checkEmailUser(User userToCheck) {
        return usersById.values()
                .stream()
                .map(getEmailUser)
                .noneMatch(email -> email.equals(userToCheck.getEmail()));
    }

    public boolean checkUserSocialSecurityNumber(User userToCheck){
        return usersById.values()
                .stream()
                .map(getSsnUser)
                .noneMatch(ssn -> userToCheck.getSocialSecurityNumber().equals(ssn));
    }
}
