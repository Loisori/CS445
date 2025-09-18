package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Role;

import java.util.List;
import java.util.Optional;

public interface AuthServiceimpl {
    public void SaveRole(Role role);

    public List<Role> GetAllRole();

    public Role GetRoleById(Long id);

    public Optional<Role> GetRoleByName(String roleName);


}
