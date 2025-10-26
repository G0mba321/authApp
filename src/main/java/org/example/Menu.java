package org.example;

import lombok.RequiredArgsConstructor;
import org.example.entity.Role;
import org.example.service.RoleService;
import org.example.service.UserRoleService;
import org.example.service.UserService;
import org.example.entity.User;

import java.util.Scanner;

@RequiredArgsConstructor
public class Menu {
    private static final Scanner sc = new Scanner(System.in);
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    public void menuTable() {

        int choice;

        do {
            System.out.println("Welcome \n 1.Sign up \n 2.Log in \n 3.Log out \n 4.See all users \n 5.Remove user");
            System.out.println(" 6.Create role \n 7.Remove role \n 8.See all roles \n 9.Add role to user");
            System.out.println((" 10.Remove role from user \n 0. Exit program"));
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
                case 6:
                    createRole();
                    break;
                case 7:
                    removeRole();
                    break;
                case 8:
                    seeAllRoles();
                    break;
                case 9:
                    createRoleToUser();
                    break;
                case 10:
                    removeRoleFromUser();
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

    private void createRole() {
        try {
            System.out.println("Enter role name");
            String roleName = sc.nextLine();
            roleService.createRole(roleName);
            System.out.println("Role created");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private void removeRole() {
        try {
            System.out.println("Enter role name");
            String roleName = sc.nextLine();
            roleService.removeRole(roleName);
            System.out.println("Role created");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private void seeAllRoles() {
        try {
            for (Role role : roleService.findAllRoles()) {
                System.out.println(role);
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createRoleToUser() {
        try {
            System.out.println("Enter role name");
            String roleName = sc.nextLine();
            System.out.println("Enter user name");
            String userName = sc.nextLine();
            userRoleService.addRoleToUser(userName, roleName);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private void removeRoleFromUser() {
        try {
            System.out.println("Enter role name");
            String roleName = sc.nextLine();
            System.out.println("Enter user name");
            String userName = sc.nextLine();
            userRoleService.removeRoleFromUser(userName, roleName);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}