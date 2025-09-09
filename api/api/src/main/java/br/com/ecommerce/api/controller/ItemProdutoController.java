package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.ItemProduto;
import br.com.ecommerce.api.model.Produto;
import br.com.ecommerce.api.service.ItemProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itensdopedido")
public class ItemProdutoController {
    private final ItemProdutoService itemProdutoService;

    public ItemProdutoController(ItemProdutoService service){
        itemProdutoService = service;
    }

    @GetMapping
    public ResponseEntity<List<ItemProduto>> listarItensDoPedido(){
        List<ItemProduto> itensDoPedido = itemProdutoService.listarTodos();
        return ResponseEntity.ok().body(itensDoPedido);
    }

    // Método para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<ItemProduto> buscarItemProdutoPorId(@PathVariable Integer id){
        // @PathVariable extrai o valor da variável "{id}" da URL e o coloca no parâmetro
        // id do método
        ItemProduto itemProduto = itemProdutoService.buscarPorId(id);
        // Senão for encontrado ele retorna o status 400 - Not found
        if(itemProduto == null){
            return ResponseEntity.notFound().build();
        }
        // Se for encontrado ele retorna o status 200 - Ok
        return ResponseEntity.ok(itemProduto);
    }
    @PostMapping
    public ResponseEntity<ItemProduto> cadastrarItemProduto(@RequestBody ItemProduto itemProduto){
        ItemProduto novoItemProduto = itemProdutoService.cadastrarItemProduto(itemProduto);
        return new ResponseEntity<>(novoItemProduto, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ItemProduto> atualizarItemProduto(@PathVariable Integer id, @RequestBody ItemProduto itemProduto){
        ItemProduto itemProdutoAtualizado = itemProdutoService.atualizarItemProduto(id,itemProduto);
        if(itemProdutoAtualizado == null){
            return ResponseEntity.notFound().build(); // 404 - não encontrado
        }
        return ResponseEntity.ok(itemProdutoAtualizado);// 200 - 0k
    }


}
