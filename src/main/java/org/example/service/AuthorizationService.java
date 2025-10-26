package org.example.service;

import org.example.entity.User;

import java.util.Optional;

public class AuthorizationService {
    protected static Optional<User> currentUser = Optional.empty();

    protected static void isAuthorized() {
        if (currentUser.isEmpty()) {
            throw new RuntimeException("You are not authorized");
        }

    }
}
