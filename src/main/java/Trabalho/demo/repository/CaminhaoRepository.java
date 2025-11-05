package Trabalho.demo.repository;

import Trabalho.demo.model.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Repository para operações CRUD no banco de dados com a entidade Caminhao.
 * Herda todos os métodos básicos do JpaRepository.
 */
@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
    // Métodos adicionais de busca podem ser definidos aqui, se necessário.
}
