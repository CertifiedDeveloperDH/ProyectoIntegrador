package com.example.auth_service.repository;

import com.example.auth_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface FeignUserRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    User getUserById(RequestParam String id);
}
