package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.ItemProduto;
import br.com.ecommerce.api.repository.ItemProdutoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemProdutoService {
    private final ItemProdutoRepository itemProdutoRepository;

    public ItemProdutoService(ItemProdutoRepository repo){
        itemProdutoRepository = repo;
    }

    public List<ItemProduto> listarTodos(){
        return itemProdutoRepository.findAll();
    }
}
