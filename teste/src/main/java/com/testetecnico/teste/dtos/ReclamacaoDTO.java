package com.testetecnico.teste.dtos;

public class ReclamacaoDTO {
    private String title;
    private String cpf;
    private String description;
    private Long clientId;

    public ReclamacaoDTO() {
    }

    public ReclamacaoDTO(String title, String cpf, String description, Long clientId) {
        this.title = title;
        this.cpf = cpf;
        this.description = description;
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
