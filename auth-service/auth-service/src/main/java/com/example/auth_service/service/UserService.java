package com.example.auth_service.service;

import com.example.auth_service.model.User;
import com.example.auth_service.repository.FeignUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private FeignUserRepository feignUserRepository;

    public UserService(FeignUserRepository feignUserRepository) {
        super();
        this.feignUserRepository = feignUserRepository;
    }

    @Override
    public User getUser(String id){
        return feignUserRepository.getUserById(id);
    }

}
