package br.com.bprates.clientes_service.service;

import br.com.bprates.clientes_service.model.Cliente;
import br.com.bprates.clientes_service.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obterLista() {
        return clienteRepository.findAll();
    }

    public Cliente obterPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Cliente> obterClientesAtivos() {
        return clienteRepository.findByIsAtivoTrue();
    }

    public Cliente incluir(Cliente cliente) {
                return clienteRepository.save(cliente);
    }

    public void excluir(Integer id) {
        clienteRepository.deleteById(id);
    }
}