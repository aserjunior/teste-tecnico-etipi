package com.testetecnico.teste.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testetecnico.teste.dtos.ReclamacaoDTO;
import com.testetecnico.teste.entities.Reclamacao;
import com.testetecnico.teste.entities.User;
import com.testetecnico.teste.repositories.ReclamacaoRepository;
import com.testetecnico.teste.repositories.UserRepository;

@Service
public class ReclamacaoService {
    
    @Autowired
    private ReclamacaoRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<Reclamacao> findAll() {
        return repository.findAll();
    }

    public Reclamacao findById(Long id) {
        Optional<Reclamacao> obj = repository.findById(id);
        return obj.get();
    }

    public List<Reclamacao> findByUser(User user) {
        return repository.findByClient(user);
    }

    public Reclamacao insert(ReclamacaoDTO dto) {

        User user = userRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new RuntimeException("User not found!"));

        Reclamacao reclamacao = new Reclamacao();
        reclamacao.setTitle(dto.getTitle());
        reclamacao.setDescription(dto.getDescription());
        reclamacao.setCpf(dto.getCpf());
        reclamacao.setClient(user);
        reclamacao.setCreatedAt(Instant.now());

        return repository.save(reclamacao);
    }
}
