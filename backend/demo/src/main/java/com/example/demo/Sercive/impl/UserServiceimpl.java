package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Users;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceimpl {
    public void SaveUser(Users user);

    public List<Users> getAllUsers();

    public Users getUserByUsername(String username);

    public Boolean ExistUser(String username);

    public Boolean ExitEmail(String email);

    public Users getUserByEmail(String email);

    public Users getUserById(Long id);
}
