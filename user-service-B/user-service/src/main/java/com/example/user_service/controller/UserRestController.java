package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    // 📌 Registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    // 📌 Obtener información del usuario autenticado
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getUserInfo(JwtAuthenticationToken token) {
        // Recuperamos la información del usuario desde el JWT
        return ResponseEntity.ok(token.getTokenAttributes());
    }

    // 📌 Obtener un usuario por ID (Solo si el usuario está autenticado y es su propio perfil)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id, JwtAuthenticationToken token) {
        // Verificamos que el ID del usuario autenticado coincida con el ID solicitado
        Long authenticatedUserId = (Long) token.getTokenAttributes().get("user_id"); // Suponiendo que el JWT tenga un "user_id"
        if (!authenticatedUserId.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // No permitido
        }

        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 📌 Actualizar un usuario por ID (Solo si el usuario está autenticado y es su propio perfil)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails, JwtAuthenticationToken token) {
        Long authenticatedUserId = (Long) token.getTokenAttributes().get("user_id"); // Verificamos que el ID en el JWT coincida con el ID
        if (!authenticatedUserId.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // No permitido
        }

        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 📌 Eliminar un usuario por ID (Solo si el usuario está autenticado y es su propio perfil)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, JwtAuthenticationToken token) {
        Long authenticatedUserId = (Long) token.getTokenAttributes().get("user_id");
        if (!authenticatedUserId.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // No permitido
        }

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 Obtener todos los usuarios (endpoint extra)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
