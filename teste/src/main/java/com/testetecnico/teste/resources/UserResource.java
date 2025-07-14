package com.testetecnico.teste.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testetecnico.teste.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    @GetMapping
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Aser", "123456789", "aser@teste.com", "123");
        return ResponseEntity.ok().body(u);
    }
}
