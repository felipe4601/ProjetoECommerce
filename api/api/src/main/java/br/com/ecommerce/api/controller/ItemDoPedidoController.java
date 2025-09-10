package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.ItemDoPedido;
import br.com.ecommerce.api.service.ItemDoPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itensdopedido")
public class ItemDoPedidoController {
    private final ItemDoPedidoService itemDoPedidoService;

    public ItemDoPedidoController(ItemDoPedidoService service){
        itemDoPedidoService = service;
    }

    @GetMapping
    public ResponseEntity<List<ItemDoPedido>> listarItensDoPedido(){
        List<ItemDoPedido> itensDoPedido = itemDoPedidoService.listarTodos();
        return ResponseEntity.ok().body(itensDoPedido);
    }

    // Método para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<ItemDoPedido> buscarItemDoPedidoPorId(@PathVariable Integer id){
        // @PathVariable extrai o valor da variável "{id}" da URL e o coloca no parâmetro
        // id do método
        ItemDoPedido itemDoPedido = itemDoPedidoService.buscarPorId(id);
        // Senão for encontrado ele retorna o status 400 - Not found
        if(itemDoPedido == null){
            return ResponseEntity.notFound().build();
        }
        // Se for encontrado ele retorna o status 200 - Ok
        return ResponseEntity.ok(itemDoPedido);
    }
    @PostMapping
    public ResponseEntity<ItemDoPedido> cadastrarItemProduto(@RequestBody ItemDoPedido itemDoPedido){
        ItemDoPedido novoItemDoPedido = itemDoPedidoService.cadastrarItemDoPedido(itemDoPedido);
        return new ResponseEntity<>(novoItemDoPedido, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ItemDoPedido> atualizarItemDoPedido(@PathVariable Integer id, @RequestBody ItemDoPedido itemDoPedido){
        ItemDoPedido itemDoPedidoAtualizado = itemDoPedidoService.atualizarItemDoPedido(id, itemDoPedido);
        if(itemDoPedidoAtualizado == null){
            return ResponseEntity.notFound().build(); // 404 - não encontrado
        }
        return ResponseEntity.ok(itemDoPedidoAtualizado);// 200 - 0k
    }

    // Método para deletar Item do Produto
    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDoPedido> deletarItemProduto(@PathVariable Integer id){
        ItemDoPedido itemDoPedidoDeletado = itemDoPedidoService.deletarItemDoPedido(id);
        // se o produto não for encontrado ele retorna 404
        // not found - não encontrado
        if(itemDoPedidoDeletado == null){
            return ResponseEntity.notFound().build();
        }
        // mas se o produtor foi encontrado ele retorna 201
        return ResponseEntity.noContent().build();
    }


}
