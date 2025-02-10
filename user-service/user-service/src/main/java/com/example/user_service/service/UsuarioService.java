package com.example.user_service.service;

import com.example.user_service.DTO.RegistroRequest;
import com.example.user_service.repository.UsuarioRepository;
import com.example.user_service.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AccountClient accountClient; // Comunicación con account-service

    public Usuario registrarUsuario(RegistroRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNyap(request.getNyap());
        usuario.setDni(request.getDni());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        usuario.setContrasenia(encriptar(request.getContrasenia()));
        usuario = usuarioRepository.save(usuario);

        // Llamada a account-service para asignar CVU
        accountClient.asignarCVU(usuario.getId());

        return usuario;
    }

    private String encriptar(String contrasenia) {
        return new BCryptPasswordEncoder().encode(contrasenia);
    }
}

