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
//
//    // método para buscar por nome
//    public Optional<Produto> findByNomeProdutoIgoreCase(String nomeProduto){
//        return produtoRepository.findByNomeProdutoIgoreCase(nomeProduto);
//
//    }
//
    // método para buscar por id
    public Produto buscarPorId(Integer id){
        // Usamos optional, pois ele é um contêiner que pode ou não ter um objeto dentro
        // é usado para os famosos erros NullPointerException
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElse(null);
    }

    // método para cadastrar
    public Produto cadastrarProduto(Produto produto){
        return produtoRepository.save(produto);
        // salvar no banco de dados, que representa o meu banco de dados
        // atualmente.
        // traduzindo para sql seria um INSERT INTO
    }

    // Método para atualizar produto
    public Produto atualizarProduto(Integer id, Produto novoProduto){
        Produto produtoExistente = buscarPorId(id);
        // Encontra o produto existente

        if(produtoExistente == null){
            // Verifica se é nulo
            return null;
        }
        produtoExistente.setNomeProduto(novoProduto.getNomeProduto());
        produtoExistente.setDescricao(novoProduto.getDescricao());
        produtoExistente.setEstoqueDisponivel(novoProduto.getEstoqueDisponivel());
        produtoExistente.setPreco(novoProduto.getPreco());
        produtoExistente.setImagemUrl(novoProduto.getImagemUrl());
        // usam o método set para atualizar o produto
        // e usa save para atualizar o produto dentro do banco de dados
        // que no momento é o repository
        return produtoRepository.save(produtoExistente);
    }

    // Método para deletar produto
    public void deletarProduto(Integer id){
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}

