package br.com.ecommerce.api.service;


import br.com.ecommerce.api.model.Pedido;
import br.com.ecommerce.api.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository repo){
        pedidoRepository = repo;
    }

    public List<Pedido> listarTodos(){
        return pedidoRepository.findAll();
    }

    // Método para buscar por id
    public Pedido buscarPorId(Integer id){
        // Usamos optional, pois ele é um contêiner, que pode receber um objeto ou ser vazio
        // dessa forma, nós evitamos o erro de apontar para apontar para objetos vazios
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElse(null);
    }

    // Método para cadastrar Pedidos
    public Pedido cadastrarPedido(Pedido pedido){

        return pedidoRepository.save(pedido);
    }
    // Método para atualizar pedido
    public Pedido atualizarPedido(Integer id, Pedido novoPedido){
        Pedido pedidoExistente = buscarPorId(id);
        if(pedidoExistente == null){
            return null;
        }
        pedidoExistente.setCliente(novoPedido.getCliente());
        pedidoExistente.setDataPedido(novoPedido.getDataPedido());
        pedidoExistente.setStatus(novoPedido.getStatus());
        pedidoExistente.setValorTotal(novoPedido.getValorTotal());
        return pedidoRepository.save(pedidoExistente);
    }
}
