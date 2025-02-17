package com.example.auth_service.implementation;

import com.example.auth_service.DTO.UserDTO;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private static final String keycloakServerUrl = "http://localhost:8080"; // URL de tu Keycloak
    private static final String realm = "my-realm"; // Nombre de tu realm en Keycloak
    private static final String clientId = "my-client-id"; // ID del cliente en Keycloak
    private static final String clientSecret = "my-client-secret"; // Secreto del cliente en Keycloak

    // Método de login
    public Map<String, Object> login(UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Autenticación en Keycloak usando el email y password del UserDTO
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakServerUrl)
                    .realm(realm)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .grantType("password")
                    .username(userDTO.getEmail())  // Usamos el email del UserDTO
                    .password(userDTO.getContrasenia())  // Usamos la contraseña del UserDTO
                    .build();

            // Obtener el token de acceso
            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();

            // Agregar el token a la respuesta
            response.put("access_token", tokenResponse.getToken());
            response.put("refresh_token", tokenResponse.getRefreshToken());
            response.put("expires_in", tokenResponse.getExpiresIn());

            return response;

        } catch (Exception e) {
            response.put("error", "Error en autenticación: " + e.getMessage());
            return response;
        }
    }

    // Método para hacer logout y revocar el token
    public Map<String, Object> logout(String token) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Se hace la solicitud de revocación de token a Keycloak usando RestTemplate
            String url = keycloakServerUrl + "/realms/" + realm + "/protocol/openid-connect/revoke";
            RestTemplate restTemplate = new RestTemplate();

            // Crear el cuerpo de la solicitud
            String revokeBody = "token=" + token +
                    "&client_id=" + clientId +
                    "&client_secret=" + clientSecret;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            // Crear la solicitud
            HttpEntity<String> entity = new HttpEntity<>(revokeBody, headers);

            // Enviar la solicitud POST a Keycloak para revocar el token
            ResponseEntity<String> revokeResponse = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            if (revokeResponse.getStatusCode().is2xxSuccessful()) {
                response.put("message", "Logout exitoso y token revocado");
            } else {
                response.put("error", "Error al revocar el token: " + revokeResponse.getBody());
            }

        } catch (Exception e) {
            response.put("error", "Error en logout: " + e.getMessage());
        }

        return response;
    }
}
