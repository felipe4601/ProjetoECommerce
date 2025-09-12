package br.com.ecommerce.api.repository;


import br.com.ecommerce.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // Métodos adicionais que não vem no pacote padrão, e precisamos adicionar no repository
    // Buscar pelo nome do produto sem case sentitive
    Optional<Produto> findByNomeProdutoIgnoreCase(String nomeProduto);
//    // Optional é um tipo, que receber um objeto nulo ou não.
    List<Produto> findByNomeProdutoContainsIgnoreCase(String nomeProduto);
//    // Buscar pela descrição
    List<Produto> findByDescricaoContainsIgnoreCase(String descricao);
//    // Buscar produtos com preco maior que um determinado valor
    List<Produto> findByPrecoGreaterThan(BigDecimal valor);
//    // Buscar produto cujo nome contehna um texto específico como  o LIKE no
//    // sql
    List<Produto> findByNomeProdutoContaining(String texto);


}
