package com.example.user_service.usuario;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nyap;
    private Integer dni;
    private String email;
    private String telefono;
    private String contrasenia;

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNyap(String nyap) {
        this.nyap = nyap;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
