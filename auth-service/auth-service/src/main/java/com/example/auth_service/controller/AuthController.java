package com.example.auth_service.controller;

import com.example.auth_service.DTO.UserDTO;
import com.example.auth_service.implementation.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDTO userDTO) {
        // Se pasa el objeto UserDTO al AuthService
        Map<String, Object> response = authService.login(userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(@RequestParam String token) {
        // Se pasa el token al AuthService para logout
        Map<String, Object> response = authService.logout(token);
        return ResponseEntity.ok(response);
    }
}
