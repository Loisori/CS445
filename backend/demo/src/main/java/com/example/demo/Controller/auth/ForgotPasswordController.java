package com.example.demo.Controller.auth;

import com.example.demo.Config.Utill.EmailUtil;
import com.example.demo.Config.Utill.RandomUtil;
import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.Email;
import com.example.demo.DTO.newpassword;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class ForgotPasswordController {
    @Autowired
    private RandomUtil randomUtil;

    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserService userService;

    private Map<String, String> savaotp = new HashMap<>();

    @PostMapping("/forgotPassword")
    public Apireponsi<String> forgotPassword(@RequestBody Email email) {
        try {
            if (!userService.ExitEmail(email.getEmail())) {
                return new Apireponsi<>(HttpStatus.NOT_FOUND, "Tài khoản không tồn tại", null, null);
            }

            String otp = randomUtil.generateOTP(6);
            savaotp.put(email.getEmail(), otp);

            emailUtil.sendOTP(email.getEmail(), "Mã xác nhận lấy lại mật khẩu: " + otp);

            return new Apireponsi<>(HttpStatus.OK, "Gửi mã OTP thành công", null, null);

        } catch (Exception e) {
            e.printStackTrace();
            return new Apireponsi<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống: " + e.getMessage(), null, null);
        }
    }


    @PostMapping("/resetPassword")
    public Apireponsi<String> resetPassword(@RequestBody newpassword newpassword2) {
        try {
            String otp = newpassword2.getOtp();
            String savedOtp = savaotp.get(newpassword2.getEmail());

            if (savedOtp == null || !otp.equals(savedOtp)) {
                return new Apireponsi<>(HttpStatus.BAD_REQUEST, "Mã OTP không hợp lệ", null, null);
            }

            Users user = userService.getUserByEmail(newpassword2.getEmail());
            String mk = user.getPassword();
            if (user == null) {
                return new Apireponsi<>(HttpStatus.UNAUTHORIZED, "Tài khoản không tồn tại", null, null);
            }

            user.setPassword(newpassword2.getNewpassword1());
            userService.SaveUser(user);

            return new Apireponsi<>(HttpStatus.OK, "Lấy lại mật khẩu thành công", mk + " Đổi Thành ->" + newpassword2.getNewpassword1(), null);

        } catch (Exception e) {
            e.printStackTrace();
            return new Apireponsi<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống: " + e.getMessage(), null, null);
        }
    }
}
