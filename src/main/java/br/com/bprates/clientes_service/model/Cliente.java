package br.com.bprates.clientes_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Por favor, preencha o nome do cliente")
    private String nome;

    @NotBlank(message = "Por favor, preencha o email do cliente")
    @Email(message = "O email deve ser válido!")
    private String email;

    @NotBlank(message = "Por favor, preencha o cep do cliente")
    private String cep;

    //Nesse cara não vou colocar validação porque o processo de ativação pode ser feito pelo sistema
    private Boolean isAtivo;

    public Cliente() {}

    public Cliente(String nome, String email, String cep, Boolean isAtivo) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.isAtivo = isAtivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void setAtivo(Boolean ativo) {
        isAtivo = ativo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", isAtivo=" + isAtivo +
                '}';
    }
}