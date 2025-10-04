//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Role;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Sercive.impl.AuthServiceimpl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceimpl {

    private final RoleRepository roleRepository;


    public List<Role> GetAllRole() {
        return this.roleRepository.findAll();
    }

    public void SaveRole(Role role) {
        this.roleRepository.save(role);
    }

    public Role GetRoleById(Long id) {
        return (Role) this.roleRepository.findById(id).orElse(null);
    }

    public Optional<Role> GetRoleByName(String roleName) {
        return this.roleRepository.findByname(roleName);
    }
}
