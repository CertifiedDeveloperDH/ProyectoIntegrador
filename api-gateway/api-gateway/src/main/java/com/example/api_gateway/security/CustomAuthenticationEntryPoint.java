package com.example.api_gateway.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/users")) {
            // Usuario intenta acceder a /users sin autenticación -> redirigir a /auth/login
            response.sendRedirect("/auth/login");
        } else if (requestURI.equals("/auth/logout")) {
            // Usuario intenta cerrar sesión sin autenticación -> redirigir a /auth/login
            response.sendRedirect("/auth/login");
        } else {
            // Cualquier otra ruta no autenticada -> redirigir a /auth/register
            response.sendRedirect("/auth/register");
        }
    }
}
