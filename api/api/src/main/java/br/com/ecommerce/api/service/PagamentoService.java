package br.com.ecommerce.api.service;

import br.com.ecommerce.api.Dto.PagamentoCadastroDTO;
import br.com.ecommerce.api.Dto.PagamentoListagemDTO;
import br.com.ecommerce.api.model.Pagamento;
import br.com.ecommerce.api.model.Pedido;
import br.com.ecommerce.api.repository.PagamentoRepository;
import br.com.ecommerce.api.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    // Criando método listarTodos()

    // Método para buscarPorId
    public Pagamento buscarPorId(Integer id){
        // Optional para não ocorrer o erro de objeto nulo
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        return pagamento.orElse(null);
    }
    // Método para cadastrar pagamento
    public Pagamento criarPagamento(PagamentoCadastroDTO dto){
        // 1. Usa o 'o idPedido' da DTO para buscar a ENTIDADE 'Pedido' completa no banco.
        Pedido pedidoAssociado = pedidoRepository.findById(dto.getIdPedido()).orElse(null);
        if(pedidoAssociado == null){
            return null;
        }
        // Cria uma instância de pagamento
        Pagamento novoPagamento = new Pagamento();

        // Mapeia os dados da DTO para a nova entidade.
        novoPagamento.setFormaPagamento(dto.getFormaPagamento());
        novoPagamento.setPedido(pedidoAssociado);

        // Define outros campos com base na lógica de negócios
        pedidoAssociado.setStatus("PENDENTE");
        novoPagamento.setDataPagamento(OffsetDateTime.now());

        return pagamentoRepository.save(novoPagamento);
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

    // Método para as Dtos
    // Método para listar todos os campos definidos na DTO
    public List<PagamentoListagemDTO> listarTodos(){
        // Busca todas as entidades do banco
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        // Mapeia a lista de entidades para uma lista de DTOs
        return pagamentos.stream()
                // Método para conversão
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }
    private PagamentoListagemDTO converterParaListagemDTO (Pagamento pagamento){
        PagamentoListagemDTO dtoPagamento = new PagamentoListagemDTO();

        // Mapeamento campo a campo
        dtoPagamento.setFormaPagamento(pagamento.getFormaPagamento());
        dtoPagamento.setDataPagamento(pagamento.getDataPagamento());
        dtoPagamento.setIdPedido(pagamento.getPedido().getId());
        dtoPagamento.setValorPedido(pagamento.getPedido().getValorTotal());

        return dtoPagamento;
    }


    // Métododo para realizar venda

}

