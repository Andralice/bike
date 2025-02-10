package com.start.bike.controller;

import com.start.bike.entity.User;
import com.start.bike.service.UserService;
import org.apache.kafka.common.config.types.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            if (userService.isUserExists(user.getUsername())) {
                return ResponseEntity.badRequest().body("User already exists");
            }
            userService.insertUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to register user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println(user);
        try {
            Object result = userService.selectUser(user.getUsername(), user.getPassword());
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
        try {
            if (Objects.equals(userService.selectUserByPassword(user.getUsername()).getPassword(), user.getPassword())){
                Object result = userService.updateUser(user.getUsername(), user.getPassword());
                if (result != null) {
                    return ResponseEntity.ok("Update successful");
                } else {
                    return ResponseEntity.status(401).body("Invalid username or password");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update: " + e.getMessage());
        }
        return null;
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody User user) {
        try {
            Object result = userService.deleteUser(user.getUsername());
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



