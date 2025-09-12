package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.Dto.PagamentoCadastroDTO;
import br.com.ecommerce.api.Dto.PagamentoListagemDTO;
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
    public ResponseEntity<List<PagamentoListagemDTO>> listarPagamentos(){
        List<PagamentoListagemDTO> pagamentos = pagamentoService.listarTodos();
        return ResponseEntity.ok(pagamentos);
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
    public ResponseEntity<Pagamento> cadastrar(@RequestBody PagamentoCadastroDTO dto){
        Pagamento pagamentoSalvo = pagamentoService.criarPagamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoSalvo);
    }

    // Método para atualizar pagamento
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Integer id, @RequestBody Pagamento pagamento){
        Pagamento pagamentoAtualizado = pagamentoService.atualizarPagamento(id,pagamento);
        if(pagamentoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPagamento(@PathVariable Integer id) {
        Pagamento pagamentoDeletado = pagamentoService.deletarPagamento(id);
        // se o objeto pagamentoDeletado for null ele retorna o erro 404
        // pagamento não encontrado
        if (pagamentoDeletado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
        // mas se o pagamento for encontrado ele retorna o status
        // code 204 É a maneira padrão e correta de dizer
        // "A exclusão foi bem-sucedida.
    }
}
