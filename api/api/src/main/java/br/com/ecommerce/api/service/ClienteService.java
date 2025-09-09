package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Cliente;
import br.com.ecommerce.api.model.ItemProduto;
import br.com.ecommerce.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    // Injeção de Dependência
    // Falar que Service depende de alguém

    private final ClienteRepository clienteRepository;
    // ela é final, porque sempre será o mesmo repositório
    // final é uma variável constante, nunca muda
    public ClienteService(ClienteRepository repo){
        clienteRepository = repo;
    }
    // Serve para avisar que o serviço precisa de um repository

    // método para listar
    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }


    // Método para cadastrar Cliente
    public Cliente cadastrarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    // Método para buscar por id
    public Cliente buscarPorId(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    // Método para atualizar
    public Cliente atualizarCliente(Integer id, Cliente novoCliente){
        Cliente clienteExistente = buscarPorId(id);
        if(clienteExistente == null){
            return null;
        }
        clienteExistente.setNomeCompleto(novoCliente.getNomeCompleto());
        clienteExistente.setTelefone(novoCliente.getTelefone());
        clienteExistente.setEmail(novoCliente.getEmail());
        clienteExistente.setDataCadastro(novoCliente.getDataCadastro());
        clienteExistente.setSenha(novoCliente.getSenha());
        return clienteRepository.save(clienteExistente);
    }
}

