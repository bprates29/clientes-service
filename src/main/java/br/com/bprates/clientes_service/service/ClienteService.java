package br.com.bprates.clientes_service.service;

import br.com.bprates.clientes_service.clients.ContaClient;
import br.com.bprates.clientes_service.dtos.ClienteContaDTO;
import br.com.bprates.clientes_service.messaging.ClienteProducer;
import br.com.bprates.clientes_service.model.Cliente;
import br.com.bprates.clientes_service.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;
    private final ContaClient contaClient;
    private final ClienteProducer clienteProducer;

    public ClienteService(ClienteRepository clienteRepository, ContaClient contaClient, ClienteProducer clienteProducer) {
        this.clienteRepository = clienteRepository;
        this.contaClient = contaClient;
        this.clienteProducer = clienteProducer;
    }

    public List<Cliente> obterLista() {
        logger.info("Obtendo lista de todos os clientes");
        return clienteRepository.findAll();
    }

    public Cliente obterPorId(Integer id) {
        logger.info("Buscando cliente com ID: {}", id);
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente == null) {
            logger.warn("Cliente não encontrado com ID: {}", id);
        } else {
            logger.info("Cliente encontrado: {}", cliente.getNome());
        }

        return cliente;
    }

    public List<Cliente> buscarPorNome(String nome) {
        logger.info("Buscando clientes com nome contendo: {}", nome);
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Cliente> obterClientesAtivos() {
        logger.info("Obtendo lista de clientes ativos");
        return clienteRepository.findByIsAtivoTrue();
    }

    public Cliente incluir(Cliente cliente) {
        logger.info("Incluindo novo cliente: {}", cliente.getNome());

        Cliente novoCliente = clienteRepository.save(cliente);

        // Log antes de enviar a mensagem
        logger.info("Chamando clienteProducer.enviarMensagem");

        // Envolva em um bloco try-catch para capturar exceções
        try {
            clienteProducer.enviarMensagem("Cliente criado com ID: " + novoCliente.getId());
            logger.info("Mensagem enviada com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem para o RabbitMQ: {}", e.getMessage(), e);
        }

        return novoCliente;
    }

    public void excluir(Integer id) {
        logger.info("Excluindo cliente com ID: {}", id);
        clienteRepository.deleteById(id);
    }

    public ClienteContaDTO obterContasPorClienteId(Integer id) {
        logger.info("Obtendo cliente e contas vinculadas com ID: {}", id);

        Cliente cliente = obterPorId(id);

        if (cliente == null) {
            logger.error("Cliente com ID: {} não encontrado", id);
            throw new RuntimeException("Cliente não encontrado com id: " + id);
        }

        var contaDTOs = contaClient.getContasByClienteId(id);
        logger.info("Contas encontradas para cliente com ID: {}", id);

        return new ClienteContaDTO(cliente.getNome(), cliente.getEmail(), contaDTOs);
    }
}