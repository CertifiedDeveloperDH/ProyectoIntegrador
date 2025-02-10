package com.example.user_service.controller;

import com.example.user_service.DTO.RegistroRequest;
import com.example.user_service.service.UsuarioService;
import com.example.user_service.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrar(@RequestBody RegistroRequest request) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(request));
    }

}
