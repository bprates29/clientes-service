package br.com.bprates.clientes_service.clients;

import br.com.bprates.clientes_service.dtos.ContaDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ContaClient {

    private final WebClient webClient;

    public ContaClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://contas-service")
                .defaultHeaders(headers -> headers.setBasicAuth("bprates", "infnet2024"))
                .build();
    }

    public List<ContaDTO> getContasByClienteId(Integer clienteId) {
        return this.webClient.get()
                .uri("/contas/cliente/{id}", clienteId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ContaDTO>>() {})
                .block();
    }
}

