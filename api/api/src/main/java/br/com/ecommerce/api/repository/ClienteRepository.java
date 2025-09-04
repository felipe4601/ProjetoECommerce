package br.com.ecommerce.api.repository;
import br.com.ecommerce.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// preciso anotar para que o spring entenda que isso é um repositório:
@Repository
// Herdando os métodos CRUD:
public interface ClienteRepository extends JpaRepository <Cliente, Integer>{
    // informando ao spring para que ele crie os métodos para a tabela cliente
    // e informando o tipo da chave primária
    // JpaRepository - CRUD
    // ele traz os métodos crud


}
