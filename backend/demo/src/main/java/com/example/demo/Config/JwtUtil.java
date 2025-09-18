package com.example.demo.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "TXlTdXBlclNlY3JldEtleUZvckpXVEdlbmVyYXRpb24xMjM0NTY3ODkwIUAj";
    private static final long JWT_EXPIRATION = 36000000; // 10 giờ

    // ================== GENERATE TOKEN ==================
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    // ================== EXTRACT CLAIMS ==================
    //T → Giá trị của claim được trả về theo function bạn truyền vào
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
// String → username của token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
// Date → ngày hết hạn token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
//laims → chứa username, roles, issuedAt, exp,...
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
//Boolean → true nếu token hết hạn
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ================== VALIDATE TOKEN ==================
    //Boolean → true nếu token hợp lệ và username trùng
    public Boolean validateToken(String token, String username) {
        try {
            final String tokenUsername = extractUsername(token);
            return (tokenUsername.equals(username) && !isTokenExpired(token));
        } catch (JwtException e) {
            // SignatureException, ExpiredJwtException, MalformedJwtException...
            return false;
        }
    }
}
