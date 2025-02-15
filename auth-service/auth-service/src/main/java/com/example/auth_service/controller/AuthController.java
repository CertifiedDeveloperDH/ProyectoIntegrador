package com.example.auth_service.controller;


import com.example.auth_service.DTO.UserDTO;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.FeignUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {

    @Autowired
    private FeignUserRepository userRepository;

    @GetMapping("/email")
    public UserDTO getUserByEmail(@RequestParam String email) {
        User user = (User) userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;

        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getContrasenia());
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setContrasenia(user.getContrasenia()); // En una implementación real, nunca envíes la contraseña en texto plano.
        return userDTO;
    }
}

