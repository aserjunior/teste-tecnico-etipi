package com.testetecnico.teste.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.testetecnico.teste.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.testetecnico.teste.entities.User;
import com.testetecnico.teste.dtos.ReclamacaoDTO;
import com.testetecnico.teste.entities.Reclamacao;
import com.testetecnico.teste.services.ReclamacaoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/v1/reclamacoes")
public class ReclamacaoResource {

    @Autowired
    private ReclamacaoService service;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Reclamacao>> findAll() {
        List<Reclamacao> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReclamacaoDTO> findById(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userService.findByEmail(email);

        Reclamacao reclamacao = service.findById(id);

        if (!reclamacao.getClient().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }

        ReclamacaoDTO dto = new ReclamacaoDTO(reclamacao);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/current")
    public ResponseEntity<List<ReclamacaoDTO>> findMyReclamacoes() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userService.findByEmail(email);

        List<Reclamacao> reclamacoes = service.findByUser(user);

        List<ReclamacaoDTO> dtos = reclamacoes.stream()
                .map(ReclamacaoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<Reclamacao> insert(@RequestBody ReclamacaoDTO dto) {
        Reclamacao obj = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
