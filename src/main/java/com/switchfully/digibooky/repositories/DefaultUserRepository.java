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

    private final ConcurrentHashMap<String, User> usersById;

    public DefaultUserRepository() {
        usersById = new ConcurrentHashMap<>();
    }

    public User save(User user) {
            usersById.put(user.getId(), user);
            return user;
    }

    public ConcurrentHashMap<String, User> getUsersById() {
        return usersById;
    }
}
