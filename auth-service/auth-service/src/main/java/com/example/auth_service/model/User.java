package com.example.auth_service.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class User {
    private Long id;             // ID autogenerado (Long para bases de datos como MySQL o PostgreSQL)
    private String nyap;         // Nombre y Apellido (String)
    private String dni;          // Documento Nacional de Identidad (String, para evitar problemas con ceros iniciales)
    private String email;        // Correo electrónico (String)
    private String telefono;     // Teléfono (String, por si incluye códigos de país o guiones)
    private String password;  // Contraseña (String, encriptada con BCrypt)

    // Constructor con generación automática de CVU y Alias
    public User(String nyap, String dni, String email, String telefono, String password) {
        this.nyap = nyap;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getNyap() {
        return nyap;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNyap(String nyap) {
        this.nyap = nyap;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
