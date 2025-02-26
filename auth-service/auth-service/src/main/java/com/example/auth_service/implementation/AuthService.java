package com.example.auth_service.implementation;

import com.example.auth_service.DTO.UserDTO;
import com.example.auth_service.client.KeycloakClient;
import com.example.auth_service.client.UserServiceFeignClient;
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
        try {
            // 🔹 Intentar loguearse primero (si no existe, Keycloak lanzará error)
            keycloakClient.login(userDTO.getEmail(), userDTO.getPassword());
            return Map.of("error", "El usuario ya está registrado");
        } catch (Exception e) {
            // 🔹 Si Keycloak lanza error, significa que el usuario no existe -> proceder con el registro
            try {
                keycloakClient.registerUser(userDTO);

                // 🔹 Crear instancia de User para User-Service
                UserDTO user = new UserDTO(userDTO.getNyap(), userDTO.getDni(), userDTO.getEmail(),
                        userDTO.getTelefono(), userDTO.getPassword());

                userServiceFeignClient.registerUser(user);  // Registrar en User-Service

                return Map.of("message", "Usuario registrado correctamente");

            } catch (Exception userServiceError) {
                // 🔹 Si el registro en User-Service falla, eliminar al usuario de Keycloak para evitar inconsistencia
                revertKeycloakRegistration(userDTO.getEmail());
                return Map.of("error", "Error al registrar usuario en User-Service");
            }
        }
    }

    // 🔹 Método para eliminar un usuario de Keycloak si algo falla
    private void revertKeycloakRegistration(String email) {
        try {
            // 🔹 Obtener token de administrador
            String adminToken = keycloakClient.getAdminToken();

            // 🔹 Buscar usuario en Keycloak para obtener su ID
            String userId = keycloakClient.getUserIdByEmail(adminToken, email);

            // 🔹 Eliminar usuario si existe
            if (userId != null) {
                keycloakClient.deleteUser(adminToken, userId);
            }
        } catch (Exception e) {
            System.out.println("⚠ Error al revertir registro en Keycloak: " + e.getMessage());
        }
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
