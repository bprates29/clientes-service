package br.com.bprates.clientes_service.dtos;

public class ContaDTO {

    private String numeroConta;
    private Double saldo;

    public ContaDTO() {}

    public ContaDTO(String numeroConta, Double saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}