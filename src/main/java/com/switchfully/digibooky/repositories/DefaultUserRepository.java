package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.custom.exceptions.UnknownUserException;
import com.switchfully.digibooky.domain.user.Address;
import com.switchfully.digibooky.domain.user.User;
import com.switchfully.digibooky.security.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.switchfully.digibooky.repositories.validators.Validator.assertDataManagementMapIsNotEmpty;


@Repository
public class DefaultUserRepository implements UserRepository {

    private final ConcurrentHashMap<String, User> usersById;

    public DefaultUserRepository() {
        usersById = new ConcurrentHashMap<>();
        //creating dummy data
        User user = new User.Builder("Maxim",
                "Verbeeck",
                new Address.Builder()
                        .withCity("NY")
                        .withPostalCode(2000)
                        .withStreetNumber(13)
                        .withStreetName("Walibi")
                        .build(),
                Role.ADMIN)
                .withEmail("banana@hotmail.com")
                .withSocialSecurityNumber("12345")
                .withPassword("monkey")
                .withUsername("HulaBaloo")
                .build();
        usersById.put(user.getId(), user);
    }

    public User save(User user) {
            usersById.put(user.getId(), user);
            return user;
    }

    @Override
    public ConcurrentHashMap<String, User> getUsersById() {
        return usersById;
    }

    @Override
    public List<User> getAllUsers() {
        assertDataManagementMapIsNotEmpty(usersById);
        return new ArrayList<>(usersById.values());
    }

    @Override
    public User getUser(String username) {
        return usersById.values()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(UnknownUserException::new);
    }
}
