package com.example.demo.Controller.auth;
import com.example.demo.Config.Utill.EmailUtil;
import com.example.demo.Config.Utill.RandomUtil;
import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.Email;
import com.example.demo.DTO.newpassword;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.UserService;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/auth"})
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final RandomUtil randomUtil;

    private final EmailUtil emailUtil;

    private final UserService userService;
    private Map<String, String> savaotp = new HashMap();

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping({"/forgotPassword"})
    public Apireponsi<String> forgotPassword(@RequestBody Email email) {
        try {
            if (!this.userService.ExitEmail(email.getEmail())) {
                return new Apireponsi(HttpStatus.NOT_FOUND, "Tài khoản không tồn tại", (Object)null, (String)null);
            } else {
                RandomUtil var10000 = this.randomUtil;
                String otp = RandomUtil.generateOTP(6);
                this.savaotp.put(email.getEmail(), otp);
                this.emailUtil.sendOTP(email.getEmail(), "Mã xác nhận lấy lại mật khẩu: " + otp);
                return new Apireponsi(HttpStatus.OK, "Gửi mã OTP thành công", (Object)null, (String)null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Apireponsi(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống: " + e.getMessage(), (Object)null, (String)null);
        }
    }

    @PostMapping({"/resetPassword"})
    public Apireponsi<String> resetPassword(@RequestBody newpassword newpassword2) {
        try {
            String otp = newpassword2.getOtp();
            String savedOtp = (String)this.savaotp.get(newpassword2.getEmail());
            if (savedOtp != null && otp.equals(savedOtp)) {
                Users user = this.userService.getUserByEmail(newpassword2.getEmail());
                String mk = user.getPassword();
                if (user == null) {
                    return new Apireponsi(HttpStatus.UNAUTHORIZED, "Tài khoản không tồn tại", (Object)null, (String)null);
                } else {
                    user.setPassword(passwordEncoder.encode(newpassword2.getNewpassword1()));
                    this.userService.SaveUser(user);    
                    return new Apireponsi(HttpStatus.OK, "Lấy lại mật khẩu thành công", mk + " Đổi Thành ->" + newpassword2.getNewpassword1(), (String)null);
                }
            } else {
                return new Apireponsi(HttpStatus.BAD_REQUEST, "Mã OTP không hợp lệ", (Object)null, (String)null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Apireponsi(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống: " + e.getMessage(), (Object)null, (String)null);
        }
    }
}