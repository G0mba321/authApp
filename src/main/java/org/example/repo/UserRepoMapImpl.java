package org.example.repo;


import org.example.entity.User;

import java.util.*;

public class UserRepoMapImpl implements UserRepo {
    private final Map<UUID, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void remove(User user) {
        userMap.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<User> logInByUserNameAndPassword(String name, String password) {
        return userMap.values().stream()
                .filter(user -> user.getUsername().equals(name) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public Optional<User> findByUserName(String name) {
        return userMap.values().stream()
                .filter(user -> user.getUsername().equals(name))
                .findFirst();
    }

    @Override
    public void close() throws Exception {

    }
}
