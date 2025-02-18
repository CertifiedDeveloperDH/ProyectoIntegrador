package com.example.user_service.client;

import com.example.user_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

    @PostMapping("/users/register")
    void registerUser(@RequestBody User user);
}

