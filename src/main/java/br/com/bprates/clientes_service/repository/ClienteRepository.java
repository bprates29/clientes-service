package br.com.bprates.clientes_service.repository;

import br.com.bprates.clientes_service.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNome(String nome);

    List<Cliente> findByIsAtivoTrue();

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}

