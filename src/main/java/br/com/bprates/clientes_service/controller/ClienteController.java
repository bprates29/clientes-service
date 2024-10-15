package br.com.bprates.clientes_service.controller;

import br.com.bprates.clientes_service.model.Cliente;
import br.com.bprates.clientes_service.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "listar")
    public List<Cliente> obterLista(){
        return clienteService.obterLista();
    }

    @GetMapping(value = "{id}")
    public Cliente obterPorId(@PathVariable Integer id) {
        return clienteService.obterPorId(id);
    }

    @GetMapping("/buscar/nome")
    public List<Cliente> buscarPorNome(@RequestParam String nome) {
        return clienteService.buscarPorNome(nome);
    }

    @GetMapping("/ativos")
    public List<Cliente> obterClientesAtivos() {
        return clienteService.obterClientesAtivos();
    }

    @PostMapping(value = "incluir")
    public String incluir(@RequestBody Cliente cliente) {

        clienteService.incluir(cliente);

        return "A inclusão do " + cliente + " foi realizada com sucesso!!!";
    }

    @PutMapping(value = "atualizar/{id}")
    public String atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteService.obterPorId(id);
        if (clienteExistente != null) {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setAtivo(clienteAtualizado.getAtivo());
            clienteService.incluir(clienteExistente);
            return "A atualização do cliente " + clienteExistente.getNome() + " foi realizada com sucesso!";
        } else {
            return "Cliente com id " + id + " não encontrado.";
        }
    }

    @DeleteMapping(value = "{id}")
    public String excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
        return "A exclusão do id " + id + " foi realizada com sucesso!!!";
    }
}
