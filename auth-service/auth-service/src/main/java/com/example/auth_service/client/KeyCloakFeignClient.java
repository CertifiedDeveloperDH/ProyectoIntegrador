package com.example.auth_service.client;

import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "keycloak-client", url = "http://localhost:8088")
public interface KeyCloakFeignClient {

    // 🔹 Registrar un usuario en Keycloak
    @PostMapping(value = "/admin/realms/my-realm/users", consumes = "application/json")
    void createUser(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> user);

    // 🔹 Obtener un token de acceso
    @PostMapping(value = "/realms/my-realm/protocol/openid-connect/token", consumes = "application/x-www-form-urlencoded")
    @ResponseBody
    Map<String, Object> getToken(@RequestParam("grant_type") String grantType,
                                 @RequestParam("client_id") String clientId,
                                 @RequestParam("client_secret") String clientSecret,
                                 @RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "password", required = false) String password);

    // 🔹 Revocar un token
    @PostMapping(value = "/realms/my-realm/protocol/openid-connect/logout", consumes = "application/x-www-form-urlencoded")
    void logout(@RequestParam("client_id") String clientId,
                @RequestParam("client_secret") String clientSecret,
                @RequestParam("refresh_token") String refreshToken);

    // 🔹 Obtener usuario por email
    @GetMapping(value = "/admin/realms/my-realm/users")
    List<Map<String, Object>> getUsersByEmail(@RequestHeader("Authorization") String token,
                                              @RequestParam("email") String email);

    // 🔹 Eliminar usuario por ID
    @DeleteMapping(value = "/admin/realms/my-realm/users/{id}")
    void deleteUser(@RequestHeader("Authorization") String token, @PathVariable("id") String userId);

}

