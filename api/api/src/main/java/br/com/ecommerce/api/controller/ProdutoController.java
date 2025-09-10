package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@ResponseBody
// Controller é a porta para o front, ou seja, ele rebebe as requisições e
// retorna a resposta
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
            // Se estiver vazio ele retorna status 404
            // produto não encontrado
        }
        return ResponseEntity.ok(produto);
        // Mas senão ele retorna o status 200
    }

    @PostMapping
    // Do tipo ResponseEntity, pois ele irá retornar um statusCode de Produto
    // @RequstBody determina de onde o cliente virá, se não o spring não
    // irá entender de onde o cliente está vindo
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
        // 1.Tentar cadastrar um cliente
        produtoService.cadastrarProduto(produto);
        // Código 200 - OK
        // return ResponseEntity.ok(produto);
        // Código 201 - CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoService.atualizarProduto(id,produto);
        if(produtoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoAtualizado);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarProduto(@PathVariable Integer id){
        Produto produtoDeletado = produtoService.deletarProduto(id);

        if(produtoDeletado == null){
            return ResponseEntity.notFound().build();
            // Retorna o erro 404 - produto não encontrado
        }
        return ResponseEntity.noContent().build();
        // Retorna o status code 204 - no content
        // É a maneira padrão e correta de dizer
        // "A exclusão foi bem-sucedida.
    }
}
