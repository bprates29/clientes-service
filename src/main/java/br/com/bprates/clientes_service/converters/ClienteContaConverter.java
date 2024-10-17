package br.com.bprates.clientes_service.converters;
import br.com.bprates.clientes_service.dtos.ClienteContaDTO;
import br.com.bprates.clientes_service.dtos.ContaDTO;
import br.com.bprates.clientes_service.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteContaConverter {

    // Método para converter Cliente e Lista de Contas em ClienteContaDTO
    public static ClienteContaDTO convertToDTO(Cliente cliente, List<ContaDTO> contas) {
        List<ContaDTO> contaDTOs = contas.stream()
                .map(conta -> new ContaDTO(conta.getNumeroConta(), conta.getSaldo()))
                .collect(Collectors.toList());

        // Retorna o DTO completo
        return new ClienteContaDTO(cliente.getNome(), cliente.getEmail(), contaDTOs);
    }
}

