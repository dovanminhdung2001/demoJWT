package com.example.jwtdemo.service.impl;

import com.example.jwtdemo.dto.UserDTO;
import com.example.jwtdemo.entity.UserEntity;
import com.example.jwtdemo.repo.UserRepo;
import com.example.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserEntity get(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Override
    public Page<UserEntity> list(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public UserEntity create(UserDTO user) {
//        if (getByEmail((user.getEmail())) == null) {
            UserEntity newUser = new UserEntity(
                    user.getName(),
                    user.getEmail(),
                    bcryptEncoder.encode(user.getPassword()),
                    false
            );
            return userRepo.save(newUser);
//        } else {
//            return new UserEntity();
//        }
    }

    @Override
    public UserEntity update(UserDTO dto) {
        if (dto.getId() != null) {
            UserEntity user = userRepo.findById(dto.getId()).get();

            if (dto.getName() != null)
                user.setName(dto.getName());

            if (dto.getEmail() != null)
                user.setEmail(dto.getEmail());

            if (dto.getPassword() != null)
                user.setPassword(bcryptEncoder.encode(user.getPassword()));

            return userRepo.save(user);
        }
        return new UserEntity();
    }

    @Override
    public void delete(Long id) {
        UserEntity user = userRepo.findById(id).get();

        user.setDeleted(true);
        userRepo.save(user);
    }
}
