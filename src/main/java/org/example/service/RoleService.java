package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Role;
import org.example.repo.RoleRepo;

import java.util.List;

@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public Role createRole(String name) {
        AuthorizationService.isAuthorized();
        Role newRole = new Role(name);
        if (roleRepo.findRoleName(name).isPresent()) {
            throw new RuntimeException("Role with name - " + name + " already exists");
        }
        roleRepo.save(newRole);
        return newRole;
    }

    public Role removeRole(String name) {
        AuthorizationService.isAuthorized();
        Role foundRole = roleRepo.findRoleName(name)
                .orElseThrow(() -> new RuntimeException("No role with this name"));
        roleRepo.remove(foundRole);
        return foundRole;
    }

    public List<Role> findAllRoles() {
        AuthorizationService.isAuthorized();
        return roleRepo.findAll();
    }

    public Role findRoleByName(String name) {
        return roleRepo.findRoleName(name)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
