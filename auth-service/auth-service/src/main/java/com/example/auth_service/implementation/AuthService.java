package com.example.auth_service.implementation;

import com.example.auth_service.DTO.UserDTO;
import com.example.auth_service.client.KeycloakClient;
import com.example.auth_service.client.UserServiceFeignClient;
import com.example.auth_service.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService implements IAuthService {

    private final KeycloakClient keycloakClient;
    private final UserServiceFeignClient userServiceFeignClient;

    public AuthService(KeycloakClient keycloakClient, UserServiceFeignClient userServiceFeignClient) {
        this.keycloakClient = keycloakClient;
        this.userServiceFeignClient = userServiceFeignClient;
    }

    // 🔹 Registro de usuario en Keycloak y User-Service
    @Override
    public Map<String, Object> register(UserDTO userDTO) {
        // Convertimos UserDTO a User (si necesitas almacenamiento interno en User-Service)
        User user = new User(userDTO.getNyap(), userDTO.getDni(), userDTO.getEmail(), userDTO.getTelefono(), userDTO.getPassword());

        keycloakClient.registerUser(userDTO);  // Keycloak
        userServiceFeignClient.registerUser(user);  // User-Service

        return Map.of("message", "Usuario registrado correctamente");
    }

    // 🔹 Login en Keycloak
    @Override
    public Map<String, Object> login(UserDTO userDTO) {
        return keycloakClient.login(userDTO.getEmail(), userDTO.getPassword());
    }

    // 🔹 Logout en Keycloak
    @Override
    public void logout(String refreshToken) {
        keycloakClient.logout(refreshToken);
    }
}
