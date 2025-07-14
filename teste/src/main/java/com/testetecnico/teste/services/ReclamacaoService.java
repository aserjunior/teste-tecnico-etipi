package com.testetecnico.teste.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testetecnico.teste.entities.Reclamacao;
import com.testetecnico.teste.repositories.ReclamacaoRepository;

@Service
public class ReclamacaoService {
    
    @Autowired
    private ReclamacaoRepository repository;

    public List<Reclamacao> findAll() {
        return repository.findAll();
    }

    public Reclamacao findById(Long id) {
        Optional<Reclamacao> obj = repository.findById(id);
        return obj.get();
    }
}
