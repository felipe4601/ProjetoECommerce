package br.com.ecommerce.api.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PagamentoListagemDTO {
    // Campos que queremos expor na Api

    private String formaPagamento;
    private String status;
    private OffsetDateTime dataPagamento;
    private Integer idPedido;
    private BigDecimal valorPedido;
}
