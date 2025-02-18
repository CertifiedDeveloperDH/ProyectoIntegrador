package com.example.auth_service.client;

        import com.example.auth_service.DTO.UserDTO;
        import org.springframework.stereotype.Component;

        import java.util.HashMap;
        import java.util.Map;

@Component
public class KeycloakClient {

    private final KeyCloakFeignClient keycloakFeignClient;  // Nombre corregido
    private static final String CLIENT_ID = "backend-client";
    private static final String CLIENT_SECRET = "your-client-secret";

    public KeycloakClient(KeyCloakFeignClient keycloakFeignClient) {
        this.keycloakFeignClient = keycloakFeignClient;
    }

    // 🔹 Registrar usuario en Keycloak
    public void registerUser(UserDTO userDTO) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", userDTO.getEmail());
        user.put("email", userDTO.getEmail());
        user.put("enabled", true);

        // 🔹 Estructura correcta de `credentials`
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", userDTO.getPassword());
        credentials.put("temporary", false);

        user.put("credentials", new Object[]{credentials});

        // 🔹 Obtener token de administrador
        String adminToken = getAdminToken();
        keycloakFeignClient.createUser("Bearer " + adminToken, user);
    }

    // 🔹 Login en Keycloak y obtener JWT
    public Map<String, Object> login(String email, String password) {
        return keycloakFeignClient.getToken("password", CLIENT_ID, CLIENT_SECRET, email, password);
    }

    // 🔹 Logout de Keycloak
    public void logout(String refreshToken) {
        keycloakFeignClient.logout(CLIENT_ID, CLIENT_SECRET, refreshToken);
    }

    // 🔹 Obtener token de administrador (usado para registrar usuarios)
    private String getAdminToken() {
        Map<String, Object> tokenResponse = keycloakFeignClient.getToken(
                "client_credentials", CLIENT_ID, CLIENT_SECRET, null, null
        );
        return tokenResponse.get("access_token").toString();
    }
}
