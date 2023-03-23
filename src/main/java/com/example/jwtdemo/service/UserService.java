package com.example.jwtdemo.service;

import com.example.jwtdemo.dto.UserDTO;
import com.example.jwtdemo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserEntity get(Long id);
    UserEntity getByEmail(String email);
    Page<UserEntity> list(Pageable pageable);
    UserEntity create(UserDTO dto);
    UserEntity update(UserDTO dto);
    void delete(Long id);
}
