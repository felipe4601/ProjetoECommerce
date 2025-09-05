package br.com.ecommerce.api.controller;


import br.com.ecommerce.api.model.Cliente;
import br.com.ecommerce.api.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// Avisando para o spring que isso é um controller
@RequestMapping("/api/clientes")
public class ClienteController{
    // Controler  depedende de service

    private final ClienteService clienteService;

    public ClienteController(ClienteService service){
        clienteService = service;
    }
    // porta para um método, que pega os dados do banco
    @GetMapping
    public ResponseEntity<List<Cliente>> listarCliente(){
        // 1. Pegar a lista de clientes
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok().body(clientes);
    }
    // Resposta para sites
}
