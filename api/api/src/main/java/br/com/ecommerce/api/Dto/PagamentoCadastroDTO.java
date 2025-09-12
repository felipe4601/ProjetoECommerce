package br.com.ecommerce.api.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoCadastroDTO {
    private Integer idPedido;
    private String formaPagamento;
}
