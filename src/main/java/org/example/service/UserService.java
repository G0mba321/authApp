package org.example.service;

import org.example.entity.User;
import org.example.repo.UserRepo;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User signUp(String name, String password) {
        User newUser = new User(name, password);
        if (userRepo.findByUserName(name).isPresent()) {
            throw new RuntimeException("User with name - " + name + " already exists");
        }
        userRepo.save(newUser);
        return newUser;
    }

    public User signIn(String name, String password) throws RuntimeException {
        Optional<User> optionalUser = userRepo.logInByUserNameAndPassword(name, password);
        User user = optionalUser.orElseThrow(() -> new RuntimeException("No such user found."));
        AuthorizationService.currentUser = Optional.of(user);
        return user;
    }

    public User logOut() {
        if (AuthorizationService.currentUser.isPresent()) {
            Optional<User> logOutUser = AuthorizationService.currentUser;
            AuthorizationService.currentUser = Optional.empty();
            return logOutUser.get();
        } else {
            throw new RuntimeException("No such user found.");
        }
    }

    public List<User> findAllUsers() {
        AuthorizationService.isAuthorized();
        return userRepo.findAll();
    }

    public User removeUser(String name) {
        AuthorizationService.isAuthorized();
        User foundedUser = userRepo.findByUserName(name)
                .orElseThrow(() -> new RuntimeException("No such user found."));
        userRepo.remove(foundedUser);
        if (foundedUser.equals(AuthorizationService.currentUser.get())) {
            logOut();
        }
        return foundedUser;
    }

    public User findUserByName(String name) {
        return userRepo.findByUserName(name)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

}
