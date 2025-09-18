package com.example.demo.Sercive;

import com.example.demo.Entity.Role;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Sercive.impl.AuthServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements AuthServiceimpl {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> GetAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void SaveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role GetRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Role> GetRoleByName(String roleName) {
        return roleRepository.findByname(roleName);
    }
}
