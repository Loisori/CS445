//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Role;
import java.util.List;
import java.util.Optional;

public interface AuthServiceimpl {
    void SaveRole(Role role);

    List<Role> GetAllRole();

    Role GetRoleById(Long id);

    Optional<Role> GetRoleByName(String roleName);
}
