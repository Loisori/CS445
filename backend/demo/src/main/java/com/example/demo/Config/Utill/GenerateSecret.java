package com.example.demo.Config.Utill;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class GenerateSecret { public static void main(String[] args) {
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 256-bit key
    String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
    System.out.println("Your Secret Key (Base64): " + base64Key);
}
}
