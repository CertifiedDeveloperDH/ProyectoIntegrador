package com.example.auth_service.controller;


import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/me")
    public String getUserInfo(JwtAuthenticationToken token) {
        return "Usuario autenticado: " + token.getToken().getClaims();
    }
}

