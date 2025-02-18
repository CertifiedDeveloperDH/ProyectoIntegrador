package com.example.auth_service.controller;

import com.example.auth_service.DTO.UserDTO;
import com.example.auth_service.implementation.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🔹 Registro de usuario
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authService.register(userDTO));
    }

    // 🔹 Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authService.login(userDTO));
    }

    // 🔹 Logout
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok().build();
    }
}
