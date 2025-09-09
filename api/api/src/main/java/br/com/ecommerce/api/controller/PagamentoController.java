package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Pagamento;
import br.com.ecommerce.api.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService service){
        pagamentoService = service;
    }
    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamentos(){
        List<Pagamento> pagamentos = pagamentoService.listarTodos();
        return ResponseEntity.ok().body(pagamentos);
    }
    // Método para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPagamentoPorId(@PathVariable Integer id){
        // @PathVariable pega o {id} da URL e coloca no parâmetro id do método
        Pagamento pagamento = pagamentoService.buscarPorId(id);
        // Se o pagamento não for encontrado ele retorna o status 400 - NotFound
        if(pagamento == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagamento);
        // senão ele retorna o status 200 - ok e o pagamento encontrado
    }

    @PostMapping
    public ResponseEntity<Pagamento> cadastrarPagamento(@RequestBody Pagamento pagamento){
        pagamentoService.cadastrarPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    // Método para atualizar pagamento
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Integer id, @RequestBody Pagamento pagamento){
        Pagamento pagamentoAtualizado = pagamentoService.cadastrarPagamento(pagamento);
        if(pagamentoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagamentoAtualizado);
    }
}
