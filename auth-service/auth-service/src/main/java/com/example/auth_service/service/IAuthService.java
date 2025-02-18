package com.example.auth_service.service;

import com.example.auth_service.DTO.UserDTO;

import java.util.Map;

public interface IAuthService {
    public Map<String, Object> login(UserDTO userDTO);
    public void logout(String token);
    public Map<String, Object> register(UserDTO userDTO);
}
