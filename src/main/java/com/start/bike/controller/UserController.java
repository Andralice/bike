package com.start.bike.controller;
import com.start.bike.service.UserService;
import com.start.bike.entity.User;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
