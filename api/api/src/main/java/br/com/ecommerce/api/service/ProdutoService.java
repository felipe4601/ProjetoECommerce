package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository repo){
        produtoRepository = repo;
    }

    // criando método listar todos
    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }
}
