package br.com.ecommerce.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity// avisa para o jpa, que isso é uma tabela
@Table(name = "pedido", schema = "ecommerce") // o spring entende através da anotação
// @Table, que isso é uma tabela e a transforma em uma classe
// @
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Integer id;

    @Column(name = "data_pedido")
    private OffsetDateTime dataPedido;

    @Column(name = "valor_total", precision = 10, scale = 4)
    private BigDecimal valorTotal;// o

    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false) // é dessa forma que o java entende o muito para muitos
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}