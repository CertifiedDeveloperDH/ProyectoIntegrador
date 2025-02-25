package com.example.api_gateway.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String requestURI = request.getRequestURI();
        Map<String, Object> errorResponse = new HashMap<>();

        if (requestURI.startsWith("/users")) {
            // Usuario no autenticado intentando acceder a /users → Indicar que debe loguearse
            errorResponse.put("error", "Unauthorized access");
            errorResponse.put("message", "Please log in to access this resource.");
            errorResponse.put("redirect", "/auth/login");
        } else {
            // Cualquier otro acceso no autenticado → Indicar que debe registrarse
            errorResponse.put("error", "Unauthorized access");
            errorResponse.put("message", "Please register to access this resource.");
            errorResponse.put("redirect", "/auth/register");
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}

