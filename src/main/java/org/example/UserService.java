package org.example;

import org.example.entity.User;
import org.example.repo.UserRepo;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepo userRepo;
    private Optional<User> currentUser = Optional.empty();

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
        currentUser = Optional.of(user);
        return user;
    }

    public User logOut() {
        if (currentUser.isPresent()) {
            Optional<User> logOutUser = currentUser;
            currentUser = Optional.empty();
            return logOutUser.get();
        } else {
            throw new RuntimeException("No such user found.");
        }
    }

    public List<User> findAllUsers() {
        isAuthorized();
        return userRepo.findAll();
    }

    public User removeUser(String name) {
        isAuthorized();
        User foundedUser = userRepo.findByUserName(name)
                .orElseThrow(() -> new RuntimeException("No such user found."));
        userRepo.remove(foundedUser);
        if (foundedUser.equals(currentUser.get())) {
            logOut();
        }
        return foundedUser;
    }

    private void isAuthorized() {
        if (currentUser.isEmpty()) {
            throw new RuntimeException("You are not authorized");
        }
    }

}
