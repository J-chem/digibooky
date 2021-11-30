package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;


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
}
