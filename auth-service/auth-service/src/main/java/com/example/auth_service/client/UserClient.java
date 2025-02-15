package com.example.auth_service.client;

import com.example.auth_service.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service") // Nombre del servicio registrado en Eureka o en el balanceador de carga
public interface UserClient {
    @GetMapping("/users/email")
    UserDTO getUserByEmail(@RequestParam String email);
}
