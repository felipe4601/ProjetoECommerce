package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    // Criando a relação entre controller e service
    private ProdutoService produtoService;
    public ProdutoController(ProdutoService service){
        produtoService = service;
    }

    // criando o método getter para pegar dados do banco de dados
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok().body(produtos);
    }
}
