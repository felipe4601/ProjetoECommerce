package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.ItemProduto;
import br.com.ecommerce.api.repository.ItemProdutoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemProdutoService {
    private final ItemProdutoRepository itemProdutoRepository;

    public ItemProdutoService(ItemProdutoRepository repo){
        itemProdutoRepository = repo;
    }

    public List<ItemProduto> listarTodos(){
        return itemProdutoRepository.findAll();
    }

    // Método para buscar por id
    public ItemProduto buscarPorId(Integer id){
        // Usamos Optional para não causar erro de objeto nulo
        Optional<ItemProduto> itemProduto = itemProdutoRepository.findById(id);
        return itemProduto.orElse(null);
    }


    // Método para cadastrar item
    public ItemProduto cadastrarItemProduto(ItemProduto itemProduto){
        return itemProdutoRepository.save(itemProduto);
        // Traduzindo para sql: INSERT INTO ecommerce.item_produto

    }

    // Método para atualizar itemProduto
    public ItemProduto atualizarItemProduto(Integer id, ItemProduto novoItemProduto){
        ItemProduto itemProdutoExistente = buscarPorId(id);
        if(itemProdutoExistente == null){
            return null;
        }
        itemProdutoExistente.setPedido(novoItemProduto.getPedido());
        itemProdutoExistente.setProduto(novoItemProduto.getProduto());
        itemProdutoExistente.setQuantidade(novoItemProduto.getQuantidade());
        return itemProdutoRepository.save(itemProdutoExistente);
    }
}
