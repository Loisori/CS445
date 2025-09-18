package com.example.demo.Controller.auth;

import com.example.demo.Config.JwtUtil;
import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.AuthService;
import com.example.demo.Sercive.CustomUserDetailsService;
import com.example.demo.Sercive.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil Jwtutil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/registerUser/INVESTOR")
    public ResponseEntity<Apireponsi<Users>> registerInvestor(@Valid @RequestBody RegisterRequest registerRequest) {
        try {


            if (userService.ExistUser(registerRequest.getName())) {
                Apireponsi<Users> error = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Username Đã Tồn tại ", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            if (userService.ExitEmail(registerRequest.getEmail())) {
                Apireponsi<Users> error1 = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Email Đã Tồn tại ", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
            }
            Users user = new Users();
            Role investorRole = authService.GetRoleByName("INVESTOR")
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setEmail(registerRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setRole(investorRole);
            user.setUsername(registerRequest.getName());
            user.setPhone(registerRequest.getPhone());
            user.setCreatedAt(LocalDateTime.now());
            userService.SaveUser(user);
            Apireponsi<Users> reponse = new Apireponsi<>(HttpStatus.CREATED, "Đăng Kí Thành Công", user, null);
            return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
        } catch (Exception e) {
            Apireponsi<Users> error = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

        }

    }

    @PostMapping("/registerUser/CREATOR")
    public ResponseEntity<Apireponsi<Users>> registerCreator(@RequestBody RegisterRequest registerRequest) {
        try {
            if (userService.ExistUser(registerRequest.getName())) {
                Apireponsi<Users> error = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Username Đã Tồn tại ", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            if (userService.ExitEmail(registerRequest.getEmail())) {
                Apireponsi<Users> error1 = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Email Đã Tồn tại ", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
            }
            Users user = new Users();
            Role investorRole = authService.GetRoleByName("CREATOR")
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            user.setEmail(registerRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setRole(investorRole);
            user.setUsername(registerRequest.getName());
            user.setPhone(registerRequest.getPhone());
            user.setCreatedAt(LocalDateTime.now());
            userService.SaveUser(user);
            Apireponsi<Users> reponse = new Apireponsi<>(HttpStatus.CREATED, "Đăng Kí Thành Công", user, null);
            return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
        } catch (Exception e) {
            Apireponsi<Users> error = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

        }

    }

    @PostMapping("/login")
    public ResponseEntity<Apireponsi<Map<String, Object>>> login(@RequestBody LoginRequest loginRequest) {
        try {
           Users userOpt = userService.getUserByUsername(loginRequest.getUsername());

            if (userOpt==null) {
                Apireponsi<Map<String, Object>> error = new Apireponsi<>(
                        HttpStatus.BAD_REQUEST,
                        "Tài khoản không tồn tại",
                        null,
                        null
                );
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }



            // kiểm tra password
            if (!passwordEncoder.matches(loginRequest.getPassword(), userOpt.getPassword())) {
                Apireponsi<Map<String, Object>> error = new Apireponsi<>(
                        HttpStatus.BAD_REQUEST,
                        "Password không chính xác",
                        null,
                        null
                );
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            // Lấy UserDetails cho JWT
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userOpt.getUsername());

            // Tạo JWT token
            String token = Jwtutil.generateToken(loginRequest.getUsername());

            // Tạo map trả về gồm token + username + role
            Map<String, Object> data = Map.of(
                    "token", token,
                    "username", userOpt.getUsername(),
                    "role", userOpt.getRole().getName()
            );

            Apireponsi<Map<String, Object>> response = new Apireponsi<>(
                    HttpStatus.OK,
                    "Login thành công",
                    data,
                    null
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Apireponsi<Map<String, Object>> error = new Apireponsi<>(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Lỗi server: " + e.getMessage(),
                    null,
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

}


