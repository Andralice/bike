package com.start.bike.controller;

import com.start.bike.entity.User;
import com.start.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            if (userService.isUserExists(user)) {
                return ResponseEntity.badRequest().body("User already exists");
            }

            // 设置创建时间和更新时间
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            user.setCreateTime(currentTime);
            user.setUpdateTime(currentTime);
            // 插入用户数据
            userService.insertUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to register user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Object result = userService.selectUser(user);
            if (result != null) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to login: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {
        // 更新密码
        try {
            if (Objects.equals(userService.selectUserByPassword(user).getPassword(), user.getPassword())){
                Object result = userService.updateUser(user);
                if (result != null) {
                    return ResponseEntity.ok("Update successful");
                } else {
                    return ResponseEntity.status(401).body("Invalid username or password");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update: " + e.getMessage());
        }
        // 更新权限


        return null;
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody User user) {
        try {
            Object result = userService.deleteUser(user);
            if (result != null) {
                return ResponseEntity.ok("Delete successful");
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete: " + e.getMessage());
        }
    }
}



