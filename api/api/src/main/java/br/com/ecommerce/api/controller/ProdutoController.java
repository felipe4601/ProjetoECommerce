package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@ResponseBody
// ResponseBody faz a conversão automática, ele transforma o retorno de um
// método em Json antes de enviá-lo na resposta HTTP
public class ProdutoController {

    // Criando a relação entre controller e service
    private final ProdutoService produtoService;
    public ProdutoController(ProdutoService service){
        produtoService = service;
    }

    // criando o método getter para pegar dados do banco de dados
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id){
        Produto produto = produtoService.buscarPorId(id);
        if(produto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }
}
