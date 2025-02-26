package com.example.auth_service.DTO;

public class UserDTO {
    private Long id;             // ID autogenerado (Long para bases de datos como MySQL o PostgreSQL)
    private String nyap;         // Nombre y Apellido (String)
    private String dni;          // Documento Nacional de Identidad (String, para evitar problemas con ceros iniciales)
    private String email;        // Correo electrónico (String)
    private String telefono;     // Teléfono (String, por si incluye códigos de país o guiones)
    private String password;  // Contraseña (String, encriptada con BCrypt)

    public UserDTO(Long id, String nyap, String email, String password) {
        this.id = id;
        this.nyap = nyap;
        this.email = email;
        this.password = password;
    }

    public UserDTO(String nyap, String dni, String email, String telefono, String password) {
        this.nyap = nyap;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNyap() {
        return nyap;
    }

    public void setNyap(String nyap) {
        this.nyap = nyap;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
