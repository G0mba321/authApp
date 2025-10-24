package org.example.repo;



import org.example.entity.User;

import java.util.*;

public class UserRepoMapImpl implements UserRepo {
    private final Map<UUID, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        userMap.put(user.getUserId(), user);
    }

    @Override
    public void remove(User user) {
        userMap.remove(user.getUserId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<User> logInByUserNameAndPassword(String name, String password) {
        return userMap.values().stream()
                .filter(user -> user.getUsername().equals(name) && user.getUserPassword().equals(password))
                .findFirst();
    }

    @Override
    public Optional<User> findByUserName(String name) {
        return userMap.values().stream()
                .filter(user -> user.getUsername().equals(name))
                .findFirst();
    }


}
