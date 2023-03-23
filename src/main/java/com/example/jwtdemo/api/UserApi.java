package com.example.jwtdemo.api;

import com.example.jwtdemo.dto.UserDTO;
import com.example.jwtdemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping
    ResponseEntity<?> get(@RequestParam(required = false) Long id, Pageable pageable) {
        return ResponseEntity.ok(
                id == null
                        ? userService.list(pageable)
                        : userService.get(id)
        );
    }

    @PostMapping
    ResponseEntity<?> post(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    @PutMapping
    ResponseEntity<?> put(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.update(dto));
    }


    @DeleteMapping
    ResponseEntity<?> delete(@RequestBody long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
