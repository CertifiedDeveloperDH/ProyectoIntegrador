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
    private String contrasenia;  // Contraseña (String, encriptada con BCrypt)
    private String cvu;          // CVU de 22 dígitos (String, validado como número de cuenta)
    private String alias;        // Alias autogenerado (tres palabras separadas por puntos)

    // Constructor con generación automática de CVU y Alias
    public User(String nyap, String dni, String email, String telefono, String contrasenia) {
        this.nyap = nyap;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.cvu = generarCVU();
        this.alias = generarAlias();
    }
    public static String generarCVU() {
        Random random = new Random();
        StringBuilder cvu = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            cvu.append(random.nextInt(10)); // Agrega un número aleatorio entre 0 y 9
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

    public String getContrasenia() {
        return contrasenia;
    }

    public String getCvu() {
        return cvu;
    }

    public String getAlias() {
        return alias;
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

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
