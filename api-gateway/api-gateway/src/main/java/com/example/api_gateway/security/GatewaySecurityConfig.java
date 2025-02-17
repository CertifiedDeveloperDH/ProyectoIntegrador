package com.example.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/auth/me").authenticated()  // Solo accesible si está autenticado
                        .anyRequest().permitAll()  // Resto de las solicitudes permitidas
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt()  // Se configura el uso de JWT
                        .decoder(jwtDecoder())  // Método para decodificar el token JWT
                );

        return http.build();
    }

    // Configura un JwtDecoder que extrae el JWT de Keycloak
    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("http://localhost:8088/realms/my-realm");  // URI de Keycloak
    }

    // Si necesitas personalizar la conversión de autenticación con JWT
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        // Configura los roles y autorizaciones extraídas del JWT
        return jwtAuthenticationConverter;
    }
}
