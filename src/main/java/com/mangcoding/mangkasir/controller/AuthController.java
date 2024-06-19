package com.mangcoding.mangkasir.controller;

import com.mangcoding.mangkasir.dto.LoginRequest;
import com.mangcoding.mangkasir.dto.LoginResponse;
import com.mangcoding.mangkasir.dto.RegisterRequest;
import com.mangcoding.mangkasir.dto.RegisterResponse;
import com.mangcoding.mangkasir.model.User;
import com.mangcoding.mangkasir.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest userDto) {
        User user = authService.registerUser(userDto.getUsername(), userDto.getGmail(), userDto.getPassword(), userDto.getConfirmPassword());
        RegisterResponse response = new RegisterResponse(user.getId(), user.getUsername(), user.getGmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest authenticationRequest) {
        LoginResponse response = authService.authenticateUser(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}
