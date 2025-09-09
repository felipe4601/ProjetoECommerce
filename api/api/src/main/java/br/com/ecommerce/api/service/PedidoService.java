package br.com.ecommerce.api.service;


import br.com.ecommerce.api.model.Pedido;
import br.com.ecommerce.api.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository repo){
        pedidoRepository = repo;
    }

    public List<Pedido> listarTodos(){
        return pedidoRepository.findAll();
    }

    // Método para cadastrar Pedidos
    public Pedido cadastrarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
}
