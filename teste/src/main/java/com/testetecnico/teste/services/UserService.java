package com.testetecnico.teste.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testetecnico.teste.entities.User;
import com.testetecnico.teste.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public User findByEmail(String email) {
        Optional<User> obj = repository.findByEmail(email);
        return obj.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
