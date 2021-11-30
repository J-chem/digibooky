package com.switchfully.digibooky.security;

import com.switchfully.digibooky.domain.user.User;

public class SecureUser {
    private final User user;

    public SecureUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getPassword() {
        return user.getPassword();
    }
}
