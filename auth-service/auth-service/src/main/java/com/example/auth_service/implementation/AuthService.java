package com.example.auth_service.implementation;

import com.example.auth_service.DTO.UserDTO;
import com.example.auth_service.client.UserClient;
import com.example.auth_service.service.IAuthService;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.security.Key;


@Service
public class AuthService implements IAuthService {  // Ahora es una clase, no interfaz

    @Autowired
    private UserClient userClient; // Inyección de dependencia correcta

    private static final String SECRET_KEY = "MiClaveSuperSecretaParaJWT1234567890";

    @Override
    public String login(String email, String contrasenia) {
        UserDTO user = userClient.getUserByEmail(email); // Obtiene datos del usuario
        if (user == null || !user.getContrasenia().equals(contrasenia)) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        return generateToken(email);
    }

    private String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256) // Firma JWT corregida
                .compact();
    }
}