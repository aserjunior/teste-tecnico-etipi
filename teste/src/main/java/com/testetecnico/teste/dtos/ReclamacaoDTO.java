package com.testetecnico.teste.dtos;

import com.testetecnico.teste.entities.Reclamacao;

import java.time.Instant;

public class ReclamacaoDTO {
    private Long id;
    private String title;
    private String cpf;
    private String description;
    private Long clientId;
    private Instant createdAt;

    public ReclamacaoDTO() {}

    public ReclamacaoDTO(String title, String cpf, String description, Long clientId) {
        this.title = title;
        this.cpf = cpf;
        this.description = description;
        this.clientId = clientId;
    }

    public ReclamacaoDTO(Reclamacao reclamacao) {
        this.id = reclamacao.getId();
        this.title = reclamacao.getTitle();
        this.cpf = reclamacao.getCpf();
        this.description = reclamacao.getDescription();
        this.clientId = reclamacao.getClient().getId();
        this.createdAt = reclamacao.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}