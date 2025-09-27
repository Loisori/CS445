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
import java.time.LocalDateTime;
import java.util.Map;

import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping({"/api/auth"})
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final JwtUtil Jwtutil;

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping({"/registerUser/INVESTOR"})
    public ResponseEntity<Apireponsi<Users>> registerInvestor(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            if (this.userService.ExistUser(registerRequest.getName())) {
                Apireponsi<Users> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Username Đã Tồn tại ", (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            } else if (this.userService.ExitEmail(registerRequest.getEmail())) {
                Apireponsi<Users> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Email Đã Tồn tại ", (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
            } else {

                Role investorRole = (Role)this.authService.GetRoleByName("INVESTOR").orElseThrow(() -> new RuntimeException("Role not found"));
                Users user = new Users().builder()
                        .phone(registerRequest.getPhone())
                        .email(registerRequest.getEmail())
                        .role(investorRole)
                        .createdAt(LocalDateTime.now())
                        .username(registerRequest.getName())
                        .password(this.passwordEncoder.encode(registerRequest.getPassword()))
                        .wallet(0.0)
                        .build();
                this.userService.SaveUser(user);
                Apireponsi<Users> reponse = new Apireponsi(HttpStatus.CREATED, "Đăng Kí Thành Công", user, (String)null);
                return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
            }
        } catch (Exception var5) {
            Apireponsi<Users> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object)null, (String)null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping({"/registerUser/CREATOR"})
    public ResponseEntity<Apireponsi<Users>> registerCreator(@RequestBody RegisterRequest registerRequest) {
        try {
            if (this.userService.ExistUser(registerRequest.getName())) {
                Apireponsi<Users> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Username Đã Tồn tại ", (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            } else if (this.userService.ExitEmail(registerRequest.getEmail())) {
                Apireponsi<Users> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Email Đã Tồn tại ", (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
            } else {
                Role creator = (Role)this.authService.GetRoleByName("CREATOR").orElseThrow(() -> new RuntimeException("Role not found"));
                Users user = new Users().builder()
                        .phone(registerRequest.getPhone())
                        .email(registerRequest.getEmail())
                        .role(creator)
                        .createdAt(LocalDateTime.now())
                        .username(registerRequest.getName())
                        .password(this.passwordEncoder.encode(registerRequest.getPassword()))
                        .wallet(0.0)
                        .build();


                this.userService.SaveUser(user);
                Apireponsi<Users> reponse = new Apireponsi(HttpStatus.CREATED, "Đăng Kí Thành Công", user, (String)null);
                return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
            }
        } catch (Exception var5) {
            Apireponsi<Users> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object)null, (String)null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping({"/login"})
    public ResponseEntity<Apireponsi<Map<String, Object>>> login(@RequestBody LoginRequest loginRequest) {
        try {
            Users userOpt = this.userService.getUserByUsername(loginRequest.getUsername());
            if (userOpt == null) {
                Apireponsi<Map<String, Object>> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Tài khoản không tồn tại", (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            } else if (!this.passwordEncoder.matches(loginRequest.getPassword(), userOpt.getPassword())) {
                Apireponsi<Map<String, Object>> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Password không chính xác", (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            } else {
                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userOpt.getUsername());
                String token = this.Jwtutil.generateToken(loginRequest.getUsername());
                Map<String, Object> data = Map.of("token", token, "username", userOpt.getUsername(), "role", userOpt.getRole().getName());
                Apireponsi<Map<String, Object>> response = new Apireponsi(HttpStatus.OK, "Login thành công", data, (String)null);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            Apireponsi<Map<String, Object>> error = new Apireponsi(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi server: " + e.getMessage(), (Object)null, (String)null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}


