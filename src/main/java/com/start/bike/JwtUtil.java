package com.start.bike;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import com.start.bike.TokenResponse;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
@Component
public class JwtUtil {
    private String secret = "nK0TkfV8QWgU3mXyT9Yzj1lHk5eZvLxJpNcRbEwGhTq4sDfP7oA9rB6uWnM2tXa4Zi7yUb3vQwF6dI9mO5pLMN2";

    // 将 Base64 URL 安全编码的密钥解码为字节数组
    byte[] decodedKey = Base64.getUrlDecoder().decode(secret);

    // 使用解码后的字节数组创建 SecretKey 对象
    SecretKey key = Keys.hmacShaKeyFor(decodedKey);
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
    }
    // 新增的方法，返回用于签名的密钥
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
   

}