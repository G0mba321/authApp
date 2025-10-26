package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Role;
import org.example.entity.User;

@RequiredArgsConstructor
public class UserRoleService {

    private final UserService userService;
    private final RoleService roleService;

    public void addRoleToUser(String userName, String roleName) {
        try {
            AuthorizationService.isAuthorized();

            User user = userService.findUserByName(userName);
            Role role = roleService.findRoleByName(roleName);

            user.getRoles().add(role);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("check if correct name role or user");
        }

    }

    public void removeRoleFromUser(String userName, String roleName) {
        try {
            AuthorizationService.isAuthorized();

            User user = userService.findUserByName(userName);
            Role role = roleService.findRoleByName(roleName);

            user.getRoles().remove(role);
            role.getUsers().remove(user);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("check if correct name role or user");
        }
    }
}
