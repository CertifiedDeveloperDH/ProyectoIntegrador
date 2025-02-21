package com.example.auth_service.client;

import com.example.auth_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

    @PostMapping("/users/register")
    void registerUser(@RequestBody User user); // Acepta User ahora
}

