package com.start.bike.util;

import com.start.bike.entity.User;
import com.start.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserRole {
    @Autowired
    private UserService userService;

//   获取用户权限
    public String user_role(String username) {
        User user_role = userService.findUser(username);
        return user_role.getRole();
    }
}
