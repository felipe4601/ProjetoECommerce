package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.ItemDoPedido;
import br.com.ecommerce.api.repository.ItemDoPedidoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemDoPedidoService {
    private final ItemDoPedidoRepository itemDoPedidoRepository;

    public ItemDoPedidoService(ItemDoPedidoRepository repo){
        itemDoPedidoRepository = repo;
    }

    public List<ItemDoPedido> listarTodos(){
        return itemDoPedidoRepository.findAll();
    }

    // Método para buscar por id
    public ItemDoPedido buscarPorId(Integer id){
        // Usamos Optional para não causar erro de objeto nulo
        Optional<ItemDoPedido> itemProduto = itemDoPedidoRepository.findById(id);
        return itemProduto.orElse(null);
    }


    // Método para cadastrar item
    public ItemDoPedido cadastrarItemDoPedido(ItemDoPedido itemDoPedido){
        return itemDoPedidoRepository.save(itemDoPedido);
        // Traduzindo para sql: INSERT INTO ecommerce.item_produto

    }

    // Método para atualizar itemProduto
    public ItemDoPedido atualizarItemDoPedido(Integer id, ItemDoPedido novoItemDoPedido){
        ItemDoPedido itemDoPedidoExistente = buscarPorId(id);
        if(itemDoPedidoExistente == null){
            return null;
        }
        itemDoPedidoExistente.setPedido(novoItemDoPedido.getPedido());
        itemDoPedidoExistente.setProduto(novoItemDoPedido.getProduto());
        itemDoPedidoExistente.setQuantidade(novoItemDoPedido.getQuantidade());
        return itemDoPedidoRepository.save(itemDoPedidoExistente);
    }

    // Método para deletar itemDoproduto
    public ItemDoPedido deletarItemDoPedido(Integer id){
        ItemDoPedido itemDoPedido = buscarPorId(id);
        // verifica se o objeto é nulo
        if(itemDoPedido == null){
            return null;
        }
        itemDoPedidoRepository.delete(itemDoPedido);
        return itemDoPedido;
    }
}
