package br.com.bprates.clientes_service.dtos;

import java.util.List;

public class ClienteContaDTO {

    private String nome;
    private String email;
    private List<ContaDTO> contas;

    // Construtores, getters e setters
    public ClienteContaDTO() {}

    public ClienteContaDTO(String nome, String email, List<ContaDTO> contas) {
        this.nome = nome;
        this.email = email;
        this.contas = contas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ContaDTO> getContas() {
        return contas;
    }

    public void setContas(List<ContaDTO> contas) {
        this.contas = contas;
    }
}

