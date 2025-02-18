package com.example.auth_service.DTO;

public class UserDTO {
    private Long id;

    private String nyap;
    private String email;
    private String password;

    public UserDTO(Long id, String nyap, String email, String password) {
        this.id = id;
        this.nyap = nyap;
        this.email = email;
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
}
