package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UserRestController {

    @RestController
    @RequestMapping("/users")
    public class UserController {

        @Autowired
        private UserService userService;

        // 📌 Registrar un nuevo usuario
        @PostMapping("/register")
        public ResponseEntity<User> registerUser(@RequestBody User user) {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        }

        // 📌 Obtener un usuario por ID
        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            Optional<User> user = userService.getUserById(id);
            return user.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        // 📌 Actualizar un usuario por ID
        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
            try {
                User updatedUser = userService.updateUser(id, userDetails);
                return ResponseEntity.ok(updatedUser);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }

        // 📌 Eliminar un usuario por ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }

        // 📌 Obtener todos los usuarios (endpoint extra)
        @GetMapping
        public ResponseEntity<List<User>> getAllUsers() {
            return ResponseEntity.ok(userService.getAllUsers());
        }
    }
}
