//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Users;
import java.util.List;

public interface UserServiceimpl {
    void SaveUser(Users user);

    List<Users> getAllUsers();

    Users getUserByUsername(String username);

    Boolean ExistUser(String username);

    Boolean ExitEmail(String email);

    Users getUserByEmail(String email);

    Users getUserById(Long id);
}
