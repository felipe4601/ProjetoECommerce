package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Pagamento;
import br.com.ecommerce.api.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository repo){
        pagamentoRepository = repo;
    }

    // Criando método listarTodos()
    public List<Pagamento> listarTodos(){
        return pagamentoRepository.findAll();
    }
    // Método para buscarPorId
    public Pagamento buscarPorId(Integer id){
        // Optional para não ocorrer o erro de objeto nulo
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        return pagamento.orElse(null);
    }
    // Método para cadastrar pagamento
    public Pagamento cadastrarPagamento(Pagamento pagamento){
        return pagamentoRepository.save(pagamento);
    }

    // Método para atualizar
    public Pagamento atualizarPagamento(Integer id, Pagamento novoPagamento){
        Pagamento pagamentoExistente = buscarPorId(id);

        if(pagamentoExistente == null){
            return null;
        }
        pagamentoExistente.setDataPagamento(novoPagamento.getDataPagamento());
        pagamentoExistente.setFormaPagamento((novoPagamento.getFormaPagamento()));
        pagamentoExistente.setPedido(novoPagamento.getPedido());
        return pagamentoRepository.save(pagamentoExistente);
    }
    // Método para deletar pagamento
    public Pagamento deletarPagamento(Integer id){
        Pagamento pagamento = buscarPorId(id);
        // Verificamos se o pagamento existe
        if (pagamento == null) {
            return null;
        }
        pagamentoRepository.delete(pagamento);
        return pagamento;
    }
}

