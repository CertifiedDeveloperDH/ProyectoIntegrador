package com.example.auth_service.DTO;

public class UserDTO {
    private Long id;
    private String email;
    private String contrasenia;

    public UserDTO(Long id, String email, String contrasenia) {
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
