package org.example.repo;

import org.example.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepo {

    void save(Role role);

    void remove(Role role);

    List<Role> findAll();

    Optional<Role> findRoleName(String name);

}
