package br.com.ecommerce.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "produto", schema = "ecommerce") // De onde eu estou tirando a tabela
public class Produto {
    @Id // chave primaria no java
    @GeneratedValue(strategy = GenerationType.IDENTITY)// gerando a chave primária automaticamente
    @Column(name = "id_produto", nullable = false)  // coluna do banco de dados
    private Integer id; // transformando a coluna, que foi chamado no banco de dados em atributo

    @Column(name = "nome_produto", nullable = false, length = Integer.MAX_VALUE) // copia a coluna
    private String nomeProduto; // e traduz para um atributo de um objeto

    @Column(name = "descricao", nullable = false, length = Integer.MAX_VALUE) // espelhando a coluna do banco
    private String descricao; // criando um atributo descricao através da coluna criada no banco

    @Column(name = "preco", nullable = false, precision = 10, scale = 4)
    private BigDecimal preco;

    @Column(name = "estoque_disponivel", nullable = false)
    private Integer estoqueDisponivel;

    @Column(name = "imagem_url", nullable = false, length = Integer.MAX_VALUE)
    private String imagemUrl;

}