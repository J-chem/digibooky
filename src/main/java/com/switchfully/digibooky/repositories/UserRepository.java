package com.switchfully.digibooky.repositories;

import com.switchfully.digibooky.domain.user.User;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface UserRepository {
    ConcurrentHashMap<String, User> getUsersById();
    List<User> getAllUsers();
    User getUser(String username);
}
