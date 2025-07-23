package com.memorygame.controller;

import com.memorygame.model.ApiResponse;
import com.memorygame.model.User;
import com.memorygame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody User newUser) {
        if (!userService.register(newUser)) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, "Tên đăng nhập đã tồn tại!", null));
        }
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Đăng ký thành công!", newUser)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody User loginRequest) {
        User user = userService.validateLogin(loginRequest.getUsername(), loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity
                    .status(401)
                    .body(new ApiResponse<>(false, "Sai tên đăng nhập hoặc mật khẩu!", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Đăng nhập thành công!", user)
        );
    }
}
