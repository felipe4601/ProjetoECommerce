package br.com.ecommerce.api.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealizarVendaDto {
    private Integer idCliente;
    private Integer idProduto;
    private Integer quantidade;
    private String formaPagamento;

}
