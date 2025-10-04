package com.example.demo.Config.Utill;
import java.util.Random;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class RandomUtil {
    public static String generateOTP(int length) {
        String chars = "0123456789";
        StringBuilder otp = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            otp.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return otp.toString();
    }

}
