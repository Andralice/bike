package com.start.bike.controller;

import com.start.bike.entity.User;
import com.start.bike.util.StringValidator;
import com.start.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.start.bike.JwtUtil;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.start.bike.TokenResponse;
@RestController
@RequestMapping("/User")
public class UserController {
    /**
     * 200 OK：常规成功操作
     * 201 Created：资源创建成功
     * 400 Bad Request：参数校验失败
     * 404 Not Found：资源不存在
     * 500 Internal Server Error：服务器内部错误
     */

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/createUser")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        Map<String, Object> body = new HashMap<>();
        try {
            if (userService.isUserExists(user)) {
                body.put("success", "false");
                body.put("message", "用户已存在");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(body);
            }

            if (StringValidator.isAlphanumeric(user.getPassword() , 8, 16)) {
                body.put("success", "false2");
                body.put("message", "密码格式错误，需要同时包含数字和字母，且不含其他字符");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(body);
            }
            // 插入用户数据...
            userService.insertUser(user);
            body.put("success", "true");
            body.put("message", "注册成功");

            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false3");
            body.put("message", "注册失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 改为 500
                    .body(body);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> body = new HashMap<>();
        try {
            Object result = userService.selectLoginUser(user);
            if (result != null) {
                // 生成 token
                String refreshToken = jwtUtil.generateToken(user.getUsername());
                String token = jwtUtil.generateToken(user.getUsername());
                body.put("success", "true");
                body.put("token", refreshToken);
                // 返回 token 给前端
                return ResponseEntity.ok(body);
            } else {
                body.put("success", "false");
                body.put("message", "用户名或密码错误");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(body);
            }
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 改为 500
                    .body(body);
        }
    }

    @PostMapping("/selectAllUsers")
    public ResponseEntity<Map<String, Object>> selectAllUsers(int page, int size) {
        Map<String, Object> body = new HashMap<>();
        try {
            List<User> result = userService.selectAllUsers(page, size);
            body.put("success", "true");
            body.put("result", result);
            return ResponseEntity.ok(body);
        }catch (Exception e) {
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/updateUser")
    public  ResponseEntity<Map<String, Object>> update(@RequestBody User user) {
        Map<String, Object> body = new HashMap<>();
        // 更新密码
        try {
            if (StringValidator.isAlphanumeric(user.getPassword(), 8, 16)) {
                body.put("success", "false");
                body.put("message", "密码格式错误，需要同时包含数字和字母，且不含其他字符");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(body);
            }
            // 更新用户数据...
            userService.updateUser(user);
            body.put("success", "true");
            body.put("message", "更新成功");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "更新失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 改为 500
                    .body(body);
        }
    }

    @PostMapping("/deleteUser/{userId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer userId) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 1. 参数校验（根据业务需求添加）
            if (userId == null) {
                body.put("success", "false");
                body.put("message", "用户ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(body);
            }

            // 2. 执行删除操作
            boolean deleteResult = userService.deleteUserById(userId);

            if (!deleteResult) {
                body.put("success", "false");
                body.put("message", "用户不存在或删除失败");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(body);
            }

            // 3. 成功响应
            body.put("success", "true");
            body.put("message", "删除成功");
            return ResponseEntity.ok(body);

        } catch (Exception e) {
            // 4. 异常处理
            body.put("success", "false");
            body.put("message", "删除失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(body);
        }
    }
}



