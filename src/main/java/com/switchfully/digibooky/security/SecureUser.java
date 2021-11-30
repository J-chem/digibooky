package com.switchfully.digibooky.security;

import com.switchfully.digibooky.domain.user.User;

public class SecureUser {
    private final User user;
    private final String username;
    private final String password;
    private Role role;

    public SecureUser(User user, String username, String password, Role role) {
        this.user = user;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
