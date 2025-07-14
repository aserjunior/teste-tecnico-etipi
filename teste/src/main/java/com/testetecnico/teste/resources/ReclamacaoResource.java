package com.testetecnico.teste.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testetecnico.teste.entities.Reclamacao;
import com.testetecnico.teste.services.ReclamacaoService;

@RestController
@RequestMapping(value = "/reclamacoes")
public class ReclamacaoResource {

    @Autowired
    private ReclamacaoService service;

    @GetMapping
    public ResponseEntity<List<Reclamacao>> findAll() {
        List<Reclamacao> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Reclamacao> findById(@PathVariable Long id) {
        Reclamacao obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
