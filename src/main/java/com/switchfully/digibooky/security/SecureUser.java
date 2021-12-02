package com.switchfully.digibooky.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SecureUser {
    private final String username;
    private final String password;
}
