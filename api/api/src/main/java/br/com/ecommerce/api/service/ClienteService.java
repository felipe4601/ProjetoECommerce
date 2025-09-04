package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Cliente;
import br.com.ecommerce.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
