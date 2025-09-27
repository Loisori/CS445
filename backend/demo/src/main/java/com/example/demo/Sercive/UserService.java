//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Sercive.impl.UserServiceimpl;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceimpl {

    private final UserRepository userRepository;



    public Users getUserById(Long id) {
        return (Users)this.userRepository.findById(id).orElse(null);
    }

    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void SaveUser(Users user) {
        this.userRepository.save(user);
    }

    public Users getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Boolean ExistUser(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public Boolean ExitEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public Users getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
