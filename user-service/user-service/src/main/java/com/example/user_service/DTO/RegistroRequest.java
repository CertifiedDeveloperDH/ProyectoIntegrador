package com.example.user_service.DTO;


public class RegistroRequest {
    private String nyap;
    private Integer dni;
    private String email;
    private String telefono;
    private String contrasenia;

    public String getNyap() {
        return nyap;
    }

    public Integer getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}

