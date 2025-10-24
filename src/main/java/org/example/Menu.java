package org.example;

import org.example.entity.User;
import org.example.repo.UserRepoMapImpl;

import java.util.Scanner;

public class Menu {
    static final Scanner sc = new Scanner(System.in);
    UserService userService = new UserService(new UserRepoMapImpl());

    public void menuTable() {

        int choice;

        do {
            System.out.println("Welcome \n 1.Sign up \n 2.Log in \n 3.Log out \n 4.See all users \n 5.Remove user");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    singUp();
                    break;
                case 2:
                    logIn();
                    break;
                case 3:
                    logOut();
                    break;
                case 4:
                    seeAllUsers();
                    break;
                case 5:
                    removeUser();
                    break;
            }
        } while (choice != 0);
    }

    private void singUp() {
        try {
            System.out.println("Enter username");
            String userName = sc.nextLine();
            System.out.println("Enter password");
            String userPassword = sc.nextLine();
            System.out.println(userService.signUp(userName, userPassword));
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private void logIn() {
        try {
            System.out.println("Enter username");
            String userName2 = sc.nextLine();
            System.out.println("Enter password");
            String userPassword2 = sc.nextLine();
            System.out.println(userService.signIn(userName2, userPassword2));
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private void logOut() {
        try {
            User user = userService.logOut();
            System.out.println("User: " + user.getUsername() + " is logged out");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private void seeAllUsers() {
        try {
            for (User user : userService.findAllUsers()) {
                System.out.println(user);
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private void removeUser() {
        try {
            System.out.println("Enter username");
            String userName = sc.nextLine();
            userService.removeUser(userName);
            System.out.println(userName + " removed");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
