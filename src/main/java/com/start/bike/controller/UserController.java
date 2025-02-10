package com.start.bike.controller;
import com.start.bike.service.UserService;
import com.start.bike.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
