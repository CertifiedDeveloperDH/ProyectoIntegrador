package com.example.auth_service.implementation;

import com.example.auth_service.DTO.UserDTO;

import java.util.Map;

public interface IAuthService {
    Map<String, Object> login(UserDTO userDTO);
    void logout(String token);
    Map<String, Object> register(UserDTO userDTO);
}
