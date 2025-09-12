package br.com.ecommerce.api.service;


import br.com.ecommerce.api.Dto.RealizarVendaDto;
import br.com.ecommerce.api.model.*;
import br.com.ecommerce.api.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class PedidoService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemDoPedidoRepository itemDoPedidoRepository;
    private final PagamentoRepository pagamentoRepository;


    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository
            , ProdutoRepository produtoRepository, ItemDoPedidoRepository itemDoPedidoRepository
            , PagamentoRepository pagamentoRepository){
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemDoPedidoRepository = itemDoPedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<Pedido> listarTodos(){
        return pedidoRepository.findAll();
    }

    // Método para buscar por id
    public Pedido buscarPorId(Integer id){
        // Usamos optional, pois ele é um contêiner, que pode receber um objeto ou ser vazio
        // dessa forma, nós evitamos o erro de apontar para apontar para objetos vazios
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElse(null);
    }

    // Método para cadastrar Pedidos
//    public Pedido cadastrarPedido(Pedido pedido){
//
//        return pedidoRepository.save(pedido);
//    }
    // Método para atualizar pedido
    public Pedido atualizarPedido(Integer id, Pedido novoPedido){
        Pedido pedidoExistente = buscarPorId(id);
        if(pedidoExistente == null){
            return null;
        }
        pedidoExistente.setCliente(novoPedido.getCliente());
        pedidoExistente.setDataPedido(novoPedido.getDataPedido());
        pedidoExistente.setStatus(novoPedido.getStatus());
        pedidoExistente.setValorTotal(novoPedido.getValorTotal());
        return pedidoRepository.save(pedidoExistente);
    }

    // Método para deletar pedido
    public Pedido deletarPedido(Integer id){
        Pedido pedido = buscarPorId(id);
        if(pedido == null){
            return null;
        }
        pedidoRepository.delete(pedido);
        return pedido;
    }

    // Método dto para realizar venda
    public Pedido realizarPedido(RealizarVendaDto dto){
        Pedido novoPedido = new Pedido();
        Pagamento novoPagamento = new Pagamento();
        Cliente clienteAssociado = clienteRepository.findById(dto.getIdCliente()).orElse(null);
        if(clienteAssociado == null){
            return null;
        }
        // Recebendo Produto e sua quantidade
        Produto produtoAssociado = produtoRepository.findById(dto.getIdProduto()).orElse(null);
        if(produtoAssociado == null){
            return null;
        }

        //Mapeando a classe Pedido
        novoPedido.setCliente(clienteAssociado);
        novoPedido.setDataPedido(OffsetDateTime.now());
        novoPedido.setValorTotal(BigDecimal.valueOf(dto.getQuantidade()).multiply(produtoAssociado.getPreco()));
        novoPedido.setStatus("PENDENTE");

        // Mapeando a classe Produto
        produtoAssociado.setEstoqueDisponivel(produtoAssociado.getEstoqueDisponivel()-dto.getQuantidade());


        // Mapeando a classe ItemDoPedido
        ItemDoPedido quantidadeItensDoPedido = new ItemDoPedido();
        quantidadeItensDoPedido.setProduto(produtoAssociado);
        quantidadeItensDoPedido.setPedido(novoPedido);
        quantidadeItensDoPedido.setQuantidade(dto.getQuantidade());



        // Mapeando a classe Pagamento
        novoPagamento.setFormaPagamento(dto.getFormaPagamento());
        novoPagamento.setPedido(novoPedido);
        novoPagamento.setDataPagamento(OffsetDateTime.now());
        // Salvando as demais classes
        itemDoPedidoRepository.save(quantidadeItensDoPedido);
        pagamentoRepository.save(novoPagamento);
        produtoRepository.save(produtoAssociado);
        return pedidoRepository.save(novoPedido);
    }
}


