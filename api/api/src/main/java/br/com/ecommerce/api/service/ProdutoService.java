package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // método para buscar por nome
    public Optional<Produto> findByNomeProdutoIgoreCase(String nomeProduto){
        return produtoRepository.findByNomeProdutoIgoreCase(nomeProduto);
    }

    // método para buscar por id
    public Produto buscarPorId(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElse(null);
    }

    // método para cadastrar
    public Produto cadastrarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    // Método para atualizar produto
    public Produto atualizarProduto(Integer id, Produto novoProduto){
        Produto produtoExistente = buscarPorId(id);

        if(produtoExistente == null){
            return null;
        }
        produtoExistente.setNomeProduto(novoProduto.getNomeProduto());

        return produtoRepository.save(produtoExistente);
    }

    // Método para deletar produto
    public void deletarProduto(Integer id){
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}

