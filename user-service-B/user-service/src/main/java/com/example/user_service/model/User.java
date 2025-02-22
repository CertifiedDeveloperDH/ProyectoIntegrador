package com.example.user_service.model;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "users") // Nombre de la tabla en la base de datos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento para MySQL
    private Long id;

    @Column(nullable = false) // No permite valores nulos
    private String nyap;

    @Column(nullable = false, unique = true) // No permite valores nulos y debe ser único
    private String dni;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String password;

    @Column(unique = true) // Debe ser único
    private String cvu;

    @Column(unique = true) // Debe ser único
    private String alias;

    // Constructores
    public User() {
    }

    public User(String nyap, String dni, String email, String telefono, String password) {
        this.nyap = nyap;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.cvu = generarCVU();
        this.alias = generarAlias();
    }

    public User(Long id, String nyap, String dni, String email, String telefono, String passwor) {
        this.id = id;
        this.nyap = nyap;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

    // Métodos para generar CVU y Alias (como antes)
    public static String generarCVU() {
        Random random = new Random();
        StringBuilder cvu = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            cvu.append(random.nextInt(10));
        }
        return cvu.toString();
    }

    public static String generarAlias() {
        List<String> palabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("palabras.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras.add(linea.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (palabras.size() < 3) {
            throw new IllegalArgumentException("El archivo debe contener al menos 3 palabras.");
        }

        Collections.shuffle(palabras);
        return palabras.get(0) + "." + palabras.get(1) + "." + palabras.get(2);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}