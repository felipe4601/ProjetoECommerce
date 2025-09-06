package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.ItemProduto;
import br.com.ecommerce.api.service.ItemProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
