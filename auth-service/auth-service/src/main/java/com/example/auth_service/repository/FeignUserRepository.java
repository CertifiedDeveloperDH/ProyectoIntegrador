package com.example.auth_service.repository;

public interface FeignUserRepository {
    ScopedValue<Object> findByEmail(String email){

    };
}
