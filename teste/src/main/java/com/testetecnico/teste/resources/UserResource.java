package com.testetecnico.teste.resources;

import java.net.URI;
import java.util.List;

import com.testetecnico.teste.dtos.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testetecnico.teste.entities.User;
import com.testetecnico.teste.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserResource {

    @Autowired
    private UserService service;

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getCpf(), user.getName(), user.getEmail());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> dtos = list.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/current")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // supondo que o username seja o email

        User user = service.findByEmail(email); // Você precisa ter esse método
        return ResponseEntity.ok(convertToDTO(user));
    }
    
    @PostMapping
    public ResponseEntity<User> insert (@RequestBody User obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
