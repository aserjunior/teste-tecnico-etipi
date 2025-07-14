package com.testetecnico.teste.entities;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reclamacao")
public class Reclamacao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Instant created_at;

    @ManyToOne
    @JoinColumn(name = "client_cpf")
    private User client;

    public Reclamacao() {

    }

    public Reclamacao(Long id, String title, String description, Instant created_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Instant created_at) {
        this.created_at = created_at;
    }
}
