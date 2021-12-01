package com.switchfully.digibooky.security;

public class SecureUser {
    private final String username;
    private final String password;

    public SecureUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
