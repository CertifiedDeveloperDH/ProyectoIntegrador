package com.example.auth_service.client;

import com.example.auth_service.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "http://localhost:8087")
public interface UserServiceFeignClient {

    @PostMapping("/users/register")
    ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO);

}

