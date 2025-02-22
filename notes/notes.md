# 该文件为本项目的笔记
## 前后端的连接
在 Spring Boot 中配置 CORS
如果你决定在 Spring Boot 中配置 CORS，下面是一个简单的例子，允许来自任何来源的请求（仅适用于开发阶段，请根据生产需求调整）：

```java


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // 允许所有来源
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // 允许的方法
            }
        };
    }
}
```
在 Vue CLI 中设置代理
如果你使用的是 Vue CLI 构建工具，那么可以在项目的根目录下创建或编辑 vue.config.js 文件，添加如下代码：

```javascript

module.exports = {
devServer: {
proxy: {
'/api': {
target: 'http://localhost:8081', // 后端地址
changeOrigin: true,
pathRewrite: {
'^/api': '' // 重写路径，去掉 /api 前缀
}
}
}
}
};
```
现在，当你从 Vue 发起 /api 开头的请求时，这些请求会被代理到 http://localhost:8081 上对应的路径。


user表的sql语句：
-- 创建数据库 boke
CREATE DATABASE IF NOT EXISTS boke;

-- 使用新创建的数据库
USE boke;

-- 创建 users 表
CREATE TABLE IF NOT EXISTS users (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
password_hash VARCHAR(255) NOT NULL,
role ENUM('user', 'admin', 'moderator') DEFAULT 'user',
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
last_login TIMESTAMP
);

-- 可选：插入一条管理员用户的记录（仅用于初始化）
INSERT INTO users (username, email, password_hash, role)
VALUES ('admin', 'admin@example.com', '$2a$10$DvnUJpX74f9Q6Z3tH7z7Ee8nL5bYzZ5WqKwRjPmOyMvBZGzg9xTqS', 'admin')
ON DUPLICATE KEY UPDATE id=id; -- 如果已经存在则不插入

-- 注意：上面的密码哈希值是示例，实际使用时应替换为通过安全哈希算法生成的真实密码哈希。



# jwt，详细
在Java项目中，如果你发现生成JWT的方法（如generateToken）显示为“未使用”，这通常是因为该方法没有被任何其他代码调用。为了确保该方法被正确使用，你需要在适当的地方调用它。以下是几种常见的使用场景和示例代码，帮助你理解如何在项目中使用generateToken方法。

常见使用场景
用户登录成功后生成JWT：
在用户登录验证成功后，生成一个JWT并返回给前端。
刷新令牌时生成新的JWT：
当访问令牌过期时，通过刷新令牌生成一个新的访问令牌。
授权中间件或过滤器中解析和验证JWT：
在每个受保护的API请求中，解析和验证JWT以确认用户身份。
示例代码
1. 用户登录成功后生成JWT
   假设你有一个用户登录的服务类，当用户登录成功后，你可以调用JwtUtil中的generateToken方法来生成JWT，并将其返回给前端。

java
深色版本
package com.start.bike.service;

import com.start.bike.JwtUtil;
import com.start.bike.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        // 假设这里进行了用户名和密码的验证
        User user = authenticateUser(username, password);
        if (user != null) {
            // 登录成功，生成JWT
            return jwtUtil.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private User authenticateUser(String username, String password) {
        // 这里进行实际的用户认证逻辑
        // 例如查询数据库检查用户名和密码是否匹配
        // 返回用户对象或null
        return new User(username); // 假设用户存在
    }
}
2. 刷新令牌时生成新的JWT
   假设你有一个控制器处理刷新令牌的请求，当收到有效的刷新令牌时，可以生成一个新的JWT并返回给前端。


package com.start.bike.controller;

import com.start.bike.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/refresh-token")
    public String refreshToken(@RequestBody RefreshTokenRequest request) {
        // 验证刷新令牌的有效性
        boolean isValidRefreshToken = validateRefreshToken(request.getRefreshToken());
        if (isValidRefreshToken) {
            // 生成新的JWT
            return jwtUtil.generateToken(request.getUsername());
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    private boolean validateRefreshToken(String refreshToken) {
        // 实际的刷新令牌验证逻辑
        // 例如查询数据库检查刷新令牌是否有效
        return true; // 假设刷新令牌有效
    }
}

class RefreshTokenRequest {
private String username;
private String refreshToken;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
3. 授权中间件或过滤器中解析和验证JWT
   假设你有一个过滤器，在每个受保护的API请求中解析和验证JWT。

java
深色版本
package com.start.bike.config;

import com.start.bike.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                // 处理无效或过期的令牌
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Claims claims = jwtUtil.extractAllClaims(jwt);
            // 根据需要设置SecurityContext
            // SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
确保依赖注入配置正确
确保你的Spring Boot应用程序已经正确配置了依赖注入，以便能够自动将JwtUtil注入到需要使用它的类中。

启用组件扫描：确保你的Spring Boot应用启用了组件扫描，这样Spring才能找到并管理带有@Component或@Service注解的类。
依赖注入：确保在需要使用JwtUtil的地方使用@Autowired注解进行依赖注入。
java
深色版本
@Autowired
private JwtUtil jwtUtil;
完整示例
以下是一个完整的示例，展示了如何在一个Spring Boot应用程序中使用JwtUtil类生成、验证和解析JWT。

1. JwtUtil 类
   java
   深色版本
   package com.start.bike;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
@Value("${jwt.secret}")
private String secret;

    public String generateToken(String username) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 86400000; // 设置过期时间为24小时
        Date now = new Date(nowMillis);
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token, String username) {
        try {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            // 处理无效或过期的令牌
            return false;
        }
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

    private Claims extractAllClaims(String token) throws Exception {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new Exception("Invalid or expired JWT token");
        }
    }
}
2. UserService 类
   java
   深色版本
   package com.start.bike.service;

import com.start.bike.JwtUtil;
import com.start.bike.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        // 假设这里进行了用户名和密码的验证
        User user = authenticateUser(username, password);
        if (user != null) {
            // 登录成功，生成JWT
            return jwtUtil.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private User authenticateUser(String username, String password) {
        // 这里进行实际的用户认证逻辑
        // 例如查询数据库检查用户名和密码是否匹配
        // 返回用户对象或null
        return new User(username); // 假设用户存在
    }
}
3. AuthController 类
   java
   深色版本
   package com.start.bike.controller;

import com.start.bike.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework jadx.servlet.http.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return jwtUtil.generateToken(request.getUsername());
    }

    @PostMapping("/refresh-token")
    public String refreshToken(@RequestBody RefreshTokenRequest request) {
        // 验证刷新令牌的有效性
        boolean isValidRefreshToken = validateRefreshToken(request.getRefreshToken());
        if (isValidRefreshToken) {
            // 生成新的JWT
            return jwtUtil.generateToken(request.getUsername());
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    private boolean validateRefreshToken(String refreshToken) {
        // 实际的刷新令牌验证逻辑
        // 例如查询数据库检查刷新令牌是否有效
        return true; // 假设刷新令牌有效
    }
}

class LoginRequest {
private String username;
private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class RefreshTokenRequest {
private String username;
private String refreshToken;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
4. JwtFilter 类
   java
   深色版本
   package com.start.bike.config;

import com.start.bike.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                // 处理无效或过期的令牌
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Claims claims = jwtUtil.extractAllClaims(jwt);
            // 根据需要设置SecurityContext
            // SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}