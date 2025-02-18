package com.example.auth_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "keycloak-client", url = "http://localhost:8088")
public interface KeycloakFeignClient {

    // 🔹 Registrar un usuario en Keycloak
    @PostMapping("/admin/realms/my-realm/users")
    void createUser(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> user);

    // 🔹 Obtener un token de acceso
    @PostMapping("/realms/my-realm/protocol/openid-connect/token")
    @RequestParam("grant_type") String grantType,
    @RequestParam("client_id") String clientId,
    @RequestParam("client_secret") String clientSecret,
    @RequestParam("username") String username,
    @RequestParam("password") String password
    Map<String, Object> getToken();

    // 🔹 Revocar un token
    @PostMapping("/realms/my-realm/protocol/openid-connect/logout")
    @RequestParam("client_id") String clientId,
    @RequestParam("client_secret") String clientSecret,
    @RequestParam("refresh_token") String refreshToken
    void logout();
}

