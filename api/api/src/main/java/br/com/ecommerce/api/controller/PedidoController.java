package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Pedido;
import br.com.ecommerce.api.repository.PedidoRepository;
import br.com.ecommerce.api.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService service){
        pedidoService = service;
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok().body(pedidos);
    }
}
