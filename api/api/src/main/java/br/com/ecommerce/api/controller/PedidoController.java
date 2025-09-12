package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.Dto.RealizarVendaDto;
import br.com.ecommerce.api.model.Pedido;
import br.com.ecommerce.api.repository.PedidoRepository;
import br.com.ecommerce.api.service.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService service){
        pedidoService = service;
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok().body(pedidos);
    }
    // Método para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id){
        // pathvariable extrai o {id} do caminho e o coloca dentro do parâmetro
        // passado no método
        Pedido pedido = pedidoService.buscarPorId(id);
        // Recebendo o método do service
        if(pedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }
//    @PostMapping
//    public ResponseEntity<Pedido> cadastrarPedido(@RequestBody Pedido pedido){
//        // Usamos @RequestBody para que o spring pegue a requisição, que se espera ser um
//        // Json, e convertê-lo em um objeto Produto
//        Pedido novoPedido = pedidoService.cadastrarPedido(pedido);
//        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido){
        Pedido pedidoAtualizado = pedidoService.atualizarPedido(id,pedido);
        if(pedidoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoAtualizado);
    }

    // Método para deletar pedido
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarPedido(@PathVariable Integer id){
        Pedido pedidoDeletado = pedidoService.deletarPedido(id);
        // chama o método criado no service, e atribui o valor
        // dele ao objeto "pedido deletado"
        if(pedidoDeletado == null){
            // Retorna pedido não encontrado
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

        // Retorna o status code 204 - no content
        // É a maneira padrão e correta de dizer
        // "A exclusão foi bem-sucedida.
    }

    // Método DTO para realizar venda
    @PostMapping
    public ResponseEntity<Pedido> realizarVenda(@RequestBody RealizarVendaDto dto){
        Pedido pedidoSalvo = pedidoService.realizarPedido(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }
}
