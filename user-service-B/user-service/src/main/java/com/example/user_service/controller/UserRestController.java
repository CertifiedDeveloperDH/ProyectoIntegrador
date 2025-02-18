package com.example.user_service.controller;

import com.example.user_service.DTO.UserDTO;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // 🔹 Endpoint para registrar usuario desde `Auth-Service`
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }
}
