package com.example.auth_service.implementation;

import com.example.auth_service.DTO.UserDTO;
import com.example.auth_service.client.KeycloakClient;
import com.example.auth_service.client.UserServiceFeignClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final KeycloakClient keycloakClient;
    private final UserServiceFeignClient userServiceFeignClient;

    public AuthService(KeycloakClient keycloakClient, UserServiceFeignClient userServiceFeignClient) {
        this.keycloakClient = keycloakClient;
        this.userServiceFeignClient = userServiceFeignClient;
    }

    // 🔹 Registro de usuario en Keycloak y User-Service
    public void register(UserDTO userDTO) {
        keycloakClient.registerUser(userDTO);  // Keycloak
        userServiceFeignClient.registerUser(userDTO);  // User-Service
    }

    // 🔹 Login en Keycloak
    public Map<String, Object> login(String email, String password) {
        return keycloakClient.login(email, password);
    }

    // 🔹 Logout en Keycloak
    public void logout(String refreshToken) {
        keycloakClient.logout(refreshToken);
    }
}
