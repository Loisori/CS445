package com.example.demo.Config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "TXlTdXBlclNlY3JldEtleUZvckpXVEdlbmVyYXRpb24xMjM0NTY3ODkwIUAj";
    private static final long JWT_EXPIRATION = 36000000L;

    public JwtUtil() {
    }

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 36000000L)).signWith(Keys.hmacShaKeyFor("TXlTdXBlclNlY3JldEtleUZvckpXVEdlbmVyYXRpb24xMjM0NTY3ODkwIUAj".getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256).compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = this.extractAllClaims(token);
        return (T) claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return (String) this.extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return (Date) this.extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor("TXlTdXBlclNlY3JldEtleUZvckpXVEdlbmVyYXRpb24xMjM0NTY3ODkwIUAj".getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        try {
            String tokenUsername = this.extractUsername(token);
            return tokenUsername.equals(username) && !this.isTokenExpired(token);
        } catch (JwtException var4) {
            return false;
        }
    }
}

