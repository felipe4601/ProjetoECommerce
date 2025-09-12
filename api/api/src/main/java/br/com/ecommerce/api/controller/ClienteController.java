package br.com.ecommerce.api.controller;


import br.com.ecommerce.api.model.Cliente;
import br.com.ecommerce.api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Avisando para o spring que isso é um controller
@Tag(name = "Controller de Cliente", description = "Métodos de Clientes")
@RequestMapping("/api/clientes")
public class ClienteController{
    // Controler  depedende de service

    private final ClienteService clienteService;

    public ClienteController(ClienteService service){
        clienteService = service;
    }
    // porta de entrada para um método, que pega os dados do banco de dados
    @GetMapping
    public ResponseEntity<List<Cliente>> listarCliente(){
        // 1. Pegar a lista de clientes
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok().body(clientes);
    }
    // Resposta para sites
    //Método para buscar por id
    @Operation(
            summary = "Busca cliente por id",
            description = "Busca um cliente digitando um cliente"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id){
        Cliente cliente = clienteService.buscarPorId(id);
        if(cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    // método para cadastrar cliente
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    // Método para atualizar cadastro de cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id, Cliente cliente){
        Cliente clienteAtualizado = clienteService.atualizarCliente(id,cliente);
        if(clienteAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Integer id){
        Cliente clienteDeletado = clienteService.deletarCliente(id);
        // se o cliente não for encontrado ele retorna o status code
        // 404 - notFound: cliente não encotrado
        if (clienteDeletado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}

